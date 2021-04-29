package es.caib.portafib.logic.events;

import es.caib.portafib.jpa.EstatDeFirmaJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.logic.BitacolaLogicaLocal;
import es.caib.portafib.logic.EstatDeFirmaLogicaLocal;
import es.caib.portafib.logic.utils.EmailUtil;
import es.caib.portafib.logic.utils.PeticioHaDeSerRebutjadaException;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.entity.RevisorDeFirma;
import es.caib.portafib.utils.ConstantsV2;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;

import java.sql.Timestamp;
import java.util.Locale;

/**
 * Classe d'utilitat per encapçular operacions realitzades quan s'avança dins un flux de firma
 */
public class EstatDeFirmaEventHelper {

    private final Logger log = Logger.getLogger(this.getClass());

    private final BitacolaLogicaLocal bitacolaLogicaEjb;
    private final EstatDeFirmaLogicaLocal estatDeFirmaLogicaEjb;

    public EstatDeFirmaEventHelper(BitacolaLogicaLocal bitacolaLogicaEjb, EstatDeFirmaLogicaLocal estatDeFirmaLogicaEjb) {
        this.bitacolaLogicaEjb = bitacolaLogicaEjb;
        this.estatDeFirmaLogicaEjb = estatDeFirmaLogicaEjb;
    }

    public void doSendEmailToExternalUser(String entitatId, long peticioDeFirmaID, String titolPeticio, FirmaJPA firmaJPA)
            throws I18NException {

        String urlPortaFIB = PropietatGlobalUtil.getPortafibUrlForExternalSignatures();
        // XYZ ZZZ ZZZ TRA
        if (urlPortaFIB == null) {
            throw new I18NException("genapp.comodi",
                    "No puc obtenir la URL base de PortaFIB a partir de la propietat"
                            + " PortafibUrlForExternalSignatures. Consulti amb l'administrador de PortaFIB.");
        }

        final String urlToken = urlPortaFIB + ConstantsV2.CONTEXT_EXTERNALUSER_TOKEN + "/"
                + firmaJPA.getUsuariExternToken();

        Locale locale = new Locale(firmaJPA.getUsuariExternIdioma());
        String base = I18NCommonUtils.tradueix(locale, "usuariextern.email.subject", titolPeticio);
        String subject = "PORTAFIB: " + base;
        String message = I18NCommonUtils.tradueix(locale, "usuariextern.email.body", base, urlToken);
        String from = PropietatGlobalUtil.getAppEmail();
        String recipient = firmaJPA.getUsuariExternEmail();
        log.info("Enviant correu a usuari extern (" + recipient + ")");

        try {
            EmailUtil.postMail(subject, message, true, from, recipient);

            // Bitacola
            bitacolaLogicaEjb.createBitacola(entitatId, peticioDeFirmaID, ConstantsV2.BITACOLA_TIPUS_PETICIO,
                    ConstantsV2.BITACOLA_OP_EMAIL_USUARI_EXTERN, "OK:" + recipient);

        } catch (Throwable e) {

            // Bitacola
            bitacolaLogicaEjb.createBitacola(entitatId, peticioDeFirmaID, ConstantsV2.BITACOLA_TIPUS_PETICIO,
                    ConstantsV2.BITACOLA_OP_EMAIL_USUARI_EXTERN, "ERROR:" + recipient + ":" + e.getMessage());

            throw new I18NException("genapp.comodi", e.getMessage());
        }
    }

    public void requeritPerRevisar(PeticioDeFirmaJPA peticioDeFirma, RevisorDeFirma revisorDeFirma,
                                   FirmaEventList events) throws I18NException {
        EstatDeFirmaJPA estatDeFirmaRevisor = new EstatDeFirmaJPA();
        estatDeFirmaRevisor.setDataInici(new Timestamp(System.currentTimeMillis()));
        estatDeFirmaRevisor.setDescripcio("");
        estatDeFirmaRevisor.setFirmaID(revisorDeFirma.getFirmaID());
        estatDeFirmaRevisor.setTipusEstatDeFirmaInicialID(ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_REVISAR);
        estatDeFirmaRevisor.setUsuariEntitatID(revisorDeFirma.getUsuariEntitatID());
        estatDeFirmaRevisor.setColaboracioDelegacioID(null);
        estatDeFirmaRevisor = estatDeFirmaLogicaEjb.createFull(estatDeFirmaRevisor);

        events.requerit_per_revisar(peticioDeFirma, estatDeFirmaRevisor);

        if (log.isDebugEnabled()) {
            log.debug("   == Nou estat per REVISOR " + revisorDeFirma.getUsuariEntitatID()
                    + " (" + revisorDeFirma.getRevisorDeFirmaID() + ")");
        }
    }

    public void requeritPerSignar(PeticioDeFirmaJPA peticioDeFirma, FirmaJPA firmaJPA, FirmaEventList events,
                                  String destinatariReal)
            throws I18NException, PeticioHaDeSerRebutjadaException {
        EstatDeFirmaJPA estatDeFirmaDest = new EstatDeFirmaJPA();
        estatDeFirmaDest.setDataInici(new Timestamp(System.currentTimeMillis()));
        estatDeFirmaDest.setDescripcio("");
        estatDeFirmaDest.setFirmaID(firmaJPA.getFirmaID());
        estatDeFirmaDest.setTipusEstatDeFirmaInicialID(
                ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR);
        estatDeFirmaDest.setUsuariEntitatID(destinatariReal);
        estatDeFirmaDest = estatDeFirmaLogicaEjb.createFull(estatDeFirmaDest);

        if (firmaJPA.getRevisorDeFirmas().isEmpty()) {
            avisarUsuari(peticioDeFirma, firmaJPA, estatDeFirmaDest, events);
        }

        if (log.isDebugEnabled()) {
            log.debug("   == Nou estat per Destinatari " + firmaJPA.getDestinatariID());
        }

    }

    public void avisarUsuari(PeticioDeFirmaJPA peticioDeFirma, FirmaJPA firmaJPA, EstatDeFirmaJPA estatDeFirmaDest, FirmaEventList events) throws PeticioHaDeSerRebutjadaException, I18NException {
        if (firmaJPA.getUsuariExternEmail() != null) {
            avisarUsuariExtern(peticioDeFirma, firmaJPA);
        } else {
            events.requerit_per_firmar(peticioDeFirma, estatDeFirmaDest);
        }
    }

    private void avisarUsuariExtern(PeticioDeFirmaJPA peticioDeFirma, FirmaJPA firmaJPA) throws PeticioHaDeSerRebutjadaException, I18NException {
        switch (firmaJPA.getUsuariExternNivellSeguretat()) {

            case ConstantsV2.USUARIEXTERN_SECURITY_LEVEL_TOKEN:
                // Enviar-li correu amb TOKEN

                try {
                    doSendEmailToExternalUser(
                            peticioDeFirma.getUsuariAplicacio().getEntitatID(),
                            peticioDeFirma.getPeticioDeFirmaID(),
                            peticioDeFirma.getTitol(), firmaJPA);
                } catch (I18NException i18ne) {

                    Locale loc = new Locale("ca");
                    String msg = I18NCommonUtils.getMessage(i18ne, loc);

                    // XYZ ZZZ TRA
                    msg = "S'ha intentat enviar correu a " + firmaJPA.getUsuariExternEmail()
                            + " però ha fallat: " + msg;
                    log.error(msg, i18ne);
                    throw new PeticioHaDeSerRebutjadaException(msg);
                }

                break;

            case ConstantsV2.USUARIEXTERN_SECURITY_LEVEL_CERTIFICATE:
            case ConstantsV2.USUARIEXTERN_SECURITY_LEVEL_PASSWORD:
                // XYZ ZZZ XYZ
                throw new I18NException("genapp.comodi",
                        "Encara no es suporta el nivell de seguretat "
                                + firmaJPA.getUsuariExternNivellSeguretat());
            default:
                // XYZ ZZZ XYZ
                throw new I18NException("genapp.comodi", "Nivell de seguretat desconegut"
                        + firmaJPA.getUsuariExternNivellSeguretat());
        }
    }
}
