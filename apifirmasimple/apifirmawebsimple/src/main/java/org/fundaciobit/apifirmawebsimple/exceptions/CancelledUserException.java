package org.fundaciobit.apifirmawebsimple.exceptions;

/**
 * 
 * @author anadal
 *
 */
public class CancelledUserException extends ApiFirmaException {

  /**
   * 
   */
  public CancelledUserException() {
    super();
  }

  /**
   * @param message
   * @param description
   */
  public CancelledUserException(String message, String description) {
    super(message, description);
  }

  /**
   * @param message
   * @param cause
   */
  public CancelledUserException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @param message
   */
  public CancelledUserException(String message) {
    super(message);
  }

  /**
   * @param cause
   */
  public CancelledUserException(Throwable cause) {
    super(cause);
  }

}
