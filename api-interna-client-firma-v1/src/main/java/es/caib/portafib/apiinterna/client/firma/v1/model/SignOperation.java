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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Operacions disponibles
 */
public enum SignOperation {
  
  SIGN_OPERATION_SIGN(0),
  
  SIGN_OPERATION_COSIGN(1),
  
  SIGN_OPERATION_COUNTERSIGN(2);

  private Integer value;

  SignOperation(Integer value) {
    this.value = value;
  }

  @JsonValue
  public Integer getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static SignOperation fromValue(Integer value) {
    for (SignOperation b : SignOperation.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

