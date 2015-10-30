package org.fundaciobit.plugins.signatureweb.api;

import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * 
 * @author anadal
 *
 */
public interface IRubricGenerator {
  
  byte[] genLocalRubricGeneration(X509Certificate cert, Date data) throws Exception;

  /**
   * S'ha de definir els paràmetres a passar: id, certificat, sizes, ...
   * Adjunt vendrà el Certificat 
   * 
   * @return URL a la que s'han de passar 
   */
  String getRemoteURLRubricGeneration();
  

}
