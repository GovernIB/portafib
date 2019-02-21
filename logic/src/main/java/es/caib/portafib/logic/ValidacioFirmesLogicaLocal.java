package es.caib.portafib.logic;

import java.io.File;
import java.io.InputStream;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.validatesignature.api.IValidateSignaturePlugin;
import org.fundaciobit.plugins.validatesignature.api.ValidateSignatureResponse;

import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Local
public interface ValidacioFirmesLogicaLocal extends
    AbstractPluginLogicaLocal<IValidateSignaturePlugin> {

  public static final String JNDI_NAME = "portafib/ValidacioFirmesLogicaEJB/local";

  public ValidateSignatureResponse validateSignatureWeb(final String entitatID,
      String signType, File signatureFile, InputStream documentDetachedIS, 
      String languageUI) throws I18NException;

  public ValidateSignatureResponse validateSignatureUpgradeSignature(final String entitatID, 
      final UsuariAplicacioConfiguracio usuariAplicacioConfig,
      final String signatureType, final byte[] signedFile, byte[] documentDetachedFile,
      final String languageUI) throws I18NException;

  
  public ValidateSignatureResponse validateSignaturePassarela(final String entitatID,
      final UsuariAplicacioConfiguracio usuariAplicacioConfig,
      String signType, File signatureFile, InputStream documentDetachedIS, 
      String languageUI) throws I18NException;
  
  
  
}
