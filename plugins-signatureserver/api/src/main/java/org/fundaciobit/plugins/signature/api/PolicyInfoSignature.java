package org.fundaciobit.plugins.signature.api;

/**
 * 
 * @author anadal
 *
 */
public class PolicyInfoSignature {

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
   * 
   */
  public PolicyInfoSignature() {
  }

  /**
   * @param policyIdentifier
   * @param policyIdentifierHash
   * @param policyIdentifierHashAlgorithm
   * @param policyUrlDocument
   */
  public PolicyInfoSignature(String policyIdentifier, String policyIdentifierHash,
      String policyIdentifierHashAlgorithm, String policyUrlDocument) {
    this.policyIdentifier = policyIdentifier;
    this.policyIdentifierHash = policyIdentifierHash;
    this.policyIdentifierHashAlgorithm = policyIdentifierHashAlgorithm;
    this.policyUrlDocument = policyUrlDocument;
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

}
