package org.fundaciobit.plugins.signature.api;

import java.io.File;

/**
 * 
 * @author anadal
 *
 */
public class StatusSignature extends StatusSignaturesSet {

  // document firmat
  File signedData = null;

  boolean processed = false;

  public File getSignedData() {
    return signedData;
  }

  public void setSignedData(File signedData) {
    this.signedData = signedData;
  }

  public boolean isProcessed() {
    return processed;
  }

  public void setProcessed(boolean processed) {
    this.processed = processed;
  }

}
