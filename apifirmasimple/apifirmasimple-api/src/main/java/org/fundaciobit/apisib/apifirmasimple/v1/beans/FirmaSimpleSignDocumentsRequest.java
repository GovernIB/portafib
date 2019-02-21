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
public class FirmaSimpleSignDocumentsRequest {

  FirmaSimpleCommonInfo commonInfo;

  FirmaSimpleFileInfoSignature[] fileInfoSignatureArray;

  /**
   * 
   */
  public FirmaSimpleSignDocumentsRequest() {
    super();
  }

  public FirmaSimpleSignDocumentsRequest(FirmaSimpleCommonInfo commonInfo,
      FirmaSimpleFileInfoSignature[] fileInfoSignatureArray) {
    super();
    this.commonInfo = commonInfo;
    this.fileInfoSignatureArray = fileInfoSignatureArray;
  }

  public FirmaSimpleFileInfoSignature[] getFileInfoSignatureArray() {
    return fileInfoSignatureArray;
  }

  public void setFileInfoSignatureArray(FirmaSimpleFileInfoSignature[] fileInfoSignatureArray) {
    this.fileInfoSignatureArray = fileInfoSignatureArray;
  }

  public FirmaSimpleCommonInfo getCommonInfo() {
    return commonInfo;
  }

  public void setCommonInfo(FirmaSimpleCommonInfo commonInfo) {
    this.commonInfo = commonInfo;
  }



}
