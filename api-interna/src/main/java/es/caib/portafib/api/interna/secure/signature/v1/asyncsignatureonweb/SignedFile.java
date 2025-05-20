package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;


import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignedFileInfo;

/**
 * 
 * @author anadal
 * 16 may 2025 14:03:57
 */
public class SignedFile {

  protected Document signedFile;

  protected SignedFileInfo signedFileInfo;

  public SignedFile() {
    super();
  }

  public SignedFile(Document signedFile,
      SignedFileInfo signedFileInfo) {
    super();
    this.signedFile = signedFile;
    this.signedFileInfo = signedFileInfo;
  }

  public Document getSignedFile() {
    return signedFile;
  }

  public void setSignedFile(Document signedFile) {
    this.signedFile = signedFile;
  }

  public SignedFileInfo getSignedFileInfo() {
    return signedFileInfo;
  }

  public void setSignedFileInfo(SignedFileInfo signedFileInfo) {
    this.signedFileInfo = signedFileInfo;
  }

}
