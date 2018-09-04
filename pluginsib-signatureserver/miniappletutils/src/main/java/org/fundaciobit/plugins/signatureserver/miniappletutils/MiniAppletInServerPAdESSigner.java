package org.fundaciobit.plugins.signatureserver.miniappletutils;

import java.lang.reflect.Method;
import java.security.PrivateKey;
import java.util.Properties;

/**
 * 
 * @author anadal
 *
 */
public class MiniAppletInServerPAdESSigner extends AbstractTriFaseSigner {

  final PrivateKey privateKey;

  /**
   * @param key
   */
  public MiniAppletInServerPAdESSigner(PrivateKey privatekey) {
    super();
    this.privateKey = privatekey;
  }
  

  @Override
  public byte[] step2_signHash(final String algorithm, final byte[] hash) throws Exception {

    final Properties extraParams = null;
    final java.security.cert.Certificate[] certificateChain = null;
    
    // Firma PKCS#1
    /*
    final byte[] interSign = new AOPkcs1Signer().sign(hash, algorithm, privateKey,
        certificateChain, extraParams);
    */
    
    Class<?> AOPkcs1Signer = loadClass("es.gob.afirma.core.signers.AOPkcs1Signer");
    
    Object AOPkcs1Signer_instance = AOPkcs1Signer.newInstance();

    Method method  = getMethod(AOPkcs1Signer, "sign");

    
    final byte[] interSign;
    interSign = (byte[])method.invoke(AOPkcs1Signer_instance, hash, algorithm, privateKey,
        certificateChain, extraParams);
    
    return interSign;
  }

}
