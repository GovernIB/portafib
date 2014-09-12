package es.caib.portafib.back.form.webdb;

import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.PosicioTaulaFirmesJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PosicioTaulaFirmesForm extends PortaFIBBaseForm {
  
  private PosicioTaulaFirmesJPA posicioTaulaFirmes;
  
  public PosicioTaulaFirmesForm() {
  }
  
  public PosicioTaulaFirmesForm(PosicioTaulaFirmesForm __toClone) {
    super(__toClone);
      this.posicioTaulaFirmes = __toClone.posicioTaulaFirmes;
  }
  
  public PosicioTaulaFirmesForm(PosicioTaulaFirmesJPA posicioTaulaFirmes, boolean nou) {
    super(nou);
    this.posicioTaulaFirmes = posicioTaulaFirmes;
  }
  
  public PosicioTaulaFirmesJPA getPosicioTaulaFirmes() {
    return posicioTaulaFirmes;
  }
  public void setPosicioTaulaFirmes(PosicioTaulaFirmesJPA posicioTaulaFirmes) {
    this.posicioTaulaFirmes = posicioTaulaFirmes;
  }
  
  
  
} // Final de Classe 
