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

  public static final String USUARI_ENTITAT_PRIMERA_FIRMA_FIELD = "id";

  private PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes;

  String id; // id del usuari-entitat de la primera firma =
             // usuariEntitatPrimeraFirma
  String param1; // Parametre opcional 1
  String param2; // Parametre opcional 1

  private Set<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID;

  private List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID;

  private String redirectOnModify = null;

  private boolean readOnly = false;

  private final Map<Long, String> backgroundColorsOfBloc = new HashMap<Long, String>();

  private final Map<Long, String> backgroundColorsOfFirma = new HashMap<Long, String>();

  private final Map<Long, String> backgroundColorsOfRevisor = new HashMap<Long, String>();

  private List<StringKeyValue> listOfFluxDeFirmesForFluxDeFirmesID = new ArrayList<StringKeyValue>();

  public PlantillaDeFluxDeFirmesForm(FluxDeFirmesForm base) {
    super(base);
  }

  public List<StringKeyValue> getListOfFluxDeFirmesForFluxDeFirmesID() {
    return this.listOfFluxDeFirmesForFluxDeFirmesID;
  }

  public void setListOfFluxDeFirmesForFluxDeFirmesID(
      List<StringKeyValue> listOfFluxDeFirmesForFluxDeFirmesID) {
    this.listOfFluxDeFirmesForFluxDeFirmesID = listOfFluxDeFirmesForFluxDeFirmesID;
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
    return id;
  }

  public void setUsuariEntitatPrimeraFirma(String usuariEntitatPrimeraFirma) {
    this.id = usuariEntitatPrimeraFirma;
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

  public Map<Long, String> getBackgroundColorsOfFirma() {
    return backgroundColorsOfFirma;
  }

  public Map<Long, String> getBackgroundColorsOfRevisor() {
    return backgroundColorsOfRevisor;
  }

  public PlantillaFluxDeFirmesJPA getPlantillaFluxDeFirmes() {
    return plantillaFluxDeFirmes;
  }

  public void setPlantillaFluxDeFirmes(PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes) {
    this.plantillaFluxDeFirmes = plantillaFluxDeFirmes;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getParam1() {
    return param1;
  }

  public void setParam1(String param1) {
    this.param1 = param1;
  }

  public String getParam2() {
    return param2;
  }

  public void setParam2(String param2) {
    this.param2 = param2;
  }

}
