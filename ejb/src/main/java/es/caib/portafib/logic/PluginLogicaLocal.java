package es.caib.portafib.logic;

import javax.ejb.Local;

import es.caib.portafib.ejb.PluginService;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface PluginLogicaLocal extends PluginService {

  String JNDI_NAME = "java:app/portafib-ejb/PluginLogicaEJB";
  
  public boolean deleteOfCache(Long pluginID);
  
  public void clearCache();

}
