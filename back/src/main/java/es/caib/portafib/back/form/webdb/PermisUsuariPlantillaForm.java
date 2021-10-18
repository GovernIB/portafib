package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.persistence.PermisUsuariPlantillaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PermisUsuariPlantillaForm extends PortaFIBBaseForm {
  
  private PermisUsuariPlantillaJPA permisUsuariPlantilla;
  
  public PermisUsuariPlantillaForm() {
  }
  
  public PermisUsuariPlantillaForm(PermisUsuariPlantillaForm __toClone) {
    super(__toClone);
      this.permisUsuariPlantilla = __toClone.permisUsuariPlantilla;
    this.listOfUsuariEntitatForUsuariEntitatID = __toClone.listOfUsuariEntitatForUsuariEntitatID;
    this.listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID = __toClone.listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID;
  }
  
  public PermisUsuariPlantillaForm(PermisUsuariPlantillaJPA permisUsuariPlantilla, boolean nou) {
    super(nou);
    this.permisUsuariPlantilla = permisUsuariPlantilla;
  }
  
  public PermisUsuariPlantillaJPA getPermisUsuariPlantilla() {
    return permisUsuariPlantilla;
  }
  public void setPermisUsuariPlantilla(PermisUsuariPlantillaJPA permisUsuariPlantilla) {
    this.permisUsuariPlantilla = permisUsuariPlantilla;
  }
  
  
  private List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID;

  public List<StringKeyValue> getListOfUsuariEntitatForUsuariEntitatID() {
    return this.listOfUsuariEntitatForUsuariEntitatID;
  }

  public void setListOfUsuariEntitatForUsuariEntitatID(List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID) {
    this.listOfUsuariEntitatForUsuariEntitatID = listOfUsuariEntitatForUsuariEntitatID;
  }



  private List<StringKeyValue> listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID;

  public List<StringKeyValue> getListOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID() {
    return this.listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID;
  }

  public void setListOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID(List<StringKeyValue> listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID) {
    this.listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID = listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID;
  }



  
} // Final de Classe 
