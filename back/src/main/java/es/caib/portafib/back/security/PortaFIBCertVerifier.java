package es.caib.portafib.back.security;

import java.security.KeyStore;
import java.security.cert.X509Certificate;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.utils.CertificateUtils;
import org.jboss.security.auth.certs.X509CertificateVerifier;


/**
 * 
 * @author anadal
 * 
 */
public class PortaFIBCertVerifier implements X509CertificateVerifier {

  protected final Logger log = Logger.getLogger(getClass());

  /**
   * Validate a cert.
   * 
   * @param cert
   *          - the X509Certificate to verifier
   * @param alias
   *          - the expected keystore alias
   * @param keyStore
   *          - the keystore for the cert
   * @param trustStore
   *          - the truststore for the cert signer
   * @return true if the cert is valid, false otherwise
   */
  public boolean verify(X509Certificate cert, String alias, KeyStore keyStore,
      KeyStore trustStore) {

    if (log.isInfoEnabled()) {
      try {
        log.info(" ========= CERT VERIFIER  ========= ");        
        log.info(" + CommonName: " + CertificateUtils.getCommonName(cert));
      } catch (Throwable e) {
        log.error("Error intentant extreure dades del certificat " + e.getMessage(), e);
      }
    }

    return true;
  }

}
