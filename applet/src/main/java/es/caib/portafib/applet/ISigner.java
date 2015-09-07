package es.caib.portafib.applet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.PropertyResourceBundle;

import es.caib.portafib.utils.SignBoxRectangle;

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
  
  
  
  public void sign(PropertyResourceBundle bundleSign,InputStream input,
      OutputStream outStream, String reason, 
      int signType, int signAlgorithm, boolean signMode,
      int location_page, SignBoxRectangle signBoxRectangle, String firmatPerFormat)
     throws IOException, Exception;
  
  
 
  public BasePanel getPanelSelectCertificate(ParentPanel parentPanel);
  
  
  public boolean isSelectedCert();
  
}
