package org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.fundaciobit.apisib.core.beans.ApisIBKeyValue;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FlowTemplateSimpleKeyValue extends ApisIBKeyValue {

  /**
   * 
   */
  public FlowTemplateSimpleKeyValue() {
    super();
  }

  /**
   * @param key
   * @param value
   */
  public FlowTemplateSimpleKeyValue(String key, String value) {
    super(key, value);
  }

}
