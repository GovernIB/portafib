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
public class FlowTemplateSimpleGetTransactionIdRequest {

  protected String languageUI;

  protected boolean saveOnServer;

  protected String name;

  protected String description;

  protected boolean visibleDescription;

  /**
   * 
   */
  public FlowTemplateSimpleGetTransactionIdRequest() {
    super();
  }

  public FlowTemplateSimpleGetTransactionIdRequest(String languageUI, boolean saveOnServer,
      String name, String description, boolean visibleDescription) {
    super();
    this.languageUI = languageUI;
    this.saveOnServer = saveOnServer;
    this.name = name;
    this.description = description;
    this.visibleDescription = visibleDescription;
  }

  public String getLanguageUI() {
    return languageUI;
  }

  public void setLanguageUI(String languageUI) {
    this.languageUI = languageUI;
  }

  public boolean isVisibleDescription() {
    return visibleDescription;
  }

  public void setVisibleDescription(boolean visibleDescription) {
    this.visibleDescription = visibleDescription;
  }

  public boolean isSaveOnServer() {
    return saveOnServer;
  }

  public void setSaveOnServer(boolean saveOnServer) {
    this.saveOnServer = saveOnServer;
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
