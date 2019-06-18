package org.fundaciobit.apisib.core.beans;

import java.util.List;

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
public class ApisIBAvailableProfile<K extends ApisIBKeyValue> {

  protected String code;

  protected String name;

  protected String description;

  protected List<K> properties;

  public ApisIBAvailableProfile() {
    super();
  }

  public ApisIBAvailableProfile(String code, String name, String description,
      List<K> properties) {
    super();
    this.code = code;
    this.name = name;
    this.description = description;
    this.properties = properties;
  }
  
  public ApisIBAvailableProfile(ApisIBAvailableProfile<K> availableProfile) {
    super();
    this.code = availableProfile.code;
    this.name = availableProfile.name;
    this.description = availableProfile.description;
    this.properties = availableProfile.properties;
  }
  
  
  

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<K> getProperties() {
    return properties;
  }

  public void setProperties(List<K> properties) {
    this.properties = properties;
  }

}
