package es.caib.portafib.back.form.webdb;

import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.PosicioPaginaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PosicioPaginaForm extends PortaFIBBaseForm {
  
  private PosicioPaginaJPA posicioPagina;
  
  public PosicioPaginaForm() {
  }
  
  public PosicioPaginaForm(PosicioPaginaForm __toClone) {
    super(__toClone);
      this.posicioPagina = __toClone.posicioPagina;
  }
  
  public PosicioPaginaForm(PosicioPaginaJPA posicioPagina, boolean nou) {
    super(nou);
    this.posicioPagina = posicioPagina;
  }
  
  public PosicioPaginaJPA getPosicioPagina() {
    return posicioPagina;
  }
  public void setPosicioPagina(PosicioPaginaJPA posicioPagina) {
    this.posicioPagina = posicioPagina;
  }
  
  
  
} // Final de Classe 
