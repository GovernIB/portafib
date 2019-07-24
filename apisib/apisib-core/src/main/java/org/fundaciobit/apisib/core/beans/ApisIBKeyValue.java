package org.fundaciobit.apisib.core.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ApisIBKeyValue {

  protected String key;

  protected String value;

  /**
 * 
 */
  public ApisIBKeyValue() {
    super();
  }

  /**
   * @param key
   * @param value
   */
  public ApisIBKeyValue(String key, String value) {
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
