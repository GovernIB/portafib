package es.caib.portafib.logic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import es.caib.portafib.ejb.EntitatLocal;
import es.caib.portafib.jpa.PluginJPA;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.utils.ConstantsV2;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.validatesignature.api.IValidateSignaturePlugin;
import org.fundaciobit.plugins.validatesignature.api.SignatureRequestedInformation;
import org.fundaciobit.plugins.validatesignature.api.ValidateSignatureRequest;
import org.fundaciobit.plugins.validatesignature.api.ValidateSignatureResponse;
import org.fundaciobit.plugins.validatesignature.api.ValidationStatus;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author anadal
 */
@Stateless(name = "ValidacioFirmesLogicaEJB")
@SecurityDomain("seycon")
public class ValidacioFirmesLogicaEJB extends
    AbstractPluginLogicaEJB<IValidateSignaturePlugin> implements ValidacioFirmesLogicaLocal {

  @EJB(mappedName = EntitatLocal.JNDI_NAME)
  private EntitatLocal entitatEjb;

  @Override
  public int getTipusDePlugin() {
    return ConstantsV2.TIPUS_PLUGIN_VALIDACIOFIRMES;
  }

  @Override
  protected String getName() {
    return "Modul de Validació de Firmes";
  }

  @Override
  public ValidateSignatureResponse validateSignatureInServer(final String entitatID,
      final UsuariAplicacioConfiguracio usuariAplicacioConfig, final String signatureType,
      final byte[] signedFile, byte[] documentDetachedFile, final String languageUI)
      throws I18NException {

    // XYZ ZZZ Falta si validarFirma està a true en la configuració de l'UsrApp
    Boolean isValidarFirma = usuariAplicacioConfig.getValidarFirma();

    // No es requereix validació per part de la configuracio de UsuariApp
    if (isValidarFirma != null && isValidarFirma.booleanValue() == false) {
      return null;
    }

    final Long pluginValidateSignatureID = entitatEjb.executeQueryOne(
        EntitatFields.PLUGINVALIDAFIRMESID, EntitatFields.ENTITATID.equal(entitatID));

    if (isValidarFirma == null) {
      // El que digui la entitat: si està definit el plugin llavors
      // es valida, en cas contrari no es valida

      if (pluginValidateSignatureID == null) {
        log.info("XYZ ZZZ debug No s'ha definit plugin De validacio dins de l'entitat");
        return null;
      }

    } else {

      if (pluginValidateSignatureID == null && isValidarFirma.booleanValue() == true) {
        // Com que des de UsuariAplicació ens requereixen Signar, però l'entitat
        // no té definit el Plugin de Validació llavors llançam un error
        throw new I18NException("error.passarela.sensevalidadorfirmesdinsentitat");
        // status.setStatus(StatusSignaturesSet.STATUS_FINAL_ERROR);
        // status.setErrorMessage(I18NLogicUtils.tradueix(new Locale(languageUI),
        // "error.passarela.sensevalidadorfirmesdinsentitat"));
        // return;
      }
    }

    return this.internalValidateSignature(pluginValidateSignatureID, signatureType,
        signedFile, documentDetachedFile, languageUI);

  }

  @Override
  public ValidateSignatureResponse validateSignaturePassarela(String entitatID,
      UsuariAplicacioConfiguracio usuariAplicacioConfig, String signType, File signatureFile,
      InputStream documentDetachedIS, String languageUI) throws I18NException {

    byte[] documentDetached;
    if (documentDetachedIS == null) {
      documentDetached = null;
    } else {
      try {
        documentDetached = org.fundaciobit.pluginsib.core.utils.FileUtils
            .toByteArray(documentDetachedIS);
      } catch (IOException e1) {
        // XYZ ZZZ Falta traduir missatge
        String msg = "No s'ha pogut llegir el fitxer detached per la validació: "
            + e1.getMessage();
        log.error(msg, e1);
        throw new I18NException("genapp.comodi", msg);
      }
    }

    byte[] signature;
    try {
      signature = FileUtils.readFromFile(signatureFile);
    } catch (Exception e1) {
      // XYZ ZZZ Falta traduir missatge
      String msg = "No s'ha pogut llegir el fitxer de Firma per la validació: "
          + e1.getMessage();
      log.error(msg, e1);
      throw new I18NException("genapp.comodi", msg);
    }

    return validateSignatureInServer(entitatID, usuariAplicacioConfig, signType,
        signature, documentDetached, languageUI);
  }

  // XYZ ZZZ Això s'ha de cridar des de passarel.la i api firma simple
  @Override
  public ValidateSignatureResponse validateSignatureWeb(final String entitatID,
      String signType, File signatureFile, InputStream documentDetachedIS, String languageUI)
      throws I18NException {

    log.info("\n\n XYZ ZZZ =======  ENTRA DINS VALIDACIO DE FIRMES (EJB)  ======= \n\n");

    Long pluginValidateSignatureID = entitatEjb.executeQueryOne(
        EntitatFields.PLUGINVALIDAFIRMESID, EntitatFields.ENTITATID.equal(entitatID));

    if (pluginValidateSignatureID == null) {
      // No s'ha de validar
      return null;
    }

    byte[] documentDetached;
    if (documentDetachedIS == null) {
      documentDetached = null;
    } else {
      try {
        documentDetached = org.fundaciobit.pluginsib.core.utils.FileUtils
            .toByteArray(documentDetachedIS);
      } catch (IOException e1) {
        // XYZ ZZZ Falta traduir missatge
        String msg = "No s'ha pogut llegir el fitxer detached per la validació: "
            + e1.getMessage();
        log.error(msg, e1);
        throw new I18NException("genapp.comodi", msg);
      }
    }

    byte[] signature;
    try {
      signature = FileUtils.readFromFile(signatureFile);
    } catch (Exception e1) {
      // XYZ ZZZ Falta traduir missatge
      String msg = "No s'ha pogut llegir el fitxer de Firma per la validació: "
          + e1.getMessage();
      log.error(msg, e1);
      throw new I18NException("genapp.comodi", msg);
    }

    ValidateSignatureResponse vsresp = internalValidateSignature(pluginValidateSignatureID,
        signType, signature, documentDetached, languageUI);

    int status = vsresp.getValidationStatus().getStatus();
    if (status != ValidationStatus.SIGNATURE_VALID) {

      String msg = "La firma no és vàlida. Raó: " + vsresp.getValidationStatus().getErrorMsg();
      log.error(msg);
      // XYZ ZZZ Traduir
      throw new I18NException("genapp.comodi", msg);
    } else {

      if (log.isDebugEnabled()) {
        log.debug("FIRMA VALIDADA CORRECTAMENT");
      }
      return vsresp;
    }

  }

  /**
   * 
   * @param pluginValidateSignatureID
   * @param signType
   * @param signature
   * @param documentDetachedFile
   * @param languageUI
   * @return
   * @throws I18NException
   */
  protected ValidateSignatureResponse internalValidateSignature(
      Long pluginValidateSignatureID, String signType, byte[] signature,
      byte[] documentDetachedFile, String languageUI) throws I18NException {

    final boolean debug = log.isDebugEnabled();
    if (debug) {
      log.debug("PLUGIN ID VALIDACIO FIRMES:  " + pluginValidateSignatureID);
    }

    IValidateSignaturePlugin validator = getInstanceByPluginID(pluginValidateSignatureID);

    SignatureRequestedInformation sri = new SignatureRequestedInformation();
    sri.setReturnSignatureTypeFormatProfile(true);

    ValidateSignatureRequest vsr = new ValidateSignatureRequest();
    vsr.setLanguage(languageUI);
    vsr.setSignatureRequestedInformation(sri);
    vsr.setSignatureData(signature);
    vsr.setSignedDocumentData(documentDetachedFile);

    String error = validator.filter(vsr);
    if (error != null) {
      // XYZ ZZZ Falta Traduir missatge TODO
      PluginJPA plugin = findByPrimaryKey(pluginValidateSignatureID);
      throw new I18NException("genapp.comodi", "El validador de firmes "
          + plugin.getNom().getTraduccio(languageUI).getValor()
          + " no suporta validar fitxers del tipus " + signType
          + " o hi ha algun problema amb el validador: " + error);
    }
    ValidateSignatureResponse vsresp;
    try {
      vsresp = validator.validateSignature(vsr);

      if (vsresp == null || vsresp.getValidationStatus() == null) {
        // XYZ ZZZ TRA
        throw new Exception(
            "La resposta del validador o el camp estat del validador valen null");
      }

    } catch (Exception e) {
      PluginJPA plugin = findByPrimaryKey(pluginValidateSignatureID);
      String msg = "Error no controlat cridant al validador de firmes "
          + plugin.getNom().getTraduccio(languageUI).getValor() + ": " + e.getMessage();
      log.error(msg, e);
      // XYZ ZZZ Traduir
      throw new I18NException("genapp.comodi", msg);
    }
    return vsresp;
  }

}
