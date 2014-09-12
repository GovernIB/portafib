package es.caib.portafib.logic.utils;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.Constants;

/**
 * @author anadal
 * 
 */
@MessageDriven(name = Constants.MAIL_QUEUE, activationConfig = {
@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
@ActivationConfigProperty(propertyName = "destination", propertyValue = Constants.MAIL_QUEUE) })
public class EnvioMailsQueue implements MessageListener {

  protected final Logger log = Logger.getLogger(getClass());

  @Resource
  private MessageDrivenContext context;

  public void onMessage(Message message) {
    ObjectMessage objectMessage = (ObjectMessage) message;

    try {
      EmailInfo emailInfo = (EmailInfo) objectMessage.getObject();
      boolean isDebug = log.isDebugEnabled();
      if (isDebug) {
        log.info("Enviant avis amb id "
          + emailInfo.getIdObjectSent() + " al correu " + emailInfo.getEmail() 
          + " amb subject ] " + emailInfo.getSubject() + "[");
      }
      

      EmailUtil.postMail(emailInfo.getSubject(), emailInfo.getMessage(),
          emailInfo.isHtml(), Configuracio.getAppEmail(), emailInfo.getEmail());

      if (isDebug) {
        log.info("Enviat avis amb id "
          + emailInfo.getIdObjectSent() + " al correu " + emailInfo.getEmail() 
          + " amb subject ] " + emailInfo.getSubject() + "[");
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