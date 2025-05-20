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
public class SignatureBlock {

  protected int minimumNumberOfSignaturesRequired;

  protected List<Signature> signers;

  public SignatureBlock() {
    super();
  }

  public SignatureBlock(int minimumNumberOfSignaturesRequired,
      List<Signature> signers) {
    super();
    this.minimumNumberOfSignaturesRequired = minimumNumberOfSignaturesRequired;
    this.signers = signers;
  }

  public int getMinimumNumberOfSignaturesRequired() {
    return minimumNumberOfSignaturesRequired;
  }

  public void setMinimumNumberOfSignaturesRequired(int minimumNumberOfSignaturesRequired) {
    this.minimumNumberOfSignaturesRequired = minimumNumberOfSignaturesRequired;
  }

  public List<Signature> getSigners() {
    return signers;
  }

  public void setSigners(List<Signature> signers) {
    this.signers = signers;
  }

}
