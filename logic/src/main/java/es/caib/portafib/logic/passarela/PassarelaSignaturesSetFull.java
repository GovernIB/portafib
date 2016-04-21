package es.caib.portafib.logic.passarela;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author anadal
 *
 */
public class PassarelaSignaturesSetFull extends PassarelaSignatureStatus {

  protected final String entitatID;

  protected final PassarelaSignaturesSet signaturesSet;


  protected final Map<String, PassarelaSignatureStatusFull> statusBySignatureID = new HashMap<String, PassarelaSignatureStatusFull>();

  /**
   * @param signaturesSet
   */
  public PassarelaSignaturesSetFull(String entitatID,
      PassarelaSignaturesSet signaturesSet) {
    super();
    this.signaturesSet = signaturesSet;
    this.entitatID = entitatID;

    PassarelaFileInfoSignature[] files = this.signaturesSet.getFileInfoSignatureArray();

    for (PassarelaFileInfoSignature fileInfo : files) {
      statusBySignatureID.put(fileInfo.getSignID(), new PassarelaSignatureStatusFull());
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

  public Map<String, PassarelaSignatureStatusFull> getStatusBySignatureID() {
    return statusBySignatureID;
  }

  public String getEntitatID() {
    return entitatID;
  }



}