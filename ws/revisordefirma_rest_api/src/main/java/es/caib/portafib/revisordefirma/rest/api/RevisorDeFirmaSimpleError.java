package es.caib.portafib.revisordefirma.rest.api;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe encarregada d'enviar excepcions des del servidor al client
 * 
 * @author anadal
 *
 */
@XmlRootElement
public class RevisorDeFirmaSimpleError {

  String message;

  String type;

  String stackTrace;

  /**
   * @param message
   */
  public RevisorDeFirmaSimpleError() {
    super();
  }

  /**
   * @param message
   */
  public RevisorDeFirmaSimpleError(String message, String type) {
    super();
    this.message = message;
    this.type = type;
  }

  /**
   * @param message
   * @param type
   * @param stackTrace
   */
  public RevisorDeFirmaSimpleError(String message, String type, String stackTrace) {
    super();
    this.message = message;
    this.type = type;
    this.stackTrace = stackTrace;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getStackTrace() {
    return stackTrace;
  }

  public void setStackTrace(String stackTrace) {
    this.stackTrace = stackTrace;
  }

}
