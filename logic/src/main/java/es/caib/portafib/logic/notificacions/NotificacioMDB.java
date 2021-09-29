package es.caib.portafib.logic.notificacions;

import es.caib.portafib.ejb.UsuariAplicacioLocal;
import es.caib.portafib.logic.BitacolaLogicaLocal;
import es.caib.portafib.logic.bitacola.InfoBitacola;
import es.caib.portafib.logic.events.FirmaEvent;
import es.caib.portafib.logic.utils.NotificacioInfo;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.utils.ConstantsV2;
import org.apache.log4j.Logger;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.annotation.security.RunAs;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.concurrent.atomic.AtomicInteger;

import static es.caib.portafib.utils.ConstantsV2.BITACOLA_OP_ERROR_NOTIFICACIO;
import static es.caib.portafib.utils.ConstantsV2.BITACOLA_TIPUS_PETICIO;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination",
                        propertyValue = "jms/es.caib.portafib.PortaFIBNotificacionsQueue")})
@RunAs(ConstantsV2.PFI_ADMIN)
public class NotificacioMDB implements MessageListener {

    private final Logger log = Logger.getLogger(getClass());

    private static final AtomicInteger INSTANCES = new AtomicInteger(0);

    @Resource
    private MessageDrivenContext context;

    @EJB(mappedName = UsuariAplicacioLocal.JNDI_NAME, beanName = "UsuariAplicacioEJB")
    private UsuariAplicacioLocal usuariAplicacioEjb;

    @EJB(mappedName = BitacolaLogicaLocal.JNDI_NAME)
    private BitacolaLogicaLocal bitacolaLogicaEjb;

    @PostConstruct
    protected void init() {
        log.info("Inicialitzada instància. Total instàncies: " + INSTANCES.incrementAndGet());
    }

    @PreDestroy
    protected void destroy() {
        log.info("Destruida instància. Total instàncies: " + INSTANCES.decrementAndGet());
    }

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            NotificacioInfo info = NotificacioInfo.readNotificacioInfo(textMessage.getText());
            log.info("Processant notificació " + info.getNotificacioID());

            FirmaEvent event = info.getFirmaEvent();
            String usuariAplicacioID = event.getDestinatariUsuariAplicacioID();
            UsuariAplicacio usuariAplicacio = usuariAplicacioEjb.findByPrimaryKey(usuariAplicacioID);
            if (usuariAplicacio == null) {
                log.warn("No es troba UsuariAplicacio " + usuariAplicacioID + ". Ignoram la notificacio");
                return;
            }

            log.info("\n\tUSRAPP: " + usuariAplicacio.getUsuariAplicacioID() +
                    "\n\tSERVER: " + usuariAplicacio.getCallbackURL() +
                    "\n\tVERSIO: " + usuariAplicacio.getCallbackVersio() +
                    "\n\tPETICIO: " + event.getPeticioDeFirmaID() +
                    "\n\tEVENT: " + event.getEventID());

            NotificacioSender sender = NotificacioSenderFactory.getSender(usuariAplicacio);
            if (sender == null) {
                log.warn("L'usuari aplicació " + usuariAplicacioID + " no té un callback configurat. Ignoram la notificacio");
                return;
            }

            try {
                sender.sendNotificacio(info, usuariAplicacio);

                int operacio = NotificacioUtils.getOpericioBitacolaForEventType(event.getEventID());
                if (operacio != -1) {
                    bitacolaLogicaEjb.createBitacolaFailsafe(
                            InfoBitacola.builder(usuariAplicacio.getEntitatID())
                                    .objecteid(event.getPeticioDeFirmaID())
                                    .tipusObjecte(BITACOLA_TIPUS_PETICIO)
                                    .tipusOperacio(operacio)
                                    .descripcio("Notificat Usuari-Aplicació: " + usuariAplicacio.getUsuariAplicacioID())
                                    .objecte(event)
                                    .build());
                } else {
                    log.warn("EventID no és correspon a cap operació de bitàcola: " + event.getEventID());
                }

            } catch (Throwable th) {
                // Si es produeix un error durant la notificació cream una bitàcola.

                Throwable cause = th;
                while (cause.getCause() != null) {
                    cause = cause.getCause();
                }

                bitacolaLogicaEjb.createBitacolaFailsafe(
                        InfoBitacola.builder(usuariAplicacio.getEntitatID())
                                .objecteid(event.getPeticioDeFirmaID())
                                .tipusObjecte(BITACOLA_TIPUS_PETICIO)
                                .tipusOperacio(BITACOLA_OP_ERROR_NOTIFICACIO)
                                .descripcio("Error realitzant notificació: " + cause)
                                .objecte(event)
                                .build());
                throw th;
            }

        } catch (Throwable th) {
            context.setRollbackOnly();
            log.error("S'ha produit un error processant el missatge", th);
        }
    }
}
