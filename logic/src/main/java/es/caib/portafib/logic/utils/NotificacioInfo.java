package es.caib.portafib.logic.utils;

import java.io.Serializable;

import es.caib.portafib.logic.events.FirmaEvent;

/**
 * 
 * @author anadal
 *
 */
public class NotificacioInfo implements Serializable {

  
  private transient static final long serialVersionUID = 12344545645L;

  private long idObjectSent;

  private FirmaEvent firmaEvent;
  
  private long notificacioID;

  /**
   * 
   */
  public NotificacioInfo() {
    super();
  }



  /**
   * @param idObjectSent
   * @param firmaEvent
   * @param notificacio
   */
  public NotificacioInfo(long idObjectSent, FirmaEvent firmaEvent, long notificacioID) {
    super();
    this.idObjectSent = idObjectSent;
    this.firmaEvent = firmaEvent;
    this.notificacioID = notificacioID;
  }



  public long getIdObjectSent() {
    return idObjectSent;
  }


  public void setIdObjectSent(long idObjectSent) {
    this.idObjectSent = idObjectSent;
  }

  public FirmaEvent getFirmaEvent() {
    return firmaEvent;
  }

  public void setFirmaEvent(FirmaEvent firmaEvent) {
    this.firmaEvent = firmaEvent;
  }



  public long getNotificacioID() {
    return notificacioID;
  }



  public void setNotificacioID(long notificacioID) {
    this.notificacioID = notificacioID;
  }

}
