package org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal(u80067)
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaAsyncSimpleSignature {

  protected FirmaAsyncSimplePerson personToSign;
  protected boolean required;
  
  protected String reason;

  protected int minimumNumberOfRevisers; // Revisors;
  protected List<FirmaAsyncSimpleReviser> revisers;

  public FirmaAsyncSimpleSignature() {
    super();
  }

  public FirmaAsyncSimpleSignature(FirmaAsyncSimplePerson personToSign, boolean required,
      String reason, int minimumNumberOfRevisers, List<FirmaAsyncSimpleReviser> revisers) {
    super();
    this.personToSign = personToSign;
    this.required = required;
    this.reason = reason;
    this.minimumNumberOfRevisers = minimumNumberOfRevisers;
    this.revisers = revisers;
  }

  public FirmaAsyncSimplePerson getPersonToSign() {
    return personToSign;
  }

  public void setPersonToSign(FirmaAsyncSimplePerson personToSign) {
    this.personToSign = personToSign;
  }

  public boolean isRequired() {
    return required;
  }

  public void setRequired(boolean required) {
    this.required = required;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public int getMinimumNumberOfRevisers() {
    return minimumNumberOfRevisers;
  }

  public void setMinimumNumberOfRevisers(int minimumNumberOfRevisers) {
    this.minimumNumberOfRevisers = minimumNumberOfRevisers;
  }

  public List<FirmaAsyncSimpleReviser> getRevisers() {
    return revisers;
  }

  public void setRevisers(List<FirmaAsyncSimpleReviser> revisers) {
    this.revisers = revisers;
  }

}
