package org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans;

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
public class FlowTemplateSimpleFlowTemplateList {

  List<FlowTemplateSimpleKeyValue> list;

  public FlowTemplateSimpleFlowTemplateList() {
    super();
  }

  public FlowTemplateSimpleFlowTemplateList(List<FlowTemplateSimpleKeyValue> list) {
    super();
    this.list = list;
  }

  public List<FlowTemplateSimpleKeyValue> getList() {
    return list;
  }

  public void setList(List<FlowTemplateSimpleKeyValue> list) {
    this.list = list;
  }

}
