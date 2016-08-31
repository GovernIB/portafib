package org.fundaciobit.plugins.signatureserver.miniappletutils;

import java.security.cert.Certificate;
import java.util.Properties;
/**
 * 
 * @author anadal
 *
 */
public class MiniAppletSignInfo {

  byte[] dataToSign;
  
  String signType;

  String signAlgorithm;
  
  Certificate certificate;

  Properties properties;

  /**
   * 
   */
  public MiniAppletSignInfo() {
    super();
  }





  /**
   * @param dataToSign
   * @param signType
   * @param signAlgorithm
   * @param certificate
   * @param properties
   */
  public MiniAppletSignInfo(byte[] dataToSign, String signType, String signAlgorithm,
      Certificate certificate, Properties properties) {
    super();
    this.dataToSign = dataToSign;
    this.signType = signType;
    this.signAlgorithm = signAlgorithm;
    this.certificate = certificate;
    this.properties = properties;
  }





  public byte[] getDataToSign() {
    return dataToSign;
  }

  public void setDataToSign(byte[] dataToSign) {
    this.dataToSign = dataToSign;
  }

  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }


  public String getSignAlgorithm() {
    return signAlgorithm;
  }





  public void setSignAlgorithm(String signAlgorithm) {
    this.signAlgorithm = signAlgorithm;
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
