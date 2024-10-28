package es.caib.portafib.api.interna.secure.apisimple.v1.commons;


/**
 * 
 * @author anadal
 *
 */

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
