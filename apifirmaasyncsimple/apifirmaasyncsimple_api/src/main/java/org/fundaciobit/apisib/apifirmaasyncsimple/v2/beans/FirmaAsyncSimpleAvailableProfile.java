package org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans;

import java.util.List;

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
public class FirmaAsyncSimpleAvailableProfile extends ApisIBAvailableProfile<FirmaAsyncSimpleKeyValue> {

  public FirmaAsyncSimpleAvailableProfile() {
    super();
  }

  public FirmaAsyncSimpleAvailableProfile(String code, String name, String description,
      List<FirmaAsyncSimpleKeyValue> properties) {
    super(code, name, description, properties);
  }

  public FirmaAsyncSimpleAvailableProfile(
      ApisIBAvailableProfile<FirmaAsyncSimpleKeyValue> availableProfile) {
    super(availableProfile);
  }

}
