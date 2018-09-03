package org.fundaciobit.apifirmasimple.v1.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
public class FirmaSimpleUpgradeRequest {

  byte[] signature;

  byte[] targetCertificate;

  public FirmaSimpleUpgradeRequest() {
    super();
  }

  public FirmaSimpleUpgradeRequest(byte[] signature, byte[] targetCertificate) {
    super();
    this.signature = signature;
    this.targetCertificate = targetCertificate;
  }

  public byte[] getSignature() {
    return signature;
  }

  public void setSignature(byte[] signature) {
    this.signature = signature;
  }

  public byte[] getTargetCertificate() {
    return targetCertificate;
  }

  public void setTargetCertificate(byte[] targetCertificate) {
    this.targetCertificate = targetCertificate;
  }

}
