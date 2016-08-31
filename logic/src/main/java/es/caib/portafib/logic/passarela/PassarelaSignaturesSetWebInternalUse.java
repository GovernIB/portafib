package es.caib.portafib.logic.passarela;

import java.util.HashMap;
import java.util.Map;

import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;

/**
 * 
 * @author anadal
 *
 */
public class PassarelaSignaturesSetWebInternalUse extends PassarelaSignatureStatus {

  protected final String entitatID;

  protected final PassarelaSignaturesSet signaturesSet;


  protected final Map<String, PassarelaSignatureStatusWenInternalUse> statusBySignatureID = new HashMap<String, PassarelaSignatureStatusWenInternalUse>();

  /**
   * @param signaturesSet
   */
  public PassarelaSignaturesSetWebInternalUse(String entitatID,
      PassarelaSignaturesSet signaturesSet) {
    super();
    this.signaturesSet = signaturesSet;
    this.entitatID = entitatID;

    PassarelaFileInfoSignature[] files = this.signaturesSet.getFileInfoSignatureArray();

    for (PassarelaFileInfoSignature fileInfo : files) {
      statusBySignatureID.put(fileInfo.getSignID(), new PassarelaSignatureStatusWenInternalUse());
    }
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public PassarelaSignaturesSet getSignaturesSet() {
    return signaturesSet;
  }

  public Map<String, PassarelaSignatureStatusWenInternalUse> getStatusBySignatureID() {
    return statusBySignatureID;
  }

  public String getEntitatID() {
    return entitatID;
  }



}