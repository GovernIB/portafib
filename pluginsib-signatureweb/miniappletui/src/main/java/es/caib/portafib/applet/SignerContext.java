package es.caib.portafib.applet;

import java.net.URL;

/**
 * 
 * @author anadal
 * 
 */
public interface SignerContext {

  public String getContextParameter(String key);

  public void showURL(URL url);
  
  public String checkURL(String url);

}
