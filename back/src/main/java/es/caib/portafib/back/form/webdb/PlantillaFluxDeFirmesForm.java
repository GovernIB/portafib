package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.PlantillaFluxDeFirmesJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PlantillaFluxDeFirmesForm extends PortaFIBBaseForm {
  
  private PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes;
  
  public PlantillaFluxDeFirmesForm() {
  }
  
  public PlantillaFluxDeFirmesForm(PlantillaFluxDeFirmesForm __toClone) {
    super(__toClone);
      this.plantillaFluxDeFirmes = __toClone.plantillaFluxDeFirmes;
    this.listOfFluxDeFirmesForFluxDeFirmesID = __toClone.listOfFluxDeFirmesForFluxDeFirmesID;
    this.listOfUsuariEntitatForUsuariEntitatID = __toClone.listOfUsuariEntitatForUsuariEntitatID;
    this.listOfUsuariAplicacioForUsuariAplicacioID = __toClone.listOfUsuariAplicacioForUsuariAplicacioID;
  }
  
  public PlantillaFluxDeFirmesForm(PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes, boolean nou) {
    super(nou);
    this.plantillaFluxDeFirmes = plantillaFluxDeFirmes;
  }
  
  public PlantillaFluxDeFirmesJPA getPlantillaFluxDeFirmes() {
    return plantillaFluxDeFirmes;
  }
  public void setPlantillaFluxDeFirmes(PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes) {
    this.plantillaFluxDeFirmes = plantillaFluxDeFirmes;
  }
  
  
  private List<StringKeyValue> listOfFluxDeFirmesForFluxDeFirmesID;

  public List<StringKeyValue> getListOfFluxDeFirmesForFluxDeFirmesID() {
    return this.listOfFluxDeFirmesForFluxDeFirmesID;
  }

  public void setListOfFluxDeFirmesForFluxDeFirmesID(List<StringKeyValue> listOfFluxDeFirmesForFluxDeFirmesID) {
    this.listOfFluxDeFirmesForFluxDeFirmesID = listOfFluxDeFirmesForFluxDeFirmesID;
  }



  private List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID;

  public List<StringKeyValue> getListOfUsuariEntitatForUsuariEntitatID() {
    return this.listOfUsuariEntitatForUsuariEntitatID;
  }

  public void setListOfUsuariEntitatForUsuariEntitatID(List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID) {
    this.listOfUsuariEntitatForUsuariEntitatID = listOfUsuariEntitatForUsuariEntitatID;
  }



  private List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID;

  public List<StringKeyValue> getListOfUsuariAplicacioForUsuariAplicacioID() {
    return this.listOfUsuariAplicacioForUsuariAplicacioID;
  }

  public void setListOfUsuariAplicacioForUsuariAplicacioID(List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID) {
    this.listOfUsuariAplicacioForUsuariAplicacioID = listOfUsuariAplicacioForUsuariAplicacioID;
  }



  
} // Final de Classe 
