package es.caib.portafib.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import es.caib.portafib.logic.utils.ModulDeFirmaJPA;
import es.caib.portafib.utils.Constants;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;
import org.fundaciobit.plugins.utils.PluginsManager;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author anadal
 *
 */
@Stateless(name = "ModulDeFirmaLogicaEJB")
@SecurityDomain("seycon")
public class ModulDeFirmaLogicaEJB /* XYZ extends MetadadaEJB */  
  implements ModulDeFirmaLogicaLocal {

  // XYZ
  protected static Logger log = Logger.getLogger(ModulDeFirmaLogicaEJB.class);
  
  public static Map<Long, Properties> pluginsDB = new HashMap<Long, Properties>();
  
  public static Map<Long, ISignatureWebPlugin> pluginsCache = new HashMap<Long, ISignatureWebPlugin>();
  
  
  static  {
    
    // TODO XYZ Llegir de BBDD
    {
      Properties prop = new Properties();
      prop.setProperty(Constants.PORTAFIB_PROPERTY_BASE + "plugins.signatureweb.miniappletinserver.base_dir",
          FileSystemManager.getFilesPath().getAbsolutePath()); 
      
      
      
      prop.setProperty("class", "org.fundaciobit.plugins.signatureweb.miniappletinserver.MiniAppletInServerSignatureWebPlugin");
      
      // TODO XYZ Llegir de BBDD
      pluginsDB.put(555L, prop);
    }
    
    
    {
      Properties prop = new Properties();
      
      prop.setProperty("class", "org.fundaciobit.plugins.signatureweb.miniappletasapplet.MiniAppletAsAppletSignatureWebPlugin");
      
      // TODO XYZ Llegir de BBDD
      pluginsDB.put(666L, prop);
    }
    
    
    {
      Properties prop = new Properties();
      
      prop.setProperty("class", "org.fundaciobit.plugins.signatureweb.miniappletasapplet.MiniAppletAsJavaWebStartSignatureWebPlugin");
      
      // TODO XYZ Llegir de BBDD
      pluginsDB.put(999L, prop);
    }
    
    
    

    
  }
  
  
  public List<ModulDeFirmaJPA> getAllModulDeFirma(String entitat) throws I18NException {
    // TODO XYZ Llegir de BBDD
    List<ModulDeFirmaJPA> plugins = new ArrayList<ModulDeFirmaJPA>();
    
    log.info("XYZ getAllModulDeFirma::pluginsDB.size() = " + pluginsDB.size());
    
    for (Long id : pluginsDB.keySet()) {
      log.info("XYZ getAllModulDeFirma:: FOR ID = " + id);
      ISignatureWebPlugin plugin = getSignatureWebPluginByID(id);
      log.info("XYZ getAllModulDeFirma:: FOR PLUGIN = " + plugin);
      ModulDeFirmaJPA modul = new ModulDeFirmaJPA();
      String nom =  plugin.getName(new Locale("ca"));
      modul.setNom(nom);
      modul.setDescripcio("Una descripcio de Descripcio de " + nom );
      modul.setModulDeFirmaID(id);
      plugins.add(modul);
    } 
    
    return plugins;
    
    
  }
  
  
  public ISignatureWebPlugin getSignatureWebPluginByID(long signatureWebPluginID) throws I18NException {
  
    ISignatureWebPlugin plugin = pluginsCache.get(signatureWebPluginID);
    
    if (plugin == null) {

        Properties prop = pluginsDB.get(signatureWebPluginID);

        if (prop == null) {
          throw new I18NException("plugin.signatureweb.noexist", String.valueOf(signatureWebPluginID));
        }

        String classe = prop.getProperty("class");
        
        log.info("XYZ Classe = " + classe);

        Object pluginInstance = PluginsManager.instancePluginByClassName(
            classe, Constants.PORTAFIB_PROPERTY_BASE, prop);
        plugin = (ISignatureWebPlugin) pluginInstance;
        
        log.info("XYZ pluginInstance = " + pluginInstance);
        
        pluginsCache.put(signatureWebPluginID, plugin);
      
    }
    return plugin;

  }
  
  
  @Override
  public List<ISignatureWebPlugin> getSignatureWebPluginsByEntity(String entity) throws I18NException {
    
    List<ISignatureWebPlugin> plugins = new ArrayList<ISignatureWebPlugin>();
    
    for (Long id : pluginsDB.keySet()) {
      plugins.add(getSignatureWebPluginByID(id));
    } 
    
    return plugins;
    
  }

  
}
