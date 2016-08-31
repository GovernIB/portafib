package org.fundaciobit.plugins.signatureserver.miniappletutils;


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
  public static final String VALUE_SIGN_MODE_IMPLICIT = "implicit"; 
  public static final String VALUE_SIGN_MODE_EXPLICIT = "explicit";


  public static final String PROPERTY_POLICY_IDENTIFIER = "policyIdentifier"; //$NON-NLS-1$
  public static final String PROPERTY_POLICY_HASH_ALGORITHM = "policyIdentifierHashAlgorithm"; //$NON-NLS-1$
  public static final String PROPERTY_POLICY_HASH = "policyIdentifierHash"; //$NON-NLS-1$
  public static final String PROPERTY_POLICY_QUALIFIER = "policyQualifier"; //$NON-NLS-1$
  
  
  public static final String PROPERTY_SIGNATURE_RUBRIC_IMAGE = "signatureRubricImage"; //$NON-NLS-1$


  public static final String VALUE_SIGN_ALGORITHM_SHA1 = "SHA1withRSA"; // AOSignConstants.SIGN_ALGORITHM_SHA1WITHRSA;
  public static final String VALUE_SIGN_ALGORITHM_SHA256 = "SHA256withRSA"; // AOSignConstants.SIGN_ALGORITHM_SHA256WITHRSA;
  public static final String VALUE_SIGN_ALGORITHM_SHA384 = "SHA384withRSA"; //AOSignConstants.SIGN_ALGORITHM_SHA384WITHRSA;
  public static final String VALUE_SIGN_ALGORITHM_SHA512 = "SHA512withRSA"; //AOSignConstants.SIGN_ALGORITHM_SHA512WITHRSA;


  public static final String VALUE_SIGN_TYPE_PADES = "Adobe PDF"; // AOSignConstants.SIGN_FORMAT_PDF;
  public static final String VALUE_SIGN_TYPE_XADES = "XAdES"; // AOSignConstants.SIGN_FORMAT_XADES;
  public static final String VALUE_SIGN_TYPE_CADES = "CAdES"; // AOSignConstants.SIGN_FORMAT_CADES;

  
  // Field descriptor #155 Ljava/lang/String;
  public static final java.lang.String PADES_SUBFILTER_BASIC = "adbe.pkcs7.detached";
  
  // Field descriptor #155 Ljava/lang/String;
  public static final java.lang.String PADES_SUBFILTER_BES = "ETSI.CAdES.detached"; 

  public static final String PROPERTY_SIGNATUREPAGE = "signaturePage";

  public static final int VALUE_SIGNATUREPAGE_SENSETAULA = 0;
  public static final int VALUE_SIGNATUREPAGE_PRIMERAPAGINA = 1;
  public static final int VALUE_SIGNATUREPAGE_DARRERAPAGINA = -1;
  
  
  public static final String PROPERTY_SIGNATUREPOSITIONONPAGELOWERLEFTX = "signaturePositionOnPageLowerLeftX";
  public static final String PROPERTY_SIGNATUREPOSITIONONPAGEUPPERRIGHTX = "signaturePositionOnPageUpperRightX";
  public static final String PROPERTY_SIGNATUREPOSITIONONPAGELOWERLEFTY = "signaturePositionOnPageLowerLeftY";
  public static final String PROPERTY_SIGNATUREPOSITIONONPAGEUPPERRIGHTY = "signaturePositionOnPageUpperRightY";

  /** Sello de tiempo a nivel de firma. */
  public static final String TS_SIGN = "1";  //$NON-NLS-1$

  /** Sello de tiempo a nivel de documento. */
  public static final String TS_DOC = "2";  //$NON-NLS-1$
}
