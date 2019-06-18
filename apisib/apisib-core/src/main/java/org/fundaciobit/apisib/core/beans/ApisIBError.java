package org.fundaciobit.apisib.core.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe encarregada d'enviar excepcions des del servidor al client
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ApisIBError {

  protected String message;

  protected String type;

  protected String stackTrace;

  /**
   * @param message
   */
  public ApisIBError() {
    super();
  }

  /**
   * @param message
   */
  public ApisIBError(String message, String type) {
    super();
    this.message = message;
    this.type = type;
  }

  /**
   * @param message
   * @param type
   * @param stackTrace
   */
  public ApisIBError(String message, String type, String stackTrace) {
    super();
    this.message = message;
    this.type = type;
    this.stackTrace = stackTrace;
  }

  public ApisIBError(ApisIBError apisiberror) {
    super();
    this.message = apisiberror.message;
    this.type = apisiberror.type;
    this.stackTrace = apisiberror.stackTrace;
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
