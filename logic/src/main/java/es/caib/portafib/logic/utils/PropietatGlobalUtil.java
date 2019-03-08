package es.caib.portafib.logic.utils;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.logic.PropietatGlobalLogicaLocal;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
public class PropietatGlobalUtil implements ConstantsV2 {

  protected static final Logger log = Logger.getLogger(PropietatGlobalUtil.class);
  
  
  public static final String PROPERTY_BYENTITY_AVISOS_FIRMES_PENDENTS_DIESABANS =  PORTAFIB_PROPERTY_BASE + "avisosfirmespendents.diesabans";
  
  /**
   * 
   * @return
   */
  public static boolean isDisabledSignaturesTable() {
    final String partialname = "disablesignaturestable";
    Boolean val = getBoolean(partialname);
    return (val == null)? false: val;  
  }
  
  /**
   * 
   * @param entitatID
   * @return
   */
  /* #191
  public static boolean ignoreCheckPostSign(String entitatID) {
    // Valor per entitat
    final String partialPropertyName = "ignorecheckpostsign";
    Boolean val = getBooleanByEntitat(entitatID, partialPropertyName);
    return (val == null)? false : val;
  }
  */
  
  
  /**
   * 
   * @return
   */
  public static String getAvisosFirmesPendentsCronExpression() {
    return getString("avisosfirmespendents.cron");
  }
  

  /**
   * 
   * @param entitatID
   * @return
   */
  public static boolean isSendNotificationWhenCreateDelegacioColaboracio(String entitatID) {
    // Valor per entitat
    final String partialPropertyName = "notificationwhencreatedelegaciocolaboracio";
    Boolean val = getBooleanByEntitat(entitatID, partialPropertyName);
    
    // Valor global si no existeix el de per entitat
    if (val == null) {
      val = getBoolean(partialPropertyName);
    }
    
    return (val == null) ? false : val;
    
  }
  
  
  
  /**
   * 
   * 
   * @return
   */
  public static String getMenuLogOutUrl() {
    final String partialPropertyName = "logouturl";
    String val = getString(partialPropertyName);
    if(log.isDebugEnabled()) { log.debug(" getMenuLogOutUrl() = " + val); }
    return val;
  }
  
  

  /**
   * A partir de quants d'errors en una notificació callback s'enviarà un correu
   * al responsable de l'usuari aplicació
   * 
   * @return null si no està definit
   */
  public static Long getNumberOfErrorsInNotificationToSendMail() {
    final String partialPropertyName = "numberoferrorsinnotificationtosendmail";
    Long val = getLong(partialPropertyName);
    if(log.isDebugEnabled()) { log.debug("getNumberOfErrorsInNotificationToSendMail() = " + val); }
    return val;
  }

  /**
   * A partir de quants d'errors en una notificació callback aquesta
   * automàticament es pausarà
   * 
   * @return null si no està definit
   */

  public static Long getNumberOfErrorsToPauseNotification() throws I18NException {

    final String partialPropertyName = "numberoferrorstopausenotification";
    Long val = getLong(partialPropertyName);

    if(log.isDebugEnabled()) { log.debug(" getNumberOfErrorsToPauseNotification() = " + val); }
    return val;
  }

  /**
   * Temps mínim que s'espera abans de reintentar una notificació fallida en ms.
   * Valor per defecte 60000ms (1 minut). Ha de ser major de 15000.
   */
  public static long getNotificacionsTimeLapse() {
    final String partialPropertyName = "notificationtimelapse";
    
    Long val = getLong(partialPropertyName);

    if (val == null || val < 15000L) {
      val = 60000L;
    }

    if(log.isDebugEnabled()) { log.debug(" getNotificacionsTimeLapse() = " + val); }
    return val;
  }

  /**
   * Opcional. Valor per defecte 10. En els formularis de cerques dinàmiques
   * d'usuari, indica el màxim de resultats permesos per mostrar resultats de
   * l'usuari.
   * 
   * @param entitatID
   * @return
   */
  public static int getMaxItemsToShowInAutocomplete(String entitatID) {

    final String partialPropertyName = "maxitemstoshowinautocomplete";
    Long val = getLongByEntitat(entitatID, partialPropertyName);

    if (val == null) {
      val = 10L;
    }

    if(log.isDebugEnabled()) { log.debug("getMaxItemsToShowInAutocomplete() = " + val); }

    return val.intValue();
  }

  /**
   * Opcional. Valor per defecte 2. En els formularis de cerques dinàmiques
   * d'usuari, indica el mínim de caràcters que ha d'escriure l'usuari abans de
   * que li apareguin els resultats de la cerca. En entitats amb molts d'usuaris
   * es recomana incrementar aquest valora a 3 o 4 amb la finalitat de reduir
   * càrrega de xarxa, servidor i bbdd.
   */
  public static int getMinCharsToStartAutocomplete(String entitatID) {
    final String partialPropertyName = "mincharstostartautocomplete";
    Long val = getLongByEntitat(entitatID, partialPropertyName);

    if (val == null) {
      val = 2L;
    }

    if(log.isDebugEnabled()) { log.debug(" getMinCharsToStartAutocomplete() = " + val); }
    return val.intValue();
  }

  /**
   * Opcional. Indica Temps de validesa del Token de Firma només quan hi ha
   * multiples firmes en un bloc o hi ha delegats definits. Es a dir, el temps
   * màxim que un firmant pot tenir bloquejat un document mentre es realitza el
   * procés de firma. Valor per defecte 3*60*1000, o sigui 3 minuts. Quan la
   * firma es única en el bloc i no hi ha delegats definits llavors no hi ha
   * bloqueig de temps.
   */
  public static long getMaxTimeLockedSignInMs(String entitatID) {

    final String partialPropertyName = "maxtimelockedsigninms";
    Long val = getLongByEntitat(entitatID, partialPropertyName);

    if (val == null || val < 60 * 1000L) {
      val = 3L * 60L * 1000L; // Per defecte 180000 = 3 minuts;
    }

    if(log.isDebugEnabled()) { log.debug( "getMaxTimeLockedSignInMs() = " + val); }
    return val;
  }

  /**
   * 
   * Opcional. Si no es defineix llavors obté la URL absoluta de la petició (Pot
   * haver-hi problemes si el Apache-Proxy no té activat
   * "ProxyPreserveHost On"). Si és defineix s'utilitzarà aquesta URL com ruta
   * absoluta en els plugins de firma web que ho requereixin (JavaWebStart, SIA,
   * ...). Serveix per Plugins de Firma que han d'accedir externament al
   * Servidor de PortaFIB. Exemple:
   * es.caib.portafib.signaturemodule.absoluteurl=
   * http://portafib.ibit.org/portafib
   * 
   * 
   * @return
   */
  public static String getSignatureModuleAbsoluteURL() {
    final String partialPropertyName = "signaturemodule.absoluteurl";
    String val = getString(partialPropertyName);
    if(log.isDebugEnabled()) { log.debug( "getSignatureModuleAbsoluteURL() = " + val); }
    return val;
  }

  /**
   * És l'adreça pública d'accés al portafirmes. Es requereix fonamentalment per
   * l''inclusió de URLs cap a PortaFIB en l''enviament de correus.
   * 
   * @return
   */
  public static String getAppUrl() {
    final String partialPropertyName = "url";
    String val = getString(partialPropertyName);
    if(log.isDebugEnabled()) { log.debug("getAppUrl() = " + val); }
    return val;
  }

  /**
   * És l'adreça d'email des d'on s'enviaran les notificacions per correu als
   * usuaris.
   * 
   * @return
   */
  public static String getAppEmail() {
    final String partialPropertyName = "email.from";
    String val = getString(partialPropertyName);
    if(log.isDebugEnabled()) { log.debug(" getAppEmail() = " + val); }
    return val;
  }

  /**
   * Opcional. Expressió cron que indica cada quan s'ha d'executar l'enviador de
   * correus quan s'han definit enviament d'avisos agrupats. Per defecte
   * s'executa cada dia a les 6:00 (0 0 6 1/1 * ? *).Exemples: - L'executa cada
   * dos minuts: 0 0/2 * 1/1 * ? * - L'executa cada dia a les 6:00: 0 0 6 1/1 *
   * ? *
   * 
   * Veure www.cronmaker.com per altres valors.
   * 
   * @return
   */
  public static String getEmailsGroupedSenderCronExpression() {
    final String partialPropertyName = "emailsgroupedsendercronexpression";
    String val = getString(partialPropertyName);
    if (val == null) {
      val = "0 0 6 1/1 * ? *";
    }
    if(log.isDebugEnabled()) { log.debug( " getEmailsGroupedSenderCronExpression() = " + val); }
    return val;
  }

  /**
   * Si el valor és true llavors redireccióna segons el contexte: (a) Si entra
   * amb http dins portafib/s llavors redirecciona a portafib (b) Si entra amb
   * https dins portafib i existeix portafib/s llavors redirecciona a portafib/s
   * Si el valor es false, llavors no fa res.
   * 
   * @return
   */
  public static boolean isAutomaticRedirect() {
    final String partialPropertyName = "automaticredirect";
    Boolean val = getBoolean(partialPropertyName);
    if (val == null) {
      val = false;
    }
    if(log.isDebugEnabled()) { log.debug( "isAutomaticRedirect() = " + val); }
    return val;
  }

  /**
   * Tamany màxim de pujada de fitxers en bytes. No definit significa sense
   * límit
   * 
   * @return
   */
  public static Long getMaxUploadSizeInBytes() {
    final String partialPropertyName = "maxuploadsizeinbytes";
    Long val = getLong(partialPropertyName);
    if(log.isDebugEnabled()) { log.debug( "getMaxUploadSizeInBytes() = " + val); }
    return val;
  }

  /**
   * Tamany màxim del fitxer PDF una vegada se li han afegit els annexes i taula
   * de firmes. No definit significa sense límit
   * 
   * @return
   */
  public static Long getMaxFitxerAdaptatSizeInBytes() {
    final String partialPropertyName = "maxfitxeradaptatsizeinbytes";
    Long val = getLong(partialPropertyName);
    if(log.isDebugEnabled()) { log.debug( "getMaxFitxerAdaptatSizeInBytes() = " + val); }
    return val;
  }

  /**
   * Si val null incica que l'alta de persones i usuaris l'ha de realitzar
   * l'AdEn. En cas contrari s'utilitzarà aquest valor com a entitat on donar
   * d'alta automaticament persona i usuari. En entorns CAIB aquesta propietat
   * es ignorada
   * 
   * @return
   */
  public static String getDefaultEntity() {
    // return System.getProperty(PORTAFIB_PROPERTY_BASE + "defaultentity");
    final String partialPropertyName = "defaultentity";
    String val = getString(partialPropertyName);
    if(log.isDebugEnabled()) { log.debug( "getDefaultEntity() = " + val); }
    return val;
  }

  /**
   * S'utilitza conjuntament amb la propietat "es.caib.portafib.defaultentity".
   * Indica els roles virtuals a asssignar per defecte a l'usuari-entitat quan
   * aquest es crea automàticament. Es tracta d'una llista de roles separats per
   * comes. Els valors possibles són: ROLE_SOLI (Sol·licitant), ROLE_DEST
   * (Destinatari) ROLE_DELE (Delegat) i ROLE_COLA (Col·laborador). En entorns
   * CAIB aquesta propietat es ignorada
   * 
   * @return
   */
  public static String getDefaultRolesInCreation() {
    final String partialPropertyName = "defaultrolesincreation";
    String val = getString(partialPropertyName);
    if(log.isDebugEnabled()) { log.debug( "getDefaultRolesInCreation() = " + val); }
    return val;
  }


  
  
  /**
   * Mostar o oculta l'opció de Autofirma. Si val true sempre mostra l'opció,
   * si val false mai mostra l'opció i si val null llavors consulta el ROLE ROLE_AUTOFIRMA. 
   * @param entitatID
   * @return
   */
  public static Boolean getAutofirmaAllowed(String entitatID) {
    final String partialPropertyName = "autofirmaallowed";
    Boolean val = getBooleanByEntitat(entitatID, partialPropertyName);
    if(log.isDebugEnabled()) { log.debug( "getAutofirmaAllowed(" + entitatID+ ") = " + val); }
    return val;
  }
  
  

  
  
  // --------------------------------------------------------------------
  // --------------------------------------------------------------------
  // --------------------- AGENT SEYCON (CAIB) --------------------------
  // --------------------------------------------------------------------
  // --------------------------------------------------------------------
  
  /**
   * Opcional. En entorns CAIB, quan un agent seycon dóna d'alta un usuari a PortaFIB, emprant aquesta
   * propietat podem decidir si aquest usuari-entitat es crearà activat (true) o desactivat (false o no definit).
   *
   * @param entitatID
   * @return
   */
  public static boolean isActiveUsuariEntitatAfterAgentSeyconCreation() {
    final String partialPropertyName = "activeusuarientitatafteragentseyconcreation";
    Boolean val = getBoolean(partialPropertyName);
    if(log.isDebugEnabled()) { log.debug( "isActiveUsuariEntitatAfterAgentSeyconCreation() = " + val); }
    return val == null? false: val;
  }
  
  
  /**
   * Opcional excepte en entorns de la CAIB. Entitat sobre la qual s'aplicaran
   * les accions del “Agents Seycon”. Veure punt “5.6.-Gestió de Rols a traves
   * de triggers Oracle” per més informació.
   * es.caib.portafib.entitatidforagentssql
   * 
   * @return
   */
  public static String getEntitatIDForAgentsSQL() {
    final String partialPropertyName = "entitatidforagentssql";
    String val = getString(partialPropertyName);
    if(log.isDebugEnabled()) { log.debug( "getEntitatIDForAgentsSQL() = " + val); }
    return val;
  }

  /**
   * Opcional excepte en entorns de la CAIB. Contrasenya (o clau de pas) per
   * comprovar que les peticions http realment provenen d'un trigger de BBDD.
   * Veure punt “5.6.-Gestió de Rols a traves de triggers Oracle” per més
   * informació. es.caib.portafib.passwordforagentssql
   * 
   * @return
   */
  public static String getPasswordForAgentsSQL() {
    final String partialPropertyName = "passwordforagentssql";
    String val = getString(partialPropertyName);
    if(log.isDebugEnabled()) { log.debug( "getPasswordForAgentsSQL() = " + val); }
    return val;
  }
  
  

  // ----------------------------------------------------------------------------------
  // ----------------------------------------------------------------------------------

  protected static String getString(final String partialPropertyName) {
    try {
      PropietatGlobalLogicaLocal propietatEjb = EjbManager.getPropietatLogicaEJB();
      return propietatEjb.getProperty(PORTAFIB_PROPERTY_BASE + partialPropertyName);
    } catch (I18NException e) {
      String msg = I18NLogicUtils.getMessage(e, new Locale(Configuracio.getDefaultLanguage()));
      log.error("Unknown error getting String Property ]" + PORTAFIB_PROPERTY_BASE
          + partialPropertyName + "[: " + msg, e);
      return null;
    }
  }
  
  
  protected static String getStringByEntitat(String entitatID, final String partialPropertyName) {
    try {
      PropietatGlobalLogicaLocal propietatEjb = EjbManager.getPropietatLogicaEJB();
      return propietatEjb.getProperty(entitatID, PORTAFIB_PROPERTY_BASE + partialPropertyName);
    } catch (I18NException e) {
      String msg = I18NLogicUtils.getMessage(e, new Locale(Configuracio.getDefaultLanguage()));
      log.error("Unknown error getting String Property per l'entitat " + entitatID 
          + " ]" + PORTAFIB_PROPERTY_BASE + partialPropertyName + "[: " + msg, e);
      return null;
    }
  }
  

  protected static Long getLong(final String partialPropertyName) {
    try {
      PropietatGlobalLogicaLocal propietatEjb = EjbManager.getPropietatLogicaEJB();
      return propietatEjb.getLongProperty(PORTAFIB_PROPERTY_BASE + partialPropertyName);
    } catch (I18NException e) {
      String msg = I18NLogicUtils.getMessage(e, new Locale(Configuracio.getDefaultLanguage()));
      log.error("Unknown error getting Long Property ]" + PORTAFIB_PROPERTY_BASE
          + partialPropertyName + "[: " + msg, e);
      return null;
    }
  }
  
  
  protected static Long getLongByEntitat(String entitatID, final String partialPropertyName) {
    try {
      PropietatGlobalLogicaLocal propietatEjb = EjbManager.getPropietatLogicaEJB();
      return propietatEjb.getLongPropertyByEntitat(entitatID, PORTAFIB_PROPERTY_BASE + partialPropertyName);
    } catch (I18NException e) {
      String msg = I18NLogicUtils.getMessage(e, new Locale(Configuracio.getDefaultLanguage()));
      log.error("Unknown error getting Long Property by entitat " + entitatID 
          + "]" + PORTAFIB_PROPERTY_BASE + partialPropertyName + "[: " + msg, e);
      return null;
    }
  }

  protected static Boolean getBoolean(final String partialPropertyName) {
    try {
      PropietatGlobalLogicaLocal propietatEjb = EjbManager.getPropietatLogicaEJB();
      return propietatEjb.getBooleanProperty(PORTAFIB_PROPERTY_BASE + partialPropertyName);
    } catch (I18NException e) {
      String msg = I18NLogicUtils.getMessage(e, new Locale(Configuracio.getDefaultLanguage()));
      log.error("Unknown error getting Boolean Property ]" + PORTAFIB_PROPERTY_BASE
          + partialPropertyName + "[: " + msg, e);
      return null;
    }
  }
  
  
  protected static Boolean getBooleanByEntitat(final String entitatID,
      final String partialPropertyName) {
    try {
      PropietatGlobalLogicaLocal propietatEjb = EjbManager.getPropietatLogicaEJB();
      return propietatEjb.getBooleanPropertyByEntitat(entitatID, PORTAFIB_PROPERTY_BASE + partialPropertyName);
    } catch (I18NException e) {
      String msg = I18NLogicUtils.getMessage(e, new Locale(Configuracio.getDefaultLanguage()));
      log.error("Unknown error getting Boolean Property by entitat " + entitatID 
          + " ]" + PORTAFIB_PROPERTY_BASE + partialPropertyName + "[: " + msg, e);
      return null;
    }
  }

  
}
