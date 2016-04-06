package es.caib.portafib.logic.passarela;

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
public class PassarelaSignaturesSet {
  
  protected String signaturesSetID;

  /** Data en que la transaccio caduca. Com a màxim 2 hores després de l'inici de la transacció. */
  protected Date expiryDate;

  protected PassarelaCommonInfoSignature commonInfoSignature;

  
  protected PassarelaFileInfoSignature[] fileInfoSignatureArray;

  /**
   * 
   */
  public PassarelaSignaturesSet() {
  }

  /**
   * @param transactionID
   * @param commonInfoSignature
   * @param fileInfoSignatureArray
   */
  public PassarelaSignaturesSet(String signaturesSetID, Date expiryDate,
      PassarelaCommonInfoSignature commonInfoSignature, PassarelaFileInfoSignature[] fileInfoSignatureArray) {
    super();
    this.signaturesSetID = signaturesSetID;
    this.expiryDate = expiryDate;
    this.commonInfoSignature = commonInfoSignature;
    this.fileInfoSignatureArray = fileInfoSignatureArray;
  }


  public PassarelaCommonInfoSignature getCommonInfoSignature() {
    return commonInfoSignature;
  }

  public void setCommonInfoSignature(PassarelaCommonInfoSignature commonInfoSignature) {
    this.commonInfoSignature = commonInfoSignature;
  }


  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  public PassarelaFileInfoSignature[] getFileInfoSignatureArray() {
    return fileInfoSignatureArray;
  }

  public void setFileInfoSignatureArray(PassarelaFileInfoSignature[] fileInfoSignatureArray) {
    this.fileInfoSignatureArray = fileInfoSignatureArray;
  }

  public String getSignaturesSetID() {
    return signaturesSetID;
  }

  public void setSignaturesSetID(String signaturesSetID) {
    this.signaturesSetID = signaturesSetID;
  }

  
}
