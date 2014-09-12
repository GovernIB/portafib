package es.caib.portafib.back.form.webdb;

import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.TipusNotificacioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TipusNotificacioForm extends PortaFIBBaseForm {
  
  private TipusNotificacioJPA tipusNotificacio;
  
  public TipusNotificacioForm() {
  }
  
  public TipusNotificacioForm(TipusNotificacioForm __toClone) {
    super(__toClone);
      this.tipusNotificacio = __toClone.tipusNotificacio;
  }
  
  public TipusNotificacioForm(TipusNotificacioJPA tipusNotificacio, boolean nou) {
    super(nou);
    this.tipusNotificacio = tipusNotificacio;
  }
  
  public TipusNotificacioJPA getTipusNotificacio() {
    return tipusNotificacio;
  }
  public void setTipusNotificacio(TipusNotificacioJPA tipusNotificacio) {
    this.tipusNotificacio = tipusNotificacio;
  }
  
  
  
} // Final de Classe 
