package org.fundaciobit.plugins.signatureweb.api;

/**
 * 
 * @author anadal
 *
 */
public class CommonInfoSignature {

  String languageUI;

  String filtreCertificats;

  String username;

  PolicyInfoSignature policyInfoSignature = null;

  String urlOK;

  String urlError;

  boolean browserSupportsJava;

  /**
   * 
   */
  public CommonInfoSignature() {
  }

  /**
   * @param languageUI
   * @param filtreCertificats
   * @param username
   * @param policyInfoSignature
   * @param urlOK
   * @param urlError
   * @param browserSupportsJava
   */
  public CommonInfoSignature(String languageUI, String filtreCertificats, String username,
      PolicyInfoSignature policyInfoSignature, String urlOK, String urlError,
      boolean browserSupportsJava) {
    super();
    this.languageUI = languageUI;
    this.filtreCertificats = filtreCertificats;
    this.username = username;
    this.policyInfoSignature = policyInfoSignature;
    this.urlOK = urlOK;
    this.urlError = urlError;
    this.browserSupportsJava = browserSupportsJava;
  }

  public String getLanguageUI() {
    return languageUI;
  }

  public void setLanguageUI(String languageUI) {
    this.languageUI = languageUI;
  }

  public String getFiltreCertificats() {
    return filtreCertificats;
  }

  public void setFiltreCertificats(String filtreCertificats) {
    this.filtreCertificats = filtreCertificats;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public PolicyInfoSignature getPolicyInfoSignature() {
    return policyInfoSignature;
  }

  public void setPolicyInfoSignature(PolicyInfoSignature policyInfoSignature) {
    this.policyInfoSignature = policyInfoSignature;
  }

  public String getUrlOK() {
    return urlOK;
  }

  public void setUrlOK(String urlOK) {
    this.urlOK = urlOK;
  }

  public String getUrlError() {
    return urlError;
  }

  public void setUrlError(String urlError) {
    this.urlError = urlError;
  }

  public boolean isBrowserSupportsJava() {
    return browserSupportsJava;
  }

  public void setBrowserSupportsJava(boolean browserSupportsJava) {
    this.browserSupportsJava = browserSupportsJava;
  }

}
