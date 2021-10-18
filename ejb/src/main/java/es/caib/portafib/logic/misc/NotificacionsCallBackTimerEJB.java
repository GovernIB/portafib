package es.caib.portafib.logic.misc;

import es.caib.portafib.ejb.NotificacioWSService;
import es.caib.portafib.ejb.UsuariAplicacioService;
import es.caib.portafib.persistence.NotificacioWSJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.logic.BitacolaLogicaLocal;
import es.caib.portafib.logic.events.FirmaEvent;
import es.caib.portafib.logic.notificacions.NotificacioSender;
import es.caib.portafib.logic.notificacions.NotificacioSenderFactory;
import es.caib.portafib.logic.notificacions.NotificacioUtils;
import es.caib.portafib.logic.utils.EmailUtil;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.NotificacioInfo;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.entity.NotificacioWS;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.fields.NotificacioWSFields;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;


import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Semaphore;

import static es.caib.portafib.utils.ConstantsV2.BITACOLA_OP_ERROR_NOTIFICACIO;

import static es.caib.portafib.utils.ConstantsV2.BITACOLA_OP_NOTIFICAR_ENPROCES;
import static es.caib.portafib.utils.ConstantsV2.BITACOLA_OP_NOTIFICAR_FINALITZADA;
import static es.caib.portafib.utils.ConstantsV2.BITACOLA_OP_NOTIFICAR_FIRMA_PARCIAL;
import static es.caib.portafib.utils.ConstantsV2.BITACOLA_OP_NOTIFICAR_INVALIDADA;
import static es.caib.portafib.utils.ConstantsV2.BITACOLA_OP_NOTIFICAR_PAUSADA;
import static es.caib.portafib.utils.ConstantsV2.BITACOLA_OP_NOTIFICAR_REBUTJADA;
import static es.caib.portafib.utils.ConstantsV2.BITACOLA_TIPUS_PETICIO;
import static es.caib.portafib.utils.ConstantsV2.NOTIFICACIOAVIS_FIRMA_PARCIAL;
import static es.caib.portafib.utils.ConstantsV2.NOTIFICACIOAVIS_INVALIDAT;
import static es.caib.portafib.utils.ConstantsV2.NOTIFICACIOAVIS_PETICIO_EN_PROCES;
import static es.caib.portafib.utils.ConstantsV2.NOTIFICACIOAVIS_PETICIO_FIRMADA;
import static es.caib.portafib.utils.ConstantsV2.NOTIFICACIOAVIS_PETICIO_PAUSADA;
import static es.caib.portafib.utils.ConstantsV2.NOTIFICACIOAVIS_PETICIO_REBUTJADA;


/**
 * Servei d'enviament de Notificacions de callback mitjançant l'activació d'un timer
 *
 * @author anadal
 * @author areus
 */
@Stateless(name = "NotificacionsCallBackTimerEJB")
@RunAs(ConstantsV2.PFI_ADMIN)
@PermitAll
public class NotificacionsCallBackTimerEJB implements NotificacionsCallBackTimerLocal {

  @Resource
  private TimerService timerService;

  @EJB(mappedName = NotificacioWSService.JNDI_NAME, beanName = "NotificacioWSEJB")
  private NotificacioWSService notificacioEjb;

  @EJB(mappedName = UsuariAplicacioService.JNDI_NAME,  beanName = "UsuariAplicacioEJB")
  private UsuariAplicacioService usuariAplicacioEjb;

  @EJB(mappedName = BitacolaLogicaLocal.JNDI_NAME)
  private BitacolaLogicaLocal bitacolaLogicaEjb;

  private final Logger log = Logger.getLogger(getClass());

  /* Per controlar cridades concurrents que no s'haurien de produir però per mala implementació del JBOSS es produeixen */
  private static final Semaphore semaphore = new Semaphore(1);

  /* Darrera execució completa */
  private static long lastFullExecution = 0L;

  /* Darrera execució */
  private static long lastExecution = 0L;

  /* Propera execució */
  private static long nextExecution = 0L;

  @Override
  public void startScheduler() {
    stopScheduler();
    long timelapse = PropietatGlobalUtil.getNotificacionsTimeLapse();
    Timer timer = timerService.createIntervalTimer(timelapse, timelapse, new TimerConfig("normal", false));
    log.info("startScheduler: Proper enviament programat serà " + timer.getNextTimeout());
    nextExecution = timer.getNextTimeout().getTime();
  }

  @Override
  public void wakeUp() {
    if (!isTimerRunning()) {
      log.warn("wakeUp: El programador està aturat. No programam wakeUp. Arrancau el programador.");
      return;
    }

    if (!semaphore.tryAcquire()) {
      log.warn("wakeUp: El notificador ja està treballant ara. No feim res.");
      return;
    }

    try {
      @SuppressWarnings("unchecked")
      Collection<Timer> timers = timerService.getTimers();
      for (Timer timer : timers) {
        if (isWakeUpTimer(timer)) {
          log.info("wakeUp: Ja hi ha un wakeUp programat. No feim res.");
          return;
        }
      }

      log.info("wakeUp: Reprogamam els timers");
      startScheduler();
      Timer timer = timerService.createSingleActionTimer(2000, new TimerConfig("wakeUp", false));
      log.info("wakeUp: Programat wakeUp per " + timer.getNextTimeout());

    } finally {
      semaphore.release();
    }
  }

  private boolean isWakeUpTimer(Timer timer) {
    String timerInfo = (String) timer.getInfo();
    return timerInfo != null && timerInfo.equals("wakeUp");
  }

  @Timeout
  public void timeOutHandler(Timer timer) {
    boolean wakeUp = isWakeUpTimer(timer);
    if (!wakeUp) {
      if (log.isDebugEnabled()) {
        log.debug("timeOutHandler: Iniciant execució normal programada.");
      }
      nextExecution = timer.getNextTimeout().getTime();
    } else {
      if (log.isDebugEnabled()) {
        log.info("timeOutHandler: Iniciant execució forçada per un wakeUp.");
      }
    }

    if (!semaphore.tryAcquire()) {
      log.warn("timeOutHandler: No podem executar perquè s'ha produït una cridada concurrent");
      return;
    }
    try {
      executeTask(wakeUp);
    } finally {
      semaphore.release();
    }
  }

  @Override
  public boolean isTimerRunning() {
    return !timerService.getTimers().isEmpty();
  }

  @Override
  public void stopScheduler() {
    @SuppressWarnings("unchecked")
    Collection<Timer> timers = timerService.getTimers();
    for (Timer timer : timers) {
      log.info("stopScheduler: Cancel·lant timer");
      timer.cancel();
    }
    nextExecution = 0L;
  }

  /**
   * Retorna un array de informació de les execucions: [1] => darrra execució completa [2] =>
   * darrera execució [3] => propera execució
   */
  @Override
  public long[] getExecutionsInfo() {
    return new long[] { lastFullExecution, lastExecution, nextExecution };
  }

  private void executeTask(boolean wakeUp) {
    try {
      final long now = System.currentTimeMillis();
      final long notificacionsTimeLapse = PropietatGlobalUtil.getNotificacionsTimeLapse();
      final boolean isDebug = log.isDebugEnabled();
      
      if (isDebug) {
        log.debug("executeTask: Iniciam notificacions");
      }

      // Si s'ha demanat enviamnet de notificacions ara i no fa més de X
      // segons de la darrera execució, llavors només processam les
      // notificacions amb dataError = null

      Where whereDataError = NotificacioWSFields.DATAERROR.isNull();
      if (wakeUp && ( (lastFullExecution + notificacionsTimeLapse) > now)) {
        if (isDebug) {
          log.debug("executeTask: Només executam les Notificacions amb dataError==null");
        }
      } else {
        Timestamp nowX = new Timestamp(now - notificacionsTimeLapse);
        whereDataError = Where.OR(whereDataError, NotificacioWSFields.DATAERROR.lessThan(nowX));
        if (isDebug) {
          log.debug("executeTask: Execució completa");
        }
        lastFullExecution = now;
      }
      lastExecution = now;

      Where where = Where.AND(NotificacioWSFields.BLOQUEJADA.equal(false), whereDataError);

      Long retryToPause = PropietatGlobalUtil.getNumberOfErrorsToPauseNotification();
      if (isDebug) {
        log.debug("executeTask: Numero de reintents = " + retryToPause);
      }

      if (retryToPause != null) {
        where = Where.AND(where, NotificacioWSFields.REINTENTS.lessThan((int) (long) retryToPause));
      }

      // Temps màxim notificant, el temps programat manco 15 segons per assegurar que aturam a temps
      final long maxTempsNotificant = notificacionsTimeLapse - 15000;
      long estimatedProcessTime = 50L;
      // Processam un màxim de 1000 notificacions pendents per execució
      int maximSeleccionats = Math.min( (int) (maxTempsNotificant / estimatedProcessTime), 1000);

      List<NotificacioWS> notificacions = notificacioEjb.select(where, 0, maximSeleccionats,
          new OrderBy(NotificacioWSFields.DATACREACIO),
          new OrderBy(NotificacioWSFields.REINTENTS)); /* Dins la mateixa data no caldria ordenar pels que
        tenguin menor nombre de reintents. Però afegint aquest criteri d'ordenació la BDD agafarà l'índex definit
        per (bloquejada, reintents) que farà la consulta molt més ràpida. */

      final int notificacionsSeleccionades = notificacions.size();
      if (notificacionsSeleccionades == 0) {
        return;
      }

      log.info("executeTask: Notificacions seleccionades: " + notificacionsSeleccionades);

      // Processam les notificacions
      int count = 0;
      for (NotificacioWS notificacioWS : notificacions) {
        count++;
        log.info("Processant notificacio amb ID " + notificacioWS.getNotificacioID());
        if (notificacioWS.getDataError() != null) {
          log.info("notificacio::getDataError() => " + notificacioWS.getDataError());
          log.info("notificacio::getReintents() => " + notificacioWS.getReintents());
        }
        // Obte un NotificacioInfo a partir del notificacioWS.getDescripcio()
        processNotificacio((NotificacioWSJPA) notificacioWS);

        // Estarem fent feina durant un temps màxim per evitar cridades concurrents
        if ((System.currentTimeMillis() - now) > maxTempsNotificant) {
          log.warn("executeTask: Fa més de " + maxTempsNotificant + " ms que feim Notificacions. Aturam per evitar solapar execucions");
          break;
        }
      }

      if (count > 0) {
        log.info("executeTask: Processades " + count + "  de " + notificacionsSeleccionades + " selecionades");
      }

    } catch (Throwable e) {
      log.error("executeTask: Error general processant notificacions de callback: " + e.getMessage(), e);
    }

  }

  private void processNotificacio(NotificacioWSJPA notificacioJPA) {

    UsuariAplicacio usuariAplicacio = null;
    try {
      NotificacioInfo notificacioInfo = NotificacioInfo.readNotificacioInfo(notificacioJPA.getDescripcio());

      FirmaEvent fe = notificacioInfo.getFirmaEvent();
      if (fe == null) {
        // TODO enviar a admin
        log.error("processNotificacio: La notificacio amb ID " + notificacioJPA.getNotificacioID() + " té un FirmaEvent amb valor NULL!!!");
        return;
      }

      String usuariAplicacioID = fe.getDestinatariUsuariAplicacioID();
      usuariAplicacio = usuariAplicacioEjb.findByPrimaryKey(usuariAplicacioID);
      if (usuariAplicacio == null) {
        // TODO enviar a admin
        log.warn("processNotificacio: No es troba UsuariAplicacio " + usuariAplicacioID + ". Tancam de la notificacio");

      } else {
        log.info("\n\tUSRAPP: " + usuariAplicacio.getUsuariAplicacioID() +
                "\n\tSERVER: " + usuariAplicacio.getCallbackURL() +
                "\n\tVERSIO: " + usuariAplicacio.getCallbackVersio() +
                "\n\tPETICIO: " +notificacioJPA.getPeticioDeFirmaID() +
                "\n\tEVENT: " + notificacioInfo.getFirmaEvent().getEventID());

        NotificacioSender sender = NotificacioSenderFactory.getSender(usuariAplicacio);
        if (sender != null) {
          // Afegit el bloc de try-catch aquí per assegurar que si la notificació falla, s'itenti ver la bitàcola,
          // i independentment de que la bitàcola vagi bé, si la notificació havia donat un error aquest es propagui
          // perquè es tracti com fins ara.
          // No és molt elegant, però és la manera més segura d'afegir la bitàcola d'error de notificació sense rompre
          // res del que ja hi ha.
          // Caldrà però a qualque moment refactoritzar tot el codi per millor el tractament de les excepcions a tot
          // el procés de les notificacions.
          try {

            sender.sendNotificacio(notificacioInfo, usuariAplicacio);

          } catch (Throwable t) {
            // Si la notificació dona qualsevol error, intentam fer una bitàcola
            try {
              // Intentam recuperar el motiu primigeni de l'error
              Throwable cause = t;
              while (cause.getCause() != null) {
                cause = cause.getCause();
              }

              bitacolaLogicaEjb.createBitacola(
                      usuariAplicacio.getEntitatID(),
                      notificacioJPA.getPeticioDeFirmaID(),
                      BITACOLA_TIPUS_PETICIO,
                      BITACOLA_OP_ERROR_NOTIFICACIO,
                      "Error realitzant notificació: " + cause,
                      notificacioInfo.getFirmaEvent());

            } catch (Throwable logged) {
              // Si la creació de bitàcola falla, només logejam l'excepció que dona, però a continuació...
              log.warn("Error intentant crear bitàcola d'error de notificació", logged);
            }
            // Rellamçam l'error original que havia donat l'enviament de notificació perquè continui el procés...
            throw t;
          }
        }

        int operacio = NotificacioUtils.getOpericioBitacolaForEventType(notificacioInfo.getFirmaEvent().getEventID());
        if (operacio != -1) {
          bitacolaLogicaEjb.createBitacola(
                  usuariAplicacio.getEntitatID(),
                  notificacioJPA.getPeticioDeFirmaID(),
                  BITACOLA_TIPUS_PETICIO,
                  operacio,
                  "Notificat Usuari-Aplicació: " + usuariAplicacio.getUsuariAplicacioID(),
                  notificacioInfo.getFirmaEvent());
        } else {
          log.warn("EventID no és correspon a cap operació de bitàcola: " + notificacioInfo.getFirmaEvent().getEventID());
        }
      }

      notificacioEjb.delete(notificacioJPA);

    } catch (Throwable th) {
      StringWriter errors = new StringWriter();
      th.printStackTrace(new PrintWriter(errors));

      String msgError;
      if (th instanceof I18NException) {
        Locale loc = new Locale(usuariAplicacio==null? Configuracio.getDefaultLanguage() : usuariAplicacio.getIdiomaID());
        msgError =  I18NLogicUtils.getMessage((I18NException)th, loc);
      } else {
        msgError = th.getMessage();
      }

      log.error("processNotificacio: Error en la notificacio amb ID=" + notificacioJPA.getNotificacioID(), th); // ,

      String fullError = msgError
            + "\n--------------------------------------------\n"
            + errors.toString();
      notificacioJPA.setError(fullError);
      notificacioJPA.setDataError(new Timestamp(System.currentTimeMillis()));
      notificacioJPA.setReintents(notificacioJPA.getReintents() + 1);

      try {
        notificacioEjb.update(notificacioJPA);

        // Avisar a l'administrador de l'usuari app ?
        Long sendMail = PropietatGlobalUtil.getNumberOfErrorsInNotificationToSendMail();
        if(sendMail != null && sendMail == notificacioJPA.getReintents()) {

          if (usuariAplicacio == null) {
            log.error("processNotificacio: No es pot enviar el correu a l'administrador ja que la "
                  + "instància de usuariAplicacio val null", new Exception());
          } else {

            String dest = usuariAplicacio.getEmailAdmin();
            final String from = PropietatGlobalUtil.getAppEmail();
            final boolean isHtml = true;
            final String url = PropietatGlobalUtil.getAppUrl() + ConstantsV2.CONTEXT_ADEN_NOTIFICACIONSWS + "/list";
            Locale loc = new Locale(usuariAplicacio.getIdiomaID());
            String subject = I18NLogicUtils.tradueix(loc, "notificacioerrorcallback.subject");
            String message =  I18NLogicUtils.tradueix(loc, "notificacioerrorcallback.message",
                  usuariAplicacio.getUsuariAplicacioID(), url);
            try {
              EmailUtil.postMail(subject, message, isHtml, from, dest);
            } catch (Exception e1) {
              log.error("processNotificacio: Error enviant correu a administrador d'entitat "
                    + dest + " a causa d'errors en la notificació de l'usuari app "
                    + usuariAplicacio.getUsuariAplicacioID(), e1);
            }
          }
        }

        // Pausar la notificacio?
        Long pause = PropietatGlobalUtil.getNumberOfErrorsToPauseNotification();
        if (pause != null && notificacioJPA.getReintents() >= pause) {
          notificacioJPA.setBloquejada(true);
          notificacioEjb.update(notificacioJPA);
        }

      } catch (I18NException e2) {
        // TODO avisar a admin
        log.error(I18NLogicUtils.getMessage(e2, new Locale(Configuracio.getDefaultLanguage())), e2);
      }
    }
  }

  @Override
  public void testCallBackAPI(String usuariAplicacioID) throws Exception {
    UsuariAplicacioJPA usuariAplicacio = usuariAplicacioEjb.findByPrimaryKey(usuariAplicacioID);
    NotificacioSender sender = NotificacioSenderFactory.getSender(usuariAplicacio);
    if (sender != null) {
      sender.testApi(usuariAplicacio);
    }
  }
}