package es.caib.portafib.back.form.webdb;

import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.TipusMetadadaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TipusMetadadaForm extends PortaFIBBaseForm {
  
  private TipusMetadadaJPA tipusMetadada;
  
  public TipusMetadadaForm() {
  }
  
  public TipusMetadadaForm(TipusMetadadaForm __toClone) {
    super(__toClone);
      this.tipusMetadada = __toClone.tipusMetadada;
  }
  
  public TipusMetadadaForm(TipusMetadadaJPA tipusMetadada, boolean nou) {
    super(nou);
    this.tipusMetadada = tipusMetadada;
  }
  
  public TipusMetadadaJPA getTipusMetadada() {
    return tipusMetadada;
  }
  public void setTipusMetadada(TipusMetadadaJPA tipusMetadada) {
    this.tipusMetadada = tipusMetadada;
  }
  
  
  
} // Final de Classe 
