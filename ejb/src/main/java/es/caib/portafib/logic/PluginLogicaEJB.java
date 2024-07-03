package es.caib.portafib.logic;

import es.caib.portafib.ejb.PluginEJB;
import es.caib.portafib.model.entity.Plugin;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.v3.IPluginIB;

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
public class PluginLogicaEJB extends PluginEJB implements PluginLogicaLocal {

    private static final Map<Long, IPluginIB> pluginsCache = new HashMap<Long, IPluginIB>();

    @EJB(mappedName = es.caib.portafib.ejb.TraduccioService.JNDI_NAME)
    protected es.caib.portafib.ejb.TraduccioService traduccioEjb;

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
            //IPlugin
            Object p = pluginsCache.remove(pluginID);
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

    /**
     * 
     * @param pluginID
     * @param pluginInstance
     */
    public void addPluginToCache(Long pluginID, IPluginIB pluginInstance) {
        synchronized (pluginsCache) {
            pluginsCache.put(pluginID, pluginInstance);
        }
    }

    /** TODO XYZ ZZZ Object =>  IPluginIB */
    public IPluginIB getPluginFromCache(Long pluginID) {
        synchronized (pluginsCache) {
            return pluginsCache.get(pluginID);
        }
    }

}
