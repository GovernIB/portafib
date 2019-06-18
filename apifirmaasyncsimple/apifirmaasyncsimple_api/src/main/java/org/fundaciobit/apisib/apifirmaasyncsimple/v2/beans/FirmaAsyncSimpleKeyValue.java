package org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans;

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
public class FirmaAsyncSimpleKeyValue extends ApisIBKeyValue {

  /**
   * 
   */
  public FirmaAsyncSimpleKeyValue() {
    super();
  }

  /**
   * @param key
   * @param value
   */
  public FirmaAsyncSimpleKeyValue(String key, String value) {
    super(key, value);
  }

}
