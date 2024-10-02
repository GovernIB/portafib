package es.caib.portafib.api.interna.secure.apisimple.v1.exceptions;

/**
 * 
 * @author anadal
 *
 */
public class ApisIBServerException extends AbstractApisIBException {

  /**
   * 
   */
  public ApisIBServerException() {
    super();
  }

  /**
   * @param message
   * @param description
   */
  public ApisIBServerException(String message, String description) {
    super(message, description);
  }

  /**
   * @param message
   * @param cause
   */
  public ApisIBServerException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @param message
   */
  public ApisIBServerException(String message) {
    super(message);
  }

  /**
   * @param cause
   */
  public ApisIBServerException(Throwable cause) {
    super(cause);
  }

}
