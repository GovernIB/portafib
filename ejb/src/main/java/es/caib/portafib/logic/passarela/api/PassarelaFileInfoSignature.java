package es.caib.portafib.logic.passarela.api;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.fundaciobit.pluginsib.signature.api.FileInfoSignature;

import es.caib.portafib.logic.passarela.PassarelaKeyValue;
import es.caib.portafib.model.bean.FitxerBean;

/**
 *
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PassarelaFileInfoSignature {

  protected FitxerBean fileToSign;

  /**
   * Només per CAdES i XAdEs Detached amb firma prèvia
   */
  protected FitxerBean previusSignatureDetachedFile = null;

  protected String signID;

  protected String name;

  protected String reason;

  protected String location;

  protected String signerEmail;

  protected int signNumber;

  protected String languageSign;

  protected int signOperation;

  protected String signType;

  protected String signAlgorithm;

  protected int signMode;

  protected int signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;

  protected PassarelaSignaturesTableHeader signaturesTableHeader;

  protected PassarelaSecureVerificationCodeStampInfo secureVerificationCodeStampInfo;

  protected boolean useTimeStamp;

  protected String expedientCodi;

  protected String expedientNom;

  protected String expedientUrl;

  protected String procedimentCodi;

  protected String procedimentNom;

  protected List<PassarelaKeyValue> additionalInformation = null;

  /**
   * 
   */
  public PassarelaFileInfoSignature() {
    super();
  }

  /**
   * 
   * @param fileToSign
   * @param previusSignatureDetachedFile
   * @param signID
   * @param name
   * @param reason
   * @param location
   * @param signerEmail
   * @param signNumber
   * @param languageSign
   * @param signOperation
   * @param signType
   * @param signAlgorithm
   * @param signMode
   * @param signaturesTableLocation
   * @param signaturesTableHeader
   * @param secureVerificationCodeStampInfo
   * @param useTimeStamp
   * @param expedientCodi
   * @param expedientNom
   * @param expedientUrl
   * @param procedimentCodi
   * @param procedimentNom
   * @param additionalInformation
   */
  public PassarelaFileInfoSignature(FitxerBean fileToSign,
      FitxerBean previusSignatureDetachedFile, String signID, String name, String reason,
      String location, String signerEmail, int signNumber, String languageSign,
      int signOperation, String signType, String signAlgorithm, int signMode,
      int signaturesTableLocation, PassarelaSignaturesTableHeader signaturesTableHeader,
      PassarelaSecureVerificationCodeStampInfo secureVerificationCodeStampInfo,
      boolean useTimeStamp, String expedientCodi, String expedientNom, String expedientUrl,
      String procedimentCodi, String procedimentNom,
      List<PassarelaKeyValue> additionalInformation) {
    super();
    this.fileToSign = fileToSign;
    this.previusSignatureDetachedFile = previusSignatureDetachedFile;
    this.signID = signID;
    this.name = name;
    this.reason = reason;
    this.location = location;
    this.signerEmail = signerEmail;
    this.signNumber = signNumber;
    this.languageSign = languageSign;
    this.signOperation = signOperation;
    this.signType = signType;
    this.signAlgorithm = signAlgorithm;
    this.signMode = signMode;
    this.signaturesTableLocation = signaturesTableLocation;
    this.signaturesTableHeader = signaturesTableHeader;
    this.secureVerificationCodeStampInfo = secureVerificationCodeStampInfo;
    this.useTimeStamp = useTimeStamp;
    this.expedientCodi = expedientCodi;
    this.expedientNom = expedientNom;
    this.expedientUrl = expedientUrl;
    this.procedimentCodi = procedimentCodi;
    this.procedimentNom = procedimentNom;
    this.additionalInformation = additionalInformation;
  }

  /**
   * @param fileToSign
   * @param signID
   * @param name
   * @param reason
   * @param location
   * @param signerEmail
   * @param signNumber
   * @param languageSign
   * @param signType
   * @param signAlgorithm
   * @param signMode
   * @param signaturesTableLocation
   * @param signaturesTableHeader
   * @param secureVerificationCodeStampInfo
   * @param useTimeStamp
   */
  @Deprecated
  public PassarelaFileInfoSignature(FitxerBean fileToSign, String signID, String name,
      String reason, String location, String signerEmail, int signNumber, String languageSign,
      String signType, String signAlgorithm, int signMode, int signaturesTableLocation,
      PassarelaSignaturesTableHeader signaturesTableHeader,
      PassarelaSecureVerificationCodeStampInfo secureVerificationCodeStampInfo,
      boolean useTimeStamp) { 
    super();
    this.fileToSign = fileToSign;
    this.signID = signID;
    this.name = name;
    this.reason = reason;
    this.location = location;
    this.signerEmail = signerEmail;
    this.signNumber = signNumber;
    this.languageSign = languageSign;
    this.signType = signType;
    this.signAlgorithm = signAlgorithm;
    this.signMode = signMode;
    this.signaturesTableLocation = signaturesTableLocation;
    this.signaturesTableHeader = signaturesTableHeader;
    this.secureVerificationCodeStampInfo = secureVerificationCodeStampInfo;
    this.useTimeStamp = useTimeStamp;
  }

  public FitxerBean getFileToSign() {
    return fileToSign;
  }

  public void setFileToSign(FitxerBean fileToSign) {
    this.fileToSign = fileToSign;
  }

  public String getSignID() {
    return signID;
  }

  public void setSignID(String signID) {
    this.signID = signID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getSignerEmail() {
    return signerEmail;
  }

  public void setSignerEmail(String signerEmail) {
    this.signerEmail = signerEmail;
  }

  public int getSignNumber() {
    return signNumber;
  }

  public void setSignNumber(int signNumber) {
    this.signNumber = signNumber;
  }

  public String getLanguageSign() {
    return languageSign;
  }

  public void setLanguageSign(String languageSign) {
    this.languageSign = languageSign;
  }

  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }

  public String getSignAlgorithm() {
    return signAlgorithm;
  }

  public void setSignAlgorithm(String signAlgorithm) {
    this.signAlgorithm = signAlgorithm;
  }

  public int getSignMode() {
    return signMode;
  }

  public void setSignMode(int signMode) {
    this.signMode = signMode;
  }

  public int getSignaturesTableLocation() {
    return signaturesTableLocation;
  }

  public void setSignaturesTableLocation(int signaturesTableLocation) {
    this.signaturesTableLocation = signaturesTableLocation;
  }

  public PassarelaSignaturesTableHeader getSignaturesTableHeader() {
    return signaturesTableHeader;
  }

  public void setSignaturesTableHeader(PassarelaSignaturesTableHeader signaturesTableHeader) {
    this.signaturesTableHeader = signaturesTableHeader;
  }

  public PassarelaSecureVerificationCodeStampInfo getSecureVerificationCodeStampInfo() {
    return secureVerificationCodeStampInfo;
  }

  public void setSecureVerificationCodeStampInfo(
      PassarelaSecureVerificationCodeStampInfo secureVerificationCodeStampInfo) {
    this.secureVerificationCodeStampInfo = secureVerificationCodeStampInfo;
  }

  public boolean isUseTimeStamp() {
    return useTimeStamp;
  }

  public void setUseTimeStamp(boolean useTimeStamp) {
    this.useTimeStamp = useTimeStamp;
  }

  public FitxerBean getPreviusSignatureDetachedFile() {
    return previusSignatureDetachedFile;
  }

  public void setPreviusSignatureDetachedFile(FitxerBean previusSignatureDetachedFile) {
    this.previusSignatureDetachedFile = previusSignatureDetachedFile;
  }

  public String getExpedientCodi() {
    return expedientCodi;
  }

  public void setExpedientCodi(String expedientCodi) {
    this.expedientCodi = expedientCodi;
  }

  public String getExpedientNom() {
    return expedientNom;
  }

  public void setExpedientNom(String expedientNom) {
    this.expedientNom = expedientNom;
  }

  public String getExpedientUrl() {
    return expedientUrl;
  }

  public void setExpedientUrl(String expedientUrl) {
    this.expedientUrl = expedientUrl;
  }

  public String getProcedimentCodi() {
    return procedimentCodi;
  }

  public void setProcedimentCodi(String procedimentCodi) {
    this.procedimentCodi = procedimentCodi;
  }

  public String getProcedimentNom() {
    return procedimentNom;
  }

  public void setProcedimentNom(String procedimentNom) {
    this.procedimentNom = procedimentNom;
  }

  public List<PassarelaKeyValue> getAdditionalInformation() {
    return additionalInformation;
  }

  public void setAdditionalInformation(List<PassarelaKeyValue> additionalInformation) {
    this.additionalInformation = additionalInformation;
  }

  public int getSignOperation() {
    return signOperation;
  }

  public void setSignOperation(int signOperation) {
    this.signOperation = signOperation;
  }

}
