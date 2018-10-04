package es.caib.portafib.logic.misc;

import javax.ejb.Local;

/**
 *
 * @author anadal
 *
 */
@Local
public interface NotificacionsCallBackTimerLocal /* extends AbstractTimerLocal */ {

  public static final String JNDI_NAME = "portafib/NotificacionsCallBackTimerEJB/local";

  /**
   * 
   */
  public void wakeUp();
  

  /**
   * Retorna un array de informació de les execucions:
   *     [1] => darrera execució completa
   *     [2] => darrera execució
   *     [3] => propera execució
   * @return
   */
  public long[] getExecutionsInfo(); 
  
  
  public void startScheduler();


  public void stopScheduler();
  
  public boolean isTimerRunning();
  
}
