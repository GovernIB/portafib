package org.fundaciobit.plugins.signature.api;

import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * 
 * @author anadal
 *
 */
public interface IRubricGenerator {
  
  byte[] genenerateRubricImage(X509Certificate cert, Date data) throws Exception;

}
