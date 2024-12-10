package es.caib.portafib.api.interna.secure.firma.v1.commons.apisib;

import java.util.List;

import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleKeyValue;


/**
 * 
 * @author anadal(u80067)
 *
 */

public class ApisIBAvailableProfile {

  protected String code;

  protected String name;

  protected String description;

  protected List<FirmaSimpleKeyValue> properties;

  public ApisIBAvailableProfile() {
    super();
  }

  public ApisIBAvailableProfile(String code, String name, String description,
      List<FirmaSimpleKeyValue> properties) {
    super();
    this.code = code;
    this.name = name;
    this.description = description;
    this.properties = properties;
  }
  
  public ApisIBAvailableProfile(ApisIBAvailableProfile availableProfile) {
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

  public List<FirmaSimpleKeyValue> getProperties() {
    return properties;
  }

  public void setProperties(List<FirmaSimpleKeyValue> properties) {
    this.properties = properties;
  }

}
