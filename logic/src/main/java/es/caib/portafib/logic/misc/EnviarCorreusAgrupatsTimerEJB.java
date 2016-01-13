package es.caib.portafib.logic.misc;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;
import javax.annotation.security.RunAs;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.apache.log4j.Logger;
import org.jboss.ejb3.annotation.SecurityDomain;
import org.quartz.impl.triggers.CronTriggerImpl;

import es.caib.portafib.logic.utils.PropietatGlobalUtil;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "EnviarCorreusAgrupatsTimerEJB")
@SecurityDomain("seycon")
@RunAs("PFI_ADMIN")
public class EnviarCorreusAgrupatsTimerEJB implements EnviarCorreusAgrupatsTimerLocal {

  @Resource
  TimerService timerService;

  protected final Logger log = Logger.getLogger(getClass());

  private static final String NAME_TIMER = "CorreusAgrupatsTimer";

  @Resource
  private SessionContext context;
  
  public EnviarCorreusAgrupatsTimerEJB() {
  }

  public void clearTimers() {
    removeTimer(NAME_TIMER);
  }

  @Timeout
  public void timeOutHandler(Timer timer) {
    try {
      long timeRemaining = timer.getTimeRemaining();

      timer.cancel();

      removeTimer(NAME_TIMER);

      nextExecution();

      // Si han passat més de 30segons de l'hora prevista d'execució
      // llavors no l'executam.
      if (timeRemaining > -30000) {
        enviarCorreus();
      } else {
        log.warn("Timer programat per " + new Date(System.currentTimeMillis() + timeRemaining)
            + " no s'executara.");
      }

    } catch (Throwable e) {
      log.error("Error enviant correus agrupats: " + e.getMessage(), e);
    }

  }

  protected Date nextExecution() throws ParseException {
    String cronExpression = PropietatGlobalUtil.getEmailsGroupedSenderCronExpression();

    if (cronExpression == null || cronExpression.trim().length() == 0
        || !org.quartz.CronExpression.isValidExpression(cronExpression)) {
      // Valor per defecte = cada dia a les 6:00
      cronExpression = "0 0 6 1/1 * ? *";
    }

    Date currTime = new Date();
    CronTriggerImpl tr = new CronTriggerImpl();
    tr.setCronExpression(cronExpression);
    Date nextFireAt = tr.getFireTimeAfter(currTime);

    TimerService timerService = context.getTimerService();
    Timer timer2 = timerService.createTimer(nextFireAt, NAME_TIMER);

    if (log.isDebugEnabled()) 
    { 
      log.debug("Reference time: " + currTime);
      log.debug("Next fire after reference time: " + nextFireAt);
      log.debug("timeoutHandler : " + timer2.getInfo());
    }
    return nextFireAt;

  }

  protected void removeTimer(String name) {
    TimerService timerService = context.getTimerService();
    for (Object obj : timerService.getTimers()) {
      javax.ejb.Timer timer = (javax.ejb.Timer) obj;
      String scheduled = (String) timer.getInfo();

      if (scheduled.equals(name)) {
        log.info("Removing old timer : " + scheduled + "(" + timer.getNextTimeout() + ")");
        timer.cancel();
      }
    }
  }

  @Override
  public void startScheduler()  {

    try {
      clearTimers();

      Date nextExecution = nextExecution();

      log.info("Primer enviament de Correus Agrupats sera " + nextExecution);

    } catch (ParseException e) {
      log.fatal("Error creant timer de l'Enviador de Correus Agrupats: " + e.getMessage(), e);
    }
  }

  @Override
  public void stopScheduler() {
    clearTimers();
  }

  @Override
  public void enviarCorreus() {
    try {

      EnviarCorreusAgrupatsUtils.enviarAvisosAgrupats();

    } catch (Throwable e) {
      log.error("Error enviant Avisos Agrupats: " + e.getMessage(), e);
    }

  }

}