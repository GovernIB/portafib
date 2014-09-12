package es.caib.portafib.back.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.fundaciobit.genapp.common.StringKeyValue;

import es.caib.portafib.back.form.webdb.FluxDeFirmesForm;
import es.caib.portafib.jpa.PlantillaFluxDeFirmesJPA;

/**
 * 
 * @author anadal
 *
 */
public class PlantillaDeFluxDeFirmesForm extends FluxDeFirmesForm {
  
  
  
  private PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes;
  
  
  private List<StringKeyValue> listOfUsuariEntitatFavorit;
  
  private Set<StringKeyValue>  listOfUsuariEntitatForUsuariEntitatID;

  private List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID;
  
  private String usuariEntitatPrimeraFirma;
  
  private String redirectOnModify = null;
  
  private boolean readOnly = false;
  
  private Map<Long,String> backgroundColorsOfBloc = new HashMap<Long,String>();
  
  private Map<Long,String> backgroundColorsOfFirma = new HashMap<Long,String>();
  


  private List<StringKeyValue> listOfFluxDeFirmesForFluxDeFirmesID = new ArrayList<StringKeyValue>();



  



  
  public PlantillaDeFluxDeFirmesForm(FluxDeFirmesForm base) {
    super(base);
  }
  
  public List<StringKeyValue> getListOfFluxDeFirmesForFluxDeFirmesID() {
    return this.listOfFluxDeFirmesForFluxDeFirmesID;
  }

  public void setListOfFluxDeFirmesForFluxDeFirmesID(List<StringKeyValue> listOfFluxDeFirmesForFluxDeFirmesID) {
    this.listOfFluxDeFirmesForFluxDeFirmesID = listOfFluxDeFirmesForFluxDeFirmesID;
  }
  

  public List<StringKeyValue> getListOfUsuariEntitatFavorit() {
    return listOfUsuariEntitatFavorit;
  }

  public void setListOfUsuariEntitatFavorit(List<StringKeyValue> listOfUsuariEntitatFavorit) {
    this.listOfUsuariEntitatFavorit = listOfUsuariEntitatFavorit;
  }


  public Set<StringKeyValue> getListOfUsuariEntitatForUsuariEntitatID() {
    return listOfUsuariEntitatForUsuariEntitatID;
  }


  public void setListOfUsuariEntitatForUsuariEntitatID(
      Set<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID) {
    this.listOfUsuariEntitatForUsuariEntitatID = listOfUsuariEntitatForUsuariEntitatID;
  }


  public List<StringKeyValue> getListOfUsuariAplicacioForUsuariAplicacioID() {
    return listOfUsuariAplicacioForUsuariAplicacioID;
  }


  public void setListOfUsuariAplicacioForUsuariAplicacioID(
      List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID) {
    this.listOfUsuariAplicacioForUsuariAplicacioID = listOfUsuariAplicacioForUsuariAplicacioID;
  }


  public String getUsuariEntitatPrimeraFirma() {
    return usuariEntitatPrimeraFirma;
  }


  public void setUsuariEntitatPrimeraFirma(String usuariEntitatPrimeraFirma) {
    this.usuariEntitatPrimeraFirma = usuariEntitatPrimeraFirma;
  }


  public String getRedirectOnModify() {
    return redirectOnModify;
  }


  public void setRedirectOnModify(String redirectOnModify) {
    this.redirectOnModify = redirectOnModify;
  }


  public boolean isReadOnly() {
    return readOnly;
  }


  public void setReadOnly(boolean readOnly) {
    this.readOnly = readOnly;
  }


  public Map<Long, String> getBackgroundColorsOfBloc() {
    return backgroundColorsOfBloc;
  }


  public void setBackgroundColorsOfBloc(Map<Long, String> backgroundColorsOfBloc) {
    this.backgroundColorsOfBloc = backgroundColorsOfBloc;
  }


  public Map<Long, String> getBackgroundColorsOfFirma() {
    return backgroundColorsOfFirma;
  }


  public void setBackgroundColorsOfFirma(Map<Long, String> backgroundColorsOfFirma) {
    this.backgroundColorsOfFirma = backgroundColorsOfFirma;
  }

  public PlantillaFluxDeFirmesJPA getPlantillaFluxDeFirmes() {
    return plantillaFluxDeFirmes;
  }

  public void setPlantillaFluxDeFirmes(PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes) {
    this.plantillaFluxDeFirmes = plantillaFluxDeFirmes;
  }




}
