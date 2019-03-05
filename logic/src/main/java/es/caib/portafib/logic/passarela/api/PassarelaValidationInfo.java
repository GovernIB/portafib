package es.caib.portafib.logic.passarela.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PassarelaValidationInfo {

  protected Boolean checkAdministrationIDOfSigner;
  protected Boolean checkDocumentModifications;
  protected Boolean checkValidationSignature;
  protected String noCheckValidationReason;

  public PassarelaValidationInfo() {
    super();
  }

  public PassarelaValidationInfo(Boolean checkAdministrationIDOfSigner,
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
