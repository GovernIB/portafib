package es.caib.portafib.utils;


/**
 *
 * @author anadal
 *
 */
public interface ConstantsV2 {

  public static final String PORTAFIB_PROPERTY_BASE = "es.caib.portafib.";

  public static final String SECURITY_DOMAIN = "seycon";

  public static final String MAIL_SERVICE = "java:/es.caib.portafib.mail";

  public static final String MAIL_QUEUE = "jms/es.caib.portafib.PortaFIBMailsQueue";

  // XYZ ZZZ 
  public static final String NOTIFICACIONS_QUEUE = "jms/es.caib.portafib.PortaFIBNotificacionsQueue";
  
  public static final String PDF_MIME_TYPE = "application/pdf";
  
  public static final String PDF_FILE_EXTENSION = "pdf";
  
  
  public static final int TIPUSESTATPETICIODEFIRMA_NOINICIAT = 0;
  public static final int TIPUSESTATPETICIODEFIRMA_ENPROCES = 1;
  public static final int TIPUSESTATPETICIODEFIRMA_PAUSAT = 2;
  public static final int TIPUSESTATPETICIODEFIRMA_REBUTJAT = 3;
  public static final int TIPUSESTATPETICIODEFIRMA_FIRMAT = 4;
  
  public static final int[] TIPUSESTATPETICIODEFIRMA = {
    TIPUSESTATPETICIODEFIRMA_NOINICIAT,TIPUSESTATPETICIODEFIRMA_ENPROCES,
    TIPUSESTATPETICIODEFIRMA_PAUSAT, TIPUSESTATPETICIODEFIRMA_REBUTJAT,
    TIPUSESTATPETICIODEFIRMA_FIRMAT
  };

  // Estat Inicials d'un EstatDeFirma
  public static final long TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR = 0L;
  public static final long TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR = 1L;  
  // Indica que el colaborador ha indicat als altres possible colaboradors
  // que ell ja s'ho mira per la qual cosa el sistema descarta la tasca
  // de la resta de colaboradors
  public static final long TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR = 2L;
  public static final long TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_REVISAR = 3L;
  
  public static final long[] TIPUSESTATDEFIRMAINICIAL = new long[] {
    TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR,
    TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR,
    TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR,
    TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_REVISAR
  };

  // Estats Finals d'un EstatDeFirma
  public static final long TIPUSESTATDEFIRMAFINAL_VALIDAT = 0L;
  public static final long TIPUSESTATDEFIRMAFINAL_INVALIDAT = 1L;
  public static final long TIPUSESTATDEFIRMAFINAL_FIRMAT = 2L;
  public static final long TIPUSESTATDEFIRMAFINAL_REBUTJAT = 3L;
  // Es passa a aquest estat quan la tasca encomanada ha sigut realitzada
  // per un altra persona
  public static final long TIPUSESTATDEFIRMAFINAL_DESCARTAT = 4L;
  // Revisor
  public static final long TIPUSESTATDEFIRMAFINAL_ACCEPTAT = 5L;

  public static final long[] TIPUSESTATDEFIRMAFINAL = new long[] {
      TIPUSESTATDEFIRMAFINAL_VALIDAT, TIPUSESTATDEFIRMAFINAL_INVALIDAT,
      TIPUSESTATDEFIRMAFINAL_FIRMAT, TIPUSESTATDEFIRMAFINAL_REBUTJAT,
      TIPUSESTATDEFIRMAFINAL_DESCARTAT, TIPUSESTATDEFIRMAFINAL_ACCEPTAT };

  // TIPUS DE METADADA
  public static final int TIPUSMETADADA_STRING = 0;
  public static final int TIPUSMETADADA_INTEGER = 1;
  public static final int TIPUSMETADADA_DECIMAL = 2;
  public static final int TIPUSMETADADA_BOOLEAN = 3;
  public static final int TIPUSMETADADA_BASE64 = 4;
  public static final int TIPUSMETADADA_DATE = 5;
  
  
  // ------------------ ROLES REALS
  // Rol ADMIN: Administrador PortaFIB
  public static final String PFI_ADMIN = "PFI_ADMIN";
  // Rol USER: Admin. d'Entitat, Sol·licitant, Destinatari, Delegat i Col·laborador
  public static final String PFI_USER = "PFI_USER"; 

  // ------------------ ROLES VIRTUALS
  // Administrador PortaFIB
  public static final String ROLE_ADMIN = "ROLE_ADMIN";
  // ROLE USER - SEYCON
  public static final String ROLE_USER = "ROLE_USER";  
  // Destinatari
  public static final String ROLE_DEST = "ROLE_DEST";
  // Delegat
  public static final String ROLE_DELE = "ROLE_DELE";
  // Col·laborador
  public static final String ROLE_COLA = "ROLE_COLA";
  // Administrador d''Entitat
  public static final String ROLE_ADEN = "ROLE_ADEN";
  // Sol·licitant
  public static final String ROLE_SOLI = "ROLE_SOLI";
  // Revisor
  public static final String ROLE_REVI = "ROLE_REVI";

  // TIPUS NOTIFICACIO-AVIS

  public static final long NOTIFICACIOAVIS_PETICIO_EN_PROCES = 0;
  public static final long NOTIFICACIOAVIS_REQUERIT_PER_VALIDAR = 10;
  public static final long NOTIFICACIOAVIS_DESCARTAT_PER_VALIDAR = 15;
  public static final long NOTIFICACIOAVIS_REQUERIT_PER_FIRMAR = 20;
  public static final long NOTIFICACIOAVIS_DESCARTAT_PER_FIRMAR = 25;
  public static final long NOTIFICACIOAVIS_VALIDAT = 30;
  public static final long NOTIFICACIOAVIS_INVALIDAT = 40;
  public static final long NOTIFICACIOAVIS_FIRMA_PARCIAL = 50;
  public static final long NOTIFICACIOAVIS_PETICIO_FIRMADA = 60;
  public static final long NOTIFICACIOAVIS_PETICIO_REBUTJADA = 70;
  public static final long NOTIFICACIOAVIS_PETICIO_PAUSADA = 80;
  public static final long NOTIFICACIOAVIS_REQUERIT_PER_REVISAR = 90;
  
  
  // TIPUS PLUGINS
  // NOTA: Es un combo box del camp tipus de la taula PLugin (cridar el genapp per afegir valors)
  public static final int TIPUS_PLUGIN_MODULDEFIRMA_WEB = 0;
  public static final int TIPUS_PLUGIN_SEGELLDETEMPS = 1;
  public static final int TIPUS_PLUGIN_CUSTODIA = 2;
  public static final int TIPUS_PLUGIN_MODULDEFIRMA_SERVIDOR = 3;
  public static final int TIPUS_PLUGIN_VALIDACIOFIRMES = 4;
  

  // Contextes WEB per enllaços dels mails.
  public static final String CONTEXT_SOLI_PETICIOFIRMA = "/soli/firma/peticio";
  public static final String CONTEXT_SOLI_PETICIOFIRMA_ACTIVA = CONTEXT_SOLI_PETICIOFIRMA + "Activa";
  public static final String CONTEXT_SOLI_PETICIOFIRMA_FIRMADA = CONTEXT_SOLI_PETICIOFIRMA + "Firmada";
  public static final String CONTEXT_SOLI_PETICIOFIRMA_REBUTJADA = CONTEXT_SOLI_PETICIOFIRMA + "Rebutjada";

  public static final String CONTEXT_DEST_ESTATFIRMA = "/dest/estatDeFirma";  
  public static final String CONTEXT_DEST_ESTATFIRMA_PENDENT = CONTEXT_DEST_ESTATFIRMA + "Pendent";
  public static final String CONTEXT_DEST_ESTATFIRMA_FIRMAT = CONTEXT_DEST_ESTATFIRMA + "Firmada";
  public static final String CONTEXT_DEST_ESTATFIRMA_REBUTJAT = CONTEXT_DEST_ESTATFIRMA + "Rebutjada";
  
  public static final String CONTEXT_DELE_ESTATFIRMA = "/dele/estatDeFirma";
  public static final String CONTEXT_DELE_ESTATFIRMA_PENDENT = CONTEXT_DELE_ESTATFIRMA + "Pendent";
  public static final String CONTEXT_DELE_ESTATFIRMA_FIRMAT = CONTEXT_DELE_ESTATFIRMA + "Firmada";
  public static final String CONTEXT_DELE_ESTATFIRMA_REBUTJAT = CONTEXT_DELE_ESTATFIRMA + "Rebutjada";

  public static final String CONTEXT_COLA_ESTATFIRMA = "/cola/estatDeFirma";
  public static final String CONTEXT_COLA_ESTATFIRMA_PENDENT = CONTEXT_COLA_ESTATFIRMA + "Pendent";
  public static final String CONTEXT_COLA_ESTATFIRMA_VALIDAT = CONTEXT_COLA_ESTATFIRMA + "Validat";
  public static final String CONTEXT_COLA_ESTATFIRMA_INVALIDAT = CONTEXT_COLA_ESTATFIRMA + "Invalidat";
  public static final String CONTEXT_COLA_ESTATFIRMA_DESCARTAT = CONTEXT_COLA_ESTATFIRMA + "Descartat";
  
  
  public static final String CONTEXT_REVI_ESTATFIRMA = "/revi/estatDeFirma";
  public static final String CONTEXT_REVI_ESTATFIRMA_PENDENT = CONTEXT_REVI_ESTATFIRMA + "Pendent";
  public static final String CONTEXT_REVI_ESTATFIRMA_ACCEPTADA = CONTEXT_REVI_ESTATFIRMA + "Acceptada";
  public static final String CONTEXT_REVI_ESTATFIRMA_REBUTJAT = CONTEXT_REVI_ESTATFIRMA + "Rebutjada";
  

  public static final String CONTEXT_ADEN_PETICIOFIRMA = "/aden/peticiofirmaaplicacio";
  
  public static final String CONTEXT_ADEN_NOTIFICACIONSWS = "/aden/notificaciows";

  
  //==============================================================
  // ---   Operació de Firma - PORTAFIB v2.0 #164 -----
  // =============================================================
  public static final int TIPUS_OPERACIO_FIRMA_FIRMAR = 0;
  public static final int TIPUS_OPERACIO_FIRMA_COFIRMAR = 1;
  public static final int TIPUS_OPERACIO_FIRMA_CONTRAFIRMAR = 2;
  
  
  public static final int TIPUSFIRMA_PADES = 0;
  public static final int TIPUSFIRMA_XADES = 1;
  public static final int TIPUSFIRMA_CADES = 2;
  public static final int TIPUSFIRMA_SMIME = 3;
  
  
  public static final int[] TIPUSFIRMA_SUPPORTED = { TIPUSFIRMA_PADES }; 
  

  public static final int DOC_PDF = 0;
  public static final int DOC_IMG = 1;
  public static final int DOC_TXT = 2;
  public static final int DOC_BIN = 3;
  
  
  // #199  TIPUS METADADES XYZ ZZZ
//  0;"tipusmetadada.string";"java.lang.String"
//  1;"tipusmetadada.integer";"java.math.BigInteger"
//  2;"tipusmetadada.decimal";"java.math.BigDecimal"
//  3;"tipusmetadada.boolean";"java.lang.Boolean"
//  4;"tipusmetadada.base64";"org.fundaciobit.pluginsib.core.utils.Base64"
//  5;"tipusmetadada.date";"org.fundaciobit.pluginsib.core.utils.ISO8601"

  

//prioritat.0=Prioridad Pausada
//prioritat.1=Prioridad Insignificante
//prioritat.2=Prioridad Muy Baja
//prioritat.3=Prioridad Baja
//prioritat.4=Prioridad Normal-Baja
//prioritat.5=Prioridad Normal
//prioritat.6=Prioridad Normal-Alta
//prioritat.7=Prioridad Alta
//prioritat.8=Prioridad Muy Alta
//prioritat.9=Prioridad Inmediata


  public static final int PRIORITAT_BAIXA = 3;
  public static final int PRIORITAT_NORMAL = 5;
  public static final int PRIORITAT_ALTA = 7;


  
  
  public static final String BARCODE_PDF417_PLUGIN = "org.fundaciobit.plugins.barcode.pdf417.Pdf417Plugin";
  
  public static final String BARCODE_QR_PLUGIN = "org.fundaciobit.plugins.barcode.qrcode.QrCodePlugin";
  
  public static final String BARCODE_128_PLUGIN = "org.fundaciobit.plugins.barcode.barcode128.BarCode128Plugin";
  

  
  //==============================================================
  // --- POSICIO DE TAULA DE FIRMES - PORTAFIB v2.0 #166 -----
  // =============================================================

  public static final int TAULADEFIRMES_SENSETAULA = 0;
  public static final int TAULADEFIRMES_PRIMERAPAGINA = 1;
  public static final int TAULADEFIRMES_DARRERAPAGINA = -1;
  public static final int TAULADEFIRMES_RUBRICA_EN_FIRMA = 2;
  
  public static final int[] TAULADEFIRMES = {
    TAULADEFIRMES_SENSETAULA,
    TAULADEFIRMES_PRIMERAPAGINA,
    TAULADEFIRMES_DARRERAPAGINA
    // TAULADEFIRMES_RUBRICA_EN_FIRMA no disponible fins que s'implementi en #73
  };
  
 
  // ----------- POSICIO PAGINA 
  
  /**
   * Indicates that the signature will be added in the upper part of the PDF document.
   */
  public static final int POSICIO_PAGINA_ADALT = 1;
  
  /**
   * Indicates that the signature will be added in the bottom part of the PDF document.
   */
  public static final int POSICIO_PAGINA_ABAIX = 2;
  
  /**
   * Indicates that the signature will be added in the left part of the PDF document.
   */
  public static final int POSICIO_PAGINA_ESQUERRA = 3;
  
  /**
   * Indicates that the signature will be added int the right part of the PDF document.
   */
  public static final int POSICIO_PAGINA_DRETA = 4;

  /**
   * Neither code bars nor signer information will be added to the PDF document.
   */
  public static final int POSICIO_PAGINA_CAP = 0;
  
  
  
  
  
  //========================================================
  // ----- ESTADISTICA #168 ------
  // ========================================================

  public static final int ESTADISTICA_TIPUS_PETICIO_INICI = 0;
  
  public static final int ESTADISTICA_TIPUS_PETICIO_FINAL = 1;
  
  public static final int ESTADISTICA_TIPUS_PETICIO_REBUTJADA = 2;


  
 //========================================================
 // ----- POLITICA DE CUSTODIA ------
 // ========================================================

  /**
   * CustodiaInfo per Defecte de l'entitat val NULL 
   */
  public static final int CUSTODIA_NO_PERMETRE = 0;
  
  /**
   * CustodiaInfo per Defecte de l'entitat diferent de NULL i editable = false 
   */
  public static final int CUSTODIA_NOMES_PLANTILLA_PER_DEFECTE = 1;

  public static final int CUSTODIA_NOMES_PLANTILLES_DEFINIDES_EN_ENTITAT = 2;

  /**
   * CustodiaInfo per Defecte de l'entitat diferent de NULL, editable = true 
   * i només una plantilla de custòdia disponible per l'entitat 
   */
  public static final int CUSTODIA_EDITABLE_SENSE_CANVI_PLUGIN = 3;
  
  /**
   * CustodiaInfo per Defecte de l'entitat diferent de NULL, editable = true 
   * i multiples plantilles de custòdia disponible per l'entitat  
   */
  public static final int CUSTODIA_TOTALMENT_EDITABLE = 4;

  
  
  
  //========================================================
  // ----- POLITICA DE CUSTODIA - PORTAFIB v2.0 #165 ------
  // =======================================================
  
  // [USR_APP_CONFIG] El que s´hagi definit dins l´Entitat
  public static final int POLITICA_CUSTODIA_EL_DEFINIT_EN_ENTITAT = -1;
  
  // No permetre
  public static final int POLITICA_CUSTODIA_NO_PERMETRE = 0;
  
  // Només Plantilles de l´Entitat (No editables)
  public static final int POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT = 1;
  
  // Obligatori Plantilla definida en Entitat o Config. usuari Aplicació.
  public static final int POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA = 2;
  
  // [ENTITAT] Opcional plantilla Entitat (Per defecte Actiu)
  public static final int POLITICA_CUSTODIA_OPCIONAL_PLANTILLA_DEFINIDA_DEFECTE_ACTIU = 3;
  
  // [ENTITAT] Opcional plantilla Entitat (Per defecte NO Actiu)
  public static final int POLITICA_CUSTODIA_OPCIONAL_PLANTILLA_DEFINIDA_DEFECTE_NO_ACTIU = 4;
  
  // Llibertat Total (selecció, edició i us)
  public static final int POLITICA_CUSTODIA_LLIBERTAT_TOTAL = 5;
  

  

  
  

  // ==============================================================
  // --- POLITICA DE PLUGIN DE FIRMA WEB - PORTAFIB v2.0 #173 -----
  // ==============================================================
      
  // Només plugins de l´entitat
  public static final int POLITICA_PLUGIN_FIRMA_WEB_NOMES_PLUGINS_ENTITAT = 0;
  
  // Plugins de l´entitat més plugins addicionals (afegir o llevar)
  public static final int POLITICA_PLUGIN_FIRMA_WEB_ENTITAT_I_ADDICIONALS = 1;

  // Només plugins addicionals (Només els que tenguin marcat afegir)
  public static final int POLITICA_PLUGIN_FIRMA_WEB_NOMES_ADDICIONALS = 2;
  
  
  public static final int[] POLITICA_PLUGIN_FIRMA_WEB = new int[] {
    POLITICA_PLUGIN_FIRMA_WEB_NOMES_PLUGINS_ENTITAT,
    POLITICA_PLUGIN_FIRMA_WEB_ENTITAT_I_ADDICIONALS,
    POLITICA_PLUGIN_FIRMA_WEB_NOMES_ADDICIONALS
  };
  

  
 //==============================================================
 // --- POLITICA DE MOSTRAR PROPIETATS EN PLUGIN - PORTAFIB v2.0 #160 -----
 // ==============================================================
   
  // No mostrar ni propietats d´administrador ni propietats entitat
  public static final int PLUGIN_POLITICA_MOSTRAR_PROPIETATS_NO_MOSTRAR_RES=0;
  
  // Permetre editar propietats entitat però no mostrar propietats administrador
  public static final int PLUGIN_POLITICA_MOSTRAR_PROPIETATS_EDIT_ENTITAT_OCULTAR_ADMIN=1;
  
  // Permetre editar propietats entitat i mostrar propietats administrador
  public static final int PLUGIN_POLITICA_MOSTRAR_PROPIETATS_EDIT_ENTITAT_MOSTRAR_ADMIN=2;
  
  // Permetre editar propietats entitat i editar propietats administrador
  public static final int PLUGIN_POLITICA_MOSTRAR_PROPIETATS_EDIT_ENTITAT_EDIT_ADMIN=3;
  
  
  public static final int[] PLUGIN_POLITICA_MOSTRAR_PROPIETATS = new int[] {
    PLUGIN_POLITICA_MOSTRAR_PROPIETATS_NO_MOSTRAR_RES,
    PLUGIN_POLITICA_MOSTRAR_PROPIETATS_EDIT_ENTITAT_OCULTAR_ADMIN,    
    PLUGIN_POLITICA_MOSTRAR_PROPIETATS_EDIT_ENTITAT_MOSTRAR_ADMIN,
    PLUGIN_POLITICA_MOSTRAR_PROPIETATS_EDIT_ENTITAT_EDIT_ADMIN
  };

  
  //==============================================================
  // --- POLITICA DE US DEL PLUGIN - PORTAFIB v2.0 #160 -----
  // ==============================================================
  
  // Plantilla
  public static final int PLUGIN_POLITICA_DE_US_PLANTILLA = 0;
  
  // Només entitat
  public static final int PLUGIN_POLITICA_DE_US_NOMES_ENTITAT = 1;
  
  // Ho poden usar totes les entitats
  public static final int PLUGIN_POLITICA_DE_US_USAR_TOTHOM = 2;

  
  public static final int[] PLUGIN_POLITICA_DE_US = {
    PLUGIN_POLITICA_DE_US_PLANTILLA ,
    PLUGIN_POLITICA_DE_US_NOMES_ENTITAT,
    PLUGIN_POLITICA_DE_US_USAR_TOTHOM
  };

  



  // ========================================================
  // ----- CONSTANTS ESPECIFIQUES DE CADA FIRMA ------
  // ========================================================


  public static final int SIGN_ALGORITHM_SHA1WITHRSA=0;
  public static final int SIGN_ALGORITHM_SHA256WITHRSA=1;
  public static final int SIGN_ALGORITHM_SHA384WITHRSA=2;
  public static final int SIGN_ALGORITHM_SHA512WITHRSA=3;

  public static final boolean SIGN_MODE_IMPLICIT = false;

  public static final boolean SIGN_MODE_EXPLICIT = true;

  // ========================================================
  // -------------  TAULA DE FIRMES I LOGOS- ----------------
  // ========================================================

  
  public static final int LOGO_SIDE = (int)(1.25f  * 72f);
  // Abans l'altura era 0,75 * 72 = 54 per 4 linies per casella
  // Ara volem un màxim de 5 linies per casella que significa una altura de 67,5 (= 54*5/4) 
  public static final int SIGNBOX_HEIGHT = (int)(67.5); // =57.6     ANTIC 0.5inch = = 36
  public static final int SIGNBOX_START = (int)(1.25f  * 72f); // =90

  public static final int LOWER_MARGIN_PAGE = (int)(1.1f  * 72f); // = 72

  public static final int A4_AMPLE = 595;
  public static final int A4_ALT = 842;

  // Hauria de ser calculat
  public static final int MAX_FIRMES_PER_TAULA 
      = (A4_ALT - LOWER_MARGIN_PAGE - SIGNBOX_START)/SIGNBOX_HEIGHT; // ~10;  
  

}
