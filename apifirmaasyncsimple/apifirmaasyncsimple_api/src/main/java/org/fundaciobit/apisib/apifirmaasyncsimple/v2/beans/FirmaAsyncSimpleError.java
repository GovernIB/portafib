package org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.fundaciobit.apisib.core.beans.ApisIBError;

/**
 * Classe encarregada d'enviar excepcions des del servidor al client
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaAsyncSimpleError extends ApisIBError {

  /**
   * @param message
   */
  public FirmaAsyncSimpleError() {
    super();
  }

  /**
   * @param message
   */
  public FirmaAsyncSimpleError(String message, String type) {
    super(message, type);
  }

  /**
   * @param message
   * @param type
   * @param stackTrace
   */
  public FirmaAsyncSimpleError(String message, String type, String stackTrace) {
    super(message, type, stackTrace);
  }

  public FirmaAsyncSimpleError(ApisIBError apisiberror) {
    super(apisiberror);
  }

}
