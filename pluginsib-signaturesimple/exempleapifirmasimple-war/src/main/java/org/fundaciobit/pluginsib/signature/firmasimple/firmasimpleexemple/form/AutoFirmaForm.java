package org.fundaciobit.pluginsib.signature.firmasimple.firmasimpleexemple.form;

import java.util.List;

import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleAvailableProfile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 
 * @author anadal
 * 
 */
public class AutoFirmaForm {

  public static final String VISUALITZACIO_FULLVIEW = "fullview";

  public static final String VISUALITZACIO_IFRAME = "iframe";

  protected String profileServer;

  protected String profileWeb;

  protected List<FirmaSimpleAvailableProfile> profilesServer;

  protected List<FirmaSimpleAvailableProfile> profilesWeb;

  protected String motiu;

  protected String langUI;

  protected String langDoc;

  protected String username;

  protected String nif;

  protected String email;

  protected String location;

  protected String visualitzacio;

  protected CommonsMultipartFile fitxerAFirmarID;

  protected CommonsMultipartFile fitxerAFirmarID2;

  protected CommonsMultipartFile fitxerAFirmarID3;

  protected CommonsMultipartFile fitxerAFirmarID4;

  public AutoFirmaForm() {
  }

  public CommonsMultipartFile getFitxerAFirmarID() {
    return fitxerAFirmarID;
  }

  public void setFitxerAFirmarID(CommonsMultipartFile fitxerAFirmarID) {
    this.fitxerAFirmarID = fitxerAFirmarID;
  }

  public CommonsMultipartFile getFitxerAFirmarID2() {
    return fitxerAFirmarID2;
  }

  public void setFitxerAFirmarID2(CommonsMultipartFile fitxerAFirmarID2) {
    this.fitxerAFirmarID2 = fitxerAFirmarID2;
  }

  public CommonsMultipartFile getFitxerAFirmarID3() {
    return fitxerAFirmarID3;
  }

  public void setFitxerAFirmarID3(CommonsMultipartFile fitxerAFirmarID3) {
    this.fitxerAFirmarID3 = fitxerAFirmarID3;
  }

  public CommonsMultipartFile getFitxerAFirmarID4() {
    return fitxerAFirmarID4;
  }

  public void setFitxerAFirmarID4(CommonsMultipartFile fitxerAFirmarID4) {
    this.fitxerAFirmarID4 = fitxerAFirmarID4;
  }

  public java.lang.String getMotiu() {
    return motiu;
  }

  public void setMotiu(java.lang.String motiu) {
    this.motiu = motiu;
  }

  public String getLangUI() {
    return langUI;
  }

  public void setLangUI(String langUI) {
    this.langUI = langUI;
  }

  public String getLangDoc() {
    return langDoc;
  }

  public void setLangDoc(String langDoc) {
    this.langDoc = langDoc;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getNif() {
    return nif;
  }

  public void setNif(String nif) {
    this.nif = nif;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getVisualitzacio() {
    return visualitzacio;
  }

  public void setVisualitzacio(String visualitzacio) {
    this.visualitzacio = visualitzacio;
  }

  public String getProfileServer() {
    return profileServer;
  }

  public void setProfileServer(String profileServer) {
    this.profileServer = profileServer;
  }

  public String getProfileWeb() {
    return profileWeb;
  }

  public void setProfileWeb(String profileWeb) {
    this.profileWeb = profileWeb;
  }

  public List<FirmaSimpleAvailableProfile> getProfilesServer() {
    return profilesServer;
  }

  public void setProfilesServer(List<FirmaSimpleAvailableProfile> profilesServer) {
    this.profilesServer = profilesServer;
  }

  public List<FirmaSimpleAvailableProfile> getProfilesWeb() {
    return profilesWeb;
  }

  public void setProfilesWeb(List<FirmaSimpleAvailableProfile> profilesWeb) {
    this.profilesWeb = profilesWeb;
  }



}
