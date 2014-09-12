package es.caib.portafib.logic.utils;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.certificate.ICertificatePlugin;
import org.fundaciobit.plugins.documentconverter.IDocumentConverterPlugin;
import org.fundaciobit.plugins.documentcustody.IDocumentCustodyPlugin;
import org.fundaciobit.plugins.userinformation.IUserInformationPlugin;
import org.fundaciobit.plugins.utils.PluginsManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 * 
 */
public class PortaFIBPluginsManager implements Constants {

  protected static Logger log = Logger.getLogger(PortaFIBPluginsManager.class);

  public static final String LOGIN_PLUGIN_KEY = PORTAFIB_PROPERTY_BASE + "userinformationplugin";

  public static final String CERTIFICATE_PLUGIN_KEY = PORTAFIB_PROPERTY_BASE + "certificateplugin";

  public static final String DOCUMENTCONVERTER_PLUGIN_KEY = PORTAFIB_PROPERTY_BASE + "documentconverterplugin";

  public static final String DOCUMENTCUSTODY_PLUGIN_KEY = PORTAFIB_PROPERTY_BASE + "documentcustodyplugin";

  public static IUserInformationPlugin loginPlugin = null;

  public static ICertificatePlugin certificatePlugin = null;
  
  public static IDocumentConverterPlugin documentConverterPlugin = null;
  
  public static IDocumentCustodyPlugin documentCustodyPlugin = null;
  
  /**
   * 
   * @return null si no existeix 
   * @throws Exception
   */
  public static IDocumentConverterPlugin getDocumentConverterPluginInstance() {

    if (documentConverterPlugin == null) {
      final String propertyPlugin = DOCUMENTCONVERTER_PLUGIN_KEY;
      Object pluginInstance = PluginsManager.instancePluginByProperty(propertyPlugin, Constants.PORTAFIB_PROPERTY_BASE);
      documentConverterPlugin = (IDocumentConverterPlugin) pluginInstance;
    }
    return documentConverterPlugin;
  }

  public static IUserInformationPlugin getUserInformationPluginInstance() throws I18NException {
    if (loginPlugin == null) {
      final String propertyPlugin = LOGIN_PLUGIN_KEY;
      Object pluginInstance = PluginsManager.instancePluginByProperty(propertyPlugin, Constants.PORTAFIB_PROPERTY_BASE);
      if (pluginInstance == null) {
        throw new I18NException("plugin.donotinstantiateplugin", new I18NArgumentCode("plugin.userinfo"));
      }
      loginPlugin = (IUserInformationPlugin) pluginInstance;
    }
    return loginPlugin;
  }

  public static ICertificatePlugin getCertificatePluginInstance() throws I18NException {

    if (certificatePlugin == null) {
      final String propertyPlugin = CERTIFICATE_PLUGIN_KEY;
      Object pluginInstance = PluginsManager.instancePluginByProperty(propertyPlugin, Constants.PORTAFIB_PROPERTY_BASE);
      if (pluginInstance == null) {
        throw new I18NException("plugin.donotinstantiateplugin", new I18NArgumentCode("plugin.certificat"));
      }
      certificatePlugin = (ICertificatePlugin) pluginInstance;
    }
    return certificatePlugin;
  }

  
  public static IDocumentCustodyPlugin getDocumentCustodyPluginInstance() {

    if (documentCustodyPlugin == null) {
      final String propertyPlugin = DOCUMENTCUSTODY_PLUGIN_KEY;
      Object pluginInstance = PluginsManager.instancePluginByProperty(propertyPlugin, Constants.PORTAFIB_PROPERTY_BASE);
      documentCustodyPlugin = (IDocumentCustodyPlugin) pluginInstance;
    }
    return documentCustodyPlugin;
  }
  
  
  
  public static IDocumentCustodyPlugin getDocumentCustodyPluginByClassName(String className) {

    if (documentCustodyPlugin == null) {
      Object pluginInstance = PluginsManager.instancePluginByClassName(className, Constants.PORTAFIB_PROPERTY_BASE);
      documentCustodyPlugin = (IDocumentCustodyPlugin) pluginInstance;
    }
    return documentCustodyPlugin;
  }
  
  
/*
  
  public static Object instancePluginByProperty(String propertyPlugin) {
    // Valor de la Clau
    String className = System.getProperty(propertyPlugin);
    if (className == null || className.trim().length() <= 0) {
      return null;
    }
    return instancePluginByClassName(className);
  }
  

  public static Object instancePluginByClassName(String className) {
    // Carregant la classe
    log.info("Carregant classe " + className + " ...");
    Class<?> c;
    try {
      c = Class.forName(className);
    } catch (Exception ex) {
      final String msg = "Error carregant la classe " + className 
        + " associada a un plugin:" + ex.getMessage();
      log.error(msg, ex);
      return null;
    }
    // Instanciant la classe
    Object pluginInstance;
    try {
      pluginInstance = c.newInstance();
    } catch (Exception e) {
      final String msg = "Error instanciant la classe " + c.getName() 
        + " associada a un plugin:" + e.getMessage();
      log.error(msg, e);
      return null;
    }
    return pluginInstance;
  }
*/
}
