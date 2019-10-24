package es.caib.portafib.logic;


import es.caib.portafib.ejb.PluginEJB;
import es.caib.portafib.model.entity.Plugin;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.IPlugin;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author anadal
 *
 */
@Stateless(name = "PluginLogicaEJB")
@SecurityDomain("seycon")
public class PluginLogicaEJB extends PluginEJB implements PluginLogicaLocal {

  private static final Map<Long, IPlugin> pluginsCache = new HashMap<Long, IPlugin>();

  @EJB(mappedName = es.caib.portafib.ejb.TraduccioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TraduccioLocal traduccioEjb;
  
  @Override
  public Plugin update(Plugin instance) throws I18NException {
    if (instance != null) {
      deleteOfCache(instance.getPluginID());
    }
    return super.update(instance);
  }

  
  @Override
  public boolean deleteOfCache(Long pluginID) {
    synchronized (pluginsCache) {
      IPlugin p = pluginsCache.remove(pluginID);
      return p != null;
    }
  }
  
  
  @Override
  public void delete(Plugin instance) {
    if (instance != null) {
      deleteOfCache(instance.getPluginID());

      // NOTA: Les traduccions s'esborren automàticament
      super.delete(instance);
    }
  }
  
  @Override
  public void clearCache() {
    synchronized (pluginsCache) {
      pluginsCache.clear();
    }
  }
  
  
  public void addPluginToCache(Long pluginID, IPlugin pluginInstance) { 
    synchronized (pluginsCache) {
      pluginsCache.put(pluginID, pluginInstance);  
    }
  }
  
  public IPlugin getPluginFromCache(Long pluginID) {
    synchronized (pluginsCache) {
      return  pluginsCache.get(pluginID);  
    }
  }
  
}
