package es.caib.portafib.logic.passarela;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import es.caib.portafib.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.model.entity.PerfilDeFirma;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSetWeb;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Local
public interface PassarelaDeFirmaWebLocal extends AbstractPassarelaDeFirmaLocal {

  String JNDI_NAME = "java:app/portafib-ejb/PassarelaDeFirmaWebEJB";

  public static final String PASSARELA_CONTEXTPATH = "/public/passarela";

  public static final String PASSARELA_CONTEXTPATH_FINAL = "/final";

  public Map<String, PassarelaSignaturesSetWebInternalUse> getAllTransactionsByEntitatID(
      String entitatID) throws I18NException;

  public String startTransaction(PassarelaSignaturesSet signaturesSet, String entitatID,
      boolean fullView, UsuariAplicacioJPA usuariAplicacio, PerfilDeFirma perfilDeFirma,
      Map<String, UsuariAplicacioConfiguracioJPA> configBySignID,
      Map<String, Long> tipusDocumentalBySignID, int origenPeticioDeFirma)
      throws I18NException, I18NValidationException;

  public PassarelaSignatureStatus getStatusTransaction(String transactionID)
      throws I18NException;

  public PassarelaSignaturesSetWebInternalUse getSignaturesSetFullByTransactionID(
      String transactionID) throws I18NException;

  public List<PassarelaSignatureResult> getSignatureResults(String transactionID,
      boolean addFiles) throws I18NException;

  public PassarelaSignatureResult getSignatureResult(String transactionID, String signID)
      throws I18NException;

  public File getFitxerOriginalPath(String transactionID, String signID);

  public File getFitxerFirmatPath(String transactionID, String signID);

  public PassarelaSignaturesSetWebInternalUse finalProcesDeFirma(String transactionID,
      SignaturesSetWeb ss, boolean administrationIdCanBeValidatedFromPlugin) throws I18NException, IOException;

  public void closeTransaction(String transactionID);

}
