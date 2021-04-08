package es.caib.portafib.logic.notificacions;

import es.caib.portafib.ejb.NotificacioWSLocal;
import es.caib.portafib.jpa.NotificacioWSJPA;
import es.caib.portafib.logic.utils.NotificacioInfo;
import es.caib.portafib.model.entity.NotificacioWS;
import es.caib.portafib.model.fields.NotificacioWSFields;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.ejb3.annotation.TransactionTimeout;

import javax.annotation.Resource;
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
 * 
 * @author areus
 */
@Stateless
@SecurityDomain("seycon")
public class NotificacioQueueServiceEJB implements NotificacioQueueServiceLocal {

  private static final Logger log = Logger.getLogger(NotificacioQueueServiceEJB.class);

  @EJB(mappedName = NotificacioWSLocal.JNDI_NAME)
  private NotificacioWSLocal notificacioEjb;

  @Resource(mappedName="java:/JmsXA")
  ConnectionFactory connectionFactory;

  @Resource(mappedName="jms/es.caib.portafib.PortaFIBNotificacionsQueue")
  private Queue queue;

  private List<NotificacioWS> getNotificacionsPendents() throws I18NException {
    return notificacioEjb.select(
            NotificacioWSFields.BLOQUEJADA.equal(false), 0, 1000,
            new OrderBy(NotificacioWSFields.DATACREACIO));
  }

  @Override
  @TransactionTimeout(60)
  public long encolarNotificacionsPendents() throws I18NException {
    Connection connection = null;
    try {
      connection = connectionFactory.createConnection();
      Session session = connection.createSession(true, Session.SESSION_TRANSACTED);
      MessageProducer messageProducer = session.createProducer(queue);

      List<NotificacioWS> notificacionsPendents = getNotificacionsPendents();

      for (NotificacioWS notificacio : notificacionsPendents) {
        TextMessage message = session.createTextMessage(notificacio.getDescripcio());
        messageProducer.send(message);

        notificacioEjb.delete(notificacio);
      }

      messageProducer.close();
      session.close();

      return notificacionsPendents.size();

    } catch (JMSException jmsException) {
      log.error("Error a la capa JMS", jmsException);
      throw new I18NException("genapp.comodi", "Error en la capa JMS: " + jmsException.getMessage());
    } finally {
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
