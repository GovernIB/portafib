package org.fundaciobit.apisib.apifirmasimple.v1.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.fundaciobit.apisib.core.beans.ApisIBAvailableProfile;


/**
 * 
 * @author anadal(u80067)
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaSimpleAvailableProfile extends ApisIBAvailableProfile<FirmaSimpleKeyValue> {

  protected String code;

  protected String name;

  protected String description;

  public FirmaSimpleAvailableProfile() {
    super();
  }

  public FirmaSimpleAvailableProfile(String code, String name, String description) {
    super();
    this.code = code;
    this.name = name;
    this.description = description;
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

}
