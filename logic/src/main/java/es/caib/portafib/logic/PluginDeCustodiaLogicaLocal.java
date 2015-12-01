package es.caib.portafib.logic;

import javax.ejb.Local;

import org.fundaciobit.plugins.documentcustody.IDocumentCustodyPlugin;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface PluginDeCustodiaLogicaLocal extends
    AbstractPluginLogicaLocal<IDocumentCustodyPlugin> {

  public static final String JNDI_NAME = "portafib/PluginDeCustodiaLogicaEJB/local";

}
