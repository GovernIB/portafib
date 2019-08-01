package es.caib.portafib.logic.misc;

import es.caib.portafib.ejb.NotificacioWSLocal;
import es.caib.portafib.ejb.UsuariAplicacioLocal;
import es.caib.portafib.jpa.NotificacioWSJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.BitacolaLogicaLocal;
import es.caib.portafib.logic.events.FirmaEvent;
import es.caib.portafib.logic.notificacions.NotificacioSender;
import es.caib.portafib.logic.notificacions.NotificacioSenderFactory;
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
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.Resource;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Semaphore;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "NotificacionsCallBackTimerEJB")
@SecurityDomain("seycon")
@RunAs("PFI_ADMIN")
public class NotificacionsCallBackTimerEJB implements NotificacionsCallBackTimerLocal {

  @Resource
  private TimerService timerService;

  @EJB(mappedName = NotificacioWSLocal.JNDI_NAME, beanName = "NotificacioWSEJB")
  private NotificacioWSLocal notificacioEjb;

  @EJB(mappedName = UsuariAplicacioLocal.JNDI_NAME, beanName = "UsuariAplicacioEJB")
  private UsuariAplicacioLocal usuariAplicacioEjb;

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
    Timer timer = timerService.createTimer(timelapse, timelapse, "normal");
    log.info("startScheduler: Primer enviament serà " + timer.getNextTimeout());
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
    } else {
      semaphore.release();
    }

    Collection<Timer> timers = timerService.getTimers();
    for (Timer timer : timers) {
      if (isWakeUpTimer(timer)) {
        log.info("wakeUp: Ja hi ha un wakeUp programat. No feim res.");
        return;
      }
    }

    Timer timer = timerService.createTimer(2000, "wakeUp");
    log.info("wakeUp: Programat wakeUp per " + timer.getNextTimeout());
  }

  private boolean isWakeUpTimer(Timer timer) {
    String timerInfo = (String) timer.getInfo();
    return timerInfo != null && timerInfo.equals("wakeUp");
  }

  @Timeout
  public void timeOutHandler(Timer timer) {
    boolean wakeUp = isWakeUpTimer(timer);
    if (!wakeUp) {
      log.info("-----------------------------------------------------------");
      log.info("timeOutHandler: Iniciant execució normal programada.");
      nextExecution = timer.getNextTimeout().getTime();
    } else {
      log.info("-----------------------------------------------------------");
      log.info("timeOutHandler: Iniciant execució forçada per un wakeUp.");
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

      log.info("executeTask: Iniciam notificacions");


      // Si s'ha demanat enviamnet de notificacions ara i no fa més de X
      // segons de la darrera execució, llavors només processam les
      // notificacions amb dataError = null

      Where whereDataError = NotificacioWSFields.DATAERROR.isNull();

      if (wakeUp && ( (lastFullExecution + notificacionsTimeLapse) > now)) {

        if (lastExecution + 30000 > now) {
          log.warn("executeTask: Fa manco de 30 segons de la darrera execució. " +
                "Com que això es un execució forçada llavors no executarem res.");
          return;
        }

        log.info("executeTask: Només executam les Notificacions amb dataError==null");

      } else {
        Timestamp nowX = new Timestamp(now - notificacionsTimeLapse);
        whereDataError = Where.OR(whereDataError, NotificacioWSFields.DATAERROR.lessThan(nowX));
        log.info("executeTask: Execució completa");
        lastFullExecution = now;
      }
      lastExecution = now;


      Where where = Where.AND(NotificacioWSFields.BLOQUEJADA.equal(false), whereDataError);

      Long retryToPause = PropietatGlobalUtil.getNumberOfErrorsToPauseNotification();
      log.info("executeTask: Numero de reintents = " + retryToPause);

      if (retryToPause != null) {
        where = Where.AND(where,
            NotificacioWSFields.REINTENTS.lessThan((int) (long) retryToPause));
      }

      final long notificacionsPendents = notificacioEjb.count(where);
      log.info("executeTask: Notificacions pendents: " + notificacionsPendents);
      if (notificacionsPendents == 0) {
        return;
      }

      // Temps màxim notificant, la meitat del temps programat, o com a màxim en qualsevol cas 1 minut
      final long maxTempsNotificant = Math.min(notificacionsTimeLapse / 2, 60000);
      long sleepTime = 2000L;
      long estimatedProcessTime = 500L;
      int maximSeleccionats = (int) (maxTempsNotificant / (sleepTime + estimatedProcessTime));


      long order = notificacionsPendents / maximSeleccionats;

      if (order < 2) { // Si queden entre 0 i 48 notificacions

        // Treurem 24 notificacions
        log.info("executeTask: Velocitat de notificació normal ");

      } else if (order < 4) {// Si queden entre 48 i 96 notificacions, pujam el ritme.

        sleepTime /= 2; // Treurem 40 notificacions
        maximSeleccionats = (int) (maxTempsNotificant / (sleepTime + estimatedProcessTime));
        log.info("executeTask: Velocitat de notificació +");

      } else if (order < 6) { // Si queden més de 96 notificacions i manco que 144

        sleepTime /= 8; // Treurem 80 notificacions
        maximSeleccionats = (int) (maxTempsNotificant / (sleepTime + estimatedProcessTime));
        log.info("executeTask: Velocitat de notificació ++");

      } else { // Si queden més de 144 notificacions

        sleepTime /= 20; // Treurem 100 notificacions
        maximSeleccionats = (int) (maxTempsNotificant / (sleepTime + estimatedProcessTime));
        log.info("executeTask: Velocitat de notificació +++");
      }

      List<NotificacioWS> notificacions = notificacioEjb.select(where, 0, maximSeleccionats,
          new OrderBy(NotificacioWSFields.DATACREACIO));
      final int notificacionsSeleccionades = notificacions.size();
      log.info("executeTask: Notificacions seleccionades: " + notificacionsSeleccionades);

      // Processam les notificacions
      int count = 0;
      for (NotificacioWS notificacioWS : notificacions) {

        try {
          count++;
          log.info("Processant notificacio amb ID " + notificacioWS.getNotificacioID());
          log.info("notificacio::getDataError() => " + notificacioWS.getDataError());
          log.info("notificacio::getReintents() => " + notificacioWS.getReintents());
          // Obte un NotificacioInfo a partir del notificacioWS.getDescripcio()
          processNotificacio((NotificacioWSJPA) notificacioWS);


        } catch (Exception e) {
          log.error("Error processant notificacio amb ID " + notificacioWS.getNotificacioID() + ": "
                + e.getMessage(), e);
        }


        Thread.sleep(sleepTime);

        // Estarem fent feina com a màxim la meitat del temps programat, o com a màxim 1 minut. per no saturar el servidor
        if ((System.currentTimeMillis() - now) > maxTempsNotificant) {
          log.warn("executeTask: Fa més de " + maxTempsNotificant + " ms que feim Notificacions. Aturam per no saturar servidor!!!");
          break;
        }
      }
      if (count > 0) {
        log.info("executeTask: Processades " + count + "  de " + notificacionsSeleccionades + " selecionades d'un total de " + notificacionsPendents + " pendents");
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
        log.info("  USRAPP: " + usuariAplicacio.getUsuariAplicacioID());
        log.info("  SERVER: " + usuariAplicacio.getCallbackURL());
        log.info("  VERSIO: " + usuariAplicacio.getCallbackVersio());
        log.info("  EVENT: " + notificacioInfo.getFirmaEvent().getEventID());

        NotificacioSender sender = NotificacioSenderFactory.getSender(usuariAplicacio);
        if (sender != null) {
          sender.sendNotificacio(notificacioInfo, usuariAplicacio);
        }

        String messageCode = "notificacioavis.bitacola." + notificacioInfo.getFirmaEvent().getEventID();
        String message = I18NLogicUtils.tradueix(new Locale(Configuracio.getDefaultLanguage()), messageCode);
        bitacolaLogicaEjb.createBitacola(message, notificacioInfo.getFirmaEvent().getPeticioDeFirmaID(),
              null, usuariAplicacioID);

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

      log.error("processNotificacio: Error en la notificacio amb ID=" + notificacioJPA.getNotificacioID() + ": " + msgError); // ,

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