package es.caib.portafib.back.form.webdb;

import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.persistence.IdiomaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class IdiomaForm extends PortaFIBBaseForm {
  
  private IdiomaJPA idioma;
  
  public IdiomaForm() {
  }
  
  public IdiomaForm(IdiomaForm __toClone) {
    super(__toClone);
      this.idioma = __toClone.idioma;
  }
  
  public IdiomaForm(IdiomaJPA idioma, boolean nou) {
    super(nou);
    this.idioma = idioma;
  }
  
  public IdiomaJPA getIdioma() {
    return idioma;
  }
  public void setIdioma(IdiomaJPA idioma) {
    this.idioma = idioma;
  }
  
  
  
} // Final de Classe 
