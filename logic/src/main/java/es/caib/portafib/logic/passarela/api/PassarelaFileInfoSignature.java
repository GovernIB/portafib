package es.caib.portafib.logic.passarela.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.fundaciobit.plugins.signature.api.FileInfoSignature;

import es.caib.portafib.model.bean.CustodiaInfoBean;
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

  protected String signID;

  protected String name;

  protected String reason;
  
  protected String location;
  
  protected String signerEmail;

  protected int signNumber;

  protected String languageSign;

  protected String signType;

  protected String signAlgorithm;

  protected int signMode;

  protected int signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
  
  protected PassarelaSignaturesTableHeader signaturesTableHeader;
  
  protected PassarelaSecureVerificationCodeStampInfo  secureVerificationCodeStampInfo;

  protected boolean useTimeStamp;

  /**
   * NO IMPLEMENTAT
   */
  protected CustodiaInfoBean custodiaInfo;

  /**
   * 
   */
  public PassarelaFileInfoSignature() {
    super();
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

  public CustodiaInfoBean getCustodiaInfo() {
    return custodiaInfo;
  }

  public void setCustodiaInfo(CustodiaInfoBean custodiaInfo) {
    this.custodiaInfo = custodiaInfo;
  }

  
}
