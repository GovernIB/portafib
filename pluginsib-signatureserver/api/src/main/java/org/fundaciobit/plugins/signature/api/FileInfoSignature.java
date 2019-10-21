package org.fundaciobit.plugins.signature.api;

import java.io.File;


/**
 * 
 * @author anadal
 * @author areus
 */
public class FileInfoSignature {

  public static final String PDF_MIME_TYPE = "application/pdf";

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
   * implicit La firma resultante incluirá internamente una copia de los datos
   * firmados. El uso de este valor podría generar firmas de gran tamaño.
   */
  public static final int SIGN_MODE_IMPLICIT = 0;
  /*
   * explicit La firma resultante no incluirá los datos firmados. Si no se
   * indica el parámetro mode se configura automáticamente este comportamiento.
   */
  public static final int SIGN_MODE_EXPLICIT = 1;

  public static final int SIGNATURESTABLELOCATION_WITHOUT = 0;
  public static final int SIGNATURESTABLELOCATION_FIRSTPAGE = 1;
  public static final int SIGNATURESTABLELOCATION_LASTPAGE = -1;
  
  // FIRMA
  public static final int SIGN_OPERATION_SIGN = 0;
  // COFIRMA
  public static final int SIGN_OPERATION_COSIGN = 1;
  // CONTRAFIRMA
  public static final int SIGN_OPERATION_COUNTERSIGN = 2;

  String signID;

  File fileToSign;
  
  /**
   * Només per CAdES i XAdEs Detached amb firma prèvia
   */
  protected File previusSignatureDetachedFile = null;

  String mimeType;

  String name;

  String reason;

  String location;

  String signerEmail;

  int signNumber;

  String languageSign;
  
  int signOperation;

  String signType;

  String signAlgorithm;

  int signMode;

  int signaturesTableLocation = 0;
  
  /** 
   * Només necessaria en la primera firma.
   */
  protected SignaturesTableHeader signaturesTableHeader;

  PdfVisibleSignature pdfVisibleSignature;

  boolean userRequiresTimeStamp;

  ITimeStampGenerator timeStampGenerator = null;

  SecureVerificationCodeStampInfo secureVerificationCodeStampInfo;
  
  StatusSignature statusSignature = new StatusSignature();

  PolicyInfoSignature policyInfoSignature = null;

  String expedientCode;
  String expedientName;
  String expedientUrl;
  String procedureCode;
  String procedureName;

  /**
   * 
   */
  public FileInfoSignature() {
    super();
  }

  /**
   *
   */
  @Deprecated
  public FileInfoSignature(String signID, File fileToSign, String mimeType, String name,
      String reason, String location, String signerEmail, int signNumber, String languageSign,
      String signType, String signAlgorithm, int signMode,
      int signaturesTableLocation, SignaturesTableHeader signaturesTableHeader,
      PdfVisibleSignature pdfVisibleSignature, SecureVerificationCodeStampInfo secureVerificationCodeStampInfo,
      boolean userRequiresTimeStamp, ITimeStampGenerator timeStampGenerator) {
    super();
    this.signID = signID;
    this.fileToSign = fileToSign;
    this.mimeType = mimeType;
    this.name = name;
    this.reason = reason;
    this.location = location;
    this.signerEmail = signerEmail;
    this.signNumber = signNumber;
    this.languageSign = languageSign;
    this.signType = signType;
    this.signAlgorithm = signAlgorithm;
    this.signMode = signMode;
    this.signaturesTableLocation = signaturesTableLocation;
    this.signaturesTableHeader = signaturesTableHeader;
    this.pdfVisibleSignature = pdfVisibleSignature;
    this.secureVerificationCodeStampInfo=secureVerificationCodeStampInfo;
    this.userRequiresTimeStamp = userRequiresTimeStamp;
    this.timeStampGenerator = timeStampGenerator;
  }


  /**
   * Constructor complet.
   */
  public FileInfoSignature(String signID, File fileToSign, File previusSignatureDetachedFile,
                           String mimeType, String name, String reason, String location, String signerEmail,
                           int signNumber, String languageSign, int signOperation, String signType,
                           String signAlgorithm, int signMode, int signaturesTableLocation,
                           SignaturesTableHeader signaturesTableHeader, PdfVisibleSignature pdfVisibleSignature,
                           SecureVerificationCodeStampInfo secureVerificationCodeStampInfo,
                           boolean userRequiresTimeStamp, ITimeStampGenerator timeStampGenerator,
                           PolicyInfoSignature policyInfoSignature, String expedientCode, String expedientName,
                           String expedientUrl, String procedureCode, String procedureName) {
    super();
    this.signID = signID;
    this.fileToSign = fileToSign;
    this.previusSignatureDetachedFile = previusSignatureDetachedFile;
    this.mimeType = mimeType;
    this.name = name;
    this.reason = reason;
    this.location = location;
    this.signerEmail = signerEmail;
    this.signNumber = signNumber;
    this.languageSign = languageSign;
    this.signOperation = signOperation;
    this.signType = signType;
    this.signAlgorithm = signAlgorithm;
    this.signMode = signMode;
    this.signaturesTableLocation = signaturesTableLocation;
    this.signaturesTableHeader = signaturesTableHeader;
    this.pdfVisibleSignature = pdfVisibleSignature;
    this.userRequiresTimeStamp = userRequiresTimeStamp;
    this.timeStampGenerator = timeStampGenerator;
    this.secureVerificationCodeStampInfo = secureVerificationCodeStampInfo;
    this.policyInfoSignature = policyInfoSignature;
    this.expedientCode = expedientCode;
    this.expedientName = expedientName;
    this.expedientUrl = expedientUrl;
    this.procedureCode = procedureCode;
    this.procedureName = procedureName;
  }

  public String getSignID() {
    return signID;
  }

  public void setSignID(String signID) {
    this.signID = signID;
  }

  public File getPreviusSignatureDetachedFile() {
    return previusSignatureDetachedFile;
  }

  public void setPreviusSignatureDetachedFile(File previusSignatureDetachedFile) {
    this.previusSignatureDetachedFile = previusSignatureDetachedFile;
  }

  public File getFileToSign() {
    return fileToSign;
  }

  public void setFileToSign(File fileToSign) {
    this.fileToSign = fileToSign;
  }

  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getSignerEmail() {
    return signerEmail;
  }

  public void setSignerEmail(String signerEmail) {
    this.signerEmail = signerEmail;
  }

  public int getSignNumber() {
    return signNumber;
  }

  public void setSignNumber(int signNumber) {
    this.signNumber = signNumber;
  }

  public String getLanguageSign() {
    return languageSign;
  }

  public void setLanguageSign(String languageSign) {
    this.languageSign = languageSign;
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
  
  public SignaturesTableHeader getSignaturesTableHeader() {
    return signaturesTableHeader;
  }

  public void setSignaturesTableHeader(SignaturesTableHeader signaturesTableHeader) {
    this.signaturesTableHeader = signaturesTableHeader;
  }

  public void setSignaturesTableLocation(int signaturesTableLocation) {
    this.signaturesTableLocation = signaturesTableLocation;
  }

  public PdfVisibleSignature getPdfVisibleSignature() {
    return pdfVisibleSignature;
  }

  public void setPdfVisibleSignature(PdfVisibleSignature pdfVisibleSignature) {
    this.pdfVisibleSignature = pdfVisibleSignature;
  }

  public boolean isUserRequiresTimeStamp() {
    return userRequiresTimeStamp;
  }

  public void setUserRequiresTimeStamp(boolean userRequiresTimeStamp) {
    this.userRequiresTimeStamp = userRequiresTimeStamp;
  }

  public ITimeStampGenerator getTimeStampGenerator() {
    return timeStampGenerator;
  }

  public void setTimeStampGenerator(ITimeStampGenerator timeStampGenerator) {
    this.timeStampGenerator = timeStampGenerator;
  }

  public StatusSignature getStatusSignature() {
    return statusSignature;
  }

  public void setStatusSignature(StatusSignature statusSignature) {
    this.statusSignature = statusSignature;
  }

  public SecureVerificationCodeStampInfo getSecureVerificationCodeStampInfo() {
    return secureVerificationCodeStampInfo;
  }

  public void setSecureVerificationCodeStampInfo(
      SecureVerificationCodeStampInfo secureVerificationCodeStampInfo) {
    this.secureVerificationCodeStampInfo = secureVerificationCodeStampInfo;
  }

  public PolicyInfoSignature getPolicyInfoSignature() {
    return policyInfoSignature;
  }

  public void setPolicyInfoSignature(PolicyInfoSignature policyInfoSignature) {
    this.policyInfoSignature = policyInfoSignature;
  }

  public String getExpedientCode() {
    return expedientCode;
  }

  public void setExpedientCode(String expedientCode) {
    this.expedientCode = expedientCode;
  }

  public String getExpedientName() {
    return expedientName;
  }

  public void setExpedientName(String expedientName) {
    this.expedientName = expedientName;
  }

  public String getExpedientUrl() {
    return expedientUrl;
  }

  public void setExpedientUrl(String expedientUrl) {
    this.expedientUrl = expedientUrl;
  }

  public String getProcedureCode() {
    return procedureCode;
  }

  public void setProcedureCode(String procedureCode) {
    this.procedureCode = procedureCode;
  }

  public String getProcedureName() {
    return procedureName;
  }

  public void setProcedureName(String procedureName) {
    this.procedureName = procedureName;
  }
}
