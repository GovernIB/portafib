package es.caib.portafib.back.utils;

import java.util.Date;
import java.util.Map;

import org.fundaciobit.plugins.signatureweb.api.CommonInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;


/**
 *
 * @author anadal
 *
 */
public class PortaFIBSignaturesSet extends SignaturesSet {

  protected Map<String,Long> tipusDocBySignatureID = null;
  
  protected final String urlFinalOriginal;
  
  protected Long pluginID = null;



  /**
   * @param signaturesSetID
   * @param expiryDate
   * @param commonInfoSignature
   * @param fileInfoSignatureArray
   */
  public PortaFIBSignaturesSet(String signaturesSetID, Date expiryDate,
      CommonInfoSignature commonInfoSignature, FileInfoSignature[] fileInfoSignatureArray) {
    super(signaturesSetID, expiryDate, commonInfoSignature, fileInfoSignatureArray);
    this.urlFinalOriginal = commonInfoSignature.getUrlFinal();
  }

  public Map<String, Long> getTipusDocBySignatureID() {
    return tipusDocBySignatureID;
  }

  public void setTipusDocBySignatureID(Map<String, Long> tipusDocBySignatureID) {
    this.tipusDocBySignatureID = tipusDocBySignatureID;
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
