package org.fundaciobit.plugins.signatureweb.fire;

/**
 * 
 * @author anadal
 *
 */
public class FIReSignaturesSet {
  
  final protected String appId;
  
  final protected String subjectId;

  final protected String fireTransaccionId;

  final protected FIReFileInfoSignature[] fireFileInfoSignature;



  /**
   * @param appId
   * @param subjectId
   * @param fireTransaccionId
   * @param fireFileInfoSignature
   */
  public FIReSignaturesSet(String appId, String subjectId, String fireTransaccionId,
      FIReFileInfoSignature[] fireFileInfoSignature) {
    super();
    this.appId = appId;
    this.subjectId = subjectId;
    this.fireTransaccionId = fireTransaccionId;
    this.fireFileInfoSignature = fireFileInfoSignature;
  }

  public String getFireTransaccionId() {
    return fireTransaccionId;
  }

  public FIReFileInfoSignature[] getFireFileInfoSignature() {
    return fireFileInfoSignature;
  }

  public String getAppId() {
    return appId;
  }

  public String getSubjectId() {
    return subjectId;
  }

}
