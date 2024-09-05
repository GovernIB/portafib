/*
 * API Interna de PortaFIB de consulta de tipus documentals de PortaFIB
 * Conjunt de Serveis REST de PortaFIB per atendre consultes de tipus documentals.
 *
 * The version of the OpenAPI document: 1.0-SNAPSHOT
 * Contact: otae@fundaciobit.org
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package es.caib.portafib.apiinterna.client.tipusdocumentals.v1.model;

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
  RestExceptionInfo.JSON_PROPERTY_CODE,
  RestExceptionInfo.JSON_PROPERTY_ERROR_MESSAGE,
  RestExceptionInfo.JSON_PROPERTY_STACK_TRACE,
  RestExceptionInfo.JSON_PROPERTY_CAUSE_EXCEPTION,
  RestExceptionInfo.JSON_PROPERTY_CAUSE_STACK_TRACE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class RestExceptionInfo {
  public static final String JSON_PROPERTY_CODE = "code";
  private Integer code;

  public static final String JSON_PROPERTY_ERROR_MESSAGE = "errorMessage";
  private String errorMessage;

  public static final String JSON_PROPERTY_STACK_TRACE = "stackTrace";
  private String stackTrace;

  public static final String JSON_PROPERTY_CAUSE_EXCEPTION = "causeException";
  private String causeException;

  public static final String JSON_PROPERTY_CAUSE_STACK_TRACE = "causeStackTrace";
  private String causeStackTrace;

  public RestExceptionInfo() {
  }

  public RestExceptionInfo code(Integer code) {
    
    this.code = code;
    return this;
  }

   /**
   * Codi de HTTP de l&#39;error. Veure https://en.wikipedia.org/wiki/List_of_HTTP_status_codes.
   * @return code
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_CODE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getCode() {
    return code;
  }


  @JsonProperty(JSON_PROPERTY_CODE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCode(Integer code) {
    this.code = code;
  }


  public RestExceptionInfo errorMessage(String errorMessage) {
    
    this.errorMessage = errorMessage;
    return this;
  }

   /**
   * Missatge de l&#39;error
   * @return errorMessage
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_ERROR_MESSAGE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getErrorMessage() {
    return errorMessage;
  }


  @JsonProperty(JSON_PROPERTY_ERROR_MESSAGE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }


  public RestExceptionInfo stackTrace(String stackTrace) {
    
    this.stackTrace = stackTrace;
    return this;
  }

   /**
   * Stacktrace de l&#39;excepció si n&#39;hi hagués.
   * @return stackTrace
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_STACK_TRACE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getStackTrace() {
    return stackTrace;
  }


  @JsonProperty(JSON_PROPERTY_STACK_TRACE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setStackTrace(String stackTrace) {
    this.stackTrace = stackTrace;
  }


  public RestExceptionInfo causeException(String causeException) {
    
    this.causeException = causeException;
    return this;
  }

   /**
   * Tipus de l&#39;excepció origen (cause) si n&#39;hi hagués.
   * @return causeException
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CAUSE_EXCEPTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCauseException() {
    return causeException;
  }


  @JsonProperty(JSON_PROPERTY_CAUSE_EXCEPTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCauseException(String causeException) {
    this.causeException = causeException;
  }


  public RestExceptionInfo causeStackTrace(String causeStackTrace) {
    
    this.causeStackTrace = causeStackTrace;
    return this;
  }

   /**
   * Stacktrace de l&#39;excepció origen (cause) si n&#39;hi hagués.
   * @return causeStackTrace
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CAUSE_STACK_TRACE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCauseStackTrace() {
    return causeStackTrace;
  }


  @JsonProperty(JSON_PROPERTY_CAUSE_STACK_TRACE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCauseStackTrace(String causeStackTrace) {
    this.causeStackTrace = causeStackTrace;
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
    return Objects.equals(this.code, restExceptionInfo.code) &&
        Objects.equals(this.errorMessage, restExceptionInfo.errorMessage) &&
        Objects.equals(this.stackTrace, restExceptionInfo.stackTrace) &&
        Objects.equals(this.causeException, restExceptionInfo.causeException) &&
        Objects.equals(this.causeStackTrace, restExceptionInfo.causeStackTrace);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, errorMessage, stackTrace, causeException, causeStackTrace);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestExceptionInfo {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    stackTrace: ").append(toIndentedString(stackTrace)).append("\n");
    sb.append("    causeException: ").append(toIndentedString(causeException)).append("\n");
    sb.append("    causeStackTrace: ").append(toIndentedString(causeStackTrace)).append("\n");
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

