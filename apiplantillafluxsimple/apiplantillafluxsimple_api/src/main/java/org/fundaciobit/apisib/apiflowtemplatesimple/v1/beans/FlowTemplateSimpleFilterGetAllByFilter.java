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
public class FlowTemplateSimpleFilterGetAllByFilter {

  protected String languageUI;

  protected String nameFilter;

  protected String descriptionFilter;

  public FlowTemplateSimpleFilterGetAllByFilter() {
    super();
  }

  public FlowTemplateSimpleFilterGetAllByFilter(String languageUI, String nameFilter,
      String descriptionFilter) {
    super();
    this.languageUI = languageUI;
    this.nameFilter = nameFilter;
    this.descriptionFilter = descriptionFilter;
  }

  public String getNameFilter() {
    return nameFilter;
  }

  public void setNameFilter(String nameFilter) {
    this.nameFilter = nameFilter;
  }

  public String getLanguageUI() {
    return languageUI;
  }

  public void setLanguageUI(String languageUI) {
    this.languageUI = languageUI;
  }

  public String getDescriptionFilter() {
    return descriptionFilter;
  }

  public void setDescriptionFilter(String descriptionFilter) {
    this.descriptionFilter = descriptionFilter;
  }

}
