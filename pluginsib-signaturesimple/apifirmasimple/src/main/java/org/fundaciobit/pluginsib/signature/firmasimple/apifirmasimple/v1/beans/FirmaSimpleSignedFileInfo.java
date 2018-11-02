package org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaSimpleSignedFileInfo {

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

  public static final String SIGN_ALGORITHM_SHA1 = "SHA-1";
  public static final String SIGN_ALGORITHM_SHA256 = "SHA-256";
  public static final String SIGN_ALGORITHM_SHA384 = "SHA-384";
  public static final String SIGN_ALGORITHM_SHA512 = "SHA-512";

  /*
   * implicit La firma resultante incluirá internamente una copia de los datos firmados. El uso
   * de este valor podría generar firmas de gran tamaño.
   */
  public static final int SIGN_MODE_IMPLICIT_ATTACHED = 0;
  /*
   * explicit La firma resultante no incluirá los datos firmados. Si no se indica el parámetro
   * mode se configura automáticamente este comportamiento.
   */
  public static final int SIGN_MODE_EXPLICIT_DETACHED = 1;

  public static final int SIGNATURESTABLELOCATION_WITHOUT = 0;
  public static final int SIGNATURESTABLELOCATION_FIRSTPAGE = 1;
  public static final int SIGNATURESTABLELOCATION_LASTPAGE = -1;

  // FIRMA
  public static final int SIGN_OPERATION_SIGN = 0;
  // COFIRMA
  public static final int SIGN_OPERATION_COSIGN = 1;
  // CONTRAFIRMA
  public static final int SIGN_OPERATION_COUNTERSIGN = 2;

  protected int signOperation;

  protected String signType;

  protected String signAlgorithm;

  protected int signMode;

  protected int signaturesTableLocation;

  protected boolean timeStampIncluded;

  // BES o EPES
  protected boolean policyIncluded;

  protected java.lang.String custodyFileID;

  protected java.lang.String custodyFileURL;

  public FirmaSimpleSignedFileInfo() {
    super();
  }

  public FirmaSimpleSignedFileInfo(int signOperation, String signType, String signAlgorithm,
      int signMode, int signaturesTableLocation, boolean timeStampIncluded,
      boolean policyIncluded, String custodyFileID, String custodyFileURL) {
    super();
    this.signOperation = signOperation;
    this.signType = signType;
    this.signAlgorithm = signAlgorithm;
    this.signMode = signMode;
    this.signaturesTableLocation = signaturesTableLocation;
    this.timeStampIncluded = timeStampIncluded;
    this.policyIncluded = policyIncluded;
    this.custodyFileID = custodyFileID;
    this.custodyFileURL = custodyFileURL;
  }

  public int getSignOperation() {
    return signOperation;
  }

  public void setSignOperation(int signOperation) {
    this.signOperation = signOperation;
  }

  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }

  public String getSignAlgorithm() {
    return signAlgorithm;
  }

  public void setSignAlgorithm(String signAlgorithm) {
    this.signAlgorithm = signAlgorithm;
  }

  public int getSignMode() {
    return signMode;
  }

  public void setSignMode(int signMode) {
    this.signMode = signMode;
  }

  public int getSignaturesTableLocation() {
    return signaturesTableLocation;
  }

  public void setSignaturesTableLocation(int signaturesTableLocation) {
    this.signaturesTableLocation = signaturesTableLocation;
  }

  public boolean isTimeStampIncluded() {
    return timeStampIncluded;
  }

  public void setTimeStampIncluded(boolean timeStampIncluded) {
    this.timeStampIncluded = timeStampIncluded;
  }

  public boolean isPolicyIncluded() {
    return policyIncluded;
  }

  public void setPolicyIncluded(boolean policyIncluded) {
    this.policyIncluded = policyIncluded;
  }

  public java.lang.String getCustodyFileID() {
    return custodyFileID;
  }

  public void setCustodyFileID(java.lang.String custodyFileID) {
    this.custodyFileID = custodyFileID;
  }

  public java.lang.String getCustodyFileURL() {
    return custodyFileURL;
  }

  public void setCustodyFileURL(java.lang.String custodyFileURL) {
    this.custodyFileURL = custodyFileURL;
  }

}
