package org.fundaciobit.plugins.signatureweb.miniappletinserver;

import java.lang.reflect.Method;
import java.security.PrivateKey;
import java.util.Properties;

import org.fundaciobit.plugins.signatureweb.miniappletutils.AbstractTriFaseSigner;

/**
 * 
 * @author anadal
 *
 */
public class MiniAppletInServerSigner extends AbstractTriFaseSigner {

  final PrivateKey privateKey;

  /**
   * @param key
   */
  public MiniAppletInServerSigner(PrivateKey privatekey) {
    super();
    this.privateKey = privatekey;
  }

  @Override
  public byte[] signHash(final String algorithm, final byte[] hash) throws Exception {

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
