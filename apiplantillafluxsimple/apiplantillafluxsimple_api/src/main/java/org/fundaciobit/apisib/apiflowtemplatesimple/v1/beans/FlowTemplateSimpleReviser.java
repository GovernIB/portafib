package org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans;

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
public class FlowTemplateSimpleReviser extends FlowTemplateSimplePerson {

  protected boolean required;

  public FlowTemplateSimpleReviser() {
    super();
  }

  public FlowTemplateSimpleReviser(FlowTemplateSimplePerson persona, boolean required) {
    this(persona.getPositionInTheCompany(), persona.getAdministrationID(), persona
        .getUsername(), persona.getIntermediateServerUsername(), required);
  }

  public FlowTemplateSimpleReviser(String positionInTheCompany, String administrationID,
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

  
  public static String toString(FlowTemplateSimpleReviser reviser) {
    return FlowTemplateSimplePerson.toString(reviser) + "\n" + "Required: " + reviser.isRequired();
  }
  
}
