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

  String JNDI_NAME = "java:app/portafib-ejb/ModulDeFirmaServidorLogicaEJB";

}
