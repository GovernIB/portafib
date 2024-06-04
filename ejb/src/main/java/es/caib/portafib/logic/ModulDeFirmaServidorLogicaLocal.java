package es.caib.portafib.logic;

import javax.ejb.Local;

import org.fundaciobit.pluginsib.signatureserver.api.ISignatureServerPlugin;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface ModulDeFirmaServidorLogicaLocal extends
    AbstractPluginIBLogicaLocal<ISignatureServerPlugin> {

  String JNDI_NAME = "java:app/portafib-ejb/ModulDeFirmaServidorLogicaEJB";

}
