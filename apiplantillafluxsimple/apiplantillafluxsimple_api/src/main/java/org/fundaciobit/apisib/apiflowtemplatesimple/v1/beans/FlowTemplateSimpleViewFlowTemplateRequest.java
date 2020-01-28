package org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans;

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
public class FlowTemplateSimpleViewFlowTemplateRequest {

  protected String languageUI;

  protected String flowTemplateID;

  public FlowTemplateSimpleViewFlowTemplateRequest() {
    super();
  }

  public FlowTemplateSimpleViewFlowTemplateRequest(String languageUI, String flowTemplateID) {
    super();
    this.languageUI = languageUI;
    this.flowTemplateID = flowTemplateID;
  }

  public String getLanguageUI() {
    return languageUI;
  }

  public void setLanguageUI(String languageUI) {
    this.languageUI = languageUI;
  }

  public String getFlowTemplateID() {
    return flowTemplateID;
  }

  public void setFlowTemplateID(String flowTemplateID) {
    this.flowTemplateID = flowTemplateID;
  }

}
