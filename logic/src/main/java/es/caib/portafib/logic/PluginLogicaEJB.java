package es.caib.portafib.logic;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import es.caib.portafib.ejb.PluginEJB;
import es.caib.portafib.jpa.PluginJPA;
import es.caib.portafib.model.entity.Plugin;
import es.caib.portafib.utils.Constants;

import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;
import org.fundaciobit.plugins.utils.PluginsManager;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author anadal
 *
 */
@Stateless(name = "PluginLogicaEJB")
@SecurityDomain("seycon")
public class PluginLogicaEJB  extends PluginEJB implements PluginLogicaLocal {

 
  
  public static Map<Long, ISignatureWebPlugin> pluginsCache = new HashMap<Long, ISignatureWebPlugin>();
  
  /*
 // XYZ
  
  public static Map<Long, Properties> pluginsDB = new HashMap<Long, Properties>();
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
      
      prop.setProperty("class", "org.fundaciobit.plugins.signatureweb.miniappletinclient.MiniAppletAsAppletSignatureWebPlugin");
      
      // TODO XYZ Llegir de BBDD
      pluginsDB.put(666L, prop);
    }
    
    
    {
      Properties prop = new Properties();
      
      prop.setProperty("class", "org.fundaciobit.plugins.signatureweb.miniappletinclient.MiniAppletAsJavaWebStartSignatureWebPlugin");
      
      // TODO XYZ Llegir de BBDD
      pluginsDB.put(999L, prop);
    }
    
    
    

    
  }
  */
  
  
  
  
  
  
  public List<Plugin> getAllModulDeFirma(String entitatID) throws I18NException {
    
    Where where = Where.AND(
        TIPUS.equal(Constants.TIPUS_PLUGIN_MODULDEFIRMA),
        ACTIU.equal(true),
        ENTITATID.equal(entitatID)
    );
    
//    ModulDeFirmaJPA modul;
//    modul.getModulDeFirmaID();
//    modul.getNom().getTraduccions()
    
    
    // TODO Controlar que no hi hagi cap modul de firma.
    return select(where);
    
    
    /*
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
    
    */
  }
  
  
  public ISignatureWebPlugin getSignatureWebPluginByModulDeFirmaID(long modulDeFirmaID) throws I18NException {
  
    ISignatureWebPlugin plugin = pluginsCache.get(modulDeFirmaID);
    
    if (plugin == null) {

        PluginJPA modulDeFirma = (PluginJPA)findByPrimaryKey(modulDeFirmaID);
        
       
        // TODO 
      
        if (modulDeFirma == null) {
          throw new I18NException("plugin.signatureweb.noexist", String.valueOf(modulDeFirmaID));
        }
        
        Properties prop = new Properties();
        
        try {
          if (modulDeFirma.getPropertiesAdmin() != null && modulDeFirma.getPropertiesAdmin().trim().length() != 0) {
            prop.load(new StringReader(modulDeFirma.getPropertiesAdmin()));
          }
        } catch (Exception e) {
          // TODO: handle exception
        }
        
        try {
          if (modulDeFirma.getPropertiesEntitat() != null && modulDeFirma.getPropertiesEntitat().trim().length() != 0) {
            prop.load(new StringReader(modulDeFirma.getPropertiesEntitat()));
          }          
        } catch (Exception e) {
          // TODO: handle exception
        }

        plugin = instantiateSignatureWebPlugin(prop, modulDeFirma.getClasse());
        
        pluginsCache.put(modulDeFirmaID, plugin);
      
    }
    return plugin;

  }


  private ISignatureWebPlugin instantiateSignatureWebPlugin(Properties prop, String classe) {
    ISignatureWebPlugin plugin;
    log.info("XYZ Classe = " + classe);

    Object pluginInstance = PluginsManager.instancePluginByClassName(
        classe, Constants.PORTAFIB_PROPERTY_BASE, prop);
    plugin = (ISignatureWebPlugin) pluginInstance;
    
    log.info("XYZ pluginInstance = " + pluginInstance);
    return plugin;
  }
  
  
  @Override
  public List<ISignatureWebPlugin> getSignatureWebPluginsByEntitatID(String entitatID) throws I18NException {
    
    
    List<ISignatureWebPlugin> plugins = new ArrayList<ISignatureWebPlugin>();
    
    Where where = Where.AND(
        TIPUS.equal(Constants.TIPUS_PLUGIN_MODULDEFIRMA),
        ACTIU.equal(true),
        ENTITATID.equal(entitatID)
    );
    
//    ModulDeFirmaJPA modul;
//    modul.getModulDeFirmaID();
//    modul.getNom().getTraduccions()
    
    
    // TODO Controlar que no hi hagi cap modul de firma.
    List<Plugin> modulsdefirma = select(where);
    
    
    for (Plugin mf : modulsdefirma) {
      plugins.add(getSignatureWebPluginByModulDeFirmaID(mf.getPluginID()));
    } 
    
    return plugins;
    
  }

  
}
