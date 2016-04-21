package es.caib.portafib.logic.passarela;

import org.fundaciobit.plugins.signatureweb.api.StatusSignature;

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
