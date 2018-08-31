package es.caib.portafib.logic;

import javax.ejb.Local;

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

}
