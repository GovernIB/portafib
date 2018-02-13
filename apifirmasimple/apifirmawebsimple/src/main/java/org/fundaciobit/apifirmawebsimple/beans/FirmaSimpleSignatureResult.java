package org.fundaciobit.apifirmawebsimple.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Resultat d'una firma
 * 
 * @author anadal
 *
 */
@XmlRootElement
public class FirmaSimpleSignatureResult extends FirmaSimpleSignatureStatus {

  protected String signID;

  protected FirmaSimpleFile signedFile;

  /**
   * 
   */
  public FirmaSimpleSignatureResult() {
    super();
  }


  /**
   * 
   * @param status
   * @param errorMessage
   * @param errorStackTrace
   * @param signID
   * @param signedFile
   */
  public FirmaSimpleSignatureResult(int status, String errorMessage, String errorStackTrace,
      String signID, FirmaSimpleFile signedFile) {
    super(status, errorMessage, errorStackTrace);
    this.signedFile = signedFile;
    this.signID = signID;

  }

  public FirmaSimpleFile getSignedFile() {
    return signedFile;
  }

  public void setSignedFile(FirmaSimpleFile signedFile) {
    this.signedFile = signedFile;
  }

  public String getSignID() {
    return signID;
  }

  public void setSignID(String signID) {
    this.signID = signID;
  }

}
