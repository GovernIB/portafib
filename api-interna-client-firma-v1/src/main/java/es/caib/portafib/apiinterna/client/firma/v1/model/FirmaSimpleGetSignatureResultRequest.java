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
 * FirmaSimpleGetSignatureResultRequest
 */
@JsonPropertyOrder({
  FirmaSimpleGetSignatureResultRequest.JSON_PROPERTY_TRANSACTION_I_D,
  FirmaSimpleGetSignatureResultRequest.JSON_PROPERTY_SIGN_I_D
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class FirmaSimpleGetSignatureResultRequest {
  public static final String JSON_PROPERTY_TRANSACTION_I_D = "transactionID";
  @javax.annotation.Nonnull
  private String transactionID;

  public static final String JSON_PROPERTY_SIGN_I_D = "signID";
  @javax.annotation.Nonnull
  private String signID;

  public FirmaSimpleGetSignatureResultRequest() {
  }

  public FirmaSimpleGetSignatureResultRequest transactionID(@javax.annotation.Nonnull String transactionID) {
    
    this.transactionID = transactionID;
    return this;
  }

  /**
   * Identificador de la transaccio
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

  public FirmaSimpleGetSignatureResultRequest signID(@javax.annotation.Nonnull String signID) {
    
    this.signID = signID;
    return this;
  }

  /**
   * Identificador de la firma
   * @return signID
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_SIGN_I_D)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getSignID() {
    return signID;
  }


  @JsonProperty(JSON_PROPERTY_SIGN_I_D)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setSignID(@javax.annotation.Nonnull String signID) {
    this.signID = signID;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FirmaSimpleGetSignatureResultRequest firmaSimpleGetSignatureResultRequest = (FirmaSimpleGetSignatureResultRequest) o;
    return Objects.equals(this.transactionID, firmaSimpleGetSignatureResultRequest.transactionID) &&
        Objects.equals(this.signID, firmaSimpleGetSignatureResultRequest.signID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionID, signID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FirmaSimpleGetSignatureResultRequest {\n");
    sb.append("    transactionID: ").append(toIndentedString(transactionID)).append("\n");
    sb.append("    signID: ").append(toIndentedString(signID)).append("\n");
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

