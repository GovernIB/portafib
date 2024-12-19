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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Objecte que representa un Document/Fitxer
 */
@JsonPropertyOrder({
  FirmaSimpleFile.JSON_PROPERTY_NOM,
  FirmaSimpleFile.JSON_PROPERTY_MIME,
  FirmaSimpleFile.JSON_PROPERTY_DATA
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class FirmaSimpleFile {
  public static final String JSON_PROPERTY_NOM = "nom";
  @javax.annotation.Nonnull
  private String nom;

  public static final String JSON_PROPERTY_MIME = "mime";
  @javax.annotation.Nullable
  private String mime;

  public static final String JSON_PROPERTY_DATA = "data";
  @javax.annotation.Nullable
  private byte[] data;

  public FirmaSimpleFile() {
  }

  public FirmaSimpleFile nom(@javax.annotation.Nonnull String nom) {
    
    this.nom = nom;
    return this;
  }

  /**
   * Nom del fitxer.
   * @return nom
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_NOM)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getNom() {
    return nom;
  }


  @JsonProperty(JSON_PROPERTY_NOM)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setNom(@javax.annotation.Nonnull String nom) {
    this.nom = nom;
  }

  public FirmaSimpleFile mime(@javax.annotation.Nullable String mime) {
    
    this.mime = mime;
    return this;
  }

  /**
   * Tipus mime del fitxer.
   * @return mime
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_MIME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getMime() {
    return mime;
  }


  @JsonProperty(JSON_PROPERTY_MIME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setMime(@javax.annotation.Nullable String mime) {
    this.mime = mime;
  }

  public FirmaSimpleFile data(@javax.annotation.Nullable byte[] data) {
    
    this.data = data;
    return this;
  }

  /**
   * Contingut del fitxer. En llistats aquest camp vendrà buit.
   * @return data
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DATA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public byte[] getData() {
    return data;
  }


  @JsonProperty(JSON_PROPERTY_DATA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setData(@javax.annotation.Nullable byte[] data) {
    this.data = data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FirmaSimpleFile firmaSimpleFile = (FirmaSimpleFile) o;
    return Objects.equals(this.nom, firmaSimpleFile.nom) &&
        Objects.equals(this.mime, firmaSimpleFile.mime) &&
        Arrays.equals(this.data, firmaSimpleFile.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nom, mime, Arrays.hashCode(data));
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FirmaSimpleFile {\n");
    sb.append("    nom: ").append(toIndentedString(nom)).append("\n");
    sb.append("    mime: ").append(toIndentedString(mime)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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

