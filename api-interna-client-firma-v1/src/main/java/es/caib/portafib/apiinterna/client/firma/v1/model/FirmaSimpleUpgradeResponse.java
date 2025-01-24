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
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleFile;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleUpgradedFileInfo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * FirmaSimpleUpgradeResponse
 */
@JsonPropertyOrder({
  FirmaSimpleUpgradeResponse.JSON_PROPERTY_UPGRADED_FILE,
  FirmaSimpleUpgradeResponse.JSON_PROPERTY_UPGRADED_FILE_INFO
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class FirmaSimpleUpgradeResponse {
  public static final String JSON_PROPERTY_UPGRADED_FILE = "upgradedFile";
  @javax.annotation.Nullable
  private FirmaSimpleFile upgradedFile;

  public static final String JSON_PROPERTY_UPGRADED_FILE_INFO = "upgradedFileInfo";
  @javax.annotation.Nullable
  private FirmaSimpleUpgradedFileInfo upgradedFileInfo;

  public FirmaSimpleUpgradeResponse() {
  }

  public FirmaSimpleUpgradeResponse upgradedFile(@javax.annotation.Nullable FirmaSimpleFile upgradedFile) {
    
    this.upgradedFile = upgradedFile;
    return this;
  }

  /**
   * Get upgradedFile
   * @return upgradedFile
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_UPGRADED_FILE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FirmaSimpleFile getUpgradedFile() {
    return upgradedFile;
  }


  @JsonProperty(JSON_PROPERTY_UPGRADED_FILE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setUpgradedFile(@javax.annotation.Nullable FirmaSimpleFile upgradedFile) {
    this.upgradedFile = upgradedFile;
  }

  public FirmaSimpleUpgradeResponse upgradedFileInfo(@javax.annotation.Nullable FirmaSimpleUpgradedFileInfo upgradedFileInfo) {
    
    this.upgradedFileInfo = upgradedFileInfo;
    return this;
  }

  /**
   * Get upgradedFileInfo
   * @return upgradedFileInfo
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_UPGRADED_FILE_INFO)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FirmaSimpleUpgradedFileInfo getUpgradedFileInfo() {
    return upgradedFileInfo;
  }


  @JsonProperty(JSON_PROPERTY_UPGRADED_FILE_INFO)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setUpgradedFileInfo(@javax.annotation.Nullable FirmaSimpleUpgradedFileInfo upgradedFileInfo) {
    this.upgradedFileInfo = upgradedFileInfo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FirmaSimpleUpgradeResponse firmaSimpleUpgradeResponse = (FirmaSimpleUpgradeResponse) o;
    return Objects.equals(this.upgradedFile, firmaSimpleUpgradeResponse.upgradedFile) &&
        Objects.equals(this.upgradedFileInfo, firmaSimpleUpgradeResponse.upgradedFileInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(upgradedFile, upgradedFileInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FirmaSimpleUpgradeResponse {\n");
    sb.append("    upgradedFile: ").append(toIndentedString(upgradedFile)).append("\n");
    sb.append("    upgradedFileInfo: ").append(toIndentedString(upgradedFileInfo)).append("\n");
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

