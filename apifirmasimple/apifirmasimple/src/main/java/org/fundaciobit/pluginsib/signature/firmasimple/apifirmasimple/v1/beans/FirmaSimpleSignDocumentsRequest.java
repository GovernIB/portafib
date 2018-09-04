package org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
public class FirmaSimpleSignDocumentsRequest {

  FirmaSimpleCommonInfo commonInfo;

  FirmaSimpleFileInfoSignature[] fileInfoSignatureArray;

  /**
   * 
   */
  public FirmaSimpleSignDocumentsRequest() {
    super();
  }

  /**
   * @param commonInfo
   * @param fileInfoSignatureArray
   */
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
