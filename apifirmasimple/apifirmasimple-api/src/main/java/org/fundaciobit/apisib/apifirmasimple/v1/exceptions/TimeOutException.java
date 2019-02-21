package org.fundaciobit.apisib.apifirmasimple.v1.exceptions;

/**
 * 
 * @author anadal
 *
 */
public class TimeOutException extends AbstractFirmaSimpleException {

  /**
   * 
   */
  public TimeOutException() {
    super();
  }

  /**
   * @param message
   * @param description
   */
  public TimeOutException(String message, String description) {
    super(message, description);
  }

  /**
   * @param message
   * @param cause
   */
  public TimeOutException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @param message
   */
  public TimeOutException(String message) {
    super(message);
  }

  /**
   * @param cause
   */
  public TimeOutException(Throwable cause) {
    super(cause);
  }

}
