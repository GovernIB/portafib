package es.caib.portafib.logic.utils;

import java.util.Date;
import java.util.List;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.logic.RebreAvisLogicaLocal;
import es.caib.portafib.logic.misc.EnviarCorreusAgrupatsUtils;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 * 
 */
public class EmailUtil {

    /**
      * Envia un email a un {@link List} de emails
      * 
      * @param subject
      *          Asunto del Mensaje
      * @param message
      *          Contenido a enviar
      * @param from
      *          Indica la procedencia del mensaje
      * @param isHtml
      *          Decide si el contenido del mensaje a de ser visualizado en html o
      *          no
      * @param recipients
      *          Conjunto de emails para los que va dirigido el mensaje
      * @throws Exception
      */

    protected static Logger log = Logger.getLogger(EmailUtil.class);

    public static void postMail(String subject, String message, boolean isHtml, String from, String... recipients)
            throws Exception {

        Context ctx = new InitialContext();
        Session session = (javax.mail.Session) ctx.lookup(ConstantsV2.MAIL_SERVICE);

        // Creamos el mensaje
        MimeMessage msg = new MimeMessage(session);

        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        // Indicamos los destinatarios
        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++) {
            addressTo[i] = new InternetAddress(recipients[i]);
        }

        final RecipientType type = RecipientType.TO;

        msg.setRecipients(type, addressTo);

        // Configuramos el asunto
        msg.setSubject(subject, "UTF-8");
        msg.setSentDate(new Date());

        // Configuramos el contenido
        if (isHtml) {
            msg.setHeader("Content-Type", "text/html;charset=utf-8");
            /*
            URL urlToAdd = new URL(url);
            msg.setDataHandler(new DataHandler(urlToAdd));
            */
            msg.setContent(message, "text/html;charset=utf-8");
        } else {
            msg.setContent(message, "text/plain" /*; charset=UTF-8"*/);
        }

        // Mandamos el mail
        Transport.send(msg);
    }

    /**
     * Envia un email a un {@link List} de emails
     * @param emailInfos
     * @param rebreAvisLogicaEjb
     * @throws I18NException
     */
    public static void enviarMails(List<EmailInfo> emailInfos, RebreAvisLogicaLocal rebreAvisLogicaEjb)
            throws I18NException {

        if (emailInfos == null || emailInfos.size() == 0) {
            return;
        }
        try {

            for (EmailInfo emailInfo : emailInfos) {

                String usuariEntitatId = emailInfo.getUsuariEntitatID();
                long eventID = emailInfo.getEventID();

                boolean rebreAgrupat = rebreAvisLogicaEjb.isAgruparCorreus(usuariEntitatId, eventID);

                if (usuariEntitatId != null && rebreAgrupat) {
                    // Guardar per enviar més endavant
                    EnviarCorreusAgrupatsUtils.saveAvisAgrupat(usuariEntitatId, eventID, emailInfo);

                } else {
                    // Enviar a l'instant
                    postMail(emailInfo.getSubject(), emailInfo.getMessage(), emailInfo.isHtml(),
                            Configuracio.getAppEmail(), emailInfo.getEmail());
                }
            }

        } catch (Exception e) {
            throw new I18NException(e, "error.unknown",
                    new I18NArgumentString("Error enviant mail: " + e.getMessage()));
        }

    }

}
