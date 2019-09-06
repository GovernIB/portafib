package es.caib.portafib.back.form;

import java.util.List;
import es.caib.portafib.jpa.FluxDeFirmesJPA;

/**
 * 
 * @author anadal
 *
 */
public class SeleccioFluxDeFirmesForm extends SeleccioUsuariForm {

  public static final int TIPUS_PLANTILLA_USUARI = 0;

  public static final int TIPUS_PLANTILLA_USUARI_COMPARTIT = 1;

  public static final int TIPUS_PLANTILLA_APLICACIO_COMPARTIT = 2;

  public static final int TIPUS_SELECT_PRIMER_USUARI_DEL_FLUX = 3;

  int tipus;

  // Nom del flux
  String nom;

  // Tipus
  Long fluxPlantillaUsuari;
  List<FluxDeFirmesJPA> listOfFluxPlantillaUsuari;

  Long fluxPlantillaPersonaCompartit;
  List<FluxDeFirmesJPA> listOfFluxPlantillaPersonaCompartit;

  Long fluxPlantillaAplicacioCompartit;
  List<FluxDeFirmesJPA> listOfFluxPlantillaAplicacioCompartit;

  boolean solicitantUsuariEntitat;

  String usuariAplicacioID;

  int origenPeticioDeFirma;

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

  public int getOrigenPeticioDeFirma() {
    return origenPeticioDeFirma;
  }

  public void setOrigenPeticioDeFirma(int origenPeticioDeFirma) {
    this.origenPeticioDeFirma = origenPeticioDeFirma;
  }

}
