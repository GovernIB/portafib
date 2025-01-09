/*
 * API Interna de PortaFIB que ofereix serveis associats amb la firma digital
 * Conjunt de Serveis REST de PortaFIB per atendre peticions de firma en servidor, firma web, firma asincrona, utilitats de firma i plantilla de flux de firma.
 *
 * The version of the OpenAPI document: 1.0-SNAPSHOT
 * Contact: firma@fundaciobit.org
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
import es.caib.portafib.apiinterna.client.firma.v1.model.TipusDocumentalRest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Resposta per la petició de llistats de tipus documentals.
 */
@JsonPropertyOrder({
  LlistaTipusDocumentalRest.JSON_PROPERTY_DATA,
  LlistaTipusDocumentalRest.JSON_PROPERTY_LANGUAGE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class LlistaTipusDocumentalRest {
  public static final String JSON_PROPERTY_DATA = "data";
  @javax.annotation.Nullable
  private List<TipusDocumentalRest> data = new ArrayList<>();

  public static final String JSON_PROPERTY_LANGUAGE = "language";
  @javax.annotation.Nullable
  private String language;

  public LlistaTipusDocumentalRest() {
  }

  public LlistaTipusDocumentalRest data(@javax.annotation.Nullable List<TipusDocumentalRest> data) {
    
    this.data = data;
    return this;
  }

  public LlistaTipusDocumentalRest addDataItem(TipusDocumentalRest dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<>();
    }
    this.data.add(dataItem);
    return this;
  }

  /**
   * Llistat de tipus documentals disponibles
   * @return data
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DATA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<TipusDocumentalRest> getData() {
    return data;
  }


  @JsonProperty(JSON_PROPERTY_DATA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setData(@javax.annotation.Nullable List<TipusDocumentalRest> data) {
    this.data = data;
  }

  public LlistaTipusDocumentalRest language(@javax.annotation.Nullable String language) {
    
    this.language = language;
    return this;
  }

  /**
   * Llenguatge seleccionat per la resposta
   * @return language
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LANGUAGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getLanguage() {
    return language;
  }


  @JsonProperty(JSON_PROPERTY_LANGUAGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setLanguage(@javax.annotation.Nullable String language) {
    this.language = language;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LlistaTipusDocumentalRest llistaTipusDocumentalRest = (LlistaTipusDocumentalRest) o;
    return Objects.equals(this.data, llistaTipusDocumentalRest.data) &&
        Objects.equals(this.language, llistaTipusDocumentalRest.language);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, language);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LlistaTipusDocumentalRest {\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
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

