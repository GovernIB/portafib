package es.caib.portafib.api.interna.secure.firma.v1.commons;


/**
 * 
 * @author anadal(u80067)
 *
 */

public class FirmaSimpleUpgradeRequest {

  String profileCode;

  FirmaSimpleFile signature;

  FirmaSimpleFile detachedDocument;

  /** Certificat del que penjar l'upgrade a l'hora de fer cofirmes i contrafirmes */
  FirmaSimpleFile targetCertificate;

  String languageUI;

  public FirmaSimpleUpgradeRequest() {
    super();
  }

  public FirmaSimpleUpgradeRequest(String profileCode, FirmaSimpleFile signature,
      FirmaSimpleFile detachedDocument, FirmaSimpleFile targetCertificate, String languageUI) {
    super();
    this.profileCode = profileCode;
    this.signature = signature;
    this.detachedDocument = detachedDocument;
    this.targetCertificate = targetCertificate;
    this.languageUI = languageUI;
  }

  public String getProfileCode() {
    return profileCode;
  }

  public void setProfileCode(String profileCode) {
    this.profileCode = profileCode;
  }

  public String getLanguageUI() {
    return languageUI;
  }

  public void setLanguageUI(String languageUI) {
    this.languageUI = languageUI;
  }

  public FirmaSimpleFile getSignature() {
    return signature;
  }

  public void setSignature(FirmaSimpleFile signature) {
    this.signature = signature;
  }

  public FirmaSimpleFile getTargetCertificate() {
    return targetCertificate;
  }

  public void setTargetCertificate(FirmaSimpleFile targetCertificate) {
    this.targetCertificate = targetCertificate;
  }

  public FirmaSimpleFile getDetachedDocument() {
    return detachedDocument;
  }

  public void setDetachedDocument(FirmaSimpleFile detachedDocument) {
    this.detachedDocument = detachedDocument;
  }

}
