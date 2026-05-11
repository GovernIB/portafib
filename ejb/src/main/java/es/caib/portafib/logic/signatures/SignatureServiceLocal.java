package es.caib.portafib.logic.signatures;

import es.caib.portafib.logic.PluginValidacioFirmesLogicaEJB.GrupEstadisticaValidacio;
import es.caib.portafib.model.entity.Fitxer;
import org.fundaciobit.genapp.common.i18n.I18NException;

import java.util.List;

/**
 * Servei per a l'extracció de les signatures d'un fitxer i la seva validació
 * @author anadal (u80067)
 * 7 may 2026 9:35:37
 */
public interface SignatureServiceLocal {

    String JNDI_NAME = "java:app/portafib-ejb/SignatureServiceEJB";

    List<Signature> getSignatures(Fitxer fitxer) throws I18NException;

    SignatureValidation getSignaturesValidation(Fitxer fitxer, String entitat, final String usuariAplicacioID,
            final GrupEstadisticaValidacio grupEstadistica, String lang) throws I18NException;
}
