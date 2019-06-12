package org.fundaciobit.apisib.apifirmaasyncsimple.v2.exceptions;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractFirmaAsyncSimpleException extends Exception {

  protected String description = null;

  /**
   * 
   */
  public AbstractFirmaAsyncSimpleException() {
    super();
  }

  /**
   * @param message
   * @param cause
   */
  public AbstractFirmaAsyncSimpleException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @param message
   */
  public AbstractFirmaAsyncSimpleException(String message) {
    super(message);
  }

  /**
   * @param message
   */
  public AbstractFirmaAsyncSimpleException(String message, String description) {
    super(message);
    this.description = description;
  }

  /**
   * @param cause
   */
  public AbstractFirmaAsyncSimpleException(Throwable cause) {
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
