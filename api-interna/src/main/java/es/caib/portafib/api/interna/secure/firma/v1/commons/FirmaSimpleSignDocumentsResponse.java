package es.caib.portafib.api.interna.secure.firma.v1.commons;

import java.util.List;

import es.caib.portafib.api.interna.secure.firma.v1.FirmaSimpleSignatureRest;


/**
 * 
 * @author anadal
 *
 */
public class FirmaSimpleSignDocumentsResponse {

  protected FirmaSimpleStatus statusSignatureProcess;

  protected List<FirmaSimpleSignatureRest> results;

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
      List<FirmaSimpleSignatureRest> results) {
    super();
    this.statusSignatureProcess = statusSignatureProcess;
    this.results = results;
  }

  public List<FirmaSimpleSignatureRest> getResults() {
    return results;
  }

  public void setResults(List<FirmaSimpleSignatureRest> results) {
    this.results = results;
  }

  public FirmaSimpleStatus getStatusSignatureProcess() {
    return statusSignatureProcess;
  }

  public void setStatusSignatureProcess(FirmaSimpleStatus statusSignatureProcess) {
    this.statusSignatureProcess = statusSignatureProcess;
  }

}