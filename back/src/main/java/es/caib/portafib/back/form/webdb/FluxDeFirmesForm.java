package es.caib.portafib.back.form.webdb;

import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.persistence.FluxDeFirmesJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class FluxDeFirmesForm extends PortaFIBBaseForm {
  
  private FluxDeFirmesJPA fluxDeFirmes;
  
  public FluxDeFirmesForm() {
  }
  
  public FluxDeFirmesForm(FluxDeFirmesForm __toClone) {
    super(__toClone);
      this.fluxDeFirmes = __toClone.fluxDeFirmes;
  }
  
  public FluxDeFirmesForm(FluxDeFirmesJPA fluxDeFirmes, boolean nou) {
    super(nou);
    this.fluxDeFirmes = fluxDeFirmes;
  }
  
  public FluxDeFirmesJPA getFluxDeFirmes() {
    return fluxDeFirmes;
  }
  public void setFluxDeFirmes(FluxDeFirmesJPA fluxDeFirmes) {
    this.fluxDeFirmes = fluxDeFirmes;
  }
  
  
  
} // Final de Classe 
