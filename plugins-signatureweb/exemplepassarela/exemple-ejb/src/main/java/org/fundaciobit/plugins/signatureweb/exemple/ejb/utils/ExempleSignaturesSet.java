package org.fundaciobit.plugins.signatureweb.exemple.ejb.utils;

import java.util.Date;

import org.fundaciobit.plugins.signatureweb.api.CommonInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;

/**
 *
 * @author anadal
 *
 */
public class ExempleSignaturesSet extends SignaturesSet {

  protected final String urlFinalOriginal;

  protected Long pluginID = null;

  /**
   * @param signaturesSetID
   * @param expiryDate
   * @param commonInfoSignature
   * @param fileInfoSignatureArray
   */
  public ExempleSignaturesSet(String signaturesSetID, Date expiryDate,
      CommonInfoSignature commonInfoSignature, FileInfoSignature[] fileInfoSignatureArray) {
    super(signaturesSetID, expiryDate, commonInfoSignature, fileInfoSignatureArray);
    this.urlFinalOriginal = commonInfoSignature.getUrlFinal();
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
