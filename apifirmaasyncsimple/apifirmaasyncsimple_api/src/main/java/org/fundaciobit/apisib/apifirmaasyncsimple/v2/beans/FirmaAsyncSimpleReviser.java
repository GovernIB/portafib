package org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Revisor de Firmes
 * 
 * @author anadal(u80067)
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaAsyncSimpleReviser extends FirmaAsyncSimplePerson {

  protected boolean required;

  public FirmaAsyncSimpleReviser() {
    super();
  }

  public FirmaAsyncSimpleReviser(FirmaAsyncSimplePerson persona, boolean required) {
    this(persona.getPositionInTheCompany(), persona.getAdministrationID(), persona
        .getUsername(), persona.getIntermediateServerUsername(), required);
  }

  public FirmaAsyncSimpleReviser(String positionInTheCompany, String administrationID,
      String username, String intermediateServerUsername, boolean required) {
    super(positionInTheCompany, administrationID, username, intermediateServerUsername);
    this.required = required;
  }

  public boolean isRequired() {
    return required;
  }

  public void setRequired(boolean required) {
    this.required = required;
  }

}
