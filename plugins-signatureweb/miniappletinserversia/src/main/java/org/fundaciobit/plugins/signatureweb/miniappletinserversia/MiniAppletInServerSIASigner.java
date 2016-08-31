package org.fundaciobit.plugins.signatureweb.miniappletinserversia;

import java.util.Properties;

import org.fundaciobit.plugins.signatureserver.miniappletutils.AbstractTriFaseSigner;

/**
 * 
 * @author anadal
 *
 */
public class MiniAppletInServerSIASigner extends AbstractTriFaseSigner {

  final String algorithm;

  final Properties params;

  /**
   * @param key
   */
  public MiniAppletInServerSIASigner(final String algorithm, final Properties params) {
    super();
    this.algorithm = algorithm;
    this.params = params;
  }

  @Override
  public byte[] step2_signHash(final String algorithm, final byte[] hashDocumentoParam)
      throws Exception {

    // NO FER RES
    throw new Exception("La firma es genera cridant al servidor de SIA");

  }

  public String getAlgorithm() {
    return algorithm;
  }

  public Properties getParams() {
    return params;
  }
  
}
