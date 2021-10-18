package es.caib.portafib.back.form.webdb;

import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.persistence.FitxerJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class FitxerForm extends PortaFIBBaseForm {
  
  private FitxerJPA fitxer;
  
  public FitxerForm() {
  }
  
  public FitxerForm(FitxerForm __toClone) {
    super(__toClone);
      this.fitxer = __toClone.fitxer;
  }
  
  public FitxerForm(FitxerJPA fitxer, boolean nou) {
    super(nou);
    this.fitxer = fitxer;
  }
  
  public FitxerJPA getFitxer() {
    return fitxer;
  }
  public void setFitxer(FitxerJPA fitxer) {
    this.fitxer = fitxer;
  }
  
  
  
} // Final de Classe 
