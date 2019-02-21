package org.fundaciobit.apisib.apifirmasimple.v1.beans;

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
public class FirmaSimpleAddFileToSignRequest {

  String transactionID;

  FirmaSimpleFileInfoSignature fileInfoSignature;

  /**
   *
   */
  public FirmaSimpleAddFileToSignRequest() {
    super();
  }



  /**
   * @param transactionID
   * @param fileInfoSignature
   */
  public FirmaSimpleAddFileToSignRequest(String transactionID,
      FirmaSimpleFileInfoSignature fileInfoSignature) {
    super();
    this.transactionID = transactionID;
    this.fileInfoSignature = fileInfoSignature;
  }



  public String getTransactionID() {
    return transactionID;
  }



  public void setTransactionID(String transactionID) {
    this.transactionID = transactionID;
  }



  public FirmaSimpleFileInfoSignature getFileInfoSignature() {
    return fileInfoSignature;
  }

  public void setFileInfoSignature(FirmaSimpleFileInfoSignature fileInfoSignature) {
    this.fileInfoSignature = fileInfoSignature;
  }

}
