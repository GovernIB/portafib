package org.fundaciobit.plugins.signatureweb.miniappletutils;

import java.util.Properties;

import org.fundaciobit.plugins.signatureweb.api.AbstractSignatureWebPlugin;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractMiniAppletSignaturePlugin extends AbstractSignatureWebPlugin {

  /**
   * 
   */
  public AbstractMiniAppletSignaturePlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public AbstractMiniAppletSignaturePlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public AbstractMiniAppletSignaturePlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }

  @Override
  public String[] getSupportedSignatureTypes() {
    // TODO Falta CADes, Xades, ...
    return new String[] { FileInfoSignature.SIGN_TYPE_PADES };
  }

  @Override
  public String[] getSupportedSignatureAlgorithms(String signType) {

    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {

      return new String[] { FileInfoSignature.SIGN_ALGORITHM_SHA1,
          FileInfoSignature.SIGN_ALGORITHM_SHA256,
          FileInfoSignature.SIGN_ALGORITHM_SHA384,
          FileInfoSignature.SIGN_ALGORITHM_SHA512 };
    }
    return null;
  }

}
