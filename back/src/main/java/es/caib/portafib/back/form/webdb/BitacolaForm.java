package es.caib.portafib.back.form.webdb;

import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.BitacolaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class BitacolaForm extends PortaFIBBaseForm {
  
  private BitacolaJPA bitacola;
  
  public BitacolaForm() {
  }
  
  public BitacolaForm(BitacolaForm __toClone) {
    super(__toClone);
      this.bitacola = __toClone.bitacola;
  }
  
  public BitacolaForm(BitacolaJPA bitacola, boolean nou) {
    super(nou);
    this.bitacola = bitacola;
  }
  
  public BitacolaJPA getBitacola() {
    return bitacola;
  }
  public void setBitacola(BitacolaJPA bitacola) {
    this.bitacola = bitacola;
  }
  
  
  
} // Final de Classe 
