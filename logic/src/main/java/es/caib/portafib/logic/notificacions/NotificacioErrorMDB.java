package es.caib.portafib.logic.notificacions;

import es.caib.portafib.logic.utils.NotificacioInfo;
import es.caib.portafib.utils.ConstantsV2;
import org.apache.log4j.Logger;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.Resource;
import javax.annotation.security.RunAs;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination",
                        propertyValue = "jms/es.caib.portafib.PortaFIBNotificacionsErrorQueue")})
@SecurityDomain("seycon")
@RunAs(ConstantsV2.PFI_ADMIN)
public class NotificacioErrorMDB implements MessageListener {

    private final Logger log = Logger.getLogger(getClass());

    @Resource
    private MessageDrivenContext context;

    @EJB
    private NotificacioQueueServiceLocal notificacioService;

    @Override
    public void onMessage(Message message) {
        String messageContent = null;
        try {
            TextMessage textMessage = (TextMessage) message;
            messageContent = textMessage.getText();

            NotificacioInfo notificacioInfo = NotificacioInfo.readNotificacioInfo(messageContent);
            long idVell = notificacioInfo.getNotificacioID();
            long idNou = notificacioService.recrearNotificacio(notificacioInfo);

            log.warn("Notificació amb  ID: " + idVell + ", ha superat el nombre de reintents configurat a la coa");
            log.warn("Es recrea amb estat bloquejat amb el nou id: " + idNou);

        } catch (Throwable th) {
            context.setRollbackOnly();
            log.error("S'ha produit un error recreant la notificació", th);
            if (messageContent != null) {
                log.error("Contingut de la notificació: " +
                        "\n" + messageContent + "\n");
            }
        }
    }
}
