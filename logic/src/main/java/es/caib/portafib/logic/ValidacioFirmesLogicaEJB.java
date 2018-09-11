package es.caib.portafib.logic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import es.caib.portafib.jpa.PluginJPA;
import es.caib.portafib.utils.ConstantsV2;

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

  @Override
  public int getTipusDePlugin() {
    return ConstantsV2.TIPUS_PLUGIN_VALIDACIOFIRMES;
  }

  @Override
  protected String getName() {
    return "Modul de Validació de Firmes";
  }

  // XYZ ZZZ Això s'ha de cridar des de passarel.la i api firma simple
 @Override
 public void validateSignature(String tipusFirmaNom, Long pluginValidateSignatureID,
     InputStream documentDetachedFile, File signatureFile, String languageUI)
     throws I18NException {
   
   
   log.info("\n\n XYZ ZZZ =======  ENTRA DINS VALIDACIO DE FIRMES (EJB)  ======= \n\n");
   
   
   final boolean debug = log.isDebugEnabled();
   if (debug) {
     log.debug("PLUGIN ID VALIDACIO FIRMES:  "  + pluginValidateSignatureID);
   }
   
   
   if (pluginValidateSignatureID != null) {
     IValidateSignaturePlugin validator = getInstanceByPluginID(pluginValidateSignatureID);

     ValidateSignatureRequest vsr = new ValidateSignatureRequest();
     vsr.setLanguage(languageUI); 
     SignatureRequestedInformation sri = new SignatureRequestedInformation();
     sri.setReturnSignatureTypeFormatProfile(true);
     vsr.setSignatureRequestedInformation(sri);
     try {
       vsr.setSignatureData(FileUtils.readFromFile(signatureFile));
     } catch (Exception e1) {
       // XYZ ZZZ Falta traduir missatge   
       String msg = "No s'ha pogut llegir el fitxer de Firma per la validació: " +e1.getMessage();
       log.error(msg, e1);
       throw new I18NException("genapp.comodi", msg);
     }
     if (documentDetachedFile == null) {
       vsr.setSignedDocumentData(null);
     } else {
       try {
         vsr.setSignedDocumentData(org.fundaciobit.pluginsib.core.utils.FileUtils.toByteArray(documentDetachedFile));
       } catch (IOException e1) {
         // XYZ ZZZ Falta traduir missatge
         String msg = "No s'ha pogut llegir el fitxer detached per la validació: " +e1.getMessage();
         log.error(msg, e1);
         throw new I18NException("genapp.comodi", msg);
       }
     }

     if (!validator.filter(vsr)) {
       // XYZ ZZZ Falta error missatge
       PluginJPA plugin = findByPrimaryKey(pluginValidateSignatureID);
       throw new I18NException("genapp.comodi", "El validador de firmes " 
         + plugin.getNom().getTraduccio(languageUI) + " no suporta validar fitxers del tipus " 
         + tipusFirmaNom + " o hi ha algun problema amb el validador: << XYZ ZZZ >> ");
     }
     ValidateSignatureResponse vsresp;
     try {
       vsresp =  validator.validateSignature(vsr);
       
       if (vsresp == null || vsresp.getValidationStatus() == null) {
         throw new Exception("La resposta del validador o el camp estat del validador valen null");
       }
       
       
     } catch(Exception e) {
       PluginJPA plugin = findByPrimaryKey(pluginValidateSignatureID);
       String msg = "Error no controlat cridant al validador de firmes " 
           + plugin.getNom().getTraduccio(languageUI) + ": " + e.getMessage();
       log.error(msg, e);
       // XYZ ZZZ Traduir
       throw new I18NException("genapp.comodi", msg);
     }

     int status = vsresp.getValidationStatus().getStatus();
     if (status != ValidationStatus.SIGNATURE_VALID) {

       String msg = "La firma no és vàlida. Raó: " +  vsresp.getValidationStatus().getErrorMsg();
       log.error(msg);
       // XYZ ZZZ Traduir
       throw new I18NException("genapp.comodi", msg);
     } else {
       
       if (debug) {
         log.debug("FIRMA VALIDADA CORRECTAMENT");
       }
     }
   }
 }
  

}
