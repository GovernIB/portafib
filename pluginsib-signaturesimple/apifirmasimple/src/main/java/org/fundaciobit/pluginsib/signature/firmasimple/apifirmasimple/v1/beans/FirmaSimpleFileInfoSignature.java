package org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author anadal
 *
 */
@XmlRootElement
public class FirmaSimpleFileInfoSignature {

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

  protected String expedientCodi;

  protected String expedientNom;

  protected String expedientUrl;

  protected String procedimentCodi;

  protected String procedimentNom;

  protected List<FirmaSimpleKeyValue> additionalInformation = null;

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
  public FirmaSimpleFileInfoSignature(FirmaSimpleFile fileToSign,
      String signID, String name, String reason, String location,
      String signerEmail, int signNumber, String languageSign) {
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
      FirmaSimpleFile previusSignatureDetachedFile, String signID, String name,
      String reason, String location, String signerEmail, int signNumber,
      String languageSign, List<FirmaSimpleKeyValue> additionalInformation) {
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

  public FirmaSimpleFile getPreviusSignatureDetachedFile() {
    return previusSignatureDetachedFile;
  }

  public void setPreviusSignatureDetachedFile(
      FirmaSimpleFile previusSignatureDetachedFile) {
    this.previusSignatureDetachedFile = previusSignatureDetachedFile;
  }

  public List<FirmaSimpleKeyValue> getAdditionalInformation() {
    return additionalInformation;
  }

  public void setAdditionalInformation(
      List<FirmaSimpleKeyValue> additionalInformation) {
    this.additionalInformation = additionalInformation;
  }

  public String getExpedientCodi() {
    return expedientCodi;
  }

  public void setExpedientCodi(String expedientCodi) {
    this.expedientCodi = expedientCodi;
  }

  public String getExpedientNom() {
    return expedientNom;
  }

  public void setExpedientNom(String expedientNom) {
    this.expedientNom = expedientNom;
  }

  public String getExpedientUrl() {
    return expedientUrl;
  }

  public void setExpedientUrl(String expedientUrl) {
    this.expedientUrl = expedientUrl;
  }

  public String getProcedimentCodi() {
    return procedimentCodi;
  }

  public void setProcedimentCodi(String procedimentCodi) {
    this.procedimentCodi = procedimentCodi;
  }

  public String getProcedimentNom() {
    return procedimentNom;
  }

  public void setProcedimentNom(String procedimentNom) {
    this.procedimentNom = procedimentNom;
  }

}
