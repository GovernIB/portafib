package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.TipusDocumentJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TipusDocumentForm extends PortaFIBBaseForm {
  
  private TipusDocumentJPA tipusDocument;
  
  public TipusDocumentForm() {
  }
  
  public TipusDocumentForm(TipusDocumentForm __toClone) {
    super(__toClone);
      this.tipusDocument = __toClone.tipusDocument;
    this.listOfTraduccioForNomID = __toClone.listOfTraduccioForNomID;
    this.listOfValuesForTipusDocumentBaseID = __toClone.listOfValuesForTipusDocumentBaseID;
    this.listOfUsuariAplicacioForUsuariAplicacioID = __toClone.listOfUsuariAplicacioForUsuariAplicacioID;
  }
  
  public TipusDocumentForm(TipusDocumentJPA tipusDocument, boolean nou) {
    super(nou);
    this.tipusDocument = tipusDocument;
  }
  
  public TipusDocumentJPA getTipusDocument() {
    return tipusDocument;
  }
  public void setTipusDocument(TipusDocumentJPA tipusDocument) {
    this.tipusDocument = tipusDocument;
  }
  
  java.util.List<es.caib.portafib.model.entity.Idioma> idiomesTraduccio = null;

  public java.util.List<es.caib.portafib.model.entity.Idioma> getIdiomesTraduccio() {
    return idiomesTraduccio;
  }

  public void setIdiomesTraduccio(java.util.List<es.caib.portafib.model.entity.Idioma> idiomesTraduccio) {
    this.idiomesTraduccio = idiomesTraduccio;
  }
  
  private List<StringKeyValue> listOfTraduccioForNomID;

  public List<StringKeyValue> getListOfTraduccioForNomID() {
    return this.listOfTraduccioForNomID;
  }

  public void setListOfTraduccioForNomID(List<StringKeyValue> listOfTraduccioForNomID) {
    this.listOfTraduccioForNomID = listOfTraduccioForNomID;
  }



  private List<StringKeyValue> listOfValuesForTipusDocumentBaseID;

  public List<StringKeyValue> getListOfValuesForTipusDocumentBaseID() {
    return this.listOfValuesForTipusDocumentBaseID;
  }

  public void setListOfValuesForTipusDocumentBaseID(List<StringKeyValue> listOfValuesForTipusDocumentBaseID) {
    this.listOfValuesForTipusDocumentBaseID = listOfValuesForTipusDocumentBaseID;
  }



  private List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID;

  public List<StringKeyValue> getListOfUsuariAplicacioForUsuariAplicacioID() {
    return this.listOfUsuariAplicacioForUsuariAplicacioID;
  }

  public void setListOfUsuariAplicacioForUsuariAplicacioID(List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID) {
    this.listOfUsuariAplicacioForUsuariAplicacioID = listOfUsuariAplicacioForUsuariAplicacioID;
  }



  
} // Final de Classe 
