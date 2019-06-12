package org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal(u80067)
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaAsyncSimpleMetadata {

  public static final int STRING = 0;
  public static final int INTEGER = 1;
  public static final int DECIMAL = 2;
  public static final int BOOLEAN = 3;
  public static final int BASE64 = 4;
  public static final int DATE = 5; // ISO8601

  protected String name;
  protected String value;
  protected String description = null;
  protected int type = STRING;

  public FirmaAsyncSimpleMetadata() {
    super();
  }

  public FirmaAsyncSimpleMetadata(String name, String value) {
    super();
    this.name = name;
    this.value = value;
  }

  public FirmaAsyncSimpleMetadata(String name, String value, String description, int type) {
    super();
    this.name = name;
    this.value = value;
    this.description = description;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

}
