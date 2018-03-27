package org.fundaciobit.plugins.signatureserver.afirmaserver;

/**
 * XYZ ZZZ Moure a pluginsIB
 * @author anadal
 *
 */
public interface ValidateSignatureConstants {


  public static final String SIGNTYPE_CMS = "CMS";
  public static final String SIGNTYPE_CAdES = "CAdES";
  public static final String SIGNTYPE_XAdES = "XAdES";
  public static final String SIGNTYPE_ODF = "ODF";
  public static final String SIGNTYPE_PDF = "PDF"; // ?????
  public static final String SIGNTYPE_PAdES = "PAdES";
  public static final String SIGNTYPE_OOXML = "OOXML";
  public static final String SIGNTYPE_XML_DSIG = "XML_DSIG";
  
  public static final String SIGNTYPE_PKCS7 = "PKCS7"; 
  
  public static final String SIGNTYPE_XML_TST = "XML_TST";  // OASIS  XML-encoded time-stamps.

  /** La firma està continguda dins del document: PADES, ODT, OOXML */
  public static final String SIGNFORMAT_IMPLICIT_ENVELOPED_ATTACHED = "implicit_enveloped/attached";

  /** La firma conté al document: Xades ATTACHED */
  public static final String SIGNFORMAT_IMPLICIT_ENVELOPING_ATTACHED = "implicit_enveloping/attached";

  /**
   * El documetn està forà de la firma: xades detached i cades detached
   */
  public static final String SIGNFORMAT_EXPLICIT_DETACHED = "explicit/detached";

  /**
   * Cas específic de Xades externally detached
   */
  public static final String SIGNFORMAT_EXPLICIT_EXTERNALLY_DETACHED = "explicit/externally_detached";

  /**
   * AdES-BES:Basic Electronic Signature.Es el formato más básico del estándar y
   * constituye la base para la extensión de la firma hacia otros formatos más
   * complejos.
   */
  public static final String SIGNPROFILE_BES = "AdES-BES";

  //
  /**
   * AdES-EPES:(BES + Politica de firma) Explicit Policy Electronic Signature.Es
   * una variante del formato AdES–BES que contiene información acerca de la
   * política de firma utilizada.
   */
  public static final String SIGNPROFILE_EPES = "AdES-EPES";

  /** AdES-T:Timestamp.Permite la adición de un sello de tiempo sobre la firma. */
  public static final String SIGNPROFILE_T = "AdES-T";

  /**
   * AdES-C:Complete. Añade referencias sobre los certificados utilizados y las
   * listas de revocación asociadas a los mismos.
   */
  public static final String SIGNPROFILE_C = "AdES-C";

  /**
   * AdES-X:Extended. Añade sellos de tiempo sobre las referencias introducidas
   * por los formatos AdES–C. Este formato,a su vez,puede dividirse en 2, en
   * función del contenido sellado por los sellos de tiempo: X1 y X2
   */
  public static final String SIGNPROFILE_X = "AdES-X";

  /**
   * AdES-X1: El sello de tiempo se calcula sobre la estructura
   * AdES-C(formatopordefecto).
   */
  public static final String SIGNPROFILE_X1 = "AdES-X1";

  /**
   * AdES-X2:El sello de tiempo se calcula sobre las referencias de los
   * certificados y evidencias de revocación.
   */
  public static final String SIGNPROFILE_X2 = "AdES-X2";

  /**
   * AdES-XL: ExtendedLong-Term. Añade los propios certificados (cadenas de
   * certificación) y listas de revocación.A su vez,al igual que con el formato
   * AdES-X, puede subdividirse en 2 formatos: XL1 y XL2
   */
  public static final String SIGNPROFILE_XL = "AdES-XL";

  /**
   * AdES-XL1:Si se ha tomado como base para su generación una firma con formato
   * AdES-X1(formatopordefecto).
   */
  public static final String SIGNPROFILE_XL1 = "AdES-XL1";

  /**
   * AdES-XL2: Si se ha tomado como base para sugeneración una firma con formato
   * AdES-X2.
   */
  public static final String SIGNPROFILE_XL2 = "AdES-XL2";

  /**
   * AdES-A:Archival.Permite la posibilidad de añadir periódicamente sellos de
   * tiempo sobre la firma para alargar lavigencia de la misma.
   *
   */
  public static final String SIGNPROFILE_A = "AdES-A";

  /**
   * PAdES-LTV: Long term validation (Segell temps): NOMES PER PADES. Además de
   * tener al menos una firma con el formato de los anteriores contenida en el
   * documento PDF, debe poseer al menos un diccionario de sello de tiempo que
   * selle todos los datos contenidos en el documento PDF. Adicionalmente, puede
   * incluir un diccionario DSS con todos los elementos que permiten la
   * validación de las firmas contenidas en el documentoPDF.
   */
  public static final String SIGNPROFILE_PADES_LTV = "PAdES-LTV";

  /**
   * PAdES-Basic: Evolución de la firma PDF recogida en la ISO32000-1 obligando
   * a que la firma electrónica sea CMS.
   */
  public static final String SIGNPROFILE_PADES_BASIC = "PAdES-Basic";
}
