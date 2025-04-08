package es.caib.portafib.logic.scheduler;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.logging.Logger;

import org.jboss.ejb3.annotation.TransactionTimeout;

/**
 * 
 * @author anadal
 * 8 abr 2025 10:59:24
 */
public abstract class AbstractScheduler {

    protected final Logger log = Logger.getLogger(getClass());

    @Resource
    private TimerService timerService;

    @PostConstruct
    public void init() {
        // Configurar la tarea con valores dinámicos

        String cron = getCronExpression();
        ScheduleExpression schedule;
        if (cron == null || cron.trim().length() == 0) {
            log.warn("Es desactiva el scheduler " + getSchedulerName() + " ja que no s'han definit CronExpressions");
            return;
        } else {
            try {
                schedule = fromCron(cron);
            } catch (Exception e) {
                log.error("La CronExpression ]" + cron + "[ del Scheduler " + getSchedulerName()
                        + " no està ben formada (Validar-la a cronmaker.com): " + e.getMessage());
                System.exit(-1);
                return;
            }
        }

        try {
            scheduleTask(schedule);
            log.error("\n\nArrancat correctament el Scheduler " + getSchedulerName() + " amb cron " + cron + "\n\n");
        } catch (Throwable th) {
            log.error(
                    "\n Error no controlat posant en marxa el Scheduler " + getSchedulerName() + ": " + th.getMessage(),
                    th);
        }
    }

    public void scheduleTask(ScheduleExpression schedule) {

        // Netejar Timers anteriors
        for (Timer timer : timerService.getTimers()) {
            timer.cancel();
        }

        Timer newTimer = timerService.createCalendarTimer(schedule);
        System.out.println("CREAT Schedule '" + getSchedulerName() + "': " + newTimer.getNextTimeout());
    }

    public abstract String getSchedulerName();

    /**
     * Si val null significa que no volem executar el Scheduler
     * @return
     */
    public abstract String getCronExpression();

    public final long getTimeoutTransactionInMs() {
        return TEN_MINUTES_IN_MS;
    }

    public static final long TEN_MINUTES_IN_MS = 10 * 60 * 1000;

    @Timeout
    @TransactionTimeout(value = TEN_MINUTES_IN_MS, unit = TimeUnit.MILLISECONDS)
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void onTimeout(Timer timer) {

        long start = System.currentTimeMillis();

        try {
            log.info("SCHEDULER[" + getSchedulerName() + "]: INICI --------------");
            executeTask();
            log.info("SCHEDULER[" + getSchedulerName() + "]: FINAL OK  " + (System.currentTimeMillis() - start)
                    + "ms ----------");
        } catch (Throwable e) {
            log.error("SCHEDULER[" + getSchedulerName() + "]: FINAL ERROR  " + (System.currentTimeMillis() - start)
                    + "ms: " + e.getMessage(), e);
        }
    }

    /**
     * El que hagi de fer
     */
    public abstract void executeTask();

    public static ScheduleExpression fromCron(String cron) {

        if (cron == null || cron.trim().isEmpty()) {
            throw new IllegalArgumentException("Expresión CRON vacía");
        }

        String[] parts = cron.trim().replace('?', '*').split("\\s+");
        if (parts.length == 7 || parts.length == 6) {

            ScheduleExpression schedule = new ScheduleExpression().second(parts[0]).minute(parts[1]).hour(parts[2])
                    .dayOfMonth(parts[3]).month(parts[4]).dayOfWeek(parts[5]);

            if (parts.length == 7) {
                schedule.year(parts[6]);
            }
            return schedule;
        } else {
            throw new IllegalArgumentException("La expresión CRON debe tener exactamente 6 o 7 campos: " + cron);
        }

    }
}
