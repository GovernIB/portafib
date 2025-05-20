package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

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
public class Signature {

  protected Signer signer;
  protected boolean required;
  protected String reason;
  protected int minimumNumberOfRevisers; // Revisors;
  protected List<Reviser> revisers;

  public Signature() {
    super();
  }

  public Signature(Signer signer, boolean required,
      String reason, int minimumNumberOfRevisers, List<Reviser> revisers) {
    super();
    this.signer = signer;
    this.required = required;
    this.reason = reason;
    this.minimumNumberOfRevisers = minimumNumberOfRevisers;
    this.revisers = revisers;
  }

  public Signer getSigner() {
    return signer;
  }

  public void setSigner(Signer signer) {
    this.signer = signer;
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

  public List<Reviser> getRevisers() {
    return revisers;
  }

  public void setRevisers(List<Reviser> revisers) {
    this.revisers = revisers;
  }

}
