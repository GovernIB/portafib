package org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.exceptions;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractFirmaSimpleException extends Exception {

  protected String description = null;

  /**
   * 
   */
  public AbstractFirmaSimpleException() {
    super();
  }

  /**
   * @param message
   * @param cause
   */
  public AbstractFirmaSimpleException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @param message
   */
  public AbstractFirmaSimpleException(String message) {
    super(message);
  }

  /**
   * @param message
   */
  public AbstractFirmaSimpleException(String message, String description) {
    super(message);
    this.description = description;
  }

  /**
   * @param cause
   */
  public AbstractFirmaSimpleException(Throwable cause) {
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
