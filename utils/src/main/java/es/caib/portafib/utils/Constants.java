package es.caib.portafib.utils;


/**
 *
 * @author anadal
 *
 */
public interface Constants {

  public static final String PORTAFIB_PROPERTY_BASE = "es.caib.portafib.";

  public static final String SECURITY_DOMAIN = "seycon";

  public static final String MAIL_SERVICE = "java:/es.caib.portafib.mail";

  public static final String MAIL_QUEUE = "jms/es.caib.portafib.PortaFIBMailsQueue";

  public static final String NOTIFICACIONS_QUEUE = "jms/es.caib.portafib.PortaFIBNotificacionsQueue";
  
  public static final String PDF_MIME_TYPE = "application/pdf";
  
  public static final String PDF_FILE_EXTENSION = "pdf";
  

  public static final int TIPUSESTATPETICIODEFIRMA_NOINICIAT = 0;
  public static final int TIPUSESTATPETICIODEFIRMA_ENPROCES = 1;
  public static final int TIPUSESTATPETICIODEFIRMA_PAUSAT = 2;
  public static final int TIPUSESTATPETICIODEFIRMA_REBUTJAT = 3;
  public static final int TIPUSESTATPETICIODEFIRMA_FIRMAT = 4;

  // Estat Inicials d'un EstatDeFirma
  public static final long TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR = 0L;
  // Indica que el colaborador ha indicat als altres possible colaboradors
  // que ell ja s'ho mira per la qual coas el sistema descarta la tasca
  // de la resta de colaboradors
  public static final long TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR = 2L;
  public static final long TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR = 1L;

  // Estats Finals d'un EstatDeFirma
  public static final long TIPUSESTATDEFIRMAFINAL_VALIDAT = 0L;
  public static final long TIPUSESTATDEFIRMAFINAL_INVALIDAT = 1L;
  public static final long TIPUSESTATDEFIRMAFINAL_FIRMAT = 2L;
  public static final long TIPUSESTATDEFIRMAFINAL_REBUTJAT = 3L;
  // Es passa a aquest estat quan la tasca encomanada ha sigut realitzada
  // per un altra persona
  public static final long TIPUSESTATDEFIRMAFINAL_DESCARTAT = 4L;

  public static final long[] TIPUSESTATDEFIRMAFINAL = new long[] {
      TIPUSESTATDEFIRMAFINAL_VALIDAT, TIPUSESTATDEFIRMAFINAL_INVALIDAT,
      TIPUSESTATDEFIRMAFINAL_FIRMAT, TIPUSESTATDEFIRMAFINAL_REBUTJAT,
      TIPUSESTATDEFIRMAFINAL_DESCARTAT };

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
  
  public enum RoleUsuariAplicacioEnum {

    PFI_ADMIN(Constants.PFI_ADMIN),

    PFI_USER(Constants.PFI_USER);

    String value;

    RoleUsuariAplicacioEnum(String value) {
      this.value = value;
    }
    
    @Override
    public String toString() {
      return value;
    }

  }
  

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

  public static final String CONTEXT_ADEN_PETICIOFIRMA = "/aden/peticiofirmaaplicacio";
  
  public static final String CONTEXT_ADEN_NOTIFICACIONSWS = "/aden/notificaciows";

  
  public static final int TIPUSFIRMA_PADES = 0;
  public static final int TIPUSFIRMA_XADES = 1;
  public static final int TIPUSFIRMA_CADES = 2;

  public static final int DOC_PDF = 0;
  public static final int DOC_IMG = 1;
  public static final int DOC_TXT = 2;
  public static final int DOC_BIN = 3;
  

  public static final int PRIORITAT_BAIXA = 0;
  public static final int PRIORITAT_NORMAL = 5;
  public static final int PRIORITAT_ALTA = 9;


  public static final int TAULADEFIRMES_SENSETAULA = 0;
  public static final int TAULADEFIRMES_PRIMERAPAGINA = 1;
  public static final int TAULADEFIRMES_DARRERAPAGINA = -1;
  
  public static final String BARCODE_PDF417_PLUGIN = "org.fundaciobit.plugins.barcode.pdf417.Pdf417Plugin";
  
  public static final String BARCODE_QR_PLUGIN = "org.fundaciobit.plugins.barcode.qrcode.QrCodePlugin";
  
  public static final String BARCODE_128_PLUGIN = "org.fundaciobit.plugins.barcode.barcode128.BarCode128Plugin";
 
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

  
  // ========================================================
  // ----- APPLET CONSTANTS ESPECIFIQUES DE CADA FIRMA ------
  // ========================================================
  
  // XYZ ELIMINAR

  public static final String APPLET_SOURCE = "source";
  
  public static final String APPLET_DESTINATION = "destination";

  /** Nom per mostrar durant el proces de firma */
  public static final String APPLET_IDNAME = "idname";

  /**
   * Clau d'indicar on es troba la taula de firmes: 
   *     LOCATION_NONE(0) = no n'hi ha
   *     LOCATION_FIRST_PAGE(1) = en la primera pàgina
   *     LOCATION_LAST_PAGE(-1) = en la ultima pàgina
   */
  public static final String APPLET_LOCATION_SIGN_TABLE = "sign_location_table";
  
  public static final String APPLET_REASON = "sign_reason";
  
  
  /** Codi que conté el missatge pre formatejat pel camp de "Firmat Per:" de la
   *  taula de firmes. Els camps a substituir són:
   * {0} = NOM
   * {1} = LONGITUD NIF
   * {2} = NIF
   * {3} = EMISSOR
   * {4} = LONGITUD CARREC_CERTIFICAT
   * {5} = CARREC_CERTIFICAT
   * {6} = LONGITUD UNITAT_ADMINISTRATIVA
   * {7} = UNITAT_ADMINISTRATIVA
   */
  public static final String APPLET_FIRMATPERFORMAT = "sign_firmatperformat";
  

  /**
   * Numero de firma. S'utilitza per saber on colocar el recuadre de firmes.
   * Correspon a les firmes que conte el document abans de firmar.
   */
  public static final String APPLET_SIGN_NUMBER = "sign_number";

  public static final String APPLET_LANGUAGE_SIGN = "sign_language";
  
  /**
   * Refereix a si és PADES, XADES o CADES
   * @see TIPUSFIRMA_PADES
   * @see TIPUSFIRMA_XADES
   * @see TIPUSFIRMA_CADES
   */
  public static final String APPLET_SIGN_TYPE = "sign_type";
  
  public static final String APPLET_SIGN_ALGORITHM = "sign_algorithm";


  public static final int APPLET_SIGN_ALGORITHM_SHA1WITHRSA=0;
  public static final int APPLET_SIGN_ALGORITHM_SHA256WITHRSA=1;
  public static final int APPLET_SIGN_ALGORITHM_SHA384WITHRSA=2;
  public static final int APPLET_SIGN_ALGORITHM_SHA512WITHRSA=3;

 
  public static final String APPLET_SIGN_MODE = "sign_mode";

  
  public static final boolean APPLET_SIGN_MODE_IMPLICIT = false;
  
  public static final boolean APPLET_SIGN_MODE_EXPLICIT = true;
  
  
  public static final String APPLET_SIGN_BOX_RECTANGLE = "signboxrectangle";
  
  
  // ========================================================
  // -------------  APPLET CONSTANTS GLOBALS ----------------
  // ========================================================

  public static final String APPLET_LANGUAGE_UI = "languageUI";

  public static final String APPLET_CERTIFICATE_FILTER = "certificate_filter";

  public static final String APPLET_REDIRECT = "redirect";
  
  public static final String APPLET_ISJNLP = "isjnlp";

  public static final String APPLET_SIGNERCLASS = "signerClass";
  public static final String APPLET_SIGNERCLASS_IBKEY="applet.signerClass=es.caib.portafib.applet.signers.IBKeySigner";
  public static final String APPLET_SIGNERCLASS_AFIRMA="applet.signerClass=es.caib.portafib.applet.signers.AfirmaSigner";

  public static final String APPLET_PARAMETERS_TO_READ="parametersToRead";

  public static final String APPLET_POLICYIDENTIFIER = "policyIdentifier";
  public static final String APPLET_POLICYIDENTIFIERHASH = "policyIdentifierHash";
  public static final String APPLET_POLICYIDENTIFIERHASHALGORITHM = "policyIdentifierHashAlgorithm";
  public static final String APPLET_POLICYURLDOCUMENT = "policyUrlDocument";

  public static final int APPLET_LOGO_SIDE = (int)(1.25f  * 72f);
  // Abans l'altura era 0,75 * 72 = 54 per 4 linies per casella
  // Ara volem un màxim de 5 linies per casella que significa una altura de 67,5 (= 54*5/4) 
  public static final int APPLET_HEIGHTSIGNBOX = (int)(67.5); // =57.6     ANTIC 0.5inch = = 36
  public static final int APPLET_STARTSIGNTABLE = (int)(1.25f  * 72f); // =90

  public static final int APPLET_LOWER_MARGIN_PAGE = (int)(1.1f  * 72f); // = 72

  public static final int A4_AMPLE = 595;
  public static final int A4_ALT = 842;

  // Hauria de ser calculat
  public static final int APPLET_MAX_FIRMES_PER_TAULA 
      = (A4_ALT - APPLET_LOWER_MARGIN_PAGE - APPLET_STARTSIGNTABLE)/APPLET_HEIGHTSIGNBOX; // ~10;  
  

}
