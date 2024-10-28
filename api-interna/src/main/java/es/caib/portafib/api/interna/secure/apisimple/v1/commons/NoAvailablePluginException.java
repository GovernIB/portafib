package es.caib.portafib.api.interna.secure.apisimple.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 *
 */
@Schema
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
