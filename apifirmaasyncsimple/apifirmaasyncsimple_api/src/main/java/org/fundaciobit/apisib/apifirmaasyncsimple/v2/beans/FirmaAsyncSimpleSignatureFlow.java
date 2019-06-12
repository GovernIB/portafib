package org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * XYZ ZZZ ZZZ Al final revisar si es necessari
 * @author anadal(u80067)
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaAsyncSimpleSignatureFlow {

  
  protected FirmaAsyncSimpleSignatureBlock[] signatureBlocks = null;

  public FirmaAsyncSimpleSignatureBlock[] getSignatureBlocks() {
    return signatureBlocks;
  }

  public void setSignatureBlocks(FirmaAsyncSimpleSignatureBlock[] signatureBlocks) {
    this.signatureBlocks = signatureBlocks;
  }
  
  
  
}
