package org.fundaciobit.apifirmawebsimple.exceptions;

/**
 * 
 * @author anadal
 *
 */
public abstract class ApiFirmaException extends Exception {

  protected String description = null;

  /**
   * 
   */
  public ApiFirmaException() {
    super();
  }

  /**
   * @param message
   * @param cause
   */
  public ApiFirmaException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @param message
   */
  public ApiFirmaException(String message) {
    super(message);
  }

  /**
   * @param message
   */
  public ApiFirmaException(String message, String description) {
    super(message);
    this.description = description;
  }

  /**
   * @param cause
   */
  public ApiFirmaException(Throwable cause) {
    super(cause);
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String toString() {
    String msg = super.toString();

    if (this.description != null) {
      msg = msg + "\n" + this.description;
    }

    return msg;

  }

}
