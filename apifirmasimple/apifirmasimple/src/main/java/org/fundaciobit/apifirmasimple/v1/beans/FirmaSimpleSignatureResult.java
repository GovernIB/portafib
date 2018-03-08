package org.fundaciobit.apifirmasimple.v1.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Resultat d'una firma
 * 
 * @author anadal
 *
 */
@XmlRootElement
public class FirmaSimpleSignatureResult {

  protected String signID;

  FirmaSimpleStatus status;

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
  public FirmaSimpleSignatureResult(String signID, FirmaSimpleStatus status,
      FirmaSimpleFile signedFile) {
    super();
    this.signID = signID;
    this.status = status;
    this.signedFile = signedFile;
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

  public FirmaSimpleStatus getStatus() {
    return status;
  }

  public void setStatus(FirmaSimpleStatus status) {
    this.status = status;
  }
}
