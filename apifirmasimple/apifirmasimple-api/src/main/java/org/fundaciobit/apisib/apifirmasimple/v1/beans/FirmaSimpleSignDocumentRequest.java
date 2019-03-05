package org.fundaciobit.apisib.apifirmasimple.v1.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaSimpleSignDocumentRequest {

  FirmaSimpleCommonInfo commonInfo;

  FirmaSimpleFileInfoSignature fileInfoSignature;

  /**
   * 
   */
  public FirmaSimpleSignDocumentRequest() {
    super();
  }

  public FirmaSimpleSignDocumentRequest(FirmaSimpleCommonInfo commonInfo,
      FirmaSimpleFileInfoSignature fileInfoSignature) {
    super();
    this.commonInfo = commonInfo;
    this.fileInfoSignature = fileInfoSignature;
  }

  public FirmaSimpleCommonInfo getCommonInfo() {
    return commonInfo;
  }

  public void setCommonInfo(FirmaSimpleCommonInfo commonInfo) {
    this.commonInfo = commonInfo;
  }

  public FirmaSimpleFileInfoSignature getFileInfoSignature() {
    return fileInfoSignature;
  }

  public void setFileInfoSignature(FirmaSimpleFileInfoSignature fileInfoSignature) {
    this.fileInfoSignature = fileInfoSignature;
  }

}
