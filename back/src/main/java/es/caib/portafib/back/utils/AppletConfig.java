package es.caib.portafib.back.utils;

/**
 * 
 * @author anadal
 * 
 */
public class AppletConfig {

  String languageUI;

  String redirect;

  String filtreCertificats;

  String signerClass;
  
  /**
   * Identificador de la política de firma. Si es defineix aquest valors
   * llavorses generaran firmes PAdES-EPES,CAdES-EPES y XAdES-EPES.
   */
  String policyIdentifier = null;

  /**
   * Valor en Base64. Huella digital de la política de firma. Es obligatorio
   * indicar este parámetro si se indicó también policyIdentifier.
   */
  java.lang.String policyIdentifierHash = null;

  /**
   * Indica el algoritmo utilizado para generar la Huella Digital definida en el
   * campo policyIdentifierHash. Es obligatorio indicar este parámetro si se
   * indicó también policyIdentifier. Los valores posibles son: SHA1, SHA-256,
   * SHA-384 o SHA-512
   */
  java.lang.String policyIdentifierHashAlgorithm = null;

  /**
   * URL (universalmente accesible) hacia el documento (normalmente PDF) que
   * contiene una descripción textual de la política de firma. Este parámetro es
   * opcional incluso cuando se genera una firma EPES.
   */
  java.lang.String policyUrlDocument = null;

  /**
   * Conjunt de propietats separades per comes que s'ha d'enviar a l'applet
   * com a parametre i dins l'applet s'han de llegir totes les propietats.
   */
  String parametersToRead = null;
  

  /**
   * 
   */
  public AppletConfig() {
  }
  
  
  
  /**
   * @param languageUI
   * @param redirect
   * @param filtreCertificats
   * @param signerClass
   * @param policyIdentifier
   * @param policyIdentifierHash
   * @param policyIdentifierHashAlgorithm
   * @param policyUrlDocument
   */
  public AppletConfig(String languageUI, String redirect, String filtreCertificats,
      String signerClass, String parametersToRead) {
    super();
    this.languageUI = languageUI;
    this.redirect = redirect;
    this.filtreCertificats = filtreCertificats;
    this.signerClass = signerClass;
    this.parametersToRead = parametersToRead;
  }

  /**
   * @param languageUI
   * @param redirect
   * @param filtreCertificats
   * @param signerClass
   * @param policyIdentifier
   * @param policyIdentifierHash
   * @param policyIdentifierHashAlgorithm
   * @param policyUrlDocument
   */
  public AppletConfig(String languageUI, String redirect, String filtreCertificats,
      String signerClass, String parametersToRead, String policyIdentifier, String policyIdentifierHash,
      String policyIdentifierHashAlgorithm, String policyUrlDocument) {
    super();
    this.languageUI = languageUI;
    this.redirect = redirect;
    this.filtreCertificats = filtreCertificats;
    this.signerClass = signerClass;
    this.parametersToRead = parametersToRead;
    this.policyIdentifier = policyIdentifier;
    this.policyIdentifierHash = policyIdentifierHash;
    this.policyIdentifierHashAlgorithm = policyIdentifierHashAlgorithm;
    this.policyUrlDocument = policyUrlDocument;
    
  }

  public String getLanguageUI() {
    return languageUI;
  }

  public void setLanguageUI(String languageUI) {
    this.languageUI = languageUI;
  }

  public String getRedirect() {
    return redirect;
  }

  public void setRedirect(String redirect) {
    this.redirect = redirect;
  }

  public String getFiltreCertificats() {
    return filtreCertificats;
  }

  public void setFiltreCertificats(String filtreCertificats) {
    this.filtreCertificats = filtreCertificats;
  }

  public String getSignerClass() {
    return signerClass;
  }

  public void setSignerClass(String signerClass) {
    this.signerClass = signerClass;
  }

  public String getPolicyIdentifier() {
    return policyIdentifier;
  }

  public void setPolicyIdentifier(String policyIdentifier) {
    this.policyIdentifier = policyIdentifier;
  }

  public java.lang.String getPolicyIdentifierHash() {
    return policyIdentifierHash;
  }

  public void setPolicyIdentifierHash(java.lang.String policyIdentifierHash) {
    this.policyIdentifierHash = policyIdentifierHash;
  }

  public java.lang.String getPolicyIdentifierHashAlgorithm() {
    return policyIdentifierHashAlgorithm;
  }

  public void setPolicyIdentifierHashAlgorithm(java.lang.String policyIdentifierHashAlgorithm) {
    this.policyIdentifierHashAlgorithm = policyIdentifierHashAlgorithm;
  }

  public java.lang.String getPolicyUrlDocument() {
    return policyUrlDocument;
  }

  public void setPolicyUrlDocument(java.lang.String policyUrlDocument) {
    this.policyUrlDocument = policyUrlDocument;
  }

  public String getParametersToRead() {
    return parametersToRead;
  }

  public void setParametersToRead(String parametersToRead) {
    this.parametersToRead = parametersToRead;
  }

}
