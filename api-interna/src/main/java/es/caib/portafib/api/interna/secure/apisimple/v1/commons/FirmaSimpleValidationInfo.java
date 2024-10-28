package es.caib.portafib.api.interna.secure.apisimple.v1.commons;


/**
 * 
 * @author anadal
 *
 */

public class FirmaSimpleValidationInfo {

  protected Boolean checkAdministrationIDOfSigner;

  protected Boolean checkDocumentModifications;

  protected Boolean checkValidationSignature;

  /**
   * Només s'omple si checkValidationSignature val false
   */
  protected String noCheckValidationReason;

  public FirmaSimpleValidationInfo() {
    super();
  }

  public FirmaSimpleValidationInfo(Boolean checkAdministrationIDOfSigner,
      Boolean checkDocumentModifications, Boolean checkValidationSignature,
      String noCheckValidationReason) {
    super();
    this.checkAdministrationIDOfSigner = checkAdministrationIDOfSigner;
    this.checkDocumentModifications = checkDocumentModifications;
    this.checkValidationSignature = checkValidationSignature;
    this.noCheckValidationReason = noCheckValidationReason;
  }

  public Boolean getCheckAdministrationIDOfSigner() {
    return checkAdministrationIDOfSigner;
  }

  public void setCheckAdministrationIDOfSigner(Boolean checkAdministrationIDOfSigner) {
    this.checkAdministrationIDOfSigner = checkAdministrationIDOfSigner;
  }

  public Boolean getCheckDocumentModifications() {
    return checkDocumentModifications;
  }

  public void setCheckDocumentModifications(Boolean checkDocumentModifications) {
    this.checkDocumentModifications = checkDocumentModifications;
  }

  public Boolean getCheckValidationSignature() {
    return checkValidationSignature;
  }

  public void setCheckValidationSignature(Boolean checkValidationSignature) {
    this.checkValidationSignature = checkValidationSignature;
  }

  public String getNoCheckValidationReason() {
    return noCheckValidationReason;
  }

  public void setNoCheckValidationReason(String noCheckValidationReason) {
    this.noCheckValidationReason = noCheckValidationReason;
  }

}
