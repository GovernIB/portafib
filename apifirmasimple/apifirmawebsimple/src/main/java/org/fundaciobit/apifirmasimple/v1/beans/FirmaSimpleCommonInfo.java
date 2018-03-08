package org.fundaciobit.apifirmasimple.v1.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
public class FirmaSimpleCommonInfo {

  String languageUI;

  String username;

  String administrationID;


  /**
   * 
   */
  public FirmaSimpleCommonInfo() {
  }

  /**
   * @param languageUI
   * @param username
   * @param administrationID
   */
  public FirmaSimpleCommonInfo(String languageUI, String username, String administrationID) {
    super();
    this.languageUI = languageUI;
    this.username = username;
    this.administrationID = administrationID;
  }

  public String getLanguageUI() {
    return languageUI;
  }

  public void setLanguageUI(String languageUI) {
    this.languageUI = languageUI;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAdministrationID() {
    return administrationID;
  }

  public void setAdministrationID(String administrationID) {
    this.administrationID = administrationID;
  }

}
