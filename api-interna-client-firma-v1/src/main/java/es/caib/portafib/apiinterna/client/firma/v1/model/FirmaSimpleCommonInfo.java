/*
 * API Interna de PortaFIB que ofereix serveis de firma web.
 * Conjunt de Serveis REST de PortaFIB per atendre peticions de firma a través de web de PortaFIB
 *
 * The version of the OpenAPI document: 1.0-SNAPSHOT
 * Contact: otae@fundaciobit.org
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package es.caib.portafib.apiinterna.client.firma.v1.model;

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
 * Configuracions generals de firma i identificacio del solicitant i solicitat
 */
@JsonPropertyOrder({
  FirmaSimpleCommonInfo.JSON_PROPERTY_SIGN_PROFILE,
  FirmaSimpleCommonInfo.JSON_PROPERTY_LANGUAGE_U_I,
  FirmaSimpleCommonInfo.JSON_PROPERTY_USERNAME,
  FirmaSimpleCommonInfo.JSON_PROPERTY_ADMINISTRATION_I_D,
  FirmaSimpleCommonInfo.JSON_PROPERTY_ORGANIZATION_I_D,
  FirmaSimpleCommonInfo.JSON_PROPERTY_SIGNER_EMAIL
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class FirmaSimpleCommonInfo {
  public static final String JSON_PROPERTY_SIGN_PROFILE = "signProfile";
  @javax.annotation.Nullable
  private String signProfile;

  public static final String JSON_PROPERTY_LANGUAGE_U_I = "languageUI";
  @javax.annotation.Nonnull
  private String languageUI;

  public static final String JSON_PROPERTY_USERNAME = "username";
  @javax.annotation.Nullable
  private String username;

  public static final String JSON_PROPERTY_ADMINISTRATION_I_D = "administrationID";
  @javax.annotation.Nonnull
  private String administrationID;

  public static final String JSON_PROPERTY_ORGANIZATION_I_D = "organizationID";
  @javax.annotation.Nullable
  private String organizationID;

  public static final String JSON_PROPERTY_SIGNER_EMAIL = "signerEmail";
  @javax.annotation.Nullable
  private String signerEmail;

  public FirmaSimpleCommonInfo() {
  }

  public FirmaSimpleCommonInfo signProfile(@javax.annotation.Nullable String signProfile) {
    
    this.signProfile = signProfile;
    return this;
  }

  /**
   * Identificador único del usuario
   * @return signProfile
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SIGN_PROFILE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSignProfile() {
    return signProfile;
  }


  @JsonProperty(JSON_PROPERTY_SIGN_PROFILE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSignProfile(@javax.annotation.Nullable String signProfile) {
    this.signProfile = signProfile;
  }

  public FirmaSimpleCommonInfo languageUI(@javax.annotation.Nonnull String languageUI) {
    
    this.languageUI = languageUI;
    return this;
  }

  /**
   * Idioma en que retornar valors i missatges d&#39;error
   * @return languageUI
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_LANGUAGE_U_I)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getLanguageUI() {
    return languageUI;
  }


  @JsonProperty(JSON_PROPERTY_LANGUAGE_U_I)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setLanguageUI(@javax.annotation.Nonnull String languageUI) {
    this.languageUI = languageUI;
  }

  public FirmaSimpleCommonInfo username(@javax.annotation.Nullable String username) {
    
    this.username = username;
    return this;
  }

  /**
   *  - FIRMA WEB: Requerit. És el codi d&#39;usuari dins l&#39;entitat. Per exemple en entorn CAIB serien els \&quot;u800xx\&quot; o \&quot;u[DNI]\&quot;   -FIRMA EN SERVIDOR: Opcional. Es reconama que valgui null a no ser que l&#39;administrador digui el contrari. És la configuració de firma en el sistema específic de firma. Per exemple amb el Plugin de @firma federat et pots connectar amb un usuari-password però aquest pot tenir diverses configuracions per fer firmes en servidor o àlies: \&quot;username\&quot; s&#39;utilitza de definir aquesta configuració o àlies.
   * @return username
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_USERNAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getUsername() {
    return username;
  }


  @JsonProperty(JSON_PROPERTY_USERNAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setUsername(@javax.annotation.Nullable String username) {
    this.username = username;
  }

  public FirmaSimpleCommonInfo administrationID(@javax.annotation.Nonnull String administrationID) {
    
    this.administrationID = administrationID;
    return this;
  }

  /**
   *  - FIRMA WEB: Requerit. És el DNI de la persona signant. Si esta activa la validació dins PortaFIB llavors es valida que el DNI del Certificat sigui el mateix que aquest.   - FIRMA EN SERVIDOR: Opcional. És el CIF o NIF associat al certificat en servidor. Si es defineix i si esta activa la validació dins PortaFIB llavors es valida que el DNI del Certificat sigui el mateix que aquest.
   * @return administrationID
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_ADMINISTRATION_I_D)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getAdministrationID() {
    return administrationID;
  }


  @JsonProperty(JSON_PROPERTY_ADMINISTRATION_I_D)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setAdministrationID(@javax.annotation.Nonnull String administrationID) {
    this.administrationID = administrationID;
  }

  public FirmaSimpleCommonInfo organizationID(@javax.annotation.Nullable String organizationID) {
    
    this.organizationID = organizationID;
    return this;
  }

  /**
   * Opcional. És el CIF de l&#39;organització representada pel signant. Si esta activa la validació dins PortaFIB llavors es valida que el Certificat sigui un certificat de representant d&#39;aquest CIF.
   * @return organizationID
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ORGANIZATION_I_D)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getOrganizationID() {
    return organizationID;
  }


  @JsonProperty(JSON_PROPERTY_ORGANIZATION_I_D)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setOrganizationID(@javax.annotation.Nullable String organizationID) {
    this.organizationID = organizationID;
  }

  public FirmaSimpleCommonInfo signerEmail(@javax.annotation.Nullable String signerEmail) {
    
    this.signerEmail = signerEmail;
    return this;
  }

  /**
   *  - FIRMA WEB: Opcional. Correu del Firmant. Per afegir a les dades de la firma.   - FIRMA EN SERVIDOR: Opcional. Correu del departament que ordena la firma. Per afegir a les dades de la firma.
   * @return signerEmail
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SIGNER_EMAIL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSignerEmail() {
    return signerEmail;
  }


  @JsonProperty(JSON_PROPERTY_SIGNER_EMAIL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSignerEmail(@javax.annotation.Nullable String signerEmail) {
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

