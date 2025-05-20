package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

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
public class Signer extends Person {

  /**
   * Dades d'un usuari extern
   */
  protected ExternalSigner externalSigner;

  public Signer() {
    super();
  }

  public ExternalSigner getExternalSigner() {
    return externalSigner;
  }

  public void setExternalSigner(ExternalSigner externalSigner) {
    this.externalSigner = externalSigner;
  }

}
