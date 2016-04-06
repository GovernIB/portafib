package es.caib.portafib.logic.passarela;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author anadal
 *
 */
public class PassarelaSignaturesSetFull {

  protected final String entitatID;

  protected final PassarelaSignaturesSet signaturesSet;

  /**
   * @see PassarelaSignatureResult.STATUS_INITIALIZING
   * @see PassarelaSignatureResult.STATUS_IN_PROGRESS
   * @see PassarelaSignatureResult.STATUS_FINAL_OK = 2;
   * @see PassarelaSignatureResult.STATUS_FINAL_ERROR = -1;
   * @see PassarelaSignatureResult.STATUS_CANCELLED = -2;
   */
  protected int status = PassarelaSignatureResult.STATUS_INITIALIZING;

  protected String errorMsg;

  protected final Map<String, PassarelaSignatureStatus> statusBySignatureID = new HashMap<String, PassarelaSignatureStatus>();

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
      statusBySignatureID.put(fileInfo.getSignID(), new PassarelaSignatureStatus());
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

  public Map<String, PassarelaSignatureStatus> getStatusBySignatureID() {
    return statusBySignatureID;
  }

  public String getEntitatID() {
    return entitatID;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

}