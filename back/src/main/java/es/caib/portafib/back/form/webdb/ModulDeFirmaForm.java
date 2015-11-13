package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.ModulDeFirmaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class ModulDeFirmaForm extends PortaFIBBaseForm {
  
  private ModulDeFirmaJPA modulDeFirma;
  
  public ModulDeFirmaForm() {
  }
  
  public ModulDeFirmaForm(ModulDeFirmaForm __toClone) {
    super(__toClone);
      this.modulDeFirma = __toClone.modulDeFirma;
    this.listOfTraduccioForNomID = __toClone.listOfTraduccioForNomID;
    this.listOfTraduccioForDescripcioCurtaID = __toClone.listOfTraduccioForDescripcioCurtaID;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
  }
  
  public ModulDeFirmaForm(ModulDeFirmaJPA modulDeFirma, boolean nou) {
    super(nou);
    this.modulDeFirma = modulDeFirma;
  }
  
  public ModulDeFirmaJPA getModulDeFirma() {
    return modulDeFirma;
  }
  public void setModulDeFirma(ModulDeFirmaJPA modulDeFirma) {
    this.modulDeFirma = modulDeFirma;
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



  private List<StringKeyValue> listOfTraduccioForDescripcioCurtaID;

  public List<StringKeyValue> getListOfTraduccioForDescripcioCurtaID() {
    return this.listOfTraduccioForDescripcioCurtaID;
  }

  public void setListOfTraduccioForDescripcioCurtaID(List<StringKeyValue> listOfTraduccioForDescripcioCurtaID) {
    this.listOfTraduccioForDescripcioCurtaID = listOfTraduccioForDescripcioCurtaID;
  }



  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  
} // Final de Classe 
