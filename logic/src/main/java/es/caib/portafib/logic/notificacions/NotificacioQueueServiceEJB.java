package es.caib.portafib.logic.notificacions;

import es.caib.portafib.ejb.NotificacioWSLocal;
import es.caib.portafib.jpa.NotificacioWSJPA;
import es.caib.portafib.logic.utils.NotificacioInfo;
import es.caib.portafib.model.entity.NotificacioWS;
import es.caib.portafib.model.fields.NotificacioWSFields;
import es.caib.portafib.utils.ConstantsV2;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.sql.Timestamp;
import java.util.List;


/**
 * Servei per gestionar el pas de notificacions a la coa.
 * @author areus
 */
@Stateless
@RolesAllowed(ConstantsV2.PFI_ADMIN)
public class NotificacioQueueServiceEJB implements NotificacioQueueServiceLocal {

  private static final Logger log = Logger.getLogger(NotificacioQueueServiceEJB.class);

  @EJB(mappedName = NotificacioWSLocal.JNDI_NAME)
  private NotificacioWSLocal notificacioEjb;

  @Resource(mappedName="java:/JmsXA")
  ConnectionFactory connectionFactory;

  @Resource(mappedName="java:/jms/es.caib.portafib.PortaFIBNotificacionsQueue")
  private Queue queue;

  private List<NotificacioWS> getNotificacionsPendents() throws I18NException {
    return notificacioEjb.select(
            NotificacioWSFields.BLOQUEJADA.equal(false), 0, 1000,
            new OrderBy(NotificacioWSFields.DATACREACIO));
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public long encolarNotificacionsPendents() throws I18NException {
    Connection connection = null;
    Session session = null;
    MessageProducer messageProducer = null;
    try {
      connection = connectionFactory.createConnection();
      session = connection.createSession(true, Session.SESSION_TRANSACTED);
      messageProducer = session.createProducer(queue);

      List<NotificacioWS> notificacionsPendents = getNotificacionsPendents();

      for (NotificacioWS notificacio : notificacionsPendents) {
        TextMessage message = session.createTextMessage(notificacio.getDescripcio());
        if (notificacio.getTipusNotificacioID() == ConstantsV2.NOTIFICACIOAVIS_PETICIO_FIRMADA) {
          message.setLongProperty("_AMQ_SCHED_DELIVERY", System.currentTimeMillis() + 30000);
        }
        messageProducer.send(message);
        notificacioEjb.delete(notificacio);
      }

      return notificacionsPendents.size();

    } catch (JMSException jmsException) {
      log.error("Error a la capa JMS", jmsException);
      throw new I18NException("genapp.comodi", "Error en la capa JMS: " + jmsException.getMessage());
    } finally {
      if (messageProducer != null) try { messageProducer.close(); } catch (JMSException ignored) {}
      if (session != null) try { session.close(); } catch (JMSException ignored) {}
      if (connection != null) try { connection.close(); } catch (JMSException ignored) {}
    }
  }

  @Override
  public long recrearNotificacio(NotificacioInfo info) throws I18NException {
    NotificacioWS notificacio = new NotificacioWSJPA();
    notificacio.setBloquejada(true);

    Timestamp now = new Timestamp(System.currentTimeMillis());
    notificacio.setDataCreacio(now);
    notificacio.setDataError(now);

    notificacio.setPeticioDeFirmaID(info.getFirmaEvent().getPeticioDeFirmaID());
    notificacio.setTipusNotificacioID(info.getFirmaEvent().getEventID());
    notificacio.setUsuariAplicacioID(info.getFirmaEvent().getDestinatariUsuariAplicacioID());
    notificacio.setError("Consulti la bitàcola els events de tipus Error de Notificació per la petició de firma: "
            + notificacio.getPeticioDeFirmaID());

    notificacioEjb.create(notificacio);

    info.setNotificacioID(notificacio.getNotificacioID());
    notificacio.setDescripcio(info.writeNotificacioInfo());

    return notificacio.getNotificacioID();
  }
}
