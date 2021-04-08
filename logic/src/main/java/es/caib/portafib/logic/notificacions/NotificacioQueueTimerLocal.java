package es.caib.portafib.logic.notificacions;

import es.caib.portafib.logic.misc.NotificacionsCallBackTimerLocal;

import javax.ejb.Local;

/**
 *
 * @author areus
 *
 */
@Local
public interface NotificacioQueueTimerLocal extends NotificacionsCallBackTimerLocal {

  String JNDI_NAME = "portafib/NotificacioQueueTimerEJB/local";
  
}
