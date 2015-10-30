package org.fundaciobit.plugins.signatureweb.miniappletinserver;

import java.security.PrivateKey;
import java.util.Properties;

import org.fundaciobit.plugins.signatureweb.miniappletutils.AbstractTriFaseSigner;

import es.gob.afirma.core.signers.AOPkcs1Signer;

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
  public byte[] signHash(final String algorithm, final byte[] hash) {

    final Properties extraParams = null;
    final java.security.cert.Certificate[] certificateChain = null;

    // Firma PKCS#1
    final byte[] interSign = new AOPkcs1Signer().sign(hash, algorithm, privateKey,
        certificateChain, extraParams);
    return interSign;
  }

}
