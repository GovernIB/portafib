package es.caib.portafib.api.interna.secure.apisimple.v1.commons;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Resultat d'una firma
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaSimpleSignatureResult {

  protected String signID;

  protected FirmaSimpleStatus status;

  protected FirmaSimpleFile signedFile;

  protected FirmaSimpleSignedFileInfo signedFileInfo;

  /**
   * 
   */
  public FirmaSimpleSignatureResult() {
    super();
  }

  public FirmaSimpleSignatureResult(String signID, FirmaSimpleStatus status,
      FirmaSimpleFile signedFile, FirmaSimpleSignedFileInfo signedFileInfo) {
    super();
    this.signID = signID;
    this.status = status;
    this.signedFile = signedFile;
    this.signedFileInfo = signedFileInfo;
  }

  public FirmaSimpleFile getSignedFile() {
    return signedFile;
  }

  public void setSignedFile(FirmaSimpleFile signedFile) {
    this.signedFile = signedFile;
  }

  public String getSignID() {
    return signID;
  }

  public void setSignID(String signID) {
    this.signID = signID;
  }

  public FirmaSimpleStatus getStatus() {
    return status;
  }

  public void setStatus(FirmaSimpleStatus status) {
    this.status = status;
  }

  public FirmaSimpleSignedFileInfo getSignedFileInfo() {
    return signedFileInfo;
  }

  public void setSignedFileInfo(FirmaSimpleSignedFileInfo signedFileInfo) {
    this.signedFileInfo = signedFileInfo;
  }

}
