package org.fundaciobit.plugins.signature.api;

/**
 * 
 * @author anadal
 *
 */
public class SignaturesSet {

  protected String signaturesSetID;

  protected CommonInfoSignature commonInfoSignature;

  protected FileInfoSignature[] fileInfoSignatureArray = null;

  protected StatusSignaturesSet statusSignaturesSet = new StatusSignaturesSet();

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
  public SignaturesSet(String signaturesSetID,
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

  public StatusSignaturesSet getStatusSignaturesSet() {
    return statusSignaturesSet;
  }

  public void setStatusSignaturesSet(StatusSignaturesSet statusSignaturesSet) {
    this.statusSignaturesSet = statusSignaturesSet;
  }

}
