package es.caib.portafib.logic.misc;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.annotation.Resource;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.jboss.ejb3.annotation.SecurityDomain;

import es.caib.portafib.logic.NotificacioWSLogicaEJB;
import es.caib.portafib.logic.NotificacioWSLogicaLocal;
import es.caib.portafib.logic.PropietatGlobalLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.utils.EjbManager;
import es.caib.portafib.logic.utils.NotificacioInfo;
import es.caib.portafib.logic.utils.NotificacionsQueue;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.entity.NotificacioWS;
import es.caib.portafib.model.fields.NotificacioWSFields;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "NotificacionsCallBackTimerEJB")
@SecurityDomain("seycon")
@RunAs("PFI_ADMIN")
public class NotificacionsCallBackTimerEJB implements NotificacionsCallBackTimerLocal {

  public static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

  @Resource
  TimerService timerService;

  @Resource
  private SessionContext context;

  @EJB(mappedName = PropietatGlobalLogicaLocal.JNDI_NAME)
  protected PropietatGlobalLogicaLocal propietatEjb;

  protected final Logger log = Logger.getLogger(getClass());

  // NOTA: Les següents propietats han de ser estatiques ja que
  // s'instancia un nou timer despres de cada WakeUp

  /**
   * Data de la darrera execució de reintents.
   */
  protected static long lastFullExecution = System.currentTimeMillis() - 900000;

  protected static long lastExecution = System.currentTimeMillis() - 900000;

  protected static long nextExecution = System.currentTimeMillis() + 900000;
  
  protected static Semaphore semaphore = new Semaphore(1);

  /**
   * Utilitzat enviar les notificacions en el mateix moment (al cap de 2 segons)
   * 
   */
  protected static boolean forceExecutionNow = false;

  public String getTimerName() {
    return "NotificacionsCallBackTimer";
  }

  protected long getDuration() {

    long notificacionTimeLapse = PropietatGlobalUtil.getNotificacionsTimeLapse();
    return notificacionTimeLapse;
  }

  @Override
  public void startScheduler() {

    try {

      final long duration = getDuration();

      removeTimer(getTimerName());

      createTimer(duration);

      log.info("Primer enviament de " + getTimerName() + " sera " + SDF.format(nextExecution));

    } catch (Exception e) {
      log.fatal("Error creant timer de " + getTimerName() + ": " + e.getMessage(), e);
    }
  }

  public static long lastDuration = -1;

  @Override
  // @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public void wakeUp() {

    // Despertam el timer per processar una petició URGENT
    final boolean debug = isDebug();
    if (debug) {
      log.info("NotificacionsCallBackTimerEJB::wakeUp =>> ENTRA ");
    }
    
    // No volem aturar mentre estam executant
    if (semaphore.tryAcquire()) {
      try {
        this.stopScheduler();
        forceExecutionNow = true;
  
        this.startScheduler();
      } finally {
        semaphore.release();
      }
    } else {
      log.info("wakeUp():: S'esta fent feina en aquest moment. No feim wakeup.");
    }
    if (debug) {
      log.info("NotificacionsCallBackTimerEJB::wakeUp =>> SURT ");
    }
  }

  private boolean isDebug() {
    return log.isDebugEnabled();
  }

  @Timeout
  public void timeOutHandler(Timer timer) {

    final boolean debug = isDebug();
    if (debug) {
      log.info("\n\n" + " =========================\n" 
        + "   Entra a timeOutHandler\n"
        + " =========================\n");
    }

    try {
      long duration = getDuration();
      
      if (semaphore.tryAcquire()) {
  
        try {
          
    
          if (lastDuration != duration) {
    
            if (lastDuration == -1) {
              lastDuration = duration;
            } else {
    
              if (debug) {
                log.info("\n\n" + " =========================\n" 
                  + "   NOU TIMER !!!!!!!!\n"
                  + " =========================\n");
              }
    
              lastDuration = duration;
    
              timer.cancel();
    
              createTimer(duration);
              
              return;
            }
    
          }
          
          final long now = System.currentTimeMillis();
          
          long diffExpectedNow = now - nextExecution;
          
          if (debug) {
            log.info("            Now: " + now  + "    " + SDF.format(new Date(now)) );
            log.info("  nextExecution: " + nextExecution + "    " + SDF.format(new Date(nextExecution)) );
            log.info("diffExpectedNow: " + diffExpectedNow);
          }
     
          nextExecution = now + duration;
    
          if (diffExpectedNow < 30000) {
            executeTask(duration);
          } else {
            log.warn("[" + getTimerName() + "] Timer programat no s'executara:"
                 + "\n                 Ara: " +   SDF.format(new Date(now))
                 + "\n        diffExpected: " +   diffExpectedNow
                 + "\n       Hora prevista: " +    SDF.format(new Date(now + diffExpectedNow))
                 );
          }
    
        } catch (Throwable e) {
    
          Throwable cause = e.getCause();
    
          log.error("CAUSE ===" + cause + "\n\n");
    
          if (cause != null && cause instanceof javax.naming.NameNotFoundException) {
            //
    
            log.error("XYZ ZZZ\n\n ERA UNA TASCA GUARDADA EN MEMORIA ===" + cause + "\n\n");
    
            
          } else {
             log.error("[" + getTimerName() + "] Error executant tasca: " + e.getMessage(), e);
          }
        } finally {
          semaphore.release();
        }
      } else {
        log.info("timeOutHandler() :: No ho executam ja que esta en proces el wakeUp.");
      }
    
    } finally {
      if (debug) {
        log.info("\n\n" + " =========================\n" 
          + "   Surt de timeOutHandler\n"
          + " =========================\n");
      }
    }
    

  }

  private void createTimer(long duration) {
    removeTimer(getTimerName());

    TimerService timerService = context.getTimerService();

    // 10:57:35,613 WARN [loggerI18N]
    // [com.arjuna.ats.internal.jta.transaction.arjunacore.lastResource.multipleWarning]
    // [com.arjuna.ats.internal.jta.transaction.arjunacore.lastResource.multipleWarning]
    // Multiple last resources have been added to the current transaction. This is
    // transactionally unsafe and
    // should not be relied upon. Current resource is
    // org.jboss.resource.connectionmanager.TxConnectionManager$LocalXAResource@5e7a92ad

    long whenStarts;
    if (forceExecutionNow) {
      whenStarts = 2000;
    } else {
      whenStarts = duration;
    }

    timerService.createTimer(whenStarts, duration, getTimerName());
  }

  protected void removeTimer(String name) {
    Timer timer;
    do {
      timer = searchTimerByName(name);
      if (timer != null) {
        if (isDebug()) {
          log.info("Removing old timer(" + getTimerName() + ") : " + name + "("
            + timer.getNextTimeout() + ")");
        }
        timer.cancel();
      }
    } while (timer != null);
  }

  @Override
  public boolean isTimerRunning() {
    return searchTimerByName(this.getTimerName()) != null;
  }

  @Override
  public void stopScheduler() {
    removeTimer(getTimerName());
  }

  protected Timer searchTimerByName(String name) {
    javax.ejb.Timer timer = null;
    TimerService timerService = context.getTimerService();
    for (Object obj : timerService.getTimers()) {
      timer = (javax.ejb.Timer) obj;
      String scheduled = (String) timer.getInfo();
      if (scheduled.equals(name)) {
        return timer;
      }
    }

    return timer;
  }

  // @TransactionAttribute(TransactionAttributeType.NEVER)
  // @TransactionAttribute(TransactionAttributeType.MANDATORY)
  // @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  // @Override
  public void executeTask(long notificacionTimeLapse) {

    final boolean debug = isDebug();

    // No volem que ens aturin mentre estam executant
    synchronized (log) {
      try {
        final long now = System.currentTimeMillis();

        if (debug) {
          log.info("\n\n ----- executeTask() de " + getTimerName() + " --------------");
          log.info("NotificacionsCallBackTimerEJB::now  => " + SDF.format(new Date(now)));
          log.info("NotificacionsCallBackTimerEJB::notificacionTimeLapse  => "
              + notificacionTimeLapse);
          log.info("NotificacionsCallBackTimerEJB::forceExecutionNow  => " + forceExecutionNow);
          log.info("NotificacionsCallBackTimerEJB::(lastFullExecution "
              + "+ notificacionTimeLapse) > now  => "
              + ((lastFullExecution + notificacionTimeLapse) > now));
        }

        Where whereDataError;

        // Si s'ha demanat enviamnet de notificacions ara i no fa més de X
        // segons de la darrera execució, llavors només processam les
        // notificacions amb DataError = null

        if (forceExecutionNow && ((lastFullExecution + notificacionTimeLapse) > now)) {

          /**
           * Esperarem un mínim de 30 segons entre inici de execucions per evitar saturar el
           * servidor
           */
          if (lastExecution + 30000 > now) {
            log.warn("Fa manco de 30 segons de la darrera execució. Com que "
                + "això es un notificacio forçada llavors NO executarem res. ");
            forceExecutionNow = false;
            return;
          }

          if (debug) {
            log.info("Només executam les Notificacions amb ErrorData==null");
          }
          whereDataError = Where.OR(NotificacioWSFields.DATAERROR.isNull()); //
        } else {

          Timestamp nowX = new Timestamp(now - notificacionTimeLapse);

          whereDataError = Where.OR(
          // Prioritat Màxima (primera cridada)
              NotificacioWSFields.DATAERROR.isNull(),
              // Els hi toca proper reintent
              NotificacioWSFields.DATAERROR.lessThan(nowX));
          lastFullExecution = now;
        }
        forceExecutionNow = false;
        lastExecution = now;

        Where where = Where.AND(NotificacioWSFields.BLOQUEJADA.equal(false), whereDataError);

        Long retryToPause = PropietatGlobalUtil.getNumberOfErrorsToPauseNotification();
        if (debug) {
          log.info("Numero de reintents = " + retryToPause);
        }
        if (retryToPause != null) {
          where = Where.AND(where,
              NotificacioWSFields.REINTENTS.lessThan((int) (long) retryToPause));
        }

        int firstResult = 0;
        int maxResults = 40;
        List<NotificacioWS> notificacions;
        NotificacioWSLogicaLocal notificacioLogicaEjb = EjbManager.getNotificacioLogicaEJB();

        if (debug) {
          long count = notificacioLogicaEjb.count(where);
          log.info("NotificacionsCallBackTimerEJB::TOTAL PETICIONS PENDENTS  => " + count);
        }

        notificacions = notificacioLogicaEjb.select(where, firstResult, maxResults,
            new OrderBy(NotificacioWSFields.DATACREACIO));
        //    new OrderBy(NotificacioWSFields.DATAENVIAMENT));

        if (debug) {
          log.info(" NotificacionsCallBackTimerEJB::notificacioLogicaEjb.select().size(); => "
              + notificacions.size());
        }

        final long maxTempsNotificant = Math.min(notificacionTimeLapse, 45000);

        UsuariAplicacioLogicaLocal usuariAplicacioEjb = EjbManager
            .getUsuariAplicacioLogicaEJB();

        // Processam les notificacions
        for (NotificacioWS notificacioWS : notificacions) {

          try {

            if (debug) {
              log.info("Processant notificacio amb ID " + notificacioWS.getNotificacioID());
              log.info("notificacio::getDataError() => " + notificacioWS.getDataError());
              log.info("notificacio::getReintents() => " + notificacioWS.getReintents());
            }
            // Obte un NotificacioInfo a partir del
            // notificacioWS.getDescripcio()
            NotificacioInfo notificacioInfo;
            notificacioInfo = NotificacioWSLogicaEJB
                .getNotificacioInfoFromNotificacioJPA(notificacioWS);

            NotificacionsQueue.processNotificacio(usuariAplicacioEjb, notificacioLogicaEjb,
                notificacioInfo);

          } catch (Exception e) {
            log.error(
                "Error processant notificacio amb ID " + notificacioWS.getNotificacioID()
                    + ": " + e.getMessage(), e);
          }

          Thread.sleep(1500);

          // Estarem fent feina com a màxim 45 segons per no saturar el servidor
          if ((System.currentTimeMillis() - now) > maxTempsNotificant) {
            log.warn("Fa més de " + maxTempsNotificant + "  ms que feim Notificacions."
                + " Aturam per no saturar servidor !!!!");
            break;
          }

        }

      } catch (Throwable e) {
        log.error("Error general processant notificacions de callback: " + e.getMessage(), e);
      } finally {
        if (debug) {
          log.info("\n\n");
        }
      }
    }

  }

  /**
   * Retorna un array de informació de les execucions: [1] => darrra execució completa [2] =>
   * darrera execució [3] => propera execució
   * 
   * @return
   */
  public long[] getExecutionsInfo() {
    return new long[] { lastFullExecution, lastExecution, nextExecution };
  }

}