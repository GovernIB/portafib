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
public class FirmaAsyncSimpleSignatureBlock {

  protected int minimumNumberOfSignaturesRequired;

  protected List<FirmaAsyncSimpleSignature> signers;

  public FirmaAsyncSimpleSignatureBlock() {
    super();
  }

  public FirmaAsyncSimpleSignatureBlock(int minimumNumberOfSignaturesRequired,
      List<FirmaAsyncSimpleSignature> signers) {
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

  public List<FirmaAsyncSimpleSignature> getSigners() {
    return signers;
  }

  public void setSigners(List<FirmaAsyncSimpleSignature> signers) {
    this.signers = signers;
  }

}
