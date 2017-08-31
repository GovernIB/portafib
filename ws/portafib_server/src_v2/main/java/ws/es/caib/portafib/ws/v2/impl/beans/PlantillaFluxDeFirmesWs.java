package es.caib.portafib.ws.v2.impl.beans;

import es.caib.portafib.jpa.PlantillaFluxDeFirmesJPA;


/**
 * 
 * @author anadal
 * 
 */
public class PlantillaFluxDeFirmesWs extends FluxDeFirmesWs {

  java.lang.String descripcio;
  java.lang.String usuariEntitatID;
  java.lang.String usuariAplicacioID;
  java.lang.Boolean compartir;

  /**
   * 
   */
  public PlantillaFluxDeFirmesWs() {
    super();
  }

  /**
   * @param descripcio
   * @param usuariEntitatID
   * @param usuariAplicacioID
   * @param compartir
   */
  public PlantillaFluxDeFirmesWs(String descripcio, String usuariEntitatID,
      String usuariAplicacioID, Boolean compartir, FluxDeFirmesWs __bean) {
    super(__bean);
    this.descripcio = descripcio;
    this.usuariEntitatID = usuariEntitatID;
    this.usuariAplicacioID = usuariAplicacioID;
    this.compartir = compartir;
  }

  public java.lang.String getDescripcio() {
    return descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }

  public java.lang.String getUsuariEntitatID() {
    return usuariEntitatID;
  }

  public void setUsuariEntitatID(java.lang.String usuariEntitatID) {
    this.usuariEntitatID = usuariEntitatID;
  }

  public java.lang.String getUsuariAplicacioID() {
    return usuariAplicacioID;
  }

  public void setUsuariAplicacioID(java.lang.String usuariAplicacioID) {
    this.usuariAplicacioID = usuariAplicacioID;
  }

  public java.lang.Boolean getCompartir() {
    return compartir;
  }

  public void setCompartir(java.lang.Boolean compartir) {
    this.compartir = compartir;
  }

  public static PlantillaFluxDeFirmesWs toWs(PlantillaFluxDeFirmesJPA jpa) {
    if (jpa == null) {
      return null;
    }

    FluxDeFirmesWs fluxDeFirmesWs = FluxDeFirmesWs.toWs(jpa.getFluxDeFirmes());

    // Bean
    return new PlantillaFluxDeFirmesWs(jpa.getDescripcio(), jpa.getUsuariEntitatID(),
        jpa.getUsuariAplicacioID(), jpa.getCompartir(), fluxDeFirmesWs);

  }

}
