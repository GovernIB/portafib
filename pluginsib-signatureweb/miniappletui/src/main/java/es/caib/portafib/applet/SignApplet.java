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
    signerContext = new AppletSignerContext(this);
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

  @Override
  public String getContextParameter(String key) {
    return this.signerContext.getContextParameter(key);
  }

  @Override
  public void showURL(URL url) {
    this.signerContext.showURL(url);
  }

  @Override
  public String checkURL(String url) {
    return this.signerContext.checkURL(url);
  }

  
  public static class AppletSignerContext implements SignerContext {

    final Applet applet;

    /**
     * @param applet
     */
    public AppletSignerContext(Applet applet) {
      super();
      this.applet = applet;
    }

    @Override
    public String getContextParameter(String key) {
      return this.applet.getParameter(key);
    }

    @Override
    public void showURL(URL url) {
      this.applet.getAppletContext().showDocument(url);
    }

    @Override
    public String checkURL(String url) {

      if (url == null || url.trim().length() == 0 || url.startsWith("http")) {
        return url;
      }

      // És una ruta relativa
      URL cbase = this.applet.getCodeBase();

      String newUrl = cbase.getProtocol() + "://" + cbase.getHost()
          + (cbase.getPort() == -1 ? "" : (":" + cbase.getPort())) + url;

      System.out.println(" Canviada " + url + " per " + newUrl);

      return newUrl;

    }

  }

}
