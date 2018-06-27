package org.fundaciobit.plugins.signature.api.constants;

/**
 * <p>
 * Class that represents signature types identifiers.
 * </p>
 * <b>Project:</b>
 * <p>
 * Library for the integration with the services of @Firma, eVisor and TS@.
 * </p>
 * 
 * @version 1.0, 07/02/2011.
 */
public class SignatureTypeForUpgrade {

  /**
   * Constructor method for the class DSSContants.java.
   */
  private SignatureTypeForUpgrade() {
  }

  /** */
  /**
   * Attribute that represents identifier for XADES signature 1.3.2. version.
   */
  public static final String XADES_V_1_3_2 = "http://uri.etsi.org/01903/v1.3.2#";

  /**
   * Attribute that represents identifier for XADES signature 1.2.2. version.
   */
  public static final String XADES_V_1_2_2 = "http://uri.etsi.org/01903/v1.2.2#";

  /**
   * Attribute that represents identifier for XADES signature 1.1.1. version.
   */
  public static final String XADES_V_1_1_1 = "http://uri.etsi.org/01903/v1.1.1#";

  /**
   * Attribute that represents identifier for CADES.
   */
  public static final String CADES = "http://uri.etsi.org/01733/v1.7.3#";

  /**
   * Attribute that represents identifier for XML_DSIG.
   */
  public static final String XML_DSIG = "urn:ietf:rfc:3275";

  /**
   * Attribute that represents identifier for CMS.
   */
  public static final String CMS = "urn:ietf:rfc:3369";

  /**
   * Attribute that represents identifier for CMS(TST).
   */
  public static final String CMS_TST = "urn:afirma:dss:1.0:profile:XSS:forms:CMSWithTST";

  /**
   * Attribute that represents identifier for PKCS7.
   */
  public static final String PKCS7 = "urn:ietf:rfc:2315";

  /**
   * Attribute that represents identifier for XML_TST.
   */
  public static final String XML_TST = "urn:oasis:names:tc:dss:1.0:core:schema:XMLTimeStampToken";

  /**
   * Attribute that represents identifier for ODF.
   */
  public static final String ODF = "urn:afirma:dss:1.0:profile:XSS:forms:ODF";

  /**
   * Attribute that represents identifier for PDF.
   */
  public static final String PDF = "urn:afirma:dss:1.0:profile:XSS:forms:PDF";

  /**
   * Attribute that represents identifier for PAdES.
   */
  public static final String PADES = "urn:afirma:dss:1.0:profile:XSS:forms:PAdES";

  /**
   * Attribute that represents identifier for CAdES Baseline.
   */
  public static final String CADES_BASELINE_2_2_1 = "http://uri.etsi.org/103173/v2.2.1#";

  /**
   * Attribute that represents identifier for PAdES Baseline.
   */
  public static final String PADES_BASELINE_2_1_1 = "http://uri.etsi.org/103172/v2.1.1#";

  /**
   * Attribute that represents identifier for XAdES Baseline.
   */
  public static final String XADES_BASELINE_2_1_1 = "http://uri.etsi.org/103171/v2.1.1#";

}
