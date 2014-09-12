package es.caib.portafib.back.form.webdb;

import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.TipusEstatPeticioDeFirmaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TipusEstatPeticioDeFirmaForm extends PortaFIBBaseForm {
  
  private TipusEstatPeticioDeFirmaJPA tipusEstatPeticioDeFirma;
  
  public TipusEstatPeticioDeFirmaForm() {
  }
  
  public TipusEstatPeticioDeFirmaForm(TipusEstatPeticioDeFirmaForm __toClone) {
    super(__toClone);
      this.tipusEstatPeticioDeFirma = __toClone.tipusEstatPeticioDeFirma;
  }
  
  public TipusEstatPeticioDeFirmaForm(TipusEstatPeticioDeFirmaJPA tipusEstatPeticioDeFirma, boolean nou) {
    super(nou);
    this.tipusEstatPeticioDeFirma = tipusEstatPeticioDeFirma;
  }
  
  public TipusEstatPeticioDeFirmaJPA getTipusEstatPeticioDeFirma() {
    return tipusEstatPeticioDeFirma;
  }
  public void setTipusEstatPeticioDeFirma(TipusEstatPeticioDeFirmaJPA tipusEstatPeticioDeFirma) {
    this.tipusEstatPeticioDeFirma = tipusEstatPeticioDeFirma;
  }
  
  
  
} // Final de Classe 
