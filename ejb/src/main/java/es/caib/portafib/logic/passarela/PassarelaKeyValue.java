package es.caib.portafib.logic.passarela;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * 
 * @author anadal
 *
 */
@XmlRootElement
public class PassarelaKeyValue {

  protected String key;

  protected String value;

  /**
 * 
 */
  public PassarelaKeyValue() {
    super();
  }

  /**
   * @param key
   * @param value
   */
  public PassarelaKeyValue(String key, String value) {
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
