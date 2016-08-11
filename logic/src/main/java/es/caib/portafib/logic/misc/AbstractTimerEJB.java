package es.caib.portafib.logic.misc;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.apache.log4j.Logger;
import org.quartz.impl.triggers.CronTriggerImpl;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractTimerEJB implements AbstractTimerLocal {

  @Resource
  TimerService timerService;

  protected final Logger log = Logger.getLogger(getClass());

  @Resource
  private SessionContext context;

  public AbstractTimerEJB() {
  }

  public void clearTimers() {
    removeTimer(getTimerName());
  }

  @Timeout
  public void timeOutHandler(Timer timer) {
    try {
      long timeRemaining = timer.getTimeRemaining();

      timer.cancel();

      removeTimer(getTimerName());

      nextExecution();

      // Si han passat més de 30segons de l'hora prevista d'execució
      // llavors no l'executam.
      if (timeRemaining > -30000) {
        executeTask();
      } else {
        log.warn("[" + getTimerName() + "] Timer programat per "
            + new Date(System.currentTimeMillis() + timeRemaining) + " no s'executara.");
      }

    } catch (Throwable e) {
      log.error("[" + getTimerName() + "] Error executant tasca: " + e.getMessage(), e);
    }

  }

  protected Date nextExecution() throws ParseException {

    String cronExpression = getCronExpression();

    if (cronExpression != null && cronExpression.trim().length() != 0
        && !org.quartz.CronExpression.isValidExpression(cronExpression)) {
      log.error("L'expressió cron per " + getTimerName() + " no és correcta: "
          + cronExpression);
      cronExpression = null;
    }

    if (cronExpression == null) {
      cronExpression = getDefaultCronExpression();
    }

    if (cronExpression == null) {
      log.warn("El timer " + getTimerName() + "  s'ha aturat ja que no s'ha definit"
          + " cap expressió de tipus cron.", new Exception());
      return null;
    }

    Date currTime = new Date();
    CronTriggerImpl tr = new CronTriggerImpl();
    tr.setCronExpression(cronExpression);
    Date nextFireAt = tr.getFireTimeAfter(currTime);

    TimerService timerService = context.getTimerService();
    Timer timer2 = timerService.createTimer(nextFireAt, getTimerName());

    if (log.isDebugEnabled()) {
      log.debug("[" + getTimerName() + "] Reference time: " + currTime);
      log.debug("[" + getTimerName() + "] Next fire after reference time: " + nextFireAt);
      log.debug("[" + getTimerName() + "] timeoutHandler : " + timer2.getInfo());
    }
    return nextFireAt;

  }

  protected void removeTimer(String name) {
    TimerService timerService = context.getTimerService();
    for (Object obj : timerService.getTimers()) {
      javax.ejb.Timer timer = (javax.ejb.Timer) obj;
      String scheduled = (String) timer.getInfo();

      if (scheduled.equals(name)) {
        log.info("Removing old timer(" + getTimerName() + ") : " + scheduled + "("
            + timer.getNextTimeout() + ")");
        timer.cancel();
      }
    }
  }

  @Override
  public void startScheduler() {

    try {
      clearTimers();

      Date nextExecution = nextExecution();

      log.info("Primer enviament de " + getTimerName() + " sera " + nextExecution);

    } catch (ParseException e) {
      log.fatal("Error creant timer de " + getTimerName() + ": " + e.getMessage(), e);
    }
  }

  @Override
  public void stopScheduler() {
    clearTimers();
  }

  public abstract String getTimerName();

  /**
   * Consultar cronmaker.com
   * 
   * @return
   */
  public abstract String getCronExpression();

  /**
   * Consultar cronmaker.com
   * 
   * @return Si val null significa que no s'ha d'executar si el valor principal també val null
   */
  public abstract String getDefaultCronExpression();

  /**
   * El que hagi de fer
   */
  public abstract void executeTask();

}
