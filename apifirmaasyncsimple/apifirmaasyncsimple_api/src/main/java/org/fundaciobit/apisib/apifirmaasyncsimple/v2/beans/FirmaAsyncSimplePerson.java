package org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Identificador de persona. Només s'ha d'omplir un camp.
 * 
 * @author anadal(u80067)
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaAsyncSimplePerson {

  /**
   * Identificador de Càrrec: fundaciobit_gerent, caib_president, ...
   */
  protected String positionInTheCompany;

  /**
   * NIF
   */
  protected String administrationID;

  /**
   * Nom d'usuari: u80067 o anadal
   */
  protected String username;

  /**
   * ID del servidor intermedi(PortaFIB): fundaciobit_anadal, caib_u80067
   */
  protected String intermediateServerUsername;

  public FirmaAsyncSimplePerson() {
    super();
  }

  public FirmaAsyncSimplePerson(String positionInTheCompany, String administrationID,
      String username, String intermediateServerUsername) {
    super();
    this.positionInTheCompany = positionInTheCompany;
    this.administrationID = administrationID;
    this.username = username;
    this.intermediateServerUsername = intermediateServerUsername;
  }

  public String getPositionInTheCompany() {
    return positionInTheCompany;
  }

  public void setPositionInTheCompany(String positionInTheCompany) {
    this.positionInTheCompany = positionInTheCompany;
  }

  public String getAdministrationID() {
    return administrationID;
  }

  public void setAdministrationID(String administrationID) {
    this.administrationID = administrationID;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getIntermediateServerUsername() {
    return intermediateServerUsername;
  }

  public void setIntermediateServerUsername(String intermediateServerUsername) {
    this.intermediateServerUsername = intermediateServerUsername;
  }

}