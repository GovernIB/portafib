package es.caib.portafib.back.form.webdb;

import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.PrioritatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PrioritatForm extends PortaFIBBaseForm {
  
  private PrioritatJPA prioritat;
  
  public PrioritatForm() {
  }
  
  public PrioritatForm(PrioritatForm __toClone) {
    super(__toClone);
      this.prioritat = __toClone.prioritat;
  }
  
  public PrioritatForm(PrioritatJPA prioritat, boolean nou) {
    super(nou);
    this.prioritat = prioritat;
  }
  
  public PrioritatJPA getPrioritat() {
    return prioritat;
  }
  public void setPrioritat(PrioritatJPA prioritat) {
    this.prioritat = prioritat;
  }
  
  
  
} // Final de Classe 
