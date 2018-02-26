package org.fundaciobit.apifirmawebsimple.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
public class FirmaSimpleSignatureFullResults {

  FirmaSimpleSignatureStatus statusSignatureProcess;

  List<FirmaSimpleSignatureResult> results;

  /**
   * 
   */
  public FirmaSimpleSignatureFullResults() {
    super();
  }

  /**
   * @param results
   */
  public FirmaSimpleSignatureFullResults(FirmaSimpleSignatureStatus statusSignatureProcess,
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

  public FirmaSimpleSignatureStatus getStatusSignatureProcess() {
    return statusSignatureProcess;
  }

  public void setStatusSignatureProcess(FirmaSimpleSignatureStatus statusSignatureProcess) {
    this.statusSignatureProcess = statusSignatureProcess;
  }

}
