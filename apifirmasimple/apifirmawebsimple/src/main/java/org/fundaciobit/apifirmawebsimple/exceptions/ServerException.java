package org.fundaciobit.apifirmawebsimple.exceptions;

/**
 * 
 * @author anadal
 *
 */
public class ServerException extends ApiFirmaException {

  /**
   * 
   */
  public ServerException() {
    super();
  }

  /**
   * @param message
   * @param description
   */
  public ServerException(String message, String description) {
    super(message, description);
  }

  /**
   * @param message
   * @param cause
   */
  public ServerException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @param message
   */
  public ServerException(String message) {
    super(message);
  }

  /**
   * @param cause
   */
  public ServerException(Throwable cause) {
    super(cause);
  }

}
