package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.persistence.PermisGrupPlantillaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PermisGrupPlantillaForm extends PortaFIBBaseForm {
  
  private PermisGrupPlantillaJPA permisGrupPlantilla;
  
  public PermisGrupPlantillaForm() {
  }
  
  public PermisGrupPlantillaForm(PermisGrupPlantillaForm __toClone) {
    super(__toClone);
      this.permisGrupPlantilla = __toClone.permisGrupPlantilla;
    this.listOfGrupEntitatForGrupEntitatID = __toClone.listOfGrupEntitatForGrupEntitatID;
    this.listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID = __toClone.listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID;
  }
  
  public PermisGrupPlantillaForm(PermisGrupPlantillaJPA permisGrupPlantilla, boolean nou) {
    super(nou);
    this.permisGrupPlantilla = permisGrupPlantilla;
  }
  
  public PermisGrupPlantillaJPA getPermisGrupPlantilla() {
    return permisGrupPlantilla;
  }
  public void setPermisGrupPlantilla(PermisGrupPlantillaJPA permisGrupPlantilla) {
    this.permisGrupPlantilla = permisGrupPlantilla;
  }
  
  
  private List<StringKeyValue> listOfGrupEntitatForGrupEntitatID;

  public List<StringKeyValue> getListOfGrupEntitatForGrupEntitatID() {
    return this.listOfGrupEntitatForGrupEntitatID;
  }

  public void setListOfGrupEntitatForGrupEntitatID(List<StringKeyValue> listOfGrupEntitatForGrupEntitatID) {
    this.listOfGrupEntitatForGrupEntitatID = listOfGrupEntitatForGrupEntitatID;
  }



  private List<StringKeyValue> listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID;

  public List<StringKeyValue> getListOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID() {
    return this.listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID;
  }

  public void setListOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID(List<StringKeyValue> listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID) {
    this.listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID = listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID;
  }



  
} // Final de Classe 
