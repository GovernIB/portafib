package es.caib.portafib.logic;

import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import es.caib.portafib.ejb.NotificacioWSEJB;
import es.caib.portafib.jpa.NotificacioWSJPA;
import es.caib.portafib.logic.events.FirmaEvent;
import es.caib.portafib.logic.misc.NotificacionsCallBackTimerLocal;
import es.caib.portafib.logic.utils.NotificacioInfo;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.entity.NotificacioWS;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.hibernate.Hibernate;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.ejb3.annotation.SecurityDomain;


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
  
  
  @Override
  public NotificacioWSJPA findByPrimaryKeyForNotificacioQueue(long notificacioID) {
    NotificacioWSJPA n = (NotificacioWSJPA)findByPrimaryKey(notificacioID);
    if (n == null) {
      return null;
    }
    
    Hibernate.initialize(n.getPeticioDeFirma());
    
    Hibernate.initialize(n.getPeticioDeFirma().getUsuariAplicacio());
    
    return n;
  }
  
  
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
    
    notificacio = create(notificacio);
    
    NotificacioInfo notifInfo;
    
    notifInfo = new NotificacioInfo(System.nanoTime(), firmaEvent, notificacio.getNotificacioID());
    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    

    XMLEncoder encoder = new XMLEncoder(baos);
    PersistenceDelegate pd=encoder.getPersistenceDelegate(Integer.class);
    encoder.setPersistenceDelegate(BigDecimal.class,pd );
    encoder.setPersistenceDelegate( Date.class, new PersistenceDelegate() {
        protected Expression instantiate( Object oldInstance, Encoder out )
        {
            Date date = ( Date )oldInstance;
            Long time = Long.valueOf( date.getTime() );
            return new Expression( date, date.getClass(), "new", new Object[]{time} );
        }
    } );
    
    encoder.writeObject(notifInfo);
    encoder.flush();
    encoder.close();
    
    notificacio.setDescripcio(baos.toString());
    
    
    /*
    ObjectOutputStream oos = new ObjectOutputStream(baos);
    oos.writeObject(notifInfo);
    oos.flush();
   
    notificacio.setDescripcio(Base64.encodeBytes(baos.toByteArray()));
    */
    
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

      // Hem de tornar a ficar la notificació dins la coa d'enviament
      //NotificacioInfo notifInfo = getNotificacioInfoFromNotificacioJPA(notificacio);

      //log.info("XYZ ZZZ desbloquejar Notificacio amb ID = " + notificacio.getNotificacioID());
      //log.info("XYZ ZZZ notifCallback = " + notifCallback);

      // Despertar per processar
      // XYZ ZZZ notifCallback.wakeUp();
      
      //NotificacionsQueue.enviarNotificacions(Arrays.asList(notifInfo));
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


  /**
   * 
   * @param notificacio
   * @return
   * @throws I18NException
   */
  public static NotificacioInfo getNotificacioInfoFromNotificacioJPA(NotificacioWS notificacio)
      throws I18NException {
    String desc = notificacio.getDescripcio();
    if (desc == null || desc.trim().isEmpty()) {
      throw new I18NException("error.unknown", "No té definit descripció," +
          " on es defineix la notificació serialitzada a enviar a la coa");
      
    } else {

      NotificacioInfo notifInfo;

      ByteArrayInputStream bais = new ByteArrayInputStream(desc.getBytes());

      XMLDecoder decoder = new XMLDecoder(bais);

      notifInfo = (NotificacioInfo)decoder.readObject();

      return notifInfo;

    }
  }
  
  


  
}
