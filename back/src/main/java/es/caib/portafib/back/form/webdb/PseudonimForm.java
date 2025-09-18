package es.caib.portafib.back.form.webdb;

import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.persistence.PseudonimJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PseudonimForm extends PortaFIBBaseForm {
  
  private PseudonimJPA pseudonim;
  
  public PseudonimForm() {
  }
  
  public PseudonimForm(PseudonimForm __toClone) {
    super(__toClone);
      this.pseudonim = __toClone.pseudonim;
  }
  
  public PseudonimForm(PseudonimJPA pseudonim, boolean nou) {
    super(nou);
    this.pseudonim = pseudonim;
  }
  
  public PseudonimJPA getPseudonim() {
    return pseudonim;
  }
  public void setPseudonim(PseudonimJPA pseudonim) {
    this.pseudonim = pseudonim;
  }
  
  
  
} // Final de Classe 
