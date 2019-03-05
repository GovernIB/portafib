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
public class FirmaSimpleSignDocumentResponse {

  protected FirmaSimpleStatus statusSignatureProcess;

  protected FirmaSimpleSignatureResult result;

  /**
   * 
   */
  public FirmaSimpleSignDocumentResponse() {
    super();
  }

  /**
   * @param results
   */
  public FirmaSimpleSignDocumentResponse(FirmaSimpleStatus statusSignatureProcess,
      FirmaSimpleSignatureResult result) {
    super();
    this.statusSignatureProcess = statusSignatureProcess;
    this.result = result;
  }

  public FirmaSimpleSignatureResult getResult() {
    return result;
  }

  public void setResult(FirmaSimpleSignatureResult result) {
    this.result = result;
  }

  public FirmaSimpleStatus getStatusSignatureProcess() {
    return statusSignatureProcess;
  }

  public void setStatusSignatureProcess(FirmaSimpleStatus statusSignatureProcess) {
    this.statusSignatureProcess = statusSignatureProcess;
  }

}
