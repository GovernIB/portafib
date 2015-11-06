package es.caib.portafib.applet;

import es.gob.afirma.core.signers.AOSignConstants;

/**
 * 
 * @author anadal
 *
 */
public interface MiniAppletConstants {


  public static final String PDF_MIME_TYPE = "application/pdf";
  
  
  // ========================================================
  // -------------  APPLET CONSTANTS GLOBALS ----------------
  // ========================================================

  public static final String APPLET_LANGUAGE_UI = "languageUI";

  public static final String APPLET_CERTIFICATE_FILTER = "certificate_filter";


  
  // ========================================================
  // ----- APPLET CONSTANTS ESPECIFIQUES DE CADA FIRMA ------
  // ========================================================

  public static final String APPLET_SOURCE = "source";
  
  public static final String APPLET_DESTINATION = "destination";
  
  public static final String APPLET_ERRORPAGE = "errorpage";

  /** Nom per mostrar durant el proces de firma */
  public static final String APPLET_IDNAME = "idname";
  
  public static final String APPLET_SIGN_TYPE = "signType";
  
  public static final String APPLET_SIGN_ALGORITHM = "signAlgorithm";
  
  public static final String APPLET_REDIRECT = "redirect";
  
  public static final String APPLET_MINIAPPLET_PROPERTIES = "properties";

  public static final String APPLET_ISJNLP = "isjnlp";


  // ========================================================
  // ----- PROPIETATS DEL MINIAPPLET ------
  // ========================================================
  
  
  public static final String PROPERTY_SIGN_MODE = "mode";

  public static final String PROPERTY_POLICY_IDENTIFIER = "policyIdentifier"; //$NON-NLS-1$

  public static final String PROPERTY_POLICY_HASH_ALGORITHM = "policyIdentifierHashAlgorithm"; //$NON-NLS-1$

  public static final String PROPERTY_POLICY_HASH = "policyIdentifierHash"; //$NON-NLS-1$

  public static final String PROPERTY_POLICY_QUALIFIER = "policyQualifier"; //$NON-NLS-1$

  // XYZ public static final String PROPERTY_POLICY_DESCRIPTION = "policyDescription"; //$NON-NLS-1$
  
  public static final String PROPERTY_SIGNATURE_RUBRIC_IMAGE = "signatureRubricImage"; //$NON-NLS-1$

  /**
   * Clau d'indicar on es troba la taula de firmes: 
   *     LOCATION_NONE(0) = no n'hi ha
   *     LOCATION_FIRST_PAGE(1) = en la primera pàgina
   *     LOCATION_LAST_PAGE(-1) = en la ultima pàgina
   */
  // XYZ public static final String APPLET_LOCATION_SIGN_TABLE = "sign_location_table";
  
//XYZ  public static final String APPLET_REASON = "sign_reason";
  
  
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
//XYZ public static final String APPLET_FIRMATPERFORMAT = "sign_firmatperformat";
  

  /**
   * Numero de firma. S'utilitza per saber on colocar el recuadre de firmes.
   * Correspon a les firmes que conte el document abans de firmar.
   */
//XYZ public static final String APPLET_SIGN_NUMBER = "sign_number";

//XYZ public static final String APPLET_LANGUAGE_SIGN = "sign_language";
  
  /**
   * Refereix a si és PADES, XADES o CADES
   * @see TIPUSFIRMA_PADES
   * @see TIPUSFIRMA_XADES
   * @see TIPUSFIRMA_CADES
   */
//XYZ public static final String APPLET_SIGN_TYPE = "sign_type";
  
//XYZ  public static final String APPLET_SIGN_ALGORITHM = "sign_algorithm";



  public static final String VALUE_SIGN_ALGORITHM_SHA1 = AOSignConstants.SIGN_ALGORITHM_SHA1WITHRSA;
  public static final String VALUE_SIGN_ALGORITHM_SHA256 = AOSignConstants.SIGN_ALGORITHM_SHA256WITHRSA;
  public static final String VALUE_SIGN_ALGORITHM_SHA384 = AOSignConstants.SIGN_ALGORITHM_SHA384WITHRSA;
  public static final String VALUE_SIGN_ALGORITHM_SHA512 = AOSignConstants.SIGN_ALGORITHM_SHA512WITHRSA;

  
  
 


  
  public static final String VALUE_SIGN_MODE_IMPLICIT = "implicit";
  
  public static final String VALUE_SIGN_MODE_EXPLICIT = "explicit";
  
  /*
  public static final String APPLET_SIGN_BOX_RECTANGLE = "signboxrectangle";

*/


  public static final String TIPUSFIRMA_PADES = AOSignConstants.SIGN_FORMAT_PDF;
  public static final String TIPUSFIRMA_XADES = AOSignConstants.SIGN_FORMAT_XADES;
  public static final String TIPUSFIRMA_CADES = AOSignConstants.SIGN_FORMAT_CADES;


  public static final String PROPERTY_SIGNATUREPAGE = "signaturePage";
  
  
  public static final int VALUE_SIGNATUREPAGE_SENSETAULA = 0;
  public static final int VALUE_SIGNATUREPAGE_PRIMERAPAGINA = 1;
  public static final int VALUE_SIGNATUREPAGE_DARRERAPAGINA = -1;
  
  
  public static final String PROPERTY_SIGNATUREPOSITIONONPAGELOWERLEFTX = "signaturePositionOnPageLowerLeftX";
  public static final String PROPERTY_SIGNATUREPOSITIONONPAGEUPPERRIGHTX = "signaturePositionOnPageUpperRightX";
  public static final String PROPERTY_SIGNATUREPOSITIONONPAGELOWERLEFTY = "signaturePositionOnPageLowerLeftY";
  public static final String PROPERTY_SIGNATUREPOSITIONONPAGEUPPERRIGHTY = "signaturePositionOnPageUpperRightY";
  
}
