package org.fundaciobit.plugins.signatureweb.miniappletutils;

import java.security.cert.Certificate;
import java.util.Properties;
/**
 * 
 * @author anadal
 *
 */
public class MiniAppletSignInfo {

  byte[] dataToSign;

  String algorithm;

  Certificate certificate;

  Properties properties;

  /**
   * 
   */
  public MiniAppletSignInfo() {
    super();
  }

  /**
   * @param pdf
   * @param algorithm
   * @param certificate
   * @param properties
   */
  public MiniAppletSignInfo(byte[] dataToSign, String algorithm, Certificate certificate,
      Properties properties) {
    super();
    this.dataToSign = dataToSign;
    this.algorithm = algorithm;
    this.certificate = certificate;
    this.properties = properties;
  }



  public byte[] getDataToSign() {
    return dataToSign;
  }

  public void setDataToSign(byte[] dataToSign) {
    this.dataToSign = dataToSign;
  }

  public String getAlgorithm() {
    return algorithm;
  }

  public void setAlgorithm(String algorithm) {
    this.algorithm = algorithm;
  }

  public Certificate getCertificate() {
    return certificate;
  }

  public void setCertificate(Certificate certificate) {
    this.certificate = certificate;
  }

  public Properties getProperties() {
    return properties;
  }

  public void setProperties(Properties properties) {
    this.properties = properties;
  }

}
