package es.caib.portafib.logic.signatures;

import es.caib.portafib.model.entity.Fitxer;
import org.fundaciobit.genapp.common.i18n.I18NException;

import java.util.List;

public interface SignatureServiceLocal {

    String JNDI_NAME = "java:app/portafib-logic/SignatureServiceEJB";

    List<Signature> getSignatures(Fitxer fitxer) throws I18NException;

    SignatureValidation getSignaturesValidation(Fitxer fitxer, String entitat, String lang)
            throws I18NException;
}
