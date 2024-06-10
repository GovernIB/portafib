package es.caib.portafib.logic;

import javax.ejb.Local;

import org.fundaciobit.pluginsib.documentcustody.api.IDocumentCustodyPlugin;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface PluginDeCustodiaLogicaLocal extends AbstractPluginIBLogicaLocal<IDocumentCustodyPlugin> {

    String JNDI_NAME = "java:app/portafib-ejb/PluginDeCustodiaLogicaEJB";

}
