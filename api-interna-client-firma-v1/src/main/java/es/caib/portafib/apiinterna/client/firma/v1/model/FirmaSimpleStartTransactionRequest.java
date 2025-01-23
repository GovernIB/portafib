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
 * FirmaSimpleStartTransactionRequest
 */
@JsonPropertyOrder({
  FirmaSimpleStartTransactionRequest.JSON_PROPERTY_TRANSACTION_I_D,
  FirmaSimpleStartTransactionRequest.JSON_PROPERTY_RETURN_URL,
  FirmaSimpleStartTransactionRequest.JSON_PROPERTY_VIEW,
  FirmaSimpleStartTransactionRequest.JSON_PROPERTY_LANGUAGE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class FirmaSimpleStartTransactionRequest {
  public static final String JSON_PROPERTY_TRANSACTION_I_D = "transactionID";
  @javax.annotation.Nonnull
  private String transactionID;

  public static final String JSON_PROPERTY_RETURN_URL = "returnUrl";
  @javax.annotation.Nonnull
  private String returnUrl;

  public static final String JSON_PROPERTY_VIEW = "view";
  @javax.annotation.Nonnull
  private String view;

  public static final String JSON_PROPERTY_LANGUAGE = "language";
  @javax.annotation.Nonnull
  private String language;

  public FirmaSimpleStartTransactionRequest() {
  }

  public FirmaSimpleStartTransactionRequest transactionID(@javax.annotation.Nonnull String transactionID) {
    
    this.transactionID = transactionID;
    return this;
  }

  /**
   * Identificador de transacció
   * @return transactionID
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_TRANSACTION_I_D)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getTransactionID() {
    return transactionID;
  }


  @JsonProperty(JSON_PROPERTY_TRANSACTION_I_D)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTransactionID(@javax.annotation.Nonnull String transactionID) {
    this.transactionID = transactionID;
  }

  public FirmaSimpleStartTransactionRequest returnUrl(@javax.annotation.Nonnull String returnUrl) {
    
    this.returnUrl = returnUrl;
    return this;
  }

  /**
   * Adreça web on retornar una vegada finalitzat tot el procés de firma.
   * @return returnUrl
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_RETURN_URL)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getReturnUrl() {
    return returnUrl;
  }


  @JsonProperty(JSON_PROPERTY_RETURN_URL)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setReturnUrl(@javax.annotation.Nonnull String returnUrl) {
    this.returnUrl = returnUrl;
  }

  public FirmaSimpleStartTransactionRequest view(@javax.annotation.Nonnull String view) {
    
    this.view = view;
    return this;
  }

  /**
   * Indica si la presentació de la firma es farà a pantalla completa o dins d&#39;un iframe:      • \&quot;fullview\&quot; (Constant VIEW_FULLSCREEN)      • \&quot;iframe\&quot; (Constant VIEW_IFRAME)
   * @return view
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_VIEW)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getView() {
    return view;
  }


  @JsonProperty(JSON_PROPERTY_VIEW)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setView(@javax.annotation.Nonnull String view) {
    this.view = view;
  }

  public FirmaSimpleStartTransactionRequest language(@javax.annotation.Nonnull String language) {
    
    this.language = language;
    return this;
  }

  /**
   * Idioma seleccionat
   * @return language
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_LANGUAGE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getLanguage() {
    return language;
  }


  @JsonProperty(JSON_PROPERTY_LANGUAGE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setLanguage(@javax.annotation.Nonnull String language) {
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
    FirmaSimpleStartTransactionRequest firmaSimpleStartTransactionRequest = (FirmaSimpleStartTransactionRequest) o;
    return Objects.equals(this.transactionID, firmaSimpleStartTransactionRequest.transactionID) &&
        Objects.equals(this.returnUrl, firmaSimpleStartTransactionRequest.returnUrl) &&
        Objects.equals(this.view, firmaSimpleStartTransactionRequest.view) &&
        Objects.equals(this.language, firmaSimpleStartTransactionRequest.language);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionID, returnUrl, view, language);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FirmaSimpleStartTransactionRequest {\n");
    sb.append("    transactionID: ").append(toIndentedString(transactionID)).append("\n");
    sb.append("    returnUrl: ").append(toIndentedString(returnUrl)).append("\n");
    sb.append("    view: ").append(toIndentedString(view)).append("\n");
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

