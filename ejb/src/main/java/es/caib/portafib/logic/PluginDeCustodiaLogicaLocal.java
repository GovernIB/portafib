package es.caib.portafib.logic;

import javax.ejb.Local;

import org.fundaciobit.plugins.documentcustody.api.IDocumentCustodyPlugin;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface PluginDeCustodiaLogicaLocal extends
    AbstractPluginLogicaLocal<IDocumentCustodyPlugin> {

  String JNDI_NAME = "java:app/portafib-ejb/PluginDeCustodiaLogicaEJB";

}
