package org.fundaciobit.apifirmasimple.v1.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
public class FirmaSimpleSignDocumentsResponse {

  protected FirmaSimpleStatus statusSignatureProcess;

  protected List<FirmaSimpleSignatureResult> results;

  /**
   * 
   */
  public FirmaSimpleSignDocumentsResponse() {
    super();
  }

  /**
   * @param results
   */
  public FirmaSimpleSignDocumentsResponse(FirmaSimpleStatus statusSignatureProcess,
      List<FirmaSimpleSignatureResult> results) {
    super();
    this.statusSignatureProcess = statusSignatureProcess;
    this.results = results;
  }

  public List<FirmaSimpleSignatureResult> getResults() {
    return results;
  }

  public void setResults(List<FirmaSimpleSignatureResult> results) {
    this.results = results;
  }

  public FirmaSimpleStatus getStatusSignatureProcess() {
    return statusSignatureProcess;
  }

  public void setStatusSignatureProcess(FirmaSimpleStatus statusSignatureProcess) {
    this.statusSignatureProcess = statusSignatureProcess;
  }

}
