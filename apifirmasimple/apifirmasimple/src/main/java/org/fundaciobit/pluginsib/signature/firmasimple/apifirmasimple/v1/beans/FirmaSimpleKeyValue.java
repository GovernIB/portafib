package org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * 
 * @author anadal
 *
 */
@XmlRootElement
public class FirmaSimpleKeyValue {

  protected String key;

  protected String value;

  /**
 * 
 */
  public FirmaSimpleKeyValue() {
    super();
  }

  /**
   * @param key
   * @param value
   */
  public FirmaSimpleKeyValue(String key, String value) {
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
