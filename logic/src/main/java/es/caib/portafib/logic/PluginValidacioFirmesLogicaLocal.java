package es.caib.portafib.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.validatesignature.api.IValidateSignaturePlugin;
import org.fundaciobit.plugins.validatesignature.api.ValidateSignatureResponse;

import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Local
public interface PluginValidacioFirmesLogicaLocal extends
    AbstractPluginLogicaLocal<IValidateSignaturePlugin> {

  String JNDI_NAME = "java:app/portafib-logic/PluginValidacioFirmesLogicaEJB";

  public ValidateSignatureResponse validateSignature(final String entitatID, String signType,
      IPortaFIBDataSource signature, IPortaFIBDataSource documentDetached, String languageUI)
      throws ValidacioException;

}
