package es.caib.portafib.applet;

/**
 * @author anadal
 */
public class PortaFIBAppletException extends Exception {

  protected final String title;
  
  protected final String error;

  /**
   * @param message
   * @param cause
   */
  public PortaFIBAppletException(String title, String error, Throwable cause) {
    super(title + ":" + error, cause);
    this.error = error;
    this.title = title;
  }

  /**
   * @param message
   */
  public PortaFIBAppletException(String title, String error) {
    super(title + ":" + error);
    this.error = error;
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public String getError() {
    return error;
  }

 
  
}
