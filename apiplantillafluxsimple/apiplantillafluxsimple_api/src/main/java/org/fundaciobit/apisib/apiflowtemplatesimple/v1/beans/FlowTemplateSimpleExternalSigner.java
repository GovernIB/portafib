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
public class FlowTemplateSimpleExternalSigner {

  public static final int SECURITY_LEVEL_TOKEN = 1;
  public static final int SECURITY_LEVEL_PASSWORD = 2;
  public static final int SECURITY_LEVEL_CERTIFICATE = 4;

  protected String administrationId = null;
  protected java.lang.String name = null;
  protected String surnames = null;
  protected String email = null;
  protected String language = null;
  protected int securityLevel = SECURITY_LEVEL_TOKEN;

  public FlowTemplateSimpleExternalSigner() {
    super();
  }

  public FlowTemplateSimpleExternalSigner(String administrationId, String name, String surnames,
      String email, String language, int securityLevel) {
    super();
    this.administrationId = administrationId;
    this.name = name;
    this.surnames = surnames;
    this.email = email;
    this.language = language;
    this.securityLevel = securityLevel;
  }

  public String getAdministrationId() {
    return administrationId;
  }

  public void setAdministrationId(String administrationId) {
    this.administrationId = administrationId;
  }

  public java.lang.String getName() {
    return name;
  }

  public void setName(java.lang.String name) {
    this.name = name;
  }

  public String getSurnames() {
    return surnames;
  }

  public void setSurnames(String surnames) {
    this.surnames = surnames;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public int getSecurityLevel() {
    return securityLevel;
  }

  public void setSecurityLevel(int securityLevel) {
    this.securityLevel = securityLevel;
  }
  
  
  public static String toString(FlowTemplateSimpleExternalSigner externalSigner) {
    StringBuffer str = new StringBuffer();

    str.append("ExternalSigner: ");
    str.append(externalSigner.getName()).append(" ").append(externalSigner.getSurnames());
    str.append("(").append(externalSigner.getAdministrationId()).append(")[");
    str.append(externalSigner.getEmail()).append("]\n");

    return str.toString();
  }

}
