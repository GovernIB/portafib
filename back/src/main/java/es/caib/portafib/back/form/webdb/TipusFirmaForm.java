package es.caib.portafib.back.form.webdb;

import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.TipusFirmaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TipusFirmaForm extends PortaFIBBaseForm {
  
  private TipusFirmaJPA tipusFirma;
  
  public TipusFirmaForm() {
  }
  
  public TipusFirmaForm(TipusFirmaForm __toClone) {
    super(__toClone);
      this.tipusFirma = __toClone.tipusFirma;
  }
  
  public TipusFirmaForm(TipusFirmaJPA tipusFirma, boolean nou) {
    super(nou);
    this.tipusFirma = tipusFirma;
  }
  
  public TipusFirmaJPA getTipusFirma() {
    return tipusFirma;
  }
  public void setTipusFirma(TipusFirmaJPA tipusFirma) {
    this.tipusFirma = tipusFirma;
  }
  
  
  
} // Final de Classe 
