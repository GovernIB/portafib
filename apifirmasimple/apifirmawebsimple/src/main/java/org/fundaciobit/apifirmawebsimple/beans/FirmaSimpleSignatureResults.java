package org.fundaciobit.apifirmawebsimple.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
public class FirmaSimpleSignatureResults {

  List<FirmaSimpleSignatureResult> results;

  /**
   * 
   */
  public FirmaSimpleSignatureResults() {
    super();
  }

  /**
   * @param results
   */
  public FirmaSimpleSignatureResults(List<FirmaSimpleSignatureResult> results) {
    super();
    this.results = results;
  }

  public List<FirmaSimpleSignatureResult> getResults() {
    return results;
  }

  public void setResults(List<FirmaSimpleSignatureResult> results) {
    this.results = results;
  }

}
