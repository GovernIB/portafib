package com.openlandsw.rss.gateway;

/**
 * 
 * @author anadal
 *
 */
public class CertificateInfo {
  
  final byte[] certificate;
  
  
  final String dn_certificate;
  

  /**
   * @param certificate
   * @param dn_certificate
   */
  public CertificateInfo(byte[] certificate, String dn_certificate) {
    super();
    this.certificate = certificate;
    this.dn_certificate = dn_certificate;
  }

  /** Recupera el certificado de firma */
  public byte[] getCertificate() {
    return certificate;
  }

  /**
   * 
   * @return
   */
  public String getDn_certificate() {
    return dn_certificate;
  }

}
