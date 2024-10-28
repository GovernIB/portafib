/*
 * API Interna de PortaFIB de consulta de serveis d'utilitat
 * Conjunt de Serveis REST de PortaFIB per atendre consultes generiques de Portafib
 *
 * The version of the OpenAPI document: 1.0-SNAPSHOT
 * Contact: otae@fundaciobit.org
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package es.caib.portafib.apiinterna.client.firmaservidor.v1.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * FirmaSimpleCommonInfo
 */
@JsonPropertyOrder({
  FirmaSimpleCommonInfo.JSON_PROPERTY_SIGN_PROFILE,
  FirmaSimpleCommonInfo.JSON_PROPERTY_LANGUAGE_U_I,
  FirmaSimpleCommonInfo.JSON_PROPERTY_USERNAME,
  FirmaSimpleCommonInfo.JSON_PROPERTY_ADMINISTRATION_I_D,
  FirmaSimpleCommonInfo.JSON_PROPERTY_ORGANIZATION_I_D,
  FirmaSimpleCommonInfo.JSON_PROPERTY_SIGNER_EMAIL
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class FirmaSimpleCommonInfo {
  public static final String JSON_PROPERTY_SIGN_PROFILE = "signProfile";
  private String signProfile;

  public static final String JSON_PROPERTY_LANGUAGE_U_I = "languageUI";
  private String languageUI;

  public static final String JSON_PROPERTY_USERNAME = "username";
  private String username;

  public static final String JSON_PROPERTY_ADMINISTRATION_I_D = "administrationID";
  private String administrationID;

  public static final String JSON_PROPERTY_ORGANIZATION_I_D = "organizationID";
  private String organizationID;

  public static final String JSON_PROPERTY_SIGNER_EMAIL = "signerEmail";
  private String signerEmail;

  public FirmaSimpleCommonInfo() {
  }

  public FirmaSimpleCommonInfo signProfile(String signProfile) {
    
    this.signProfile = signProfile;
    return this;
  }

   /**
   * Get signProfile
   * @return signProfile
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SIGN_PROFILE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSignProfile() {
    return signProfile;
  }


  @JsonProperty(JSON_PROPERTY_SIGN_PROFILE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSignProfile(String signProfile) {
    this.signProfile = signProfile;
  }


  public FirmaSimpleCommonInfo languageUI(String languageUI) {
    
    this.languageUI = languageUI;
    return this;
  }

   /**
   * Get languageUI
   * @return languageUI
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LANGUAGE_U_I)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getLanguageUI() {
    return languageUI;
  }


  @JsonProperty(JSON_PROPERTY_LANGUAGE_U_I)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setLanguageUI(String languageUI) {
    this.languageUI = languageUI;
  }


  public FirmaSimpleCommonInfo username(String username) {
    
    this.username = username;
    return this;
  }

   /**
   * Get username
   * @return username
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_USERNAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getUsername() {
    return username;
  }


  @JsonProperty(JSON_PROPERTY_USERNAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setUsername(String username) {
    this.username = username;
  }


  public FirmaSimpleCommonInfo administrationID(String administrationID) {
    
    this.administrationID = administrationID;
    return this;
  }

   /**
   * Get administrationID
   * @return administrationID
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ADMINISTRATION_I_D)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getAdministrationID() {
    return administrationID;
  }


  @JsonProperty(JSON_PROPERTY_ADMINISTRATION_I_D)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setAdministrationID(String administrationID) {
    this.administrationID = administrationID;
  }


  public FirmaSimpleCommonInfo organizationID(String organizationID) {
    
    this.organizationID = organizationID;
    return this;
  }

   /**
   * Get organizationID
   * @return organizationID
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ORGANIZATION_I_D)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getOrganizationID() {
    return organizationID;
  }


  @JsonProperty(JSON_PROPERTY_ORGANIZATION_I_D)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setOrganizationID(String organizationID) {
    this.organizationID = organizationID;
  }


  public FirmaSimpleCommonInfo signerEmail(String signerEmail) {
    
    this.signerEmail = signerEmail;
    return this;
  }

   /**
   * Get signerEmail
   * @return signerEmail
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SIGNER_EMAIL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSignerEmail() {
    return signerEmail;
  }


  @JsonProperty(JSON_PROPERTY_SIGNER_EMAIL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSignerEmail(String signerEmail) {
    this.signerEmail = signerEmail;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FirmaSimpleCommonInfo firmaSimpleCommonInfo = (FirmaSimpleCommonInfo) o;
    return Objects.equals(this.signProfile, firmaSimpleCommonInfo.signProfile) &&
        Objects.equals(this.languageUI, firmaSimpleCommonInfo.languageUI) &&
        Objects.equals(this.username, firmaSimpleCommonInfo.username) &&
        Objects.equals(this.administrationID, firmaSimpleCommonInfo.administrationID) &&
        Objects.equals(this.organizationID, firmaSimpleCommonInfo.organizationID) &&
        Objects.equals(this.signerEmail, firmaSimpleCommonInfo.signerEmail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(signProfile, languageUI, username, administrationID, organizationID, signerEmail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FirmaSimpleCommonInfo {\n");
    sb.append("    signProfile: ").append(toIndentedString(signProfile)).append("\n");
    sb.append("    languageUI: ").append(toIndentedString(languageUI)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    administrationID: ").append(toIndentedString(administrationID)).append("\n");
    sb.append("    organizationID: ").append(toIndentedString(organizationID)).append("\n");
    sb.append("    signerEmail: ").append(toIndentedString(signerEmail)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

