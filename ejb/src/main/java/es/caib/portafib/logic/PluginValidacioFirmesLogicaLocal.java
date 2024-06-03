package es.caib.portafib.logic;

import javax.ejb.Local;

import org.fundaciobit.pluginsib.validatesignature.api.IValidateSignaturePlugin;
import org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse;

import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Local
public interface PluginValidacioFirmesLogicaLocal extends
    AbstractPluginIBLogicaLocal<IValidateSignaturePlugin> {

  String JNDI_NAME = "java:app/portafib-ejb/PluginValidacioFirmesLogicaEJB";

  public ValidateSignatureResponse validateSignature(final String entitatID, String signType,
      IPortaFIBDataSource signature, IPortaFIBDataSource documentDetached, String languageUI)
      throws ValidacioException;

}
