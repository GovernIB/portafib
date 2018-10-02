package es.caib.portafib.revisordefirma.rest.api;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
public class RevisorDeFirmaException extends Exception {

  protected String description = null;

  /**
   * 
   */
  public RevisorDeFirmaException() {
    super();
  }

  /**
   * @param message
   * @param cause
   */
  public RevisorDeFirmaException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @param message
   */
  public RevisorDeFirmaException(String message) {
    super(message);
  }

  /**
   * @param message
   */
  public RevisorDeFirmaException(String message, String description) {
    super(message);
    this.description = description;
  }

  /**
   * @param cause
   */
  public RevisorDeFirmaException(Throwable cause) {
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
