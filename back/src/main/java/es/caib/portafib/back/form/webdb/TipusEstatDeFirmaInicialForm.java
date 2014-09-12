package es.caib.portafib.back.form.webdb;

import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.TipusEstatDeFirmaInicialJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TipusEstatDeFirmaInicialForm extends PortaFIBBaseForm {
  
  private TipusEstatDeFirmaInicialJPA tipusEstatDeFirmaInicial;
  
  public TipusEstatDeFirmaInicialForm() {
  }
  
  public TipusEstatDeFirmaInicialForm(TipusEstatDeFirmaInicialForm __toClone) {
    super(__toClone);
      this.tipusEstatDeFirmaInicial = __toClone.tipusEstatDeFirmaInicial;
  }
  
  public TipusEstatDeFirmaInicialForm(TipusEstatDeFirmaInicialJPA tipusEstatDeFirmaInicial, boolean nou) {
    super(nou);
    this.tipusEstatDeFirmaInicial = tipusEstatDeFirmaInicial;
  }
  
  public TipusEstatDeFirmaInicialJPA getTipusEstatDeFirmaInicial() {
    return tipusEstatDeFirmaInicial;
  }
  public void setTipusEstatDeFirmaInicial(TipusEstatDeFirmaInicialJPA tipusEstatDeFirmaInicial) {
    this.tipusEstatDeFirmaInicial = tipusEstatDeFirmaInicial;
  }
  
  
  
} // Final de Classe 
