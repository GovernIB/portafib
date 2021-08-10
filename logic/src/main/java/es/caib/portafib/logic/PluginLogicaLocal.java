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

  String JNDI_NAME = "java:app/portafib-logic/PluginLogicaEJB";
  
  public boolean deleteOfCache(Long pluginID);
  
  public void clearCache();

}
