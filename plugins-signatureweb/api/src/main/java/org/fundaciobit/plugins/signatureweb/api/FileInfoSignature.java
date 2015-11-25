package org.fundaciobit.plugins.signatureweb.api;

import java.io.File;

/**
 * 
 * @author anadal
 *
 */
public class FileInfoSignature {

  /** Identificador de la firma PAdES. */
  public static final String SIGN_TYPE_PADES = "PAdES";
  /** Identificador de la firma XAdES por defecto. */
  public static final String SIGN_TYPE_XADES =  "XAdES";
  /** Identificador de la firma CAdES. */
  public static final String SIGN_TYPE_CADES = "CAdES";
  /** Identificador de la firma Factura-e (derivado de XAdES-EPES). */
  public static final String SIGN_TYPE_FACTURAE = "FacturaE"; //$NON-NLS-1$
  /** Identificador de la firma OOXML (<i>Office Open XML</i>). */
  public static final String SIGN_TYPE_OOXML = "OOXML"; //$NON-NLS-1$
  /** Identificador de la firma ODF (<i>Open Document Format</i>). */
  public static final String SIGN_TYPE_ODF = "ODF"; //$NON-NLS-1$
  
  public static final String SIGN_ALGORITHM_SHA1="SHA-1";
  public static final String SIGN_ALGORITHM_SHA256="SHA-256";
  public static final String SIGN_ALGORITHM_SHA384="SHA-384";
  public static final String SIGN_ALGORITHM_SHA512="SHA-512";

  /*
  implicit
  La firma resultante incluirá internamente una copia de los datos firmados.
  El uso de este valor podría generar firmas de gran tamaño.
  */
  public static final int SIGN_MODE_IMPLICIT = 0;
  /*
  explicit
  La firma resultante no incluirá los datos firmados. Si no se indica el parámetro
  mode se configura automáticamente este comportamiento.
  */
  public static final int SIGN_MODE_EXPLICIT = 1;


  File source;
  
  String signID;

  String name;

  String reason;

  String firmatPerFormat;

  int signNumber;

  String languageSign;

  String signType;

  String signAlgorithm;

  int signMode;

  PdfInfoSignature pdfInfoSignature;
  
  
  ITimeStampGenerator timeStampGenerator = null; 


  /**
   * 
   */
  public FileInfoSignature() {
    super();
  }

  /**
   * @param source
   * @param name
   * @param reason
   * @param firmatPerFormat
   * @param signNumber
   * @param languageSign
   * @param signType
   * @param signAlgorithm
   * @param signMode
   * @param pdfInfoSignature
   */
  public FileInfoSignature(String signID, File source, String name, String reason, String firmatPerFormat,
      int signNumber, String languageSign, String signType, String signAlgorithm,
      int signMode, PdfInfoSignature pdfInfoSignature, ITimeStampGenerator timeStampGenerator) {
    super();
    setValues(signID, source, name, reason, firmatPerFormat, signNumber, languageSign, signType,
        signAlgorithm, signMode, pdfInfoSignature, timeStampGenerator);
  }
  
  
  /**
   * @param source
   * @param name
   * @param reason
   * @param firmatPerFormat
   * @param signNumber
   * @param languageSign
   * @param signType
   * @param signAlgorithm
   * @param signMode
   * @param pdfInfoSignature
   */
  public void setValues(String signID, File source, String name, String reason, String firmatPerFormat,
      int signNumber, String languageSign, String signType, String signAlgorithm,
      int signMode, PdfInfoSignature pdfInfoSignature, ITimeStampGenerator timeStampGenerator) {
    this.signID = signID;
    this.source = source;
    this.name = name;
    this.reason = reason;
    this.firmatPerFormat = firmatPerFormat;
    this.signNumber = signNumber;
    this.languageSign = languageSign;
    this.signType = signType;
    this.signAlgorithm = signAlgorithm;
    this.signMode = signMode;
    this.pdfInfoSignature = pdfInfoSignature;
    this.timeStampGenerator = timeStampGenerator;
  }

  public String getSignID() {
    return signID;
  }

  public void setSignID(String signID) {
    this.signID = signID;
  }

  public File getSource() {
    return source;
  }

  public void setSource(File source) {
    this.source = source;
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

  public String getFirmatPerFormat() {
    return firmatPerFormat;
  }

  public void setFirmatPerFormat(String firmatPerFormat) {
    this.firmatPerFormat = firmatPerFormat;
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

  public PdfInfoSignature getPdfInfoSignature() {
    return pdfInfoSignature;
  }

  public void setPdfInfoSignature(PdfInfoSignature pdfInfoSignature) {
    this.pdfInfoSignature = pdfInfoSignature;
  }
  
  public ITimeStampGenerator getTimeStampGenerator() {
    return timeStampGenerator;
  }

  public void setTimeStampGenerator(ITimeStampGenerator timeStampGenerator) {
    this.timeStampGenerator = timeStampGenerator;
  }

}
