package es.caib.portafib.back.form;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import es.caib.portafib.back.form.webdb.FluxDeFirmesFilterForm;

/**
 * 
 * @author anadal
 *
 */
@Component
public class PlantillaDeFluxDeFirmesFilterForm extends FluxDeFirmesFilterForm {

  private Map<String, String> mapOfUsuariAplicacioForUsuariAplicacioID;
  
  private Map<String, String> mapOfFluxDeFirmesForFluxDeFirmesID = new HashMap<String, String>();

  
  public PlantillaDeFluxDeFirmesFilterForm() {    
  }
  
  public PlantillaDeFluxDeFirmesFilterForm(FluxDeFirmesFilterForm ffff) {
    super(ffff);
  }
  

  public Map<String, String> getMapOfUsuariAplicacioForUsuariAplicacioID() {
    return this.mapOfUsuariAplicacioForUsuariAplicacioID;
  }

  public void setMapOfUsuariAplicacioForUsuariAplicacioID(Map<String, String> mapOfUsuariAplicacioForUsuariAplicacioID) {
    this.mapOfUsuariAplicacioForUsuariAplicacioID = mapOfUsuariAplicacioForUsuariAplicacioID;
  }
  
  public Map<String, String> getMapOfFluxDeFirmesForFluxDeFirmesID() {
    return this.mapOfFluxDeFirmesForFluxDeFirmesID;
  }

  public void setMapOfFluxDeFirmesForFluxDeFirmesID(Map<String, String> mapOfFluxDeFirmesForFluxDeFirmesID) {
    this.mapOfFluxDeFirmesForFluxDeFirmesID = mapOfFluxDeFirmesForFluxDeFirmesID;
  }

}
