package org.fundaciobit.apifirmawebsimple.beans;

/**
 * 
 * @author anadal
 *
 */
public class FirmaWebSimpleSignaturesSet  {

  public static final String VIEW_FULLSCREEN = "fullview";
  
  public static final String VIEW_IFRAME = "iframe";
  
  FirmaSimpleFileInfoSignature[] fileInfoSignatureArray;
  
  String transactionID;
  
  String returnUrl;

  String view;

  /**
   * 
   */
  public FirmaWebSimpleSignaturesSet() {
    super();
  }

  /**
   * @param transactionID
   * @param fileInfoSignatureArray
   */
  public FirmaWebSimpleSignaturesSet(String transactionID,
      FirmaSimpleFileInfoSignature[] fileInfoSignatureArray, String returnUrl, String view) {
    this.fileInfoSignatureArray = fileInfoSignatureArray;

    this.transactionID = transactionID;
    this.returnUrl = returnUrl;
    this.view = view;
  }
  
  public FirmaSimpleFileInfoSignature[] getFileInfoSignatureArray() {
    return fileInfoSignatureArray;
  }

  public void setFileInfoSignatureArray(FirmaSimpleFileInfoSignature[] fileInfoSignatureArray) {
    this.fileInfoSignatureArray = fileInfoSignatureArray;
  }

  public String getReturnUrl() {
    return returnUrl;
  }

  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl;
  }

  public String getView() {
    return view;
  }

  public void setView(String view) {
    this.view = view;
  }
  
  public String getTransactionID() {
    return transactionID;
  }

  public void setTransactionID(String transactionID) {
    this.transactionID = transactionID;
  }


}
