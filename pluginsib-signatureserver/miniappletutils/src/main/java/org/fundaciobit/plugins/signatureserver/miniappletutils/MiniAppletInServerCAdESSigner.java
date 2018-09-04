package org.fundaciobit.plugins.signatureserver.miniappletutils;

import java.lang.reflect.Method;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.Properties;

import org.fundaciobit.plugins.signatureserver.miniappletutils.MiniAppletClassLoader;


/**
 * 
 * @author anadal
 *
 */
public class MiniAppletInServerCAdESSigner extends MiniAppletClassLoader {

  
  public byte[] sign(final byte[] data,
      final String algorithm,
      final PrivateKey privateKey,
      final Certificate[] certificateChain,
      final Properties xParams) throws Exception {

    // Firma CAdES

    // AOCAdESSigner signer = new es.gob.afirma.signers.cades.AOCAdESSigner();
    Class<?> cadesSignerClass = loadClass("es.gob.afirma.signers.cades.AOCAdESSigner");
    Object cadesSignerInstance = cadesSignerClass.newInstance();
    
    // byte[] firma = signer.sign("Texto a firmar".getBytes(), "SHA1withRSA", 
    //   pke, extraParams);
    Method method  = getMethod(cadesSignerClass, "sign");
    
    final byte[] interSign = (byte[])method.invoke(cadesSignerInstance, data,
        algorithm, privateKey, certificateChain, xParams);
    
    return interSign;
  }
  
}
