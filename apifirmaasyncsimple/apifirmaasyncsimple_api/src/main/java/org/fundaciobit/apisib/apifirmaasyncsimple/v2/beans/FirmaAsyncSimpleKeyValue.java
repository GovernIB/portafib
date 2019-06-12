package org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * XYZ ZZZ ZZZ Revisar si ho utilitzam
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaAsyncSimpleKeyValue {

  protected String key;

  protected String value;

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
    super();
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
