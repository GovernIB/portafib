package org.fundaciobit.apifirmawebsimple.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
public class FirmaWebSimpleCommonInfo {

  String languageUI;

  String username;

  String administrationID;

  String returnUrl;

  /**
   * 
   */
  public FirmaWebSimpleCommonInfo() {
  }

  /**
   * @param languageUI
   * @param username
   * @param administrationID
   * @param returnUrl
   */
  public FirmaWebSimpleCommonInfo(String languageUI, String username, String administrationID,
      String returnUrl) {
    super();
    this.languageUI = languageUI;
    this.username = username;
    this.administrationID = administrationID;
    this.returnUrl = returnUrl;
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

  public String getReturnUrl() {
    return returnUrl;
  }

  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl;
  }

  public String getAdministrationID() {
    return administrationID;
  }

  public void setAdministrationID(String administrationID) {
    this.administrationID = administrationID;
  }

}
