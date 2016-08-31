package org.fundaciobit.plugins.signature.api;
/**
 * 
 * @author anadal
 *
 */
public class StatusSignaturesSet {

  public static final int STATUS_INITIALIZING = 0;

  public static final int STATUS_IN_PROGRESS = 1;

  public static final int STATUS_FINAL_OK = 2;

  public static final int STATUS_FINAL_ERROR = -1;
  
  public static final int STATUS_CANCELLED = -2;

  protected int status = STATUS_INITIALIZING;

  protected String errorMsg;

  protected Throwable errorException;
  
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

  public Throwable getErrorException() {
    return errorException;
  }

  public void setErrorException(Throwable errorException) {
    this.errorException = errorException;
  }

  
}
