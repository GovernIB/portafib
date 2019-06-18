package org.fundaciobit.apisib.apifirmasimple.v1.exceptions;

import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;



/**
 * 
 * @author anadal
 *
 */
public class CancelledUserException extends AbstractApisIBException {

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
