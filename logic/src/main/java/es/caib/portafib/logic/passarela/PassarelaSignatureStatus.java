package es.caib.portafib.logic.passarela;

import java.io.File;

/**
 * 
 * @author anadal
 *
 */

public class PassarelaSignatureStatus {

  protected int status = PassarelaSignatureResult.STATUS_INITIALIZING;

  protected File fitxerFirmat;

  protected String msgError;

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public File getFitxerFirmat() {
    return fitxerFirmat;
  }

  public void setFitxerFirmat(File fitxerFirmat) {
    this.fitxerFirmat = fitxerFirmat;
  }

  public String getMsgError() {
    return msgError;
  }

  public void setMsgError(String msgError) {
    this.msgError = msgError;
  }

}
