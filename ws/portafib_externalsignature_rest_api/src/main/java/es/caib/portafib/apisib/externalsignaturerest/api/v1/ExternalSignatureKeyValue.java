package es.caib.portafib.apisib.externalsignaturerest.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.fundaciobit.apisib.core.beans.ApisIBKeyValue;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalSignatureKeyValue extends ApisIBKeyValue {

  /**
   * 
   */
  public ExternalSignatureKeyValue() {
    super();
  }

  /**
   * @param key
   * @param value
   */
  public ExternalSignatureKeyValue(String key, String value) {
    super(key, value);
  }

}
