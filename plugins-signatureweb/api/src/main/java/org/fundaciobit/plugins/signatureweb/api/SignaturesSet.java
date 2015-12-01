package org.fundaciobit.plugins.signatureweb.api;

import java.util.Date;

/**
 * 
 * @author anadal
 *
 */
public class SignaturesSet {

  protected String signaturesSetID;

  /** Data en que les sol·lictuds de firma caduquen */
  protected Date expiryDate;

  protected CommonInfoSignature commonInfoSignature;

  protected FileInfoSignature[] fileInfoSignatureArray = null;

  /**
   * 
   */
  public SignaturesSet() {
  }

  /**
   * @param signaturesSetID
   * @param commonInfoSignature
   * @param fileInfoSignatureArray
   */
  public SignaturesSet(String signaturesSetID, Date expiryDate,
      CommonInfoSignature commonInfoSignature, FileInfoSignature[] fileInfoSignatureArray) {
    super();
    this.signaturesSetID = signaturesSetID;
    this.commonInfoSignature = commonInfoSignature;
    this.fileInfoSignatureArray = fileInfoSignatureArray;
  }

  public String getSignaturesSetID() {
    return signaturesSetID;
  }

  public void setSignaturesSetID(String signaturesSetID) {
    this.signaturesSetID = signaturesSetID;
  }

  public CommonInfoSignature getCommonInfoSignature() {
    return commonInfoSignature;
  }

  public void setCommonInfoSignature(CommonInfoSignature commonInfoSignature) {
    this.commonInfoSignature = commonInfoSignature;
  }

  public FileInfoSignature[] getFileInfoSignatureArray() {
    return fileInfoSignatureArray;
  }

  public void setFileInfoSignatureArray(FileInfoSignature[] fileInfoSignatureArray) {
    this.fileInfoSignatureArray = fileInfoSignatureArray;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

}
