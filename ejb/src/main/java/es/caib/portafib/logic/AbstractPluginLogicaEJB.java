package es.caib.portafib.logic;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import es.caib.portafib.persistence.PluginJPA;
import es.caib.portafib.model.entity.Plugin;
import es.caib.portafib.utils.ConstantsV2;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.core.IPlugin;
import org.fundaciobit.pluginsib.core.utils.PluginsManager;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

/**
 *
 * @author anadal
 *
 */
public abstract class AbstractPluginLogicaEJB<I extends IPlugin> extends PluginLogicaEJB
        implements AbstractPluginLogicaLocal<I> {

    protected abstract int getTipusDePlugin();

    protected abstract String getName();

    @Override
    public List<Plugin> getAllPlugins(String entitatID) throws I18NException {
        Where where = getWhere(entitatID);
        return select(where);
    }

    @Override
    public List<Plugin> getAllPluginsSenseEntitat() throws I18NException {
        Where where = Where.AND(
                TIPUS.equal(getTipusDePlugin()),
                ACTIU.equal(true),
                ENTITATID.isNull());
        return select(where);
    }

    public Where getWhere(String entitatID) {
        return Where.AND(
                TIPUS.equal(getTipusDePlugin()),
                ACTIU.equal(true),
                ENTITATID.equal(entitatID)
        // TODO Elegim plugin entre les genèriques o entre els específics per l'entitat
        // Where.OR(ENTITATID.isNull(), ENTITATID.equal(entitatID))
        );
    }

    @SuppressWarnings("unchecked")
    @Override
    public I getInstanceByPluginID(long pluginID) throws I18NException {

        IPlugin pluginInstance = getPluginFromCache(pluginID);

        if (pluginInstance == null) {

            PluginJPA plugin = (PluginJPA) findByPrimaryKey(pluginID);

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

            try {
                StringWriter writer = new StringWriter();
                prop.store(writer, "");

                String propietats = writer.getBuffer().toString().replace("[\\=", "[=");

                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("SP", System.getProperties());

                String t = TemplateEngine.processExpressionLanguageSquareBrackets(propietats, parameters);

                prop.load(new ByteArrayInputStream(t.getBytes(ConstantsV2.UTF_8)));

            } catch (IOException ex) {
                log.error("Error substituint propietats. Revisi la configuració del plugin " + pluginID, ex);
            }

            pluginInstance = (IPlugin) PluginsManager.instancePluginByClassName(plugin.getClasse(),
                    ConstantsV2.PORTAFIB_PROPERTY_BASE, prop);

            if (pluginInstance == null) {
                throw new I18NException("plugin.donotinstantiate", getName() + " ("
                        + plugin.getClasse() + ")");
            }

            addPluginToCache(pluginID, pluginInstance);

        }
        return (I) pluginInstance;

    }

    @Override
    public List<I> getPluginInstancesByEntitatID(String entitatID) throws I18NException {

        return getPluginInstancesBy(entitatID, null, null);

    }

    @Override
    public List<I> getPluginInstancesBy(String entitatID, List<Long> filterByPluginID,
            List<String> filterByPluginCode) throws I18NException {

        List<I> plugins = new ArrayList<I>();

        Where where = Where.AND(
                TIPUS.equal(getTipusDePlugin()),
                ACTIU.equal(true),
                ENTITATID.equal(entitatID)
        );

        if (filterByPluginID != null && filterByPluginID.size() != 0) {
            where = Where.AND(where, PLUGINID.in(filterByPluginID));
        }

        // TODO XYZ pendent afegir camp codi dins plugin
        //    if (filterByPluginCode != null && filterByPluginCode.size() != 0) {
        //      where = Where.AND(where, CODI.in(filterByPluginID));
        //    }
        List<Plugin> modulsdefirma = select(where);

        for (Plugin mf : modulsdefirma) {
            plugins.add(getInstanceByPluginID(mf.getPluginID()));
        }

        return plugins;

    }

}
