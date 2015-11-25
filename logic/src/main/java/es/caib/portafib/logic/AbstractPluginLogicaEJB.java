package es.caib.portafib.logic;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import es.caib.portafib.jpa.PluginJPA;
import es.caib.portafib.model.entity.Plugin;
import es.caib.portafib.utils.Constants;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.plugins.IPlugin;
import org.fundaciobit.plugins.utils.PluginsManager;

/**
 *
 * @author anadal
 *
 */

public abstract class AbstractPluginLogicaEJB<I extends IPlugin>  extends PluginLogicaEJB
   implements AbstractPluginLogicaLocal<I> {

  protected abstract int getTipusDePlugin();
  
  
  @Override
  public List<Plugin> getAllPlugins(String entitatID) throws I18NException {
    
    Where where = Where.AND(
        TIPUS.equal(getTipusDePlugin()),
        ACTIU.equal(true),
        ENTITATID.equal(entitatID)
    );

    return select(where);

  }
  
  @Override
  public I getInstanceByPluginID(long pluginID) throws I18NException {
  
    IPlugin pluginInstance = pluginsCache.get(pluginID);
    
    if (pluginInstance == null) {

        PluginJPA plugin = (PluginJPA)findByPrimaryKey(pluginID);

        if (plugin == null) {
          return null;
        }
        
        Properties prop = new Properties();
        
      if (plugin.getPropertiesAdmin() != null
          && plugin.getPropertiesAdmin().trim().length() != 0) {
        try {

          prop.load(new StringReader(plugin.getPropertiesAdmin()));

        } catch (Exception e) {
          // TODO Crec que no es cridarà mai
        }
      }

      if (plugin.getPropertiesEntitat() != null
          && plugin.getPropertiesEntitat().trim().length() != 0) {
        try {

          prop.load(new StringReader(plugin.getPropertiesEntitat()));

        } catch (Exception e) {
          // TODO Crec que no es cridarà mai
        }
      } 
        pluginInstance = (IPlugin) PluginsManager.instancePluginByClassName(
            plugin.getClasse(), Constants.PORTAFIB_PROPERTY_BASE, prop);
        
        pluginsCache.put(pluginID, pluginInstance);
      
    }
    return (I)pluginInstance;

  }

  
  @Override
  public List<I> getPluginInstancesByEntitatID(String entitatID) throws I18NException {
    
    
    List<I> plugins = new ArrayList<I>();
    
    Where where = Where.AND(
        TIPUS.equal(getTipusDePlugin()),
        ACTIU.equal(true),
        ENTITATID.equal(entitatID)
    );
    
    List<Plugin> modulsdefirma = select(where);
    
    
    for (Plugin mf : modulsdefirma) {
      plugins.add(getInstanceByPluginID(mf.getPluginID()));
    } 
    
    return plugins;

  }

  

  
  
}
