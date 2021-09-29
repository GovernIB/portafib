package es.caib.portafib.logic.notificacions;

import es.caib.portafib.ejb.UsuariAplicacioLocal;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.utils.ConstantsV2;
import org.apache.log4j.Logger;


import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import java.util.Collection;
import java.util.concurrent.Semaphore;

/**
 * Servei d'enviament de Notificacions de callback mitjançant l'activació d'un timer
 *
 * @author areus
 */
@Stateless
@RunAs(ConstantsV2.PFI_ADMIN)
@PermitAll
public class NotificacioQueueTimerEJB implements NotificacioQueueTimerLocal {

  @Resource
  private TimerService timerService;

  @EJB
  private NotificacioQueueServiceLocal notificacioService;

  @EJB(mappedName = UsuariAplicacioLocal.JNDI_NAME,  beanName = "UsuariAplicacioEJB")
  private UsuariAplicacioLocal usuariAplicacioEjb;

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
    if (!isWakeUpTimer(timer)) {
      log.debug("timeOutHandler: Iniciant execució normal programada.");
      nextExecution = timer.getNextTimeout().getTime();
    } else {
      log.debug("timeOutHandler: Iniciant execució forçada per un wakeUp.");
    }

    if (!semaphore.tryAcquire()) {
      log.warn("timeOutHandler: No podem executar perquè s'ha produït una cridada concurrent");
      return;
    }
    try {
      executeTask();
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

  private void executeTask() {
    log.debug("executeTask");
    try {
      lastExecution = lastFullExecution = System.currentTimeMillis();

      long notificacions = notificacioService.encolarNotificacionsPendents();

      if (notificacions > 0) {
        long duration = System.currentTimeMillis() - lastExecution;
        log.info("executeTask: Encoades " + notificacions + " notificacions en " + duration + "ms");
      } else {
        log.debug("executeTask: No hi havia notificacions pendents d'encoar");
      }

    } catch (Throwable e) {
      log.error("executeTask: Error general ecoant notificacions.", e);
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