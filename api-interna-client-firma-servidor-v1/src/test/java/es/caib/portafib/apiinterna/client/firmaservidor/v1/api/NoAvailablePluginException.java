package es.caib.portafib.apiinterna.client.firmaservidor.v1.api;

/**
 * 
 * @author anadal
 *
 */

public class NoAvailablePluginException extends AbstractApisIBException {

  /**
   * 
   */
  public NoAvailablePluginException() {
    super();
  }

  /**
   * @param message
   * @param description
   */
  public NoAvailablePluginException(String message, String description) {
    super(message, description);
  }

  /**
   * @param message
   * @param cause
   */
  public NoAvailablePluginException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @param message
   */
  public NoAvailablePluginException(String message) {
    super(message);
  }

  /**
   * @param cause
   */
  public NoAvailablePluginException(Throwable cause) {
    super(cause);
  }



}
