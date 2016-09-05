package es.caib.portafib.logic.passarela;

import java.io.File;

import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;

/**
 * Esta de Firma per  ús Intern
 * 
 * @author anadal
 *
 */

public class PassarelaSignatureStatusWebInternalUse extends  PassarelaSignatureStatus {

  protected File fitxerFirmat;

  public File getFitxerFirmat() {
    return fitxerFirmat;
  }

  public void setFitxerFirmat(File fitxerFirmat) {
    this.fitxerFirmat = fitxerFirmat;
  }

}
