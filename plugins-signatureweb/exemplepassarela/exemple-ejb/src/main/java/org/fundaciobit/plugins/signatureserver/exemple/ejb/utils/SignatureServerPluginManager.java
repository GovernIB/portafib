package org.fundaciobit.plugins.signatureserver.exemple.ejb.utils;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.signature.utils.SignaturePluginManager;
import org.fundaciobit.plugins.signatureserver.api.ISignatureServerPlugin;

/**
 * 
 * @author anadal
 *
 */
public class SignatureServerPluginManager {
  
  private static final SignaturePluginManager<ISignatureServerPlugin> pluginManager;
  
  protected static final Logger log = Logger.getLogger(SignatureServerPluginManager.class);
  
  static {
  
    String pluginPropertiesPath = System.getProperty("signatureserverplugins.path");
    
    if (pluginPropertiesPath == null) {
      log.error("La propietat 'signatureserverplugins.path' "
          + "no te valor assignat.", new Exception());
    }

    pluginManager = new SignaturePluginManager<ISignatureServerPlugin>(
        pluginPropertiesPath, "signatureserverplugins");
  
  }
  
  
  
  public static SignaturePluginManager<ISignatureServerPlugin> getInstance() {
    return pluginManager;
  };

}
