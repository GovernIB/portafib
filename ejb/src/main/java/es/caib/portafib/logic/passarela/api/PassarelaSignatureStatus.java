package es.caib.portafib.logic.passarela.api;

import org.fundaciobit.plugins.signature.api.StatusSignature;

/**
 * 
 * 
 * @author anadal
 *
 */
public class PassarelaSignatureStatus {

  protected int status = StatusSignature.STATUS_INITIALIZING;

  protected String errorMessage;

  protected String errorStackTrace;
  
  /**
   * 
   */
  public PassarelaSignatureStatus() {
    super();
  }
  
  /**
   * @param status
   * @param errorMessage
   * @param errorStackTrace
   */
  public PassarelaSignatureStatus(int status, String errorMessage, String errorStackTrace) {
    super();
    this.status = status;
    this.errorMessage = errorMessage;
    this.errorStackTrace = errorStackTrace;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorStackTrace() {
    return errorStackTrace;
  }

  public void setErrorStackTrace(String errorStackTrace) {
    this.errorStackTrace = errorStackTrace;
  }

}
