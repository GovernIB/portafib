package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.ModulDeFirmaPerTipusDeDocumentJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class ModulDeFirmaPerTipusDeDocumentForm extends PortaFIBBaseForm {
  
  private ModulDeFirmaPerTipusDeDocumentJPA modulDeFirmaPerTipusDeDocument;
  
  public ModulDeFirmaPerTipusDeDocumentForm() {
  }
  
  public ModulDeFirmaPerTipusDeDocumentForm(ModulDeFirmaPerTipusDeDocumentForm __toClone) {
    super(__toClone);
      this.modulDeFirmaPerTipusDeDocument = __toClone.modulDeFirmaPerTipusDeDocument;
    this.listOfTipusDocumentForTipusDocumentID = __toClone.listOfTipusDocumentForTipusDocumentID;
    this.listOfModulDeFirmaForModulDeFirmaID = __toClone.listOfModulDeFirmaForModulDeFirmaID;
  }
  
  public ModulDeFirmaPerTipusDeDocumentForm(ModulDeFirmaPerTipusDeDocumentJPA modulDeFirmaPerTipusDeDocument, boolean nou) {
    super(nou);
    this.modulDeFirmaPerTipusDeDocument = modulDeFirmaPerTipusDeDocument;
  }
  
  public ModulDeFirmaPerTipusDeDocumentJPA getModulDeFirmaPerTipusDeDocument() {
    return modulDeFirmaPerTipusDeDocument;
  }
  public void setModulDeFirmaPerTipusDeDocument(ModulDeFirmaPerTipusDeDocumentJPA modulDeFirmaPerTipusDeDocument) {
    this.modulDeFirmaPerTipusDeDocument = modulDeFirmaPerTipusDeDocument;
  }
  
  
  private List<StringKeyValue> listOfTipusDocumentForTipusDocumentID;

  public List<StringKeyValue> getListOfTipusDocumentForTipusDocumentID() {
    return this.listOfTipusDocumentForTipusDocumentID;
  }

  public void setListOfTipusDocumentForTipusDocumentID(List<StringKeyValue> listOfTipusDocumentForTipusDocumentID) {
    this.listOfTipusDocumentForTipusDocumentID = listOfTipusDocumentForTipusDocumentID;
  }



  private List<StringKeyValue> listOfModulDeFirmaForModulDeFirmaID;

  public List<StringKeyValue> getListOfModulDeFirmaForModulDeFirmaID() {
    return this.listOfModulDeFirmaForModulDeFirmaID;
  }

  public void setListOfModulDeFirmaForModulDeFirmaID(List<StringKeyValue> listOfModulDeFirmaForModulDeFirmaID) {
    this.listOfModulDeFirmaForModulDeFirmaID = listOfModulDeFirmaForModulDeFirmaID;
  }



  
} // Final de Classe 
