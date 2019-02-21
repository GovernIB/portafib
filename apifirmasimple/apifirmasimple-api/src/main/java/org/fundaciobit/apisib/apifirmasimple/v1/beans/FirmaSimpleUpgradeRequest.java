package org.fundaciobit.apisib.apifirmasimple.v1.beans;

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
public class FirmaSimpleUpgradeRequest {

  String profileCode;

  byte[] signature;

  /** Certificat a l'hora de fer cofirmes i contrafirmes */
  byte[] targetCertificate;

  String languageUI;

  public FirmaSimpleUpgradeRequest() {
    super();
  }

  public FirmaSimpleUpgradeRequest(String profileCode, byte[] signature,
      byte[] targetCertificate, String languageUI) {
    super();
    this.profileCode = profileCode;
    this.signature = signature;
    this.targetCertificate = targetCertificate;
    this.languageUI = languageUI;
  }

  public String getProfileCode() {
    return profileCode;
  }

  public void setProfileCode(String profileCode) {
    this.profileCode = profileCode;
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

  public String getLanguageUI() {
    return languageUI;
  }

  public void setLanguageUI(String languageUI) {
    this.languageUI = languageUI;
  }

}
