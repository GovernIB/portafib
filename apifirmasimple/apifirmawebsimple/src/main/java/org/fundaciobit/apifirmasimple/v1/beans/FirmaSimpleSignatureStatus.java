package org.fundaciobit.apifirmasimple.v1.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Resultat d'una firma
 * 
 * @author anadal
 *
 */
@XmlRootElement
public class FirmaSimpleSignatureStatus {

  protected String signID;

  FirmaSimpleStatus status;

  /**
   * 
   */
  public FirmaSimpleSignatureStatus() {
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
  public FirmaSimpleSignatureStatus(String signID,   FirmaSimpleStatus status) {
    super();
    this.signID = signID;
    this.status = status;
  }



  public FirmaSimpleStatus getStatus() {
    return status;
  }

  public void setStatus(FirmaSimpleStatus status) {
    this.status = status;
  }

  public String getSignID() {
    return signID;
  }

  public void setSignID(String signID) {
    this.signID = signID;
  }


}
