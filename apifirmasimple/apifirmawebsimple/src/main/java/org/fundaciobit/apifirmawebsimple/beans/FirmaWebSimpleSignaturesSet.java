package org.fundaciobit.apifirmawebsimple.beans;

/**
 * 
 * @author anadal
 *
 */
public class FirmaWebSimpleSignaturesSet extends FirmaSimpleSignaturesSet {

  public static final String VIEW_FULLSCREEN = "fullview";
  
  public static final String VIEW_IFRAME = "iframe";
  
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
    super(transactionID, fileInfoSignatureArray);

    this.returnUrl = returnUrl;
    this.view = view;
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

}
