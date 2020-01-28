package org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans;

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
public class FlowTemplateSimpleStatus extends ApisIBStatus {
  
  public static final int STATUS_RESERVED_ID = FlowTemplateSimpleStatus.STATUS_INITIALIZING;

  public FlowTemplateSimpleStatus() {
    super();
  }

  public FlowTemplateSimpleStatus(int status) {
    super(status, null, null);
  }
  
  public FlowTemplateSimpleStatus(int status, String errorMessage, String errorStackTrace) {
    super(status, errorMessage, errorStackTrace);
  }

}
