package es.caib.portafib.logic;

import es.caib.portafib.ejb.NotificacioWSEJB;
import es.caib.portafib.jpa.NotificacioWSJPA;
import es.caib.portafib.logic.events.FirmaEvent;
import es.caib.portafib.logic.misc.NotificacionsCallBackTimerLocal;
import es.caib.portafib.logic.utils.NotificacioInfo;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.entity.NotificacioWS;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.Timestamp;


/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "NotificacioLogicaEJB")
@SecurityDomain("seycon")
public class NotificacioWSLogicaEJB extends NotificacioWSEJB 
  implements NotificacioWSLogicaLocal {
  
  @EJB(mappedName = NotificacionsCallBackTimerLocal.JNDI_NAME) // "portafib/BlocDeFirmesLogicaEJB/local")
  private NotificacionsCallBackTimerLocal notifCallback;

  /**
   * 
   * @param firmaEvent
   * @return
   */
  @Override
  public NotificacioInfo createFullFromFirmaEvent(FirmaEvent firmaEvent) throws I18NException {
    
    NotificacioWS notificacio = new NotificacioWSJPA();
    notificacio.setBloquejada(false);
    notificacio.setDataCreacio(new Timestamp(System.currentTimeMillis()));
    notificacio.setPeticioDeFirmaID(firmaEvent.getPeticioDeFirmaID());
    notificacio.setTipusNotificacioID(firmaEvent.getEventID());
    notificacio.setUsuariAplicacioID(firmaEvent.getDestinatariUsuariAplicacioID());
    notificacio = create(notificacio);
    
    NotificacioInfo notifInfo = new NotificacioInfo(System.nanoTime(), firmaEvent, notificacio.getNotificacioID());
    notificacio.setDescripcio(notifInfo.writeNotificacioInfo());
    update(notificacio);
    
    return notifInfo;
  }

  @Override
  public NotificacioWSJPA desbloquejarNotificacio(java.lang.Long notificacioID) throws I18NException {
    
    NotificacioWSJPA notificacio = findByPrimaryKey(notificacioID);
    if (notificacio == null) {
      return null;
    }

    if (notificacio.isBloquejada()) {

      notificacio.setBloquejada(false);
      
      // Forçam a que s'executi la primera
      notificacio.setDataError(null);
      
      
      Long pause = PropietatGlobalUtil.getNumberOfErrorsToPauseNotification();
      if (pause != null && notificacio.getReintents() >= pause) {

        log.info("La notificacio " + notificacio.getNotificacioID() 
            + "esta en el numero màxim de reintents (Actual: " + notificacio.getReintents() 
            + "| Màxim permes: " + pause + "). Resetejam comptador de reintents a 0." );
        notificacio.setReintents(0);;
      }

      
      notificacio = (NotificacioWSJPA)this.update(notificacio);
    }
    return notificacio;
  }
  
  
  @Override
  public NotificacioWSJPA bloquejarNotificacio(java.lang.Long notificacioID) throws I18NException {

    NotificacioWSJPA notificacio = findByPrimaryKey(notificacioID);
    if (notificacio == null) {
      return null;
    }

    if (!notificacio.isBloquejada()) {
      notificacio.setBloquejada(true);
      notificacio = (NotificacioWSJPA)this.update(notificacio);
    }
    
    return notificacio;
    
  }
  
  
  @Override
  public NotificacioWSJPA aturarNotificacio(java.lang.Long notificacioID) throws I18NException {
    
    NotificacioWSJPA notificacio = findByPrimaryKey(notificacioID);
    if (notificacio == null) {
      return null;
    }

    
    notificacio.setBloquejada(true);
    notificacio.setDataEnviament(new Timestamp(System.currentTimeMillis()));

    notificacio = (NotificacioWSJPA)this.update(notificacio);

    return notificacio;

  }

  
  @Override
  public boolean isTimerRunning() {
    return notifCallback.isTimerRunning();
  }

  @Override
  public void startTimer() {
    notifCallback.startScheduler();
  }

  @Override
  public void stopTimer() {
    notifCallback.stopScheduler();
  }
  
  
  @Override
  public void wakeupTimer() {
    notifCallback.wakeUp();
  }
  
  
  
  /**
   * Retorna un array de informació de les execucions:
   *     [1] => darrra execució completa
   *     [2] => darrera execució
   *     [3] => propera execució
   * @return
   */
  @Override
  public long[] getExecutionsInfo() {
    return notifCallback.getExecutionsInfo();
  }


}
