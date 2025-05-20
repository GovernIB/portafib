package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

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
public class Reviser extends Person {

  protected boolean required;

  public Reviser() {
    super();
  }

  public Reviser(Person persona, boolean required) {
    this(persona.getPositionInTheCompany(), persona.getAdministrationID(), persona
        .getUsername(), persona.getIntermediateServerUsername(), required);
  }

  public Reviser(String positionInTheCompany, String administrationID,
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
