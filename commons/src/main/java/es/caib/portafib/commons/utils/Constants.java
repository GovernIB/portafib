package es.caib.portafib.commons.utils;

/**
 *
 * @author anadal
 *
 */
public interface Constants {

	public static final String PORTAFIB_PROPERTY_BASE = "es.caib.portafib.";

	public static final String MAIL_SERVICE = "java:/es.caib.portafib.mail";

	// TRUE ROLES
	public static final String PFI_ADMIN = "PFI_ADMIN";
	public static final String PFI_USER = "PFI_USER";
	public static final String PFI_WS = "PFI_WS";

	// VIRTUAL SECURITY ROLES
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";

	// EJB HIGH LEVEL ROLES
	public static final String ROLE_EJB_FULL_ACCESS = PFI_ADMIN;
	public static final String ROLE_EJB_BASIC_ACCESS = PFI_USER;
	public static final String ROLE_EJB_WS_ACCESS = PFI_WS;
	
	// API FIRMA SIMPLE STATUS VALUES
	public static final int STATUS_INITIALIZING = 0;
	public static final int STATUS_IN_PROGRESS = 1;
	public static final int STATUS_FINAL_OK = 2;
	public static final int STATUS_FINAL_ERROR = -1;
	public static final int STATUS_CANCELLED = -2;
	
	//API FIRMA SIMPLE SIGN TYPE
	
	/** Identificador de la firma PAdES. */
    public static final String SIGN_TYPE_PADES = "PAdES";
    /** Identificador de la firma XAdES por defecto. */
    public static final String SIGN_TYPE_XADES = "XAdES";
    /** Identificador de la firma CAdES. */
    public static final String SIGN_TYPE_CADES = "CAdES";
    /** Identificador de la firma Factura-e (derivado de XAdES-EPES). */
    public static final String SIGN_TYPE_FACTURAE = "FacturaE";
    /** Identificador de la firma OOXML (<i>Office Open XML</i>). */
    public static final String SIGN_TYPE_OOXML = "OOXML";
    /** Identificador de la firma ODF (<i>Open Document Format</i>). */
    public static final String SIGN_TYPE_ODF = "ODF";
    /** Identificador de Firma SMIME */
    public static final String SIGN_TYPE_SMIME = "SMIME";
    /** CAdES-ASiC-S: Formato de firma avanzada ASiC de tipo CAdES. */
    public static final String SIGN_TYPE_CADES_ASIC_S = "CAdES-ASiC-S";
    /** XAdES-ASiC-S: Formato de firma avanzada ASiC de tipo XAdES. */
    public static final String SIGN_TYPE_XADES_ASIC_S = "XAdES-ASiC-S";
    /** NONE: Firma PKCS#1. **/
    public static final String SIGN_TYPE_PKCS1 = "PKCS#1";
    
    //API FIRMA SIMPLE ENCRYPTION TYPE
    public static final String SIGN_ALGORITHM_SHA1 = "SHA-1";
    public static final String SIGN_ALGORITHM_SHA256 = "SHA-256";
    public static final String SIGN_ALGORITHM_SHA384 = "SHA-384";
    public static final String SIGN_ALGORITHM_SHA512 = "SHA-512";
    
    //Modes de firma 
    /** El fitxer de dades resultant inclou la firma: PDF, ODT, ... */
    public static final int SIGN_MODE_ATTACHED_ENVELOPED = 0;

    /** El fitxer resultant serà la firma que incloura les dades originals */
    public static final int SIGN_MODE_ATTACHED_ENVELOPING = 3;

    /**
     * El fitxer de firma no inclourà les dades: per separat trobarem un fitxer de
     * firma i el fitxer original
     */
    public static final int SIGN_MODE_DETACHED = 1;

    /**
     * Firma especial XAdES en que la firma i les dades estan al mateix nivell dins
     * de l'XML: ni la firma inclou les dades ni les dades inclouen la firma
     */
    public static final int SIGN_MODE_INTERNALLY_DETACHED = 4;

    public static final int SIGNATURESTABLELOCATION_WITHOUT = 0;
    public static final int SIGNATURESTABLELOCATION_FIRSTPAGE = 1;
    public static final int SIGNATURESTABLELOCATION_LASTPAGE = -1;
    
    //FIRMA
    public static final int SIGN_OPERATION_SIGN = 0;
    // COFIRMA
    public static final int SIGN_OPERATION_COSIGN = 1;
    // CONTRAFIRMA
    public static final int SIGN_OPERATION_COUNTERSIGN = 2;

    public static final String SIGNPROFILE_BES = "AdES-BES";
    public static final String SIGNPROFILE_EPES = "AdES-EPES";
    public static final String SIGNPROFILE_T = "AdES-T";
    public static final String SIGNPROFILE_C = "AdES-C";
    public static final String SIGNPROFILE_X = "AdES-X";
    public static final String SIGNPROFILE_X1 = "AdES-X1";
    public static final String SIGNPROFILE_X2 = "AdES-X2";
    public static final String SIGNPROFILE_XL = "AdES-XL";
    public static final String SIGNPROFILE_XL1 = "AdES-XL1";
    public static final String SIGNPROFILE_XL2 = "AdES-XL2";
    public static final String SIGNPROFILE_A = "AdES-A";
    public static final String SIGNPROFILE_PADES_LTV = "PAdES-LTV";
    public static final String SIGNPROFILE_PADES_BASIC = "PAdES-Basic";


}
