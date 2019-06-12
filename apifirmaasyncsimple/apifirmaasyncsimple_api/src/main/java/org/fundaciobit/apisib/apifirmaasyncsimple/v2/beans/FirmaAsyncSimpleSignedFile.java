package org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal(u80067)
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaAsyncSimpleSignedFile {

  protected FirmaAsyncSimpleFile signedFile;

  protected FirmaAsyncSimpleSignedFileInfo signedFileInfo;

  public FirmaAsyncSimpleSignedFile() {
    super();
  }

  public FirmaAsyncSimpleSignedFile(FirmaAsyncSimpleFile signedFile,
      FirmaAsyncSimpleSignedFileInfo signedFileInfo) {
    super();
    this.signedFile = signedFile;
    this.signedFileInfo = signedFileInfo;
  }

  public FirmaAsyncSimpleFile getSignedFile() {
    return signedFile;
  }

  public void setSignedFile(FirmaAsyncSimpleFile signedFile) {
    this.signedFile = signedFile;
  }

  public FirmaAsyncSimpleSignedFileInfo getSignedFileInfo() {
    return signedFileInfo;
  }

  public void setSignedFileInfo(FirmaAsyncSimpleSignedFileInfo signedFileInfo) {
    this.signedFileInfo = signedFileInfo;
  }

}
