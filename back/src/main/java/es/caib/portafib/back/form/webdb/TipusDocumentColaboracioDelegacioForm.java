package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.TipusDocumentColaboracioDelegacioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TipusDocumentColaboracioDelegacioForm extends PortaFIBBaseForm {
  
  private TipusDocumentColaboracioDelegacioJPA tipusDocumentColaboracioDelegacio;
  
  public TipusDocumentColaboracioDelegacioForm() {
  }
  
  public TipusDocumentColaboracioDelegacioForm(TipusDocumentColaboracioDelegacioForm __toClone) {
    super(__toClone);
      this.tipusDocumentColaboracioDelegacio = __toClone.tipusDocumentColaboracioDelegacio;
    this.listOfColaboracioDelegacioForColaboracioDelegacioID = __toClone.listOfColaboracioDelegacioForColaboracioDelegacioID;
    this.listOfTipusDocumentForTipusDocumentID = __toClone.listOfTipusDocumentForTipusDocumentID;
  }
  
  public TipusDocumentColaboracioDelegacioForm(TipusDocumentColaboracioDelegacioJPA tipusDocumentColaboracioDelegacio, boolean nou) {
    super(nou);
    this.tipusDocumentColaboracioDelegacio = tipusDocumentColaboracioDelegacio;
  }
  
  public TipusDocumentColaboracioDelegacioJPA getTipusDocumentColaboracioDelegacio() {
    return tipusDocumentColaboracioDelegacio;
  }
  public void setTipusDocumentColaboracioDelegacio(TipusDocumentColaboracioDelegacioJPA tipusDocumentColaboracioDelegacio) {
    this.tipusDocumentColaboracioDelegacio = tipusDocumentColaboracioDelegacio;
  }
  
  
  private List<StringKeyValue> listOfColaboracioDelegacioForColaboracioDelegacioID;

  public List<StringKeyValue> getListOfColaboracioDelegacioForColaboracioDelegacioID() {
    return this.listOfColaboracioDelegacioForColaboracioDelegacioID;
  }

  public void setListOfColaboracioDelegacioForColaboracioDelegacioID(List<StringKeyValue> listOfColaboracioDelegacioForColaboracioDelegacioID) {
    this.listOfColaboracioDelegacioForColaboracioDelegacioID = listOfColaboracioDelegacioForColaboracioDelegacioID;
  }



  private List<StringKeyValue> listOfTipusDocumentForTipusDocumentID;

  public List<StringKeyValue> getListOfTipusDocumentForTipusDocumentID() {
    return this.listOfTipusDocumentForTipusDocumentID;
  }

  public void setListOfTipusDocumentForTipusDocumentID(List<StringKeyValue> listOfTipusDocumentForTipusDocumentID) {
    this.listOfTipusDocumentForTipusDocumentID = listOfTipusDocumentForTipusDocumentID;
  }



  
} // Final de Classe 
