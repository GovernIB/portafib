package es.caib.portafib.api.interna.secure.firma.v1.commons;

import es.caib.portafib.api.interna.secure.apisimple.v1.apisib.ApisIBError;

/**
 * Classe encarregada d'enviar excepcions des del servidor al client
 * 
 * @author anadal
 *
 */

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
