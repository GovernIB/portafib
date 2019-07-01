package es.caib.portafib.logic.passarela;

import java.io.File;

import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;

/**
 * Esta de Firma per ús Intern
 * 
 * @author anadal
 *
 */

public class PassarelaSignatureStatusWebInternalUse extends PassarelaSignatureStatus {

  protected File fitxerFirmat;

  protected Boolean checkAdministrationIDOfSigner = null;

  protected Boolean checkDocumentModifications = null;

  protected Boolean checkValidationSignature = null;

  public PassarelaSignatureStatusWebInternalUse() {
    super();
  }

  public File getFitxerFirmat() {
    return fitxerFirmat;
  }

  public void setFitxerFirmat(File fitxerFirmat) {
    this.fitxerFirmat = fitxerFirmat;
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

}
