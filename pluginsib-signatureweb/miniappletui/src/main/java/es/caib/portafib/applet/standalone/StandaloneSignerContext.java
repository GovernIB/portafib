package es.caib.portafib.applet.standalone;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import es.caib.portafib.applet.SignerContext;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class StandaloneSignerContext implements SignerContext /* XYZ AppletStub */ {

  private final Properties standaloneParameters;

  /**
   * @param standaloneParameters
   */
  public StandaloneSignerContext(Properties standaloneParameters) {
    super();
    this.standaloneParameters = standaloneParameters;
  }

  public String getContextParameter(String key) {
    return standaloneParameters.getProperty(key);
  }

  public void showURL(URL url) {
    try {
      obreNavegador(url.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void obreNavegador(String url) throws IOException {

    // Obrim el visor d'URLS per defecte (si no és un navegador,
    // no s'obrirà al navegador)

    if (System.getProperty("os.name").toLowerCase().indexOf("windows") != -1) // si
      // es
      // windows
      Runtime.getRuntime().exec(new String[] { "cmd", "/C", "start", url });
    else if (System.getProperty("os.name").toLowerCase().indexOf("linux") != -1) // si
      // es
      // linux
      Runtime.getRuntime().exec(new String[] { "/usr/bin/firefox", url });
    else if (System.getProperty("os.name").toLowerCase().indexOf("mac os x") != -1) // si
      // es
      // mac
      Runtime.getRuntime().exec(new String[] { "open ", url });

  }

  public String checkURL(String url) {
    
    return url;
  }

  /*
  @Override
  public boolean isActive() {
    
    return true;
  }

  @Override
  public URL getDocumentBase() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public URL getCodeBase() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getParameter(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AppletContext getAppletContext() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void appletResize(int width, int height) {
    // TODO Auto-generated method stub
    
  }
*/
}