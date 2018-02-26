package org.fundaciobit.apifirmawebsimple.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
public class FirmaEnServidorSimpleSignaturesSet {

  FirmaSimpleCommonInfo commonInfo;

  FirmaSimpleFileInfoSignature[] fileInfoSignatureArray;

  /**
   * 
   */
  public FirmaEnServidorSimpleSignaturesSet() {
    super();
  }

  /**
   * @param commonInfo
   * @param fileInfoSignatureArray
   */
  public FirmaEnServidorSimpleSignaturesSet(FirmaSimpleCommonInfo commonInfo,
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
