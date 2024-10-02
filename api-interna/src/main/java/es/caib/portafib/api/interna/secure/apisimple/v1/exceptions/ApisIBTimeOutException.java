package es.caib.portafib.api.interna.secure.apisimple.v1.exceptions;

/**
 * 
 * @author anadal
 *
 */
public class ApisIBTimeOutException extends AbstractApisIBException {

  /**
   * 
   */
  public ApisIBTimeOutException() {
    super();
  }

  /**
   * @param message
   * @param description
   */
  public ApisIBTimeOutException(String message, String description) {
    super(message, description);
  }

  /**
   * @param message
   * @param cause
   */
  public ApisIBTimeOutException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @param message
   */
  public ApisIBTimeOutException(String message) {
    super(message);
  }

  /**
   * @param cause
   */
  public ApisIBTimeOutException(Throwable cause) {
    super(cause);
  }

}
