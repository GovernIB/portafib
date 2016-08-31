package es.caib.portafib.logic;

import javax.ejb.Local;

import org.fundaciobit.plugins.signatureserver.api.ISignatureServerPlugin;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface ModulDeFirmaServidorLogicaLocal extends
    AbstractPluginLogicaLocal<ISignatureServerPlugin> {

  public static final String JNDI_NAME = "portafib/ModulDeFirmaServidorLogicaEJB/local";

}
