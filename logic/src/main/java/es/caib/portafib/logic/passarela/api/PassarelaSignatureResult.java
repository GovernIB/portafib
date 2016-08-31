package es.caib.portafib.logic.passarela.api;

import es.caib.portafib.model.bean.FitxerBean;

/**
 * Resultat d'una firma
 * 
 * @author anadal
 *
 */
public class PassarelaSignatureResult extends PassarelaSignatureStatus {

  protected FitxerBean signedFile;

  protected String signID;

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
