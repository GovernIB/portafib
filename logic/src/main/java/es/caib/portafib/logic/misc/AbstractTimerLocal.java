package es.caib.portafib.logic.misc;

/**
 * 
 * @author anadal
 *
 */
public interface AbstractTimerLocal {

  public void startScheduler();

  public void stopScheduler();

  public void executeTask();
}
