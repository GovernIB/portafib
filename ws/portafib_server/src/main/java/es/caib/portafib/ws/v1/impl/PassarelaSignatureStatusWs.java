package es.caib.portafib.ws.v1.impl;

import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * @author anadal
 *
 */
@XmlType(name="passarelaSignatureStatus")
public class PassarelaSignatureStatusWs {

  protected int status = 0; // 0 == STATUS_INITIALIZING;

  protected String errorMessage;

  protected String errorStackTrace;

  /**
   * @param status
   * @param errorMessage
   * @param errorStackTrace
   */
  public PassarelaSignatureStatusWs(int status, String errorMessage, String errorStackTrace) {
    super();
    this.status = status;
    this.errorMessage = errorMessage;
    this.errorStackTrace = errorStackTrace;
  }

  /**
   * 
   */
  public PassarelaSignatureStatusWs() {
    super();
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
