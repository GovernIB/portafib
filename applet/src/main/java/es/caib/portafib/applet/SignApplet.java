package es.caib.portafib.applet;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.net.URL;

/**
 * 
 * @author anadal
 * 
 */
public class SignApplet extends Applet implements SignerContext {

  private SignerContext signerContext;
  
  
  private ParentPanel parentPanel;

  /**
   * @throws java.awt.HeadlessException
   */
  public SignApplet() throws HeadlessException {
    signerContext = this;
  }

  /**
   * @throws java.awt.HeadlessException
   */
  public SignApplet(SignerContext signerContext) throws HeadlessException {
    this.signerContext = signerContext;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.applet.Applet#start()
   */
  public void start() {
    super.start();
    this.parentPanel.start();
  }

  public void init() {
    setLayout(new BorderLayout());
    this.parentPanel = new ParentPanel(signerContext); 
    add(this.parentPanel, BorderLayout.CENTER);
  }

  public String getContextParameter(String key) {
    return super.getParameter(key);
  }

  public void showURL(URL url) {
    getAppletContext().showDocument(url);
  }

  public String checkURL(String url) {

    if(url == null || url.trim().length() == 0 || url.startsWith("http")) {
      return url;
    }
    
    // És una ruta relativa     
    URL cbase = getCodeBase();
    
    String newUrl = cbase.getProtocol() + "://" + cbase.getHost()
        + ( cbase.getPort() == -1? "" : (":" + cbase.getPort())) + url;
    
    System.out.println(" Canviada " + url + " per " + newUrl);
    
    return newUrl;
    
  }

}
