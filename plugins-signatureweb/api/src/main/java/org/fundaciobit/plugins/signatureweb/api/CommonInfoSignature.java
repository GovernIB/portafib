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
  
  String administrationID;

  PolicyInfoSignature policyInfoSignature = null;

  String urlFinal;

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
  public CommonInfoSignature(String languageUI, String filtreCertificats,
      String username, String administrationID, // = NIF
      PolicyInfoSignature policyInfoSignature, String urlFinal,
      boolean browserSupportsJava) {
    super();
    this.languageUI = languageUI;
    this.filtreCertificats = filtreCertificats;
    this.username = username;
    this.administrationID = administrationID;
    this.policyInfoSignature = policyInfoSignature;
    this.urlFinal = urlFinal;
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

  public String getUrlFinal() {
    return urlFinal;
  }

  public void setUrlFinal(String urlFinal) {
    this.urlFinal = urlFinal;
  }

  public boolean isBrowserSupportsJava() {
    return browserSupportsJava;
  }

  public void setBrowserSupportsJava(boolean browserSupportsJava) {
    this.browserSupportsJava = browserSupportsJava;
  }

  public String getAdministrationID() {
    return administrationID;
  }

  public void setAdministrationID(String administrationID) {
    this.administrationID = administrationID;
  }

}
