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


package es.caib.portafib.apiinterna.client.firma.v1.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleCommonInfo;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleFileInfoSignature;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * FirmaSimpleSignDocumentRequest
 */
@JsonPropertyOrder({
  FirmaSimpleSignDocumentRequest.JSON_PROPERTY_COMMON_INFO,
  FirmaSimpleSignDocumentRequest.JSON_PROPERTY_FILE_INFO_SIGNATURE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class FirmaSimpleSignDocumentRequest {
  public static final String JSON_PROPERTY_COMMON_INFO = "commonInfo";
  @javax.annotation.Nonnull
  private FirmaSimpleCommonInfo commonInfo;

  public static final String JSON_PROPERTY_FILE_INFO_SIGNATURE = "fileInfoSignature";
  @javax.annotation.Nonnull
  private FirmaSimpleFileInfoSignature fileInfoSignature;

  public FirmaSimpleSignDocumentRequest() {
  }

  public FirmaSimpleSignDocumentRequest commonInfo(@javax.annotation.Nonnull FirmaSimpleCommonInfo commonInfo) {
    
    this.commonInfo = commonInfo;
    return this;
  }

  /**
   * Get commonInfo
   * @return commonInfo
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_COMMON_INFO)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public FirmaSimpleCommonInfo getCommonInfo() {
    return commonInfo;
  }


  @JsonProperty(JSON_PROPERTY_COMMON_INFO)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCommonInfo(@javax.annotation.Nonnull FirmaSimpleCommonInfo commonInfo) {
    this.commonInfo = commonInfo;
  }

  public FirmaSimpleSignDocumentRequest fileInfoSignature(@javax.annotation.Nonnull FirmaSimpleFileInfoSignature fileInfoSignature) {
    
    this.fileInfoSignature = fileInfoSignature;
    return this;
  }

  /**
   * Get fileInfoSignature
   * @return fileInfoSignature
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_FILE_INFO_SIGNATURE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public FirmaSimpleFileInfoSignature getFileInfoSignature() {
    return fileInfoSignature;
  }


  @JsonProperty(JSON_PROPERTY_FILE_INFO_SIGNATURE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setFileInfoSignature(@javax.annotation.Nonnull FirmaSimpleFileInfoSignature fileInfoSignature) {
    this.fileInfoSignature = fileInfoSignature;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FirmaSimpleSignDocumentRequest firmaSimpleSignDocumentRequest = (FirmaSimpleSignDocumentRequest) o;
    return Objects.equals(this.commonInfo, firmaSimpleSignDocumentRequest.commonInfo) &&
        Objects.equals(this.fileInfoSignature, firmaSimpleSignDocumentRequest.fileInfoSignature);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commonInfo, fileInfoSignature);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FirmaSimpleSignDocumentRequest {\n");
    sb.append("    commonInfo: ").append(toIndentedString(commonInfo)).append("\n");
    sb.append("    fileInfoSignature: ").append(toIndentedString(fileInfoSignature)).append("\n");
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

