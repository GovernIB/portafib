package es.caib.portafib.back.utils;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.exportdata.IExportDataPlugin;
import org.fundaciobit.plugins.utils.PluginsManager;
import org.fundaciobit.genapp.common.crypt.AlgorithmEncrypter;
import org.fundaciobit.genapp.common.crypt.FileIDEncrypter;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.web.exportdata.DataExporterManager;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import es.caib.portafib.hibernate.HibernateFileUtil;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.Constants;


/**
 * Servlet emprat per inicialitzar el Back
 * 
 * @author anadal
 * 
 */
@RunAs("PFI_USER")
@Component
public class InitServlet extends HttpServlet {

  protected final Logger log = Logger.getLogger(getClass());
  
 
  @EJB(mappedName = es.caib.portafib.ejb.IdiomaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaLocal idiomaEjb;
  

  @Override
  public void init(ServletConfig config) throws ServletException {
    
    // TODO pendent de Inicialitzar el sistema de info de IDIOMA
    try {
      log.info("IDIOMA EJB = " + idiomaEjb);
    } catch (Throwable th) {
      log.error("Error inicialitzant el sistema de sistema de fitxers: " + th.getMessage(), th);
    }

    // Sistema de Fitxers
    // TODO Moure a logic
    try {      
      FileSystemManager.setFilesPath(Configuracio.getFilesDirectory());
      log.info("FileSystemManager path = " + FileSystemManager.getFilesPath().getAbsolutePath());
    } catch (Throwable th) {
      log.error("Error inicialitzant el sistema de sistema de fitxers: " + th.getMessage(), th);
    }

    // Sistema de Traduccions WEB
    try {
      ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
      String[] basenames = { 
          "missatges", // /WEB-INF/classes/
          "logicmissatges",
          "genapp",
          "portafib_genapp"};
      ms.setDefaultEncoding("UTF-8");
      ms.setBasenames(basenames);
      I18NUtils.setMessageSource(ms);
    } catch (Throwable th) {
      log.error("Error inicialitzant el sistema de traduccions web: " + th.getMessage(), th);
    }

    // Sistema de Traduccions LOGIC
    // TODO Moure a logic
    try {
      Class.forName(I18NLogicUtils.class.getName());
    } catch (Throwable th) {
      log.error("Error inicialitzant el sistema de traduccions logic: " + th.getMessage(), th);
    }

    // Encriptador d'identificador de Fitxer
    try {
      FileIDEncrypter encrypter = new FileIDEncrypter(Configuracio.getEncryptKey(),
          AlgorithmEncrypter.ALGORITHM_AES);
      HibernateFileUtil.setEncrypter(encrypter);
    } catch (Exception e) {
      log.error("Error instanciant File Encrypter: " + e.getMessage(), e);
    }

    // Inicialitzar els DataExporters
    try {
      String pkgsTxt =  Configuracio.getExportDataPlugins();
      if (pkgsTxt == null) {
        log.warn("No s'ha definit la propietat " + Constants.PORTAFIB_PROPERTY_BASE + "exportdataplugins" + " !!!!!");
      } else {
        String[] plugins = pkgsTxt.replace(" ", "").split(",");
        
        if (plugins == null || plugins.length == 0) {
          log.warn("No existeixen Plugins de ExportData !!!!!");
       } else {
       
         for (String class1 : plugins) {
           IExportDataPlugin edp = (IExportDataPlugin)PluginsManager.instancePluginByClassName(class1);
           if (edp == null) {
             log.warn("No s'ha pogut instanciar Plugin associat a la classe " + class1);
           } else {
             log.warn("Registrant DataExporter: " + class1);
             DataExporterManager.addDataExporter(new DataExporterPortaFIB(edp));
           } 
         }
       }
        
      }
    } catch(Throwable e) {
      log.error("Error inicialitzant els DataExporters: " + e.getMessage(), e);
    }

    // Mostrar Versió
    String ver = LogicUtils.getVersio();
    try {
      log.info("PortaFIB Version: " + ver);
    } catch (Throwable e) {
      System.out.println("PortaFIB Version: " + ver);
    }

  }

}
