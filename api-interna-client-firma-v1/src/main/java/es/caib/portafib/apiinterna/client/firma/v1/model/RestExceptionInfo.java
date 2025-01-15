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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Estructura de dades utilitzada per passar informació d&#39;un error
 */
@JsonPropertyOrder({
  RestExceptionInfo.JSON_PROPERTY_ERROR_CODE,
  RestExceptionInfo.JSON_PROPERTY_ERROR_MESSAGE,
  RestExceptionInfo.JSON_PROPERTY_STACK_TRACE,
  RestExceptionInfo.JSON_PROPERTY_STACK_TRACE_CAUSE,
  RestExceptionInfo.JSON_PROPERTY_FIELD
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class RestExceptionInfo {
  public static final String JSON_PROPERTY_ERROR_CODE = "errorCode";
  @javax.annotation.Nullable
  private Integer errorCode;

  public static final String JSON_PROPERTY_ERROR_MESSAGE = "errorMessage";
  @javax.annotation.Nonnull
  private String errorMessage;

  public static final String JSON_PROPERTY_STACK_TRACE = "stackTrace";
  @javax.annotation.Nullable
  private String stackTrace;

  public static final String JSON_PROPERTY_STACK_TRACE_CAUSE = "stackTraceCause";
  @javax.annotation.Nullable
  private String stackTraceCause;

  public static final String JSON_PROPERTY_FIELD = "field";
  @javax.annotation.Nullable
  private String field;

  public RestExceptionInfo() {
  }

  public RestExceptionInfo errorCode(@javax.annotation.Nullable Integer errorCode) {
    
    this.errorCode = errorCode;
    return this;
  }

  /**
   * Codi intern de l&#39;error. Si l&#39;Aplicació no gestiona codis d&#39;error llavors val null.
   * @return errorCode
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ERROR_CODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getErrorCode() {
    return errorCode;
  }


  @JsonProperty(JSON_PROPERTY_ERROR_CODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setErrorCode(@javax.annotation.Nullable Integer errorCode) {
    this.errorCode = errorCode;
  }

  public RestExceptionInfo errorMessage(@javax.annotation.Nonnull String errorMessage) {
    
    this.errorMessage = errorMessage;
    return this;
  }

  /**
   * Missatge de l&#39;error
   * @return errorMessage
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_ERROR_MESSAGE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getErrorMessage() {
    return errorMessage;
  }


  @JsonProperty(JSON_PROPERTY_ERROR_MESSAGE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setErrorMessage(@javax.annotation.Nonnull String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public RestExceptionInfo stackTrace(@javax.annotation.Nullable String stackTrace) {
    
    this.stackTrace = stackTrace;
    return this;
  }

  /**
   * Stacktrace de l&#39;excepció
   * @return stackTrace
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_STACK_TRACE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getStackTrace() {
    return stackTrace;
  }


  @JsonProperty(JSON_PROPERTY_STACK_TRACE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setStackTrace(@javax.annotation.Nullable String stackTrace) {
    this.stackTrace = stackTrace;
  }

  public RestExceptionInfo stackTraceCause(@javax.annotation.Nullable String stackTraceCause) {
    
    this.stackTraceCause = stackTraceCause;
    return this;
  }

  /**
   * Stacktrace de l&#39;excepció causant de l&#39;error si n&#39;hi hagués.
   * @return stackTraceCause
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_STACK_TRACE_CAUSE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getStackTraceCause() {
    return stackTraceCause;
  }


  @JsonProperty(JSON_PROPERTY_STACK_TRACE_CAUSE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setStackTraceCause(@javax.annotation.Nullable String stackTraceCause) {
    this.stackTraceCause = stackTraceCause;
  }

  public RestExceptionInfo field(@javax.annotation.Nullable String field) {
    
    this.field = field;
    return this;
  }

  /**
   * Indica el camp en que hi ha un error de validació.
   * @return field
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_FIELD)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getField() {
    return field;
  }


  @JsonProperty(JSON_PROPERTY_FIELD)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setField(@javax.annotation.Nullable String field) {
    this.field = field;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestExceptionInfo restExceptionInfo = (RestExceptionInfo) o;
    return Objects.equals(this.errorCode, restExceptionInfo.errorCode) &&
        Objects.equals(this.errorMessage, restExceptionInfo.errorMessage) &&
        Objects.equals(this.stackTrace, restExceptionInfo.stackTrace) &&
        Objects.equals(this.stackTraceCause, restExceptionInfo.stackTraceCause) &&
        Objects.equals(this.field, restExceptionInfo.field);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errorCode, errorMessage, stackTrace, stackTraceCause, field);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestExceptionInfo {\n");
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    stackTrace: ").append(toIndentedString(stackTrace)).append("\n");
    sb.append("    stackTraceCause: ").append(toIndentedString(stackTraceCause)).append("\n");
    sb.append("    field: ").append(toIndentedString(field)).append("\n");
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

