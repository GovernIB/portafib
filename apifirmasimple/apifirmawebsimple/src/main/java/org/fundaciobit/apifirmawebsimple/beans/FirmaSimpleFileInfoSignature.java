package org.fundaciobit.apifirmawebsimple.beans;

import java.util.Map;

/**
 *
 * @author anadal
 *
 */
public class FirmaSimpleFileInfoSignature {

  public static final String OPERATION_FIRMA = "FIRMA";

  public static final String OPERATION_COFIRMA = "COFIRMA";

  public static final String OPERATION_CONTRAFIRMA = "CONTRAFIRMA";

  protected FirmaSimpleFile fileToSign;

  /**
   * Només per CAdES i XAdEs Detached amb firma prèvia
   */
  protected FirmaSimpleFile previusSignatureDetachedFile = null;

  protected String signID;

  protected String name;

  protected String reason;

  protected String location;

  protected String signerEmail;

  protected int signNumber;

  protected String languageSign;

  protected String operationSign = OPERATION_FIRMA;

  protected Map<String, String> additionalInformation = null;

  /**
   * 
   */
  public FirmaSimpleFileInfoSignature() {
    super();
  }

  /**
   * @param fileToSign
   * @param signID
   * @param name
   * @param reason
   * @param location
   * @param signerEmail
   * @param signNumber
   * @param languageSign
   */
  public FirmaSimpleFileInfoSignature(FirmaSimpleFile fileToSign, String signID, String name,
      String reason, String location, String signerEmail, int signNumber, String languageSign) {
    super();
    this.fileToSign = fileToSign;
    this.signID = signID;
    this.name = name;
    this.reason = reason;
    this.location = location;
    this.signerEmail = signerEmail;
    this.signNumber = signNumber;
    this.languageSign = languageSign;
  }

  /**
   * @param fileToSign
   * @param previusSignatureFile
   * @param signID
   * @param name
   * @param reason
   * @param location
   * @param signerEmail
   * @param signNumber
   * @param languageSign
   * @param operationSign
   * @param additionalInformation
   */
  public FirmaSimpleFileInfoSignature(FirmaSimpleFile fileToSign,
      FirmaSimpleFile previusSignatureDetachedFile, String signID, String name, String reason,
      String location, String signerEmail, int signNumber, String languageSign,
      String operationSign, Map<String, String> additionalInformation) {
    super();
    this.fileToSign = fileToSign;
    this.previusSignatureDetachedFile = previusSignatureDetachedFile;
    this.signID = signID;
    this.name = name;
    this.reason = reason;
    this.location = location;
    this.signerEmail = signerEmail;
    this.signNumber = signNumber;
    this.languageSign = languageSign;
    this.operationSign = operationSign;
    this.additionalInformation = additionalInformation;
  }

  public FirmaSimpleFile getFileToSign() {
    return fileToSign;
  }

  public void setFileToSign(FirmaSimpleFile fileToSign) {
    this.fileToSign = fileToSign;
  }

  public String getSignID() {
    return signID;
  }

  public void setSignID(String signID) {
    this.signID = signID;
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

  public String getOperationSign() {
    return operationSign;
  }

  public void setOperationSign(String operationSign) {
    this.operationSign = operationSign;
  }

  public FirmaSimpleFile getPreviusSignatureDetachedFile() {
    return previusSignatureDetachedFile;
  }

  public void setPreviusSignatureDetachedFile(FirmaSimpleFile previusSignatureDetachedFile) {
    this.previusSignatureDetachedFile = previusSignatureDetachedFile;
  }

  public Map<String, String> getAdditionalInformation() {
    return additionalInformation;
  }

  public void setAdditionalInformation(Map<String, String> additionalInformation) {
    this.additionalInformation = additionalInformation;
  }

}
