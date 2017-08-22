package es.caib.portafib.ws.v1.impl;



/**
 * Resultat d'una firma
 * 
 * @author anadal
 *
 */
@javax.xml.bind.annotation.XmlType(name="passarelaSignatureResult")
public class PassarelaSignatureResultWs extends PassarelaSignatureStatusWs {

  protected FitxerBean signedFile;

  protected String signID;

  /**
   * 
   */
  public PassarelaSignatureResultWs() {
    super();
  }
  
  

  /**
   * @param status
   * @param errorMessage
   * @param errorStackTrace
   * @param signedFile
   * @param signID
   */
  public PassarelaSignatureResultWs(int status, String errorMessage, String errorStackTrace,
      FitxerBean signedFile, String signID) {
    super(status, errorMessage, errorStackTrace);
    this.signedFile = signedFile;
    this.signID = signID;
  }


  public FitxerBean getSignedFile() {
    return signedFile;
  }

  public void setSignedFile(FitxerBean signedFile) {
    this.signedFile = signedFile;
  }

  public String getSignID() {
    return signID;
  }

  public void setSignID(String signID) {
    this.signID = signID;
  }

}
