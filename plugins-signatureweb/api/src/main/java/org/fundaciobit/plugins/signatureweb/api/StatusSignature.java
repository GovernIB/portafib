package org.fundaciobit.plugins.signatureweb.api;

/**
 * 
 * @author anadal
 *
 */
public class StatusSignature extends StatusSignaturesSet {

  // document firmat
  byte[] signedData = null;

  boolean processed = false;

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
