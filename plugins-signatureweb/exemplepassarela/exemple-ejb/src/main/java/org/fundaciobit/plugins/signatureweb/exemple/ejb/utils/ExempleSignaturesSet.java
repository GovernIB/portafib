package org.fundaciobit.plugins.signatureweb.exemple.ejb.utils;

import java.util.Date;

import org.fundaciobit.plugins.signature.api.CommonInfoSignature;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSetWeb;

/**
 *
 * @author anadal
 *
 */
public class ExempleSignaturesSet extends SignaturesSetWeb {

  protected final String urlFinalOriginal;

  protected Long pluginID = null;

  /**
   * @param signaturesSetID
   * @param expiryDate
   * @param commonInfoSignature
   * @param fileInfoSignatureArray
   */
  public ExempleSignaturesSet(String signaturesSetID, Date expiryDate,
      CommonInfoSignature commonInfoSignature, FileInfoSignature[] fileInfoSignatureArray,
      String urlFinal) {
    super(signaturesSetID, expiryDate, commonInfoSignature, fileInfoSignatureArray, urlFinal);
    this.urlFinalOriginal = this.getUrlFinal();
    
  }

  public String getUrlFinalOriginal() {
    return urlFinalOriginal;
  }

  public Long getPluginID() {
    return pluginID;
  }

  public void setPluginID(Long pluginID) {
    this.pluginID = pluginID;
  }

}
