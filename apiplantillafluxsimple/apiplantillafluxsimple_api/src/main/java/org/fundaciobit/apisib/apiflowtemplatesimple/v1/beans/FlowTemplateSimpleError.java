package org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans;

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
public class FlowTemplateSimpleError extends ApisIBError {

  /**
   * @param message
   */
  public FlowTemplateSimpleError() {
    super();
  }

  /**
   * @param message
   */
  public FlowTemplateSimpleError(String message, String type) {
    super(message, type);
  }

  /**
   * @param message
   * @param type
   * @param stackTrace
   */
  public FlowTemplateSimpleError(String message, String type, String stackTrace) {
    super(message, type, stackTrace);
  }

  public FlowTemplateSimpleError(ApisIBError apisiberror) {
    super(apisiberror);
  }

}
