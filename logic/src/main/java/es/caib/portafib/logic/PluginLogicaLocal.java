package es.caib.portafib.logic;

import javax.ejb.Local;

import es.caib.portafib.ejb.PluginLocal;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface PluginLogicaLocal extends PluginLocal {
  
  public static final String JNDI_NAME = "portafib/PluginLogicaEJB/local";

}
