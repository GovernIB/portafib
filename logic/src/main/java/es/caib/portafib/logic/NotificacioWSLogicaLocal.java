package es.caib.portafib.logic;

import es.caib.portafib.ejb.NotificacioWSLocal;
import es.caib.portafib.jpa.NotificacioWSJPA;
import es.caib.portafib.logic.events.FirmaEvent;
import es.caib.portafib.logic.utils.NotificacioInfo;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;


/**
 * 
 * @author anadal
 *
 */
@Local
public interface NotificacioWSLogicaLocal extends NotificacioWSLocal {

  public NotificacioWSJPA findByPrimaryKeyForNotificacioQueue(long notificacioID);

  public NotificacioInfo createFullFromFirmaEvent(FirmaEvent firmaEvent) throws I18NException;

  public NotificacioWSJPA aturarNotificacio(java.lang.Long notificacioID) throws I18NException;
  
  public NotificacioWSJPA bloquejarNotificacio(java.lang.Long notificacioID) throws I18NException;

  public NotificacioWSJPA desbloquejarNotificacio(java.lang.Long notificacioID) throws I18NException;

  public boolean isTimerRunning();
  
  public void startTimer();
  
  public void stopTimer();
  
  /**
   * Retorna un array de informació de les execucions:
   *     [1] => darrra execució completa
   *     [2] => darrera execució
   *     [3] => propera execució
   * @return
   */
  public long[] getExecutionsInfo();
  
}

