package es.caib.portafib.back.utils;

import java.io.File;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.exportdata.IExportDataPlugin;
import org.fundaciobit.pluginsib.core.utils.PluginsManager;
import org.fundaciobit.genapp.common.crypt.AlgorithmEncrypter;
import org.fundaciobit.genapp.common.crypt.FileIDEncrypter;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.filesystem.IFileSystemManager;
import org.fundaciobit.genapp.common.filesystem.SimpleFileSystemManager;
import org.fundaciobit.genapp.common.web.exportdata.DataExporterManager;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import es.caib.portafib.hibernate.HibernateFileUtil;
import es.caib.portafib.logic.misc.AvisosFirmesPendentsTimerLocal;
import es.caib.portafib.logic.misc.EnviarCorreusAgrupatsTimerLocal;
import es.caib.portafib.logic.misc.NotificacionsCallBackTimerLocal;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.utils.Build;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;

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

    // Aturar Timer de NotificacionsWS a l'aturar Servidor
    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() {
        try {
          log.info(" \n\n HOOK SHUTDOWN BEGIN => NotificacionsCallBackTimerLocal \n");
          NotificacionsCallBackTimerLocal notifCallback;
          notifCallback = (NotificacionsCallBackTimerLocal) new InitialContext()
              .lookup(NotificacionsCallBackTimerLocal.JNDI_NAME);
          notifCallback.stopScheduler();
          log.info(" \n\n HOOK SHUTDOWN END => NotificacionsCallBackTimerLocal \n");

        } catch (Exception e) {
          // TODO: handle exception
          log.error(
              " \n\n HOOK SHUTDOWN NotificacionsCallBackTimerLocal ERROR: " + e.getMessage()
                  + " \n", e);
        }
      }
    });

    // Sistema de Fitxers
    try {
      // (1) Definir la ruta al directori
      {
        String filesPath = Configuracio.getFilesDirectory();
        if (filesPath == null || filesPath.trim().length() == 0) {
          throw new Exception("No s'ha definit la ruta dels fitxers."
              + " Comprovi que ha definit correctament la propietat "
              + "'es.caib.portafib.filesdirectory'");
        }

        FileSystemManager.setFilesPath(new File(filesPath));
        log.info("FileSystemManager path = "
            + FileSystemManager.getFilesPath().getAbsolutePath());
      }

      // (2) Gestor dels Fitxers guardats en el directori de fitxers
      {
        String classFSM = Configuracio.getFileSystemManagerClass();
        if (classFSM == null || classFSM.trim().length() == 0) {
          // throw new Exception("El valor associat a la propietat FileSystemManagerClass"
          // + " és null o està buida. Consulti el Manual de Instal·lació o de Migració.");
          classFSM = SimpleFileSystemManager.class.getName();
        }

        Class<?> clazz = Class.forName(classFSM);
        Object instancia = clazz.newInstance();

        FileSystemManager.setFileSystemManager((IFileSystemManager) instancia);

        log.info("FileSystemManager class = " + instancia.getClass().getName());
      }
      
      // (3) Revisa si el directori temporal i el directori de FileSystemManager
      // estan en la mateixa unitat
      testTmpAndFileSystemManager();

    } catch (Throwable th) {
      String msg = "\n\nError inicialitzant el sistema de sistema de fitxers: "
          + th.getMessage() + "\n\n";
      log.error(msg, th);

      new Thread() {
        public void run() {
          System.exit(-100);
        }
      }.start();
      throw new ServletException(msg, th);
    }

    // Sistema de Traduccions WEB
    try {
      ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
      String[] basenames = { "missatges", // /WEB-INF/classes/
          "logicmissatges", "genapp", "portafib_genapp" };
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
      String pkgsTxt = Configuracio.getExportDataPlugins();
      if (pkgsTxt == null) {
        log.warn("No s'ha definit la propietat " + ConstantsV2.PORTAFIB_PROPERTY_BASE
            + "exportdataplugins" + " !!!!!");
      } else {
        String[] plugins = pkgsTxt.replace(" ", "").split(",");

        if (plugins == null || plugins.length == 0) {
          log.warn("No existeixen Plugins de ExportData !!!!!");
        } else {

          for (String class1 : plugins) {
            IExportDataPlugin edp = (IExportDataPlugin) PluginsManager
                .instancePluginByClassName(class1);
            if (edp == null) {
              log.warn("No s'ha pogut instanciar Plugin associat a la classe " + class1);
            } else {
              log.warn("Registrant DataExporter: " + class1);
              DataExporterManager.addDataExporter(new DataExporterPortaFIB(edp));
            }
          }
        }

      }
    } catch (Throwable e) {
      log.error("Error inicialitzant els DataExporters: " + e.getMessage(), e);
    }

    // Enviar Notificacions en Correus Agrupats
    try {
      EnviarCorreusAgrupatsTimerLocal sinc;
      sinc = (EnviarCorreusAgrupatsTimerLocal) new InitialContext()
          .lookup(EnviarCorreusAgrupatsTimerLocal.JNDI_NAME);

      sinc.startScheduler();
    } catch (Throwable th) {
      log.error("Error desconegut inicialitzant Timer d'enviament"
          + " de notificacions agrupades : " + th.getMessage(), th);
    }

    // AvisosFirmesPendents
    try {
      AvisosFirmesPendentsTimerLocal sinc;
      sinc = (AvisosFirmesPendentsTimerLocal) new InitialContext()
          .lookup(AvisosFirmesPendentsTimerLocal.JNDI_NAME);
      sinc.startScheduler();
    } catch (Throwable th) {
      log.error("Error desconegut inicialitzant Timer d'enviament d'avisos"
          + " de peticions de firma pendents: " + th.getMessage(), th);
    }

    // NotificacionCallBack
    try {
      NotificacionsCallBackTimerLocal notifCallback;
      notifCallback = (NotificacionsCallBackTimerLocal) new InitialContext()
          .lookup(NotificacionsCallBackTimerLocal.JNDI_NAME);
      notifCallback.startScheduler();
    } catch (Throwable th) {
      log.error("Error desconegut inicialitzant Timer d'enviament de Notificacions Callback"
          + th.getMessage(), th);
      throw new ServletException(th);
    }

    // Mostrar Versió
    String ver = LogicUtils.getVersio();
    try {
      log.info("PortaFIB Version: " + ver + " Build:" + Build.BUILD);
    } catch (Throwable e) {
      System.out.println("PortaFIB Version: " + ver + " Build:" + Build.BUILD);
    }

  }
  
  
  /**
   * 
   * @throws Exception
   */
  public void testTmpAndFileSystemManager() throws Exception {

    log.info("Inici test sistema de fitxers");

    log.debug("System.getProperty('java.io.tmpdir') => "
        + System.getProperty("java.io.tmpdir"));

    // #275
    // Intentam crear i esborrar fitxer a directori temporal.
    java.io.File tmp = java.io.File.createTempFile("portafib", ".test");
    log.debug("File.createTempFile => " + tmp.getAbsolutePath());
    if (!tmp.delete()) {
      throw new Exception("Test de creació i eliminació de fitxer temporal ha fallat.");
    }

    // Intentam crear i esborrar fitxer a directori portafib
    tmp = java.io.File.createTempFile("portafib", ".test", FileSystemManager.getFilesPath());
    log.debug("File.createTempFile => " + tmp.getAbsolutePath());
    if (!tmp.delete()) {
      throw new Exception("Test de creació i eliminació de fitxer ha fallat.");
    }

    // Intentam fer un rename
    tmp = java.io.File.createTempFile("portafib", ".testrename");
    log.debug("File.createTempFile => " + tmp.getAbsolutePath());
    java.io.File filesPath = new java.io.File(FileSystemManager.getFilesPath(), tmp.getName());
    log.debug("FileSystemManager.getFilesPath() + tmp.getName() => " + filesPath.getAbsolutePath());

    // touch
    new java.io.FileOutputStream(tmp).close();

    if (!tmp.renameTo(filesPath)) {
      tmp.delete();
      throw new Exception("El sistema de fitxers temporal i "
          + "FileSystemManager.getFilesPath() apunten a unitats diferents "
          + "(o mounts diferents). Han d´estar en el mateix file system.");
    } else {
      filesPath.delete();
    }

    log.info("Test sistema de fitxers superat");
  }

}
