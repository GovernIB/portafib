/*
 * API REST INTERNA de PortaFIB
 * Conjunt de Serveis REST de PortaFIB per ser accedits des de l'interior
 *
 * The version of the OpenAPI document: 1.0.0
 * Contact: otae@fundaciobit.org
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package es.caib.portafib.apiinterna.client.model;

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
 * Model de dades de Informació bàsica a retornar.
 */
@JsonPropertyOrder({
  InfoVersion.JSON_PROPERTY_CAIB,
  InfoVersion.JSON_PROPERTY_VERSION,
  InfoVersion.JSON_PROPERTY_BUILD_TIME,
  InfoVersion.JSON_PROPERTY_JDK_VERSION
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class InfoVersion {
  public static final String JSON_PROPERTY_CAIB = "caib";
  private Boolean caib;

  public static final String JSON_PROPERTY_VERSION = "version";
  private String version;

  public static final String JSON_PROPERTY_BUILD_TIME = "buildTime";
  private String buildTime;

  public static final String JSON_PROPERTY_JDK_VERSION = "jdkVersion";
  private String jdkVersion;

  public InfoVersion() {
  }

  public InfoVersion caib(Boolean caib) {
    
    this.caib = caib;
    return this;
  }

   /**
   * És un servidor de la CAIB
   * @return caib
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_CAIB)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Boolean getCaib() {
    return caib;
  }


  @JsonProperty(JSON_PROPERTY_CAIB)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCaib(Boolean caib) {
    this.caib = caib;
  }


  public InfoVersion version(String version) {
    
    this.version = version;
    return this;
  }

   /**
   * Versió
   * @return version
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_VERSION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getVersion() {
    return version;
  }


  @JsonProperty(JSON_PROPERTY_VERSION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setVersion(String version) {
    this.version = version;
  }


  public InfoVersion buildTime(String buildTime) {
    
    this.buildTime = buildTime;
    return this;
  }

   /**
   * Data compilació
   * @return buildTime
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_BUILD_TIME)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getBuildTime() {
    return buildTime;
  }


  @JsonProperty(JSON_PROPERTY_BUILD_TIME)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setBuildTime(String buildTime) {
    this.buildTime = buildTime;
  }


  public InfoVersion jdkVersion(String jdkVersion) {
    
    this.jdkVersion = jdkVersion;
    return this;
  }

   /**
   * Versió de JDK en que s&#39;ha compilat
   * @return jdkVersion
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_JDK_VERSION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getJdkVersion() {
    return jdkVersion;
  }


  @JsonProperty(JSON_PROPERTY_JDK_VERSION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setJdkVersion(String jdkVersion) {
    this.jdkVersion = jdkVersion;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InfoVersion infoVersion = (InfoVersion) o;
    return Objects.equals(this.caib, infoVersion.caib) &&
        Objects.equals(this.version, infoVersion.version) &&
        Objects.equals(this.buildTime, infoVersion.buildTime) &&
        Objects.equals(this.jdkVersion, infoVersion.jdkVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(caib, version, buildTime, jdkVersion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InfoVersion {\n");
    sb.append("    caib: ").append(toIndentedString(caib)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    buildTime: ").append(toIndentedString(buildTime)).append("\n");
    sb.append("    jdkVersion: ").append(toIndentedString(jdkVersion)).append("\n");
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

