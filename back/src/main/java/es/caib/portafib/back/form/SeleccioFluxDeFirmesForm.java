package es.caib.portafib.back.form;

import java.util.List;

import org.fundaciobit.genapp.common.StringKeyValue;

import es.caib.portafib.jpa.FluxDeFirmesJPA;

/**
 * 
 * @author anadal
 *
 */
public class SeleccioFluxDeFirmesForm {
  
  public static final int TIPUS_PLANTILLA_USUARI = 0;
  
  public static final int TIPUS_PLANTILLA_USUARI_COMPARTIT = 1;
  
  public static final int TIPUS_PLANTILLA_APLICACIO_COMPARTIT = 2;
  
  public static final int TIPUS_LLISTA_USUARIS = 3;
  

  int tipus;
  
  // Nom del flux
  String nom;
  
  // Tipus
  Long fluxPlantillaUsuari;
  List<FluxDeFirmesJPA> listOfFluxPlantillaUsuari;

  Long fluxPlantillaPersonaCompartit;
  List<FluxDeFirmesJPA>  listOfFluxPlantillaPersonaCompartit;

  Long fluxPlantillaAplicacioCompartit;
  List<FluxDeFirmesJPA> listOfFluxPlantillaAplicacioCompartit;

  // usuaris seleccionar pel flux de firmes 
  List<String> usuarisFlux;

  // Possible usuaris del flux
  List<StringKeyValue> listOfUsuariFavorit;
  
  boolean solicitantUsuariEntitat;
  
  String usuariAplicacioID;
  
  String contexte;

  /**
   * 
   */
  public SeleccioFluxDeFirmesForm() {
    super();
  }

  public int getTipus() {
    return tipus;
  }
  public void setTipus(int tipus) {
    this.tipus = tipus;
  }
  public Long getFluxPlantillaUsuari() {
    return fluxPlantillaUsuari;
  }
  public void setFluxPlantillaUsuari(Long fluxPlantillaUsuari) {
    this.fluxPlantillaUsuari = fluxPlantillaUsuari;
  }
  public List<FluxDeFirmesJPA> getListOfFluxPlantillaUsuari() {
    return listOfFluxPlantillaUsuari;
  }
  public void setListOfFluxPlantillaUsuari(List<FluxDeFirmesJPA> listOfFluxPlantillaUsuari) {
    this.listOfFluxPlantillaUsuari = listOfFluxPlantillaUsuari;
  }
  public Long getFluxPlantillaPersonaCompartit() {
    return fluxPlantillaPersonaCompartit;
  }
  public void setFluxPlantillaPersonaCompartit(Long fluxPlantillaPersonaCompartit) {
    this.fluxPlantillaPersonaCompartit = fluxPlantillaPersonaCompartit;
  }
  public List<FluxDeFirmesJPA> getListOfFluxPlantillaPersonaCompartit() {
    return listOfFluxPlantillaPersonaCompartit;
  }
  public void setListOfFluxPlantillaPersonaCompartit(
      List<FluxDeFirmesJPA> listOfFluxPlantillaPersonaCompartit) {
    this.listOfFluxPlantillaPersonaCompartit = listOfFluxPlantillaPersonaCompartit;
  }
  public Long getFluxPlantillaAplicacioCompartit() {
    return fluxPlantillaAplicacioCompartit;
  }
  public void setFluxPlantillaAplicacioCompartit(Long fluxPlantillaAplicacioCompartit) {
    this.fluxPlantillaAplicacioCompartit = fluxPlantillaAplicacioCompartit;
  }
  public List<FluxDeFirmesJPA> getListOfFluxPlantillaAplicacioCompartit() {
    return listOfFluxPlantillaAplicacioCompartit;
  }
  public void setListOfFluxPlantillaAplicacioCompartit(
      List<FluxDeFirmesJPA> listOfFluxPlantillaAplicacioCompartit) {
    this.listOfFluxPlantillaAplicacioCompartit = listOfFluxPlantillaAplicacioCompartit;
  }

  public List<String> getUsuarisFlux() {
    return usuarisFlux;
  }

  public void setUsuarisFlux(List<String> usuarisFlux) {
    this.usuarisFlux = usuarisFlux;
  }

  public List<StringKeyValue> getListOfUsuariFavorit() {
    return listOfUsuariFavorit;
  }
  public void setListOfUsuariFavorit(List<StringKeyValue> listOfUsuariFavorit) {
    this.listOfUsuariFavorit = listOfUsuariFavorit;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public boolean isSolicitantUsuariEntitat() {
    return solicitantUsuariEntitat;
  }

  public void setSolicitantUsuariEntitat(boolean solicitantUsuariEntitat) {
    this.solicitantUsuariEntitat = solicitantUsuariEntitat;
  }

  public String getUsuariAplicacioID() {
    return usuariAplicacioID;
  }

  public void setUsuariAplicacioID(String usuariAplicacioID) {
    this.usuariAplicacioID = usuariAplicacioID;
  }

  public String getContexte() {
    return contexte;
  }

  public void setContexte(String contexte) {
    this.contexte = contexte;
  }

}
