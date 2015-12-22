package com.openlandsw.rss.gateway;

import java.util.List;

/**
 * 
 * @author anadal
 *
 */
public class QueryCertificatesResult {

  final List<CertificateInfo> certificates;

  final String owner;

  /**
   * @param certificates
   * @param owner
   */
  public QueryCertificatesResult(List<CertificateInfo> certificates, String owner) {
    super();
    this.certificates = certificates;
    this.owner = owner;
  }

  /* Recupera la lista de certificados del usuario */
  public List<CertificateInfo> getCertificates() {
    return this.certificates;
  }

  /* Recupera el usuario propietario de los certificados */
  public String getOwner() {
    return this.owner;
  }

}
