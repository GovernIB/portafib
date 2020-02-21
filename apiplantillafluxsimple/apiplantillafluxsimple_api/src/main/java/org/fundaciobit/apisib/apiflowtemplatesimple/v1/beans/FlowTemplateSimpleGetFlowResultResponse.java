package org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Resultat d'una firma
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FlowTemplateSimpleGetFlowResultResponse {

  protected FlowTemplateSimpleStatus status;

  protected FlowTemplateSimpleFlowTemplate flowInfo;

  protected List<FlowTemplateSimpleKeyValue> properties;

  /**
   * 
   */
  public FlowTemplateSimpleGetFlowResultResponse() {
    super();
  }

  public FlowTemplateSimpleGetFlowResultResponse(FlowTemplateSimpleStatus status,
      FlowTemplateSimpleFlowTemplate flowInfo, List<FlowTemplateSimpleKeyValue> properties) {
    super();
    this.status = status;
    this.flowInfo = flowInfo;
    this.properties = properties;
  }

  public FlowTemplateSimpleStatus getStatus() {
    return status;
  }

  public void setStatus(FlowTemplateSimpleStatus status) {
    this.status = status;
  }

  public FlowTemplateSimpleFlowTemplate getFlowInfo() {
    return flowInfo;
  }

  public void setFlowInfo(FlowTemplateSimpleFlowTemplate flowInfo) {
    this.flowInfo = flowInfo;
  }

  public List<FlowTemplateSimpleKeyValue> getProperties() {
    return properties;
  }

  public void setProperties(List<FlowTemplateSimpleKeyValue> properties) {
    this.properties = properties;
  }
  
  

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();
    List<FlowTemplateSimpleKeyValue> additionalInformation = getProperties();

    if (additionalInformation != null && additionalInformation.size() != 0) {
      str.append("\n").append("        + INFORMACIO ADDICIONAL:");
      for (FlowTemplateSimpleKeyValue firmaSimpleKeyValue : additionalInformation) {
        str.append("\n").append("          >> KEY[" + firmaSimpleKeyValue.getKey() + "]: "
            + firmaSimpleKeyValue.getValue());
      }
    }

    return str.toString();

  }

}
