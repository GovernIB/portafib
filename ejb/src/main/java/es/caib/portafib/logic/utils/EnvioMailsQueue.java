package es.caib.portafib.logic.utils;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;
import es.caib.portafib.logic.RebreAvisLogicaLocal;
import es.caib.portafib.logic.misc.EnviarCorreusAgrupatsUtils;
import es.caib.portafib.utils.ConstantsV2;

/**
 * @author anadal
 * @author areus
 */
@MessageDriven(name = ConstantsV2.MAIL_QUEUE, activationConfig = {
@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
@ActivationConfigProperty(propertyName = "destination", propertyValue = ConstantsV2.MAIL_QUEUE) })
public class EnvioMailsQueue implements MessageListener {

  protected final Logger log = Logger.getLogger(getClass());

  @EJB(mappedName = RebreAvisLogicaLocal.JNDI_NAME)
  private RebreAvisLogicaLocal rebreAvisLogicaEjb;

  @Resource
  private MessageDrivenContext context;

  public void onMessage(Message message) {
    ObjectMessage objectMessage = (ObjectMessage) message;
    try {
      EmailInfo emailInfo = (EmailInfo) objectMessage.getObject();
      String usuariEntitatId = emailInfo.getUsuariEntitatID();
      long eventID = emailInfo.getEventID();

      boolean rebreAgrupat = rebreAvisLogicaEjb.isAgruparCorreus(usuariEntitatId, eventID);

      if (usuariEntitatId != null && rebreAgrupat) {
        // Guardar per enviar més endavant
        EnviarCorreusAgrupatsUtils.saveAvisAgrupat(usuariEntitatId, eventID, emailInfo);

      } else {
        // Enviar a l'instant

        if (log.isDebugEnabled()) {
          log.info("Enviant avis amb id "
            + emailInfo.getIdObjectSent() + " al correu " + emailInfo.getEmail() 
            + " amb subject ] " + emailInfo.getSubject() + "[");
        }

        EmailUtil.postMail(emailInfo.getSubject(), emailInfo.getMessage(),
            emailInfo.isHtml(), PropietatGlobalUtil.getAppEmail(), emailInfo.getEmail());
      }

    } catch (JMSException jme) {
      log.error("JMS mail Problem: " + jme.getMessage());
      context.setRollbackOnly();
    } catch (Exception e) {
      log.error("Error desconegut enviant un correu-avis: " + e.getMessage(), e);
      context.setRollbackOnly();
    }

  }

}