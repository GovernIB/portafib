package org.fundaciobit.plugins.signatureweb.exemple.ejb.utils;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.signature.utils.SignaturePluginManager;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;

/**
 * 
 * @author anadal
 *
 */
public class SignatureWebPluginManager {
  
  private static final SignaturePluginManager<ISignatureWebPlugin> pluginManager;
  
  protected static final Logger log = Logger.getLogger(SignatureWebPluginManager.class);
  
  static {
  
    String pluginPropertiesPath = System.getProperty("signaturewebplugins.path");
    
    if (pluginPropertiesPath == null) {
      log.error("La propietat 'signaturewebplugins.path' "
          + "no te valor assignat.", new Exception());
    }

    pluginManager = new SignaturePluginManager<ISignatureWebPlugin>(
        pluginPropertiesPath, "signaturewebplugins");
  }
  
  
  
  public static SignaturePluginManager<ISignatureWebPlugin> getInstance() {
    return pluginManager;
  };

}
