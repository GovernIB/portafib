package org.fundaciobit.apifirmawebsimple.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
public class FirmaSimpleSignaturesSet {

  String transactionID;

  FirmaSimpleFileInfoSignature[] fileInfoSignatureArray;

  /**
   * 
   */
  public FirmaSimpleSignaturesSet() {
  }

  /**
   * @param transactionID
   * @param fileInfoSignatureArray
   */
  public FirmaSimpleSignaturesSet(String transactionID,
      FirmaSimpleFileInfoSignature[] fileInfoSignatureArray) {
    super();
    this.transactionID = transactionID;
    this.fileInfoSignatureArray = fileInfoSignatureArray;
  }

  public FirmaSimpleFileInfoSignature[] getFileInfoSignatureArray() {
    return fileInfoSignatureArray;
  }

  public void setFileInfoSignatureArray(FirmaSimpleFileInfoSignature[] fileInfoSignatureArray) {
    this.fileInfoSignatureArray = fileInfoSignatureArray;
  }

  public String getTransactionID() {
    return transactionID;
  }

  public void setTransactionID(String transactionID) {
    this.transactionID = transactionID;
  }

}
