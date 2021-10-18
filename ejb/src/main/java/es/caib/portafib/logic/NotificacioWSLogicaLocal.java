package es.caib.portafib.logic;

import es.caib.portafib.ejb.NotificacioWSService;
import es.caib.portafib.persistence.NotificacioWSJPA;
import es.caib.portafib.logic.events.FirmaEvent;
import es.caib.portafib.logic.utils.NotificacioInfo;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;


/**
 * 
 * @author anadal
 *
 */
@Local
public interface NotificacioWSLogicaLocal extends NotificacioWSService {

  String JNDI_NAME = "java:app/portafib-ejb/NotificacioWSLogicaEJB";

  public NotificacioInfo createFullFromFirmaEvent(FirmaEvent firmaEvent) throws I18NException;

  public NotificacioWSJPA aturarNotificacio(java.lang.Long notificacioID) throws I18NException;
  
  public NotificacioWSJPA bloquejarNotificacio(java.lang.Long notificacioID) throws I18NException;

  public NotificacioWSJPA desbloquejarNotificacio(java.lang.Long notificacioID) throws I18NException;


  
  /**
   * Retorna un array de informació de les execucions:
   *     [1] => darrra execució completa
   *     [2] => darrera execució
   *     [3] => propera execució
   * @return
   */  
  public long[] getExecutionsInfo();
  
  
  public boolean isTimerRunning();
  
  public void startTimer();
  
  
  public void stopTimer();
  
  public void wakeupTimer();

  
}

