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

  public static String getDefaultLanguage() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "defaultlanguage", "ca");
  }
  
  public static String getDefaultEntity() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "defaultentity");
  }
  
  public static String getDefaultRolesInCreation() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "defaultrolesincreation");
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
   * Temps mínim que s'espera abans de reintentar una notificació fallida en ms.
   * Valor per defecte 60000ms (1 minut). Ha de ser major de 15000.
   */
  public static long getNotificacionsTimeLapse() {
    Long val = Long.getLong(PORTAFIB_PROPERTY_BASE + "notificationtimelapse");
    if (val == null || val < 15000L) {
      return 60000L;
    } else {
      return val;
    }
  }
  
  
  /**
   * Si el valor és true llavors redireccióna segons el contexte:
   *    (a) Si entra amb http dins portafib/s llavors redirecciona a portafib
   *    (b) Si entra amb https dins portafib i existeix portafib/s llavors redirecciona a portafib/s
   * Si el valor es false, llavors no fa res.
   * @return
   */
  public static boolean isAutomaticRedirect() {
    return  Boolean.getBoolean(PORTAFIB_PROPERTY_BASE + "automaticredirect");
  }

  
  public static String getEntitatIDForAgentsSQL() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "entitatidforagentssql", "caib");
  }
  
  public static String getPasswordForAgentsSQL() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "passwordforagentssql");
  }

  
  public static int getMaxItemsToShowInAutocomplete() {
    return Integer.parseInt(
        System.getProperty(PORTAFIB_PROPERTY_BASE + "maxitemstoshowinautocomplete", "10"));
  }
  
  public static int getMinCharsToStartAutocomplete() {
    return Integer.parseInt(
        System.getProperty(PORTAFIB_PROPERTY_BASE + "mincharstostartautocomplete", "2"));
  }
  
  
  
  /**
   * Opcional. Indica Temps de validesa del Token de Firma només quan hi ha multiples firmes
     en un bloc o hi ha delegats definits. Es a dir, el temps màxim que un firmant pot tenir
     bloquejat un document mentre es realitza el procés de firma. Valor per defecte 3*60*1000,
     o sigui 3 minuts. Quan la firma es única en el bloc i no hi ha delegats definits
     llavors no hi ha bloqueig de temps.
   */
  public static long getMaxTimeLockedSignInMs() {
    Long val = Long.getLong(PORTAFIB_PROPERTY_BASE + "maxtimelockedsigninms");
    if (val == null  || val < 60*1000L) {
      return 3L * 60L * 1000L; // Per defecte
    } else {
      return val;
    }
  }
  
  /**
   * Opcional. Expressió cron que indica cada quan s'ha d'executar l'enviador de correus
   * quan s'han definit enviament d'avisos agrupats.
   * Per defecte s'executa cada dia a les 6:00 (0 0 6 1/1 * ? *) -->
   * @return
   */
  public static String getEmailsGroupedSenderCronExpression() {
    return  System.getProperty(PORTAFIB_PROPERTY_BASE + "emailsgroupedsendercronexpression");
  }
  
  
  /**
   * Indica si s'ha de validar el certificat emprant el Plugin de CheckCertificate quan 
   * l'autenticació es realitza emprant ClientCert 
   * @return
   */
  public static boolean checkCertificateInClientCert() {
    return  Boolean.getBoolean(PORTAFIB_PROPERTY_BASE + "checkcertificateinclientcert");
  }
  
  
}
