package org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal(u80067)
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaAsyncSimpleSignatureRequestInfo {

  protected long signatureRequestID;

  protected String languageUI;

  /**
   * @param message
   */
  public FirmaAsyncSimpleSignatureRequestInfo() {
    super();
  }

  public FirmaAsyncSimpleSignatureRequestInfo(long signatureRequestID, String languageUI) {
    super();
    this.signatureRequestID = signatureRequestID;
    this.languageUI = languageUI;
  }

  public long getSignatureRequestID() {
    return signatureRequestID;
  }

  public void setSignatureRequestID(long signatureRequestID) {
    this.signatureRequestID = signatureRequestID;
  }

  public String getLanguageUI() {
    return languageUI;
  }

  public void setLanguageUI(String languageUI) {
    this.languageUI = languageUI;
  }

}
