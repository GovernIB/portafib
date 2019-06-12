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
public class FirmaAsyncSimpleReviser {

  protected FirmaAsyncSimplePerson reviser;
  protected boolean required;

  public FirmaAsyncSimpleReviser() {
    super();
  }

  public FirmaAsyncSimpleReviser(FirmaAsyncSimplePerson reviser, boolean required) {
    super();
    this.reviser = reviser;
    this.required = required;
  }

  public FirmaAsyncSimplePerson getReviser() {
    return reviser;
  }

  public void setReviser(FirmaAsyncSimplePerson reviser) {
    this.reviser = reviser;
  }

  public boolean isRequired() {
    return required;
  }

  public void setRequired(boolean required) {
    this.required = required;
  }

}
