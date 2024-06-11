package es.caib.portafib.logic.utils;

import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.core.v3.utils.PluginsManager;
import org.fundaciobit.pluginsib.documentconverter.IDocumentConverterPlugin;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 * 
 */
public class PortaFIBPluginsManager implements ConstantsV2 {

    protected static Logger log = Logger.getLogger(PortaFIBPluginsManager.class);

    public static final String LOGIN_PLUGIN_KEY = PORTAFIB_PROPERTY_BASE + "userinformationplugin";

    public static final String DOCUMENTCONVERTER_PLUGIN_KEY = PORTAFIB_PROPERTY_BASE + "documentconverterplugin";

    public static IUserInformationPlugin loginPlugin = null;

    public static IDocumentConverterPlugin documentConverterPlugin = null;

    /**
     * 
     * @return null si no existeix 
     * @throws Exception
     */
    public static IDocumentConverterPlugin getDocumentConverterPluginInstance() {

        if (documentConverterPlugin == null) {
            final String propertyPlugin = DOCUMENTCONVERTER_PLUGIN_KEY;
            Object pluginInstance = PluginsManager.instancePluginByProperty(propertyPlugin,
                    ConstantsV2.PORTAFIB_PROPERTY_BASE, Configuracio.getPortaFIBProperties());
            documentConverterPlugin = (IDocumentConverterPlugin) pluginInstance;
        }
        return documentConverterPlugin;
    }

    public static IUserInformationPlugin getUserInformationPluginInstance() throws I18NException {
        if (loginPlugin == null) {
            final String propertyPlugin = LOGIN_PLUGIN_KEY;

            Object pluginInstance = PluginsManager.instancePluginByProperty(
                    propertyPlugin, ConstantsV2.PORTAFIB_PROPERTY_BASE, Configuracio.getPortaFIBSystemProperties());
            if (pluginInstance == null) {
                throw new I18NException("plugin.donotinstantiateplugin", new I18NArgumentCode("plugin.userinfo"));
            }
            loginPlugin = (IUserInformationPlugin) pluginInstance;
        }
        return loginPlugin;
    }

}
