package es.caib.portafib.logic.utils;

import java.math.BigInteger;
import java.security.cert.X509Certificate;

import org.fundaciobit.plugins.validatesignature.api.ValidateSignatureResponse;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class ValidacioCompletaResponse {

  protected String signType;
  protected String mime;
  protected String extension;
  protected String nifFirmant;

  protected Boolean checkAdministrationIDOfSigner = null;

  protected Boolean checkDocumentModifications = null;

  protected Boolean checkValidationSignature = null;

  protected ValidateSignatureResponse validateSignatureResponse;

  protected BigInteger numeroSerieCertificat;
  protected String emissorCertificat;
  protected String subjectCertificat;

  protected X509Certificate certificateLastSign;

  public ValidacioCompletaResponse(String signType, String mime, String extension,
      String nifFirmant, Boolean checkAdministrationIDOfSigner,
      Boolean checkDocumentModifications, Boolean checkValidationSignature,
      ValidateSignatureResponse validateSignatureResponse, BigInteger numeroSerieCertificat,
      String emissorCertificat, String subjectCertificat, X509Certificate certificateLastSign) {
    super();
    this.signType = signType;
    this.mime = mime;
    this.extension = extension;
    this.nifFirmant = nifFirmant;
    this.checkAdministrationIDOfSigner = checkAdministrationIDOfSigner;
    this.checkDocumentModifications = checkDocumentModifications;
    this.checkValidationSignature = checkValidationSignature;
    this.validateSignatureResponse = validateSignatureResponse;
    this.numeroSerieCertificat = numeroSerieCertificat;
    this.emissorCertificat = emissorCertificat;
    this.subjectCertificat = subjectCertificat;
    this.certificateLastSign = certificateLastSign;
  }

  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }

  public String getMime() {
    return mime;
  }

  public void setMime(String mime) {
    this.mime = mime;
  }

  public String getExtension() {
    return extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

  public String getNifFirmant() {
    return nifFirmant;
  }

  public void setNifFirmant(String nifFirmant) {
    this.nifFirmant = nifFirmant;
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

  public ValidateSignatureResponse getValidateSignatureResponse() {
    return validateSignatureResponse;
  }

  public void setValidateSignatureResponse(ValidateSignatureResponse validateSignatureResponse) {
    this.validateSignatureResponse = validateSignatureResponse;
  }

  public BigInteger getNumeroSerieCertificat() {
    return numeroSerieCertificat;
  }

  public void setNumeroSerieCertificat(BigInteger numeroSerieCertificat) {
    this.numeroSerieCertificat = numeroSerieCertificat;
  }

  public String getEmissorCertificat() {
    return emissorCertificat;
  }

  public void setEmissorCertificat(String emissorCertificat) {
    this.emissorCertificat = emissorCertificat;
  }

  public String getSubjectCertificat() {
    return subjectCertificat;
  }

  public void setSubjectCertificat(String subjectCertificat) {
    this.subjectCertificat = subjectCertificat;
  }

  public X509Certificate getCertificateLastSign() {
    return certificateLastSign;
  }

  public void setCertificateLastSign(X509Certificate certificateLastSign) {
    this.certificateLastSign = certificateLastSign;
  }

}
