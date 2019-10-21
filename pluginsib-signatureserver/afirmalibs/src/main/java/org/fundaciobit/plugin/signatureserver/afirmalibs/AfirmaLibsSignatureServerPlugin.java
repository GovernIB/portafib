package org.fundaciobit.plugin.signatureserver.afirmalibs;

import java.util.Properties;


/**
 *
 * @author anadal
 *
 */
@Deprecated
public class AfirmaLibsSignatureServerPlugin extends
    org.fundaciobit.plugins.signatureserver.afirmalibs.AfirmaLibsSignatureServerPlugin {

  /**
   * 
   */
  public AfirmaLibsSignatureServerPlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public AfirmaLibsSignatureServerPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public AfirmaLibsSignatureServerPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }

}
