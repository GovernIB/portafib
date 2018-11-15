package es.caib.portafib.logic.passarela.api;

import es.caib.portafib.model.bean.FitxerBean;

/**
 * Resultat d'una firma
 * 
 * @author anadal
 *
 */
public class PassarelaSignatureResult extends PassarelaSignatureStatus {

  protected FitxerBean signedFile;

  protected String signID;

  protected PassarelaCustodyInfo custodyInfo;
  
  protected PassarelaValidationInfo validationInfo;


  public PassarelaSignatureResult() {
    super();
  }

  public PassarelaSignatureResult(String signID, int status, String errorMessage,
      String errorStackTrace, FitxerBean signedFile, PassarelaCustodyInfo custodyInfo,
      PassarelaValidationInfo validationInfo) {
    super(status, errorMessage, errorStackTrace);
    this.signID = signID;
    this.signedFile = signedFile;
    this.custodyInfo = custodyInfo;
    this.validationInfo = validationInfo;
  
  }

  public PassarelaSignatureResult(String signID, FitxerBean signedFile,
      PassarelaCustodyInfo custodyInfo, PassarelaValidationInfo validationInfo) {
    super();
    this.signID = signID;
    this.signedFile = signedFile;
    this.custodyInfo = custodyInfo;
    this.validationInfo = validationInfo;
  }

  public FitxerBean getSignedFile() {
    return signedFile;
  }

  public void setSignedFile(FitxerBean signedFile) {
    this.signedFile = signedFile;
  }

  public String getSignID() {
    return signID;
  }

  public void setSignID(String signID) {
    this.signID = signID;
  }

  public PassarelaCustodyInfo getCustodyInfo() {
    return custodyInfo;
  }

  public void setCustodyInfo(PassarelaCustodyInfo custodyInfo) {
    this.custodyInfo = custodyInfo;
  }

  public PassarelaValidationInfo getValidationInfo() {
    return validationInfo;
  }

  public void setValidationInfo(PassarelaValidationInfo validationInfo) {
    this.validationInfo = validationInfo;
  }

}
