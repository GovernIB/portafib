package es.caib.portafib.api.interna.secure.apisimple.v1.commons;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.fundaciobit.apisib.core.beans.ApisIBKeyValue;

/**
 * 
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaSimpleKeyValue extends ApisIBKeyValue {

  public FirmaSimpleKeyValue() {
    super();
  }

  public FirmaSimpleKeyValue(String key, String value) {
    super(key, value);
  }

}
