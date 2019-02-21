package org.fundaciobit.apisib.apifirmasimple.v1.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaSimpleAvailableProfiles {

  protected List<FirmaSimpleAvailableProfile> availableProfiles;

  public FirmaSimpleAvailableProfiles() {
    super();
  }

  public FirmaSimpleAvailableProfiles(List<FirmaSimpleAvailableProfile> availableProfiles) {
    super();
    this.availableProfiles = availableProfiles;
  }

  public List<FirmaSimpleAvailableProfile> getAvailableProfiles() {
    return availableProfiles;
  }

  public void setAvailableProfiles(List<FirmaSimpleAvailableProfile> availableProfiles) {
    this.availableProfiles = availableProfiles;
  }

}
