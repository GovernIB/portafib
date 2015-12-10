package es.caib.portafib.logic.utils;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import es.caib.portafib.logic.RebreAvisLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.logic.misc.EnviarCorreusAgrupatsUtils;
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

  protected UsuariPersonaLogicaLocal usuariPersonaEjb = null;
  
  protected RebreAvisLogicaLocal rebreAvisLogicaEjb;

  @Resource
  private MessageDrivenContext context;

  public void onMessage(Message message) {
    ObjectMessage objectMessage = (ObjectMessage) message;

    try {
      EmailInfo emailInfo = (EmailInfo) objectMessage.getObject();
      final boolean isDebug = log.isDebugEnabled();
      
      String usuariEntitatId = emailInfo.getUsuariEntitatID();
      long eventID = emailInfo.getEventID();
      
      
      try {
        rebreAvisLogicaEjb = (RebreAvisLogicaLocal) new InitialContext()
            .lookup("portafib/RebreAvisLogicaEJB/local");
      } catch (Exception e) {
        // TODO traduccio
        throw new RuntimeException("No puc accedir al gestor de RebreAvis: " +  e.getMessage(), e);
      }
      
      
     
      
      boolean rebreAgrupat = rebreAvisLogicaEjb.isAgruparCorreus(usuariEntitatId, eventID);
      
      
      if (usuariEntitatId != null && rebreAgrupat) {
        // Guardar per enviar més endavant
        EnviarCorreusAgrupatsUtils.saveAvisAgrupat(usuariEntitatId, eventID, emailInfo);

      } else {
      
        // Enviar a l'instant
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