package es.caib.portafib.back.form.webdb;

import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.AlgorismeDeFirmaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class AlgorismeDeFirmaForm extends PortaFIBBaseForm {
  
  private AlgorismeDeFirmaJPA algorismeDeFirma;
  
  public AlgorismeDeFirmaForm() {
  }
  
  public AlgorismeDeFirmaForm(AlgorismeDeFirmaForm __toClone) {
    super(__toClone);
      this.algorismeDeFirma = __toClone.algorismeDeFirma;
  }
  
  public AlgorismeDeFirmaForm(AlgorismeDeFirmaJPA algorismeDeFirma, boolean nou) {
    super(nou);
    this.algorismeDeFirma = algorismeDeFirma;
  }
  
  public AlgorismeDeFirmaJPA getAlgorismeDeFirma() {
    return algorismeDeFirma;
  }
  public void setAlgorismeDeFirma(AlgorismeDeFirmaJPA algorismeDeFirma) {
    this.algorismeDeFirma = algorismeDeFirma;
  }
  
  
  
} // Final de Classe 
