package es.caib.portafib.applet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.PropertyResourceBundle;


/**
 * Representa una llibreria de Firma
 * 
 * @author anadal
 *
 */
public interface ISigner {

  /**
   * 
   */
  public void init(PropertyResourceBundle bundleUI,  
      SignerContext signerContext) throws Exception;
  
  
  
  public byte[] sign(PropertyResourceBundle bundleSign,InputStream input,
      
      String signType, String signAlgorithm, Properties properties)
     throws IOException, Exception;
  
  
 
  public BasePanel getPanelSelectCertificate(ParentPanel parentPanel);
  
  
  public boolean isSelectedCert();
  
}
