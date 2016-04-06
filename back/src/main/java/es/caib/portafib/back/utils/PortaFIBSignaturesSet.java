package es.caib.portafib.back.utils;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.fundaciobit.plugins.signatureweb.api.CommonInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;

import es.caib.portafib.jpa.EntitatJPA;

/**
 *
 * @author anadal
 *
 */
public class PortaFIBSignaturesSet extends SignaturesSet {

  protected Map<String, List<Long>> pluginsFirmaBySignatureID = null;
  
  protected List<Long> filterByPluginID = null;

  protected final String urlFinalOriginal;
  
  protected final EntitatJPA entitat;

  protected Long pluginID = null;

  /**
   * @param signaturesSetID
   * @param expiryDate
   * @param commonInfoSignature
   * @param fileInfoSignatureArray
   */
  public PortaFIBSignaturesSet(String signaturesSetID, Date expiryDate,
      CommonInfoSignature commonInfoSignature, FileInfoSignature[] fileInfoSignatureArray,
      EntitatJPA entitat) {
    super(signaturesSetID, expiryDate, commonInfoSignature, fileInfoSignatureArray);
    this.urlFinalOriginal = commonInfoSignature.getUrlFinal();
    this.entitat = entitat;
  }

  public Map<String, List<Long>> getPluginsFirmaBySignatureID() {
    return pluginsFirmaBySignatureID;
  }

  public void setPluginsFirmaBySignatureID(Map<String, List<Long>> pluginsFirmaBySignatureID) {
    this.pluginsFirmaBySignatureID = pluginsFirmaBySignatureID;
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

  public EntitatJPA getEntitat() {
    return entitat;
  }

  public List<Long> getFilterByPluginID() {
    return filterByPluginID;
  }

  public void setFilterByPluginID(List<Long> filterByPluginID) {
    this.filterByPluginID = filterByPluginID;
  }
  
  

}
