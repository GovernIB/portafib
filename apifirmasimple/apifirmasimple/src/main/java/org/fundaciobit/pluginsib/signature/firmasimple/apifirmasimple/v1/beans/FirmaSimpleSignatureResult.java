package org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans;

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

  protected FirmaSimpleStatus status;

  protected FirmaSimpleFile signedFile;

  protected java.lang.String custodyFileID;

  protected java.lang.String custodyFileURL;

  /**
   * 
   */
  public FirmaSimpleSignatureResult() {
    super();
  }

  
  
  public FirmaSimpleSignatureResult(String signID, FirmaSimpleStatus status,
      FirmaSimpleFile signedFile, String custodyFileID, String custodyFileURL) {
    super();
    this.signID = signID;
    this.status = status;
    this.signedFile = signedFile;
    this.custodyFileID = custodyFileID;
    this.custodyFileURL = custodyFileURL;
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

  public java.lang.String getCustodyFileURL() {
    return custodyFileURL;
  }

  public void setCustodyFileURL(java.lang.String custodyFileURL) {
    this.custodyFileURL = custodyFileURL;
  }

  public java.lang.String getCustodyFileID() {
    return custodyFileID;
  }

  public void setCustodyFileID(java.lang.String custodyFileID) {
    this.custodyFileID = custodyFileID;
  }

}
