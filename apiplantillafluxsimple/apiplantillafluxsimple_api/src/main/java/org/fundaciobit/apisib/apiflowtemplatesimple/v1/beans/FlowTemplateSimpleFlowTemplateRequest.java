package org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal(u80067)
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FlowTemplateSimpleFlowTemplateRequest {

  protected String languageUI;

  protected String flowTemplateId;

  public FlowTemplateSimpleFlowTemplateRequest() {
    super();
  }

  public FlowTemplateSimpleFlowTemplateRequest(String languageUI, String flowTemplateId) {
    super();
    this.languageUI = languageUI;
    this.flowTemplateId = flowTemplateId;
  }

  public String getLanguageUI() {
    return languageUI;
  }

  public void setLanguageUI(String languageUI) {
    this.languageUI = languageUI;
  }

  public String getFlowTemplateId() {
    return flowTemplateId;
  }

  public void setFlowTemplateId(String flowTemplateId) {
    this.flowTemplateId = flowTemplateId;
  }

}
