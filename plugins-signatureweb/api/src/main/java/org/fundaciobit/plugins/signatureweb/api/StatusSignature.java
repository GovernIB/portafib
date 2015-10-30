package org.fundaciobit.plugins.signatureweb.api;


/**
 * 
 * @author anadal
 *
 */
public class StatusSignature {

  public static final int STATUS_INITIALIZING = 0;

  public static final int STATUS_SIGNING = 1;

  public static final int STATUS_SIGNED = 2;

  public static final int STATUS_ERROR = -1;

  int status = STATUS_INITIALIZING;

  String errorMsg;

  Throwable error;
  
  // document firmat
  byte[] signedData = null;
  
  boolean processed = false;

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

  public Throwable getError() {
    return error;
  }

  public void setError(Throwable error) {
    this.error = error;
  }

  public byte[] getSignedData() {
    return signedData;
  }

  public void setSignedData(byte[] signedData) {
    this.signedData = signedData;
  }

  public boolean isProcessed() {
    return processed;
  }

  public void setProcessed(boolean processed) {
    this.processed = processed;
  }
  
  
  
  
}
