package es.caib.portafib.logic;

import java.io.File;
import java.io.InputStream;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.validatesignature.api.IValidateSignaturePlugin;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface ValidacioFirmesLogicaLocal extends
    AbstractPluginLogicaLocal<IValidateSignaturePlugin> {

  public static final String JNDI_NAME = "portafib/ValidacioFirmesLogicaEJB/local";
  
  
  public void validateSignature(String tipusFirmaNom, Long pluginValidateSignatureID,
      InputStream documentDetachedFile, File signatureFile, String languageUI)
      throws I18NException;

}
