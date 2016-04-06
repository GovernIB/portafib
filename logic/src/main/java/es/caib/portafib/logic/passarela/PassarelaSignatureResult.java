package es.caib.portafib.logic.passarela;

import es.caib.portafib.model.bean.FitxerBean;

/**
 * Resultat d'una firma
 * @author anadal
 *
 */
public class PassarelaSignatureResult {
  
  /**
   * IMPORTANT !!! 
   * Aquests valors has de ser els mateixos del Plugin SignatureWebAPI. 
   */
  public static final int STATUS_INITIALIZING = 0;

  public static final int STATUS_IN_PROGRESS = 1;

  public static final int STATUS_FINAL_OK = 2;

  public static final int STATUS_FINAL_ERROR = -1;
  
  public static final int STATUS_CANCELLED = -2;

  protected FitxerBean signedFile;
  
  protected String signID;
  
  protected int status;
  
  protected String errorMsg;

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

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }
  
  
  
}
