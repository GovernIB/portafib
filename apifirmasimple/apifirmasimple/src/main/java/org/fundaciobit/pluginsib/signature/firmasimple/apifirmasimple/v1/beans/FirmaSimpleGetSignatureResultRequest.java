package org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FirmaSimpleGetSignatureResultRequest {

  protected String transactionID;

  protected String signID;

  /**
   * 
   */
  public FirmaSimpleGetSignatureResultRequest() {
    super();
  }

  /**
   * @param transactionID
   * @param signID
   */
  public FirmaSimpleGetSignatureResultRequest(String transactionID, String signID) {
    super();
    this.transactionID = transactionID;
    this.signID = signID;
  }

  public String getTransactionID() {
    return transactionID;
  }

  public void setTransactionID(String transactionID) {
    this.transactionID = transactionID;
  }

  public String getSignID() {
    return signID;
  }

  public void setSignID(String signID) {
    this.signID = signID;
  }

}
