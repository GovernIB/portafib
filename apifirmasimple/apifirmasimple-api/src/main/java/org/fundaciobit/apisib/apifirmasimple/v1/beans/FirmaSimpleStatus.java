package org.fundaciobit.apisib.apifirmasimple.v1.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.fundaciobit.apisib.core.beans.ApisIBStatus;

/**
 * 
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaSimpleStatus extends ApisIBStatus {

  public FirmaSimpleStatus() {
    super();
  }

  public FirmaSimpleStatus(int status, String errorMessage, String errorStackTrace) {
    super(status, errorMessage, errorStackTrace);
  }

}
