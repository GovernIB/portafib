package es.caib.portafib.back.form.webdb;

import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.CodiBarresJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class CodiBarresForm extends PortaFIBBaseForm {
  
  private CodiBarresJPA codiBarres;
  
  public CodiBarresForm() {
  }
  
  public CodiBarresForm(CodiBarresForm __toClone) {
    super(__toClone);
      this.codiBarres = __toClone.codiBarres;
  }
  
  public CodiBarresForm(CodiBarresJPA codiBarres, boolean nou) {
    super(nou);
    this.codiBarres = codiBarres;
  }
  
  public CodiBarresJPA getCodiBarres() {
    return codiBarres;
  }
  public void setCodiBarres(CodiBarresJPA codiBarres) {
    this.codiBarres = codiBarres;
  }
  
  
  
} // Final de Classe 
