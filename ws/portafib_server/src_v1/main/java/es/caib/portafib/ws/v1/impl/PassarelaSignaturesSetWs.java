package es.caib.portafib.ws.v1.impl;

import java.util.Date;

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
@javax.xml.bind.annotation.XmlType(name="passarelaSignaturesSet")
public class PassarelaSignaturesSetWs {
  
  protected String signaturesSetID;

  /** Data en que la transaccio caduca. Com a màxim 2 hores després de l'inici de la transacció. */
  protected Date expiryDate;

  protected PassarelaCommonInfoSignatureWs commonInfoSignature;

  
  protected PassarelaFileInfoSignatureWs[] fileInfoSignatureArray;

  /**
   * 
   */
  public PassarelaSignaturesSetWs() {
  }

  /**
   * @param transactionID
   * @param commonInfoSignature
   * @param fileInfoSignatureArray
   */
  public PassarelaSignaturesSetWs(String signaturesSetID, Date expiryDate,
      PassarelaCommonInfoSignatureWs commonInfoSignature, PassarelaFileInfoSignatureWs[] fileInfoSignatureArray) {
    super();
    this.signaturesSetID = signaturesSetID;
    this.expiryDate = expiryDate;
    this.commonInfoSignature = commonInfoSignature;
    this.fileInfoSignatureArray = fileInfoSignatureArray;
  }


  public PassarelaCommonInfoSignatureWs getCommonInfoSignature() {
    return commonInfoSignature;
  }

  public void setCommonInfoSignature(PassarelaCommonInfoSignatureWs commonInfoSignature) {
    this.commonInfoSignature = commonInfoSignature;
  }


  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  public PassarelaFileInfoSignatureWs[] getFileInfoSignatureArray() {
    return fileInfoSignatureArray;
  }

  public void setFileInfoSignatureArray(PassarelaFileInfoSignatureWs[] fileInfoSignatureArray) {
    this.fileInfoSignatureArray = fileInfoSignatureArray;
  }

  public String getSignaturesSetID() {
    return signaturesSetID;
  }

  public void setSignaturesSetID(String signaturesSetID) {
    this.signaturesSetID = signaturesSetID;
  }

  
}
