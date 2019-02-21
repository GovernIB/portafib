package org.fundaciobit.apisib.apifirmasimple.v1.beans;

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
public class FirmaSimpleCommonInfo {

  // Perfil de Firma definit en el servidor intermig
  String signProfile;

  String languageUI;

  String username;

  String administrationID;

  /**
   * 
   */
  public FirmaSimpleCommonInfo() {
  }

  public FirmaSimpleCommonInfo(String signProfile, String languageUI, String username,
      String administrationID) {
    super();
    this.signProfile = signProfile;
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

  public String getSignProfile() {
    return signProfile;
  }

  public void setSignProfile(String signProfile) {
    this.signProfile = signProfile;
  }

}
