package es.caib.portafib.utils;

import java.io.File;

/**
 * 
 * @author anadal
 * 
 */
public class Configuracio implements Constants {
  


  public static boolean isCAIB() {
    return Boolean.getBoolean(PORTAFIB_PROPERTY_BASE + "iscaib");
  }

  public static File getFilesDirectory() {
    String path = System.getProperty(PORTAFIB_PROPERTY_BASE + "filesdirectory");
    return new File(path);
  }
  
  public static boolean isDesenvolupament() {
    return Boolean.getBoolean(PORTAFIB_PROPERTY_BASE + "development");
  }

  public static boolean isEditableUser() {
    return Boolean.getBoolean(PORTAFIB_PROPERTY_BASE + "editableuser");
  }

  public static String getAppUrl() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "url");
  }

  public static String getAppEmail() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "email.from");
  }

  public static String getAppName() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "name", "PortaFIB");
  }

  public static String getAppletSignerClass() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE +  "applet.signerClass");
  }

  public static String getDefaultLanguage() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "defaultlanguage", "ca");
  }
  
  public static String getDefaultEntity() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "defaultentity");
  }
  
  public static String getDefaultRolesInCreation() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "defaultrolesincreation");
  }
  

  public static Integer getDefaultSignAlgorithmID() {
    return Integer.getInteger(PORTAFIB_PROPERTY_BASE + "defaultsignalgorithmid",
        // TODO La documentació de miniapplet  diu que aquest algoritme 
        // esta obsolet però és l'únic que suporta Custòdia-CAIB
        Constants.APPLET_SIGN_ALGORITHM_SHA1WITHRSA);
  }

  public static boolean isCheckNifCertificate() {
    return Boolean.getBoolean(PORTAFIB_PROPERTY_BASE + "checknifcertificate");
  }

  public static byte[] getEncryptKey() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "encryptkey", "portafibportafib").getBytes();
  }

  public static Long getMaxUploadSizeInBytes() {
    return Long.getLong(PORTAFIB_PROPERTY_BASE + "maxuploadsizeinbytes");
  }

  public static Long getMaxFitxerAdaptatSizeInBytes() {
    return Long.getLong(PORTAFIB_PROPERTY_BASE + "maxfitxeradaptatsizeinbytes");
  }
  
  public static String getExportDataPlugins() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "exportdataplugins", null);
  }
  
  /**
   *  A partir de quants d'errors en una notificació callback s'enviarà 
   *  un correu al responsable de l'usuari aplicació
   * @return null si no està definit
   */
  public static Long getNumberOfErrorsInNotificationToSendMail() {
    return Long.getLong(PORTAFIB_PROPERTY_BASE + "numberoferrorsinnotificationtosendmail");
  }

  /**
   *  A partir de quants d'errors en una notificació callback 
   *  aquesta automàticament es pausarà
   * @return null si no està definit
   */
  public static Long getNumberOfErrorsToPauseNotification() {
    return Long.getLong(PORTAFIB_PROPERTY_BASE + "numberoferrorstopausenotification");
  }
  
  /**
   * Si el valor és true llavors redireccióna segons el contexte:
   *    (a) Si entra amb http dins portafibs llavors redirecciona a portafib
   *    (b) Si entra amb https dins portafib i existeix portafibs llavors redirecciona a portafibs
   * Si el valor es false, llavors no fa res.
   * @return
   */
  public static boolean isAutomaticRedirect() {
    return  Boolean.getBoolean(PORTAFIB_PROPERTY_BASE + "automaticredirect");
  }

  /**
   * @param entitatID
   * @param lang
   * @return
   */
  public static String getFirmatPerFormat(String entitatID, String lang) {
    return  System.getProperty(PORTAFIB_PROPERTY_BASE + "firmatperformat."+ entitatID + "." + lang);
  }
  
  
  /**
   * @param entitatID
   * @param lang
   * @return
   */
  public static String getMotiuDeFirmaFormat(String entitatID, String lang) {
    return  System.getProperty(PORTAFIB_PROPERTY_BASE + "motiudelegacioformat."+ entitatID + "." + lang);
  }
  
  
  public static String getEntitatIDForAgentsSQL() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "entitatidforagentssql", "caib");
  }
  
  public static String getPasswordForAgentsSQL() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "passwordforagentssql");
  }

}
