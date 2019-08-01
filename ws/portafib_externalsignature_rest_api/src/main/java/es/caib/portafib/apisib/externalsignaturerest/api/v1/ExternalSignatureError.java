package es.caib.portafib.apisib.externalsignaturerest.api.v1;

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
public class ExternalSignatureError extends ApisIBError {

  /**
   * @param message
   */
  public ExternalSignatureError() {
    super();
  }

  /**
   * @param message
   */
  public ExternalSignatureError(String message, String type) {
    super(message, type);
  }

  /**
   * @param message
   * @param type
   * @param stackTrace
   */
  public ExternalSignatureError(String message, String type, String stackTrace) {
    super(message, type, stackTrace);
  }

  public ExternalSignatureError(ApisIBError apisiberror) {
    super(apisiberror);
  }

}
