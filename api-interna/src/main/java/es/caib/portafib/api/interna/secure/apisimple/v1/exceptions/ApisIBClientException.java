package es.caib.portafib.api.interna.secure.apisimple.v1.exceptions;


/**
 * Quan hi ha un error intern de l'API Client
 * 
 * @author anadal
 *
 */
public class ApisIBClientException extends AbstractApisIBException {

  /**
   * 
   */
  public ApisIBClientException() {
    super();
  }

  /**
   * @param message
   * @param description
   */
  public ApisIBClientException(String message, String description) {
    super(message, description);
  }

  /**
   * @param message
   * @param cause
   */
  public ApisIBClientException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @param message
   */
  public ApisIBClientException(String message) {
    super(message);
  }

  /**
   * @param cause
   */
  public ApisIBClientException(Throwable cause) {
    super(cause);
  }

 
}
