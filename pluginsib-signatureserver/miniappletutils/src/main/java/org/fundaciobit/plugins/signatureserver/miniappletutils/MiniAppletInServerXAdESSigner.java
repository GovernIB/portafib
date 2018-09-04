package org.fundaciobit.plugins.signatureserver.miniappletutils;

import java.lang.reflect.Method;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.Properties;


/**
 * 
 * @author anadal
 *
 */
public class MiniAppletInServerXAdESSigner extends MiniAppletClassLoader {

  
  
  public byte[] sign(final byte[] data,
      final String algorithm,
      final PrivateKey privateKey,
      final Certificate[] certificateChain,
      final Properties xParams) throws Exception {

    // Firma Xades
    /*
    final byte[] interSign = new XAdESSigner().sign(data, algorithm, privateKey,
        certificateChain, xParams);
    */    
    Class<?> xadesSigner = loadClass("es.gob.afirma.signers.xades.XAdESSigner");
    
    //Object xadesSigner_instance = xadesSigner.newInstance();

    Method method  = getMethod(xadesSigner, "sign");

    
    final byte[] interSign;
    interSign = (byte[])method.invoke(null/*xadesSigner_instance*/, data, algorithm, privateKey,
        certificateChain, xParams);
    
    return interSign;
    
    
    
  }
  
}
