package org.fundaciobit.apisib.apifirmaasyncsimple.v2.exceptions;


/**
 * Quan hi ha un error intern de l'API Client
 * 
 * @author anadal
 *
 */
public class ClientException extends AbstractFirmaAsyncSimpleException {

  /**
   * 
   */
  public ClientException() {
    super();
  }

  /**
   * @param message
   * @param description
   */
  public ClientException(String message, String description) {
    super(message, description);
  }

  /**
   * @param message
   * @param cause
   */
  public ClientException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @param message
   */
  public ClientException(String message) {
    super(message);
  }

  /**
   * @param cause
   */
  public ClientException(Throwable cause) {
    super(cause);
  }

 
}
