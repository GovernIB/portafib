package es.caib.portafib.back.form.webdb;

import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.TipusEstatDeFirmaFinalJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TipusEstatDeFirmaFinalForm extends PortaFIBBaseForm {
  
  private TipusEstatDeFirmaFinalJPA tipusEstatDeFirmaFinal;
  
  public TipusEstatDeFirmaFinalForm() {
  }
  
  public TipusEstatDeFirmaFinalForm(TipusEstatDeFirmaFinalForm __toClone) {
    super(__toClone);
      this.tipusEstatDeFirmaFinal = __toClone.tipusEstatDeFirmaFinal;
  }
  
  public TipusEstatDeFirmaFinalForm(TipusEstatDeFirmaFinalJPA tipusEstatDeFirmaFinal, boolean nou) {
    super(nou);
    this.tipusEstatDeFirmaFinal = tipusEstatDeFirmaFinal;
  }
  
  public TipusEstatDeFirmaFinalJPA getTipusEstatDeFirmaFinal() {
    return tipusEstatDeFirmaFinal;
  }
  public void setTipusEstatDeFirmaFinal(TipusEstatDeFirmaFinalJPA tipusEstatDeFirmaFinal) {
    this.tipusEstatDeFirmaFinal = tipusEstatDeFirmaFinal;
  }
  
  
  
} // Final de Classe 
