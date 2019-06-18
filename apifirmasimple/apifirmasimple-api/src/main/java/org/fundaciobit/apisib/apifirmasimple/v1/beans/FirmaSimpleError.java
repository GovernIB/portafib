package org.fundaciobit.apisib.apifirmasimple.v1.beans;

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
public class FirmaSimpleError extends ApisIBError {

  public FirmaSimpleError() {
    super();
  }

  public FirmaSimpleError(ApisIBError apisiberror) {
    super(apisiberror);
  }

  public FirmaSimpleError(String message, String type, String stackTrace) {
    super(message, type, stackTrace);
  }

  public FirmaSimpleError(String message, String type) {
    super(message, type);
  }

  
}
