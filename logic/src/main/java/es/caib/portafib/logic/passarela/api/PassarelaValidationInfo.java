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

  public PassarelaValidationInfo() {
    super();
    // TODO Auto-generated constructor stub
  }

  public PassarelaValidationInfo(Boolean checkAdministrationIDOfSigner,
      Boolean checkDocumentModifications, Boolean checkValidationSignature) {
    super();
    this.checkAdministrationIDOfSigner = checkAdministrationIDOfSigner;
    this.checkDocumentModifications = checkDocumentModifications;
    this.checkValidationSignature = checkValidationSignature;
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
