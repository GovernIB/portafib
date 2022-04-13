package es.caib.portafib.logic.utils;

import es.caib.portafib.logic.events.FirmaEvent;

import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
   * @param notificacioID
   */
  public NotificacioInfo(long idObjectSent, FirmaEvent firmaEvent, long notificacioID) {
    super();
    this.idObjectSent = idObjectSent;
    this.firmaEvent = firmaEvent;
    this.notificacioID = notificacioID;
  }

  /**
   * Deserialitza un notificacioInfo
   */
  public static NotificacioInfo readNotificacioInfo(String descripcio)  {
    ByteArrayInputStream bais = new ByteArrayInputStream(descripcio.getBytes());
    XMLDecoder decoder = new XMLDecoder(bais);
    try {
      return (NotificacioInfo)decoder.readObject();
    } finally {
        decoder.close();
    }
  }

  /**
   * Serialitza un notificació info
   */
  public String writeNotificacioInfo() {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    XMLEncoder encoder = new XMLEncoder(baos);
    PersistenceDelegate pd=encoder.getPersistenceDelegate(Integer.class);
    encoder.setPersistenceDelegate(BigDecimal.class,pd );
    encoder.setPersistenceDelegate( Date.class, new PersistenceDelegate() {
      protected Expression instantiate(Object oldInstance, Encoder out ) {
        Date date = (Date)oldInstance;
        return new Expression( date, date.getClass(), "new", new Object[]{date.getTime()} );
      }
    } );
    encoder.writeObject(this);
    encoder.close();
    return baos.toString();
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
