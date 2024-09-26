package es.caib.portafib.api.interna.secure.apisimple.v1;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.fundaciobit.apisib.core.beans.ApisIBAvailableProfile;
import org.fundaciobit.apisib.core.beans.ApisIBKeyValue;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
// XYZ ZZZ La classe de la que exten pot ser ApisIBAvailableProfile<FirmaSimpleKeyValue> o  ApisIBAvailableProfile<FirmaAsyncSimpleKeyValue>
public class AvailableProfile {

	  protected String code;

	  protected String name;

	  protected String description;
	  
	  protected List<CommonsRestKeyValue> properties;


	  public AvailableProfile() {
	    super();
	  }
	  
	  public AvailableProfile(String code, String name, String description, List<CommonsRestKeyValue> properties) {
	    super();
	    this.code = code;
	    this.name = name;
	    this.description = description;
	    this.properties = properties;
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

