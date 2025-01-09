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
import es.caib.portafib.apiinterna.client.firma.v1.model.KeyValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Informació del signant
 */
@JsonPropertyOrder({
  FirmaSimpleSignerInfo.JSON_PROPERTY_ENI_ROL_FIRMA,
  FirmaSimpleSignerInfo.JSON_PROPERTY_ENI_SIGNER_NAME,
  FirmaSimpleSignerInfo.JSON_PROPERTY_ENI_SIGNER_ADMINISTRATION_ID,
  FirmaSimpleSignerInfo.JSON_PROPERTY_ENI_SIGN_LEVEL,
  FirmaSimpleSignerInfo.JSON_PROPERTY_SIGN_DATE,
  FirmaSimpleSignerInfo.JSON_PROPERTY_SERIAL_NUMBER_CERT,
  FirmaSimpleSignerInfo.JSON_PROPERTY_ISSUER_CERT,
  FirmaSimpleSignerInfo.JSON_PROPERTY_SUBJECT_CERT,
  FirmaSimpleSignerInfo.JSON_PROPERTY_ADDITIONAL_INFORMATION
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class FirmaSimpleSignerInfo {
  public static final String JSON_PROPERTY_ENI_ROL_FIRMA = "eniRolFirma";
  @javax.annotation.Nullable
  private String eniRolFirma;

  public static final String JSON_PROPERTY_ENI_SIGNER_NAME = "eniSignerName";
  @javax.annotation.Nullable
  private String eniSignerName;

  public static final String JSON_PROPERTY_ENI_SIGNER_ADMINISTRATION_ID = "eniSignerAdministrationId";
  @javax.annotation.Nullable
  private String eniSignerAdministrationId;

  public static final String JSON_PROPERTY_ENI_SIGN_LEVEL = "eniSignLevel";
  @javax.annotation.Nullable
  private String eniSignLevel;

  public static final String JSON_PROPERTY_SIGN_DATE = "signDate";
  @javax.annotation.Nullable
  private Date signDate;

  public static final String JSON_PROPERTY_SERIAL_NUMBER_CERT = "serialNumberCert";
  @javax.annotation.Nullable
  private String serialNumberCert;

  public static final String JSON_PROPERTY_ISSUER_CERT = "issuerCert";
  @javax.annotation.Nullable
  private String issuerCert;

  public static final String JSON_PROPERTY_SUBJECT_CERT = "subjectCert";
  @javax.annotation.Nullable
  private String subjectCert;

  public static final String JSON_PROPERTY_ADDITIONAL_INFORMATION = "additionalInformation";
  @javax.annotation.Nullable
  private List<KeyValue> additionalInformation = new ArrayList<>();

  public FirmaSimpleSignerInfo() {
  }

  public FirmaSimpleSignerInfo eniRolFirma(@javax.annotation.Nullable String eniRolFirma) {
    
    this.eniRolFirma = eniRolFirma;
    return this;
  }

  /**
   * Esquemas desarrollados a nivel local y que pueden incluir valores como válida, autentica, refrenda, visa, representa, testimonia, etc..
   * @return eniRolFirma
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ENI_ROL_FIRMA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getEniRolFirma() {
    return eniRolFirma;
  }


  @JsonProperty(JSON_PROPERTY_ENI_ROL_FIRMA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEniRolFirma(@javax.annotation.Nullable String eniRolFirma) {
    this.eniRolFirma = eniRolFirma;
  }

  public FirmaSimpleSignerInfo eniSignerName(@javax.annotation.Nullable String eniSignerName) {
    
    this.eniSignerName = eniSignerName;
    return this;
  }

  /**
   * Nombre o razón social de los firmantes.
   * @return eniSignerName
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ENI_SIGNER_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getEniSignerName() {
    return eniSignerName;
  }


  @JsonProperty(JSON_PROPERTY_ENI_SIGNER_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEniSignerName(@javax.annotation.Nullable String eniSignerName) {
    this.eniSignerName = eniSignerName;
  }

  public FirmaSimpleSignerInfo eniSignerAdministrationId(@javax.annotation.Nullable String eniSignerAdministrationId) {
    
    this.eniSignerAdministrationId = eniSignerAdministrationId;
    return this;
  }

  /**
   * NIF del firmant.
   * @return eniSignerAdministrationId
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ENI_SIGNER_ADMINISTRATION_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getEniSignerAdministrationId() {
    return eniSignerAdministrationId;
  }


  @JsonProperty(JSON_PROPERTY_ENI_SIGNER_ADMINISTRATION_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEniSignerAdministrationId(@javax.annotation.Nullable String eniSignerAdministrationId) {
    this.eniSignerAdministrationId = eniSignerAdministrationId;
  }

  public FirmaSimpleSignerInfo eniSignLevel(@javax.annotation.Nullable String eniSignLevel) {
    
    this.eniSignLevel = eniSignLevel;
    return this;
  }

  /**
   * Indicador normalizado que refleja el grado de  confianza de la firma utilizado. Ejemplos: Nick, PIN ciudadano, Firma electrónica avanzada, Claves concertadas, Firma electrónica avanzada basada en certificados, CSV, ..
   * @return eniSignLevel
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ENI_SIGN_LEVEL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getEniSignLevel() {
    return eniSignLevel;
  }


  @JsonProperty(JSON_PROPERTY_ENI_SIGN_LEVEL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEniSignLevel(@javax.annotation.Nullable String eniSignLevel) {
    this.eniSignLevel = eniSignLevel;
  }

  public FirmaSimpleSignerInfo signDate(@javax.annotation.Nullable Date signDate) {
    
    this.signDate = signDate;
    return this;
  }

  /**
   * Data en que es va realitzar la firma
   * @return signDate
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SIGN_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Date getSignDate() {
    return signDate;
  }


  @JsonProperty(JSON_PROPERTY_SIGN_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSignDate(@javax.annotation.Nullable Date signDate) {
    this.signDate = signDate;
  }

  public FirmaSimpleSignerInfo serialNumberCert(@javax.annotation.Nullable String serialNumberCert) {
    
    this.serialNumberCert = serialNumberCert;
    return this;
  }

  /**
   * Número de Sèrie del Certificat utilitzat en la firma
   * @return serialNumberCert
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SERIAL_NUMBER_CERT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSerialNumberCert() {
    return serialNumberCert;
  }


  @JsonProperty(JSON_PROPERTY_SERIAL_NUMBER_CERT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSerialNumberCert(@javax.annotation.Nullable String serialNumberCert) {
    this.serialNumberCert = serialNumberCert;
  }

  public FirmaSimpleSignerInfo issuerCert(@javax.annotation.Nullable String issuerCert) {
    
    this.issuerCert = issuerCert;
    return this;
  }

  /**
   * Issuer del Certificat utilitzat en la firma
   * @return issuerCert
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ISSUER_CERT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getIssuerCert() {
    return issuerCert;
  }


  @JsonProperty(JSON_PROPERTY_ISSUER_CERT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setIssuerCert(@javax.annotation.Nullable String issuerCert) {
    this.issuerCert = issuerCert;
  }

  public FirmaSimpleSignerInfo subjectCert(@javax.annotation.Nullable String subjectCert) {
    
    this.subjectCert = subjectCert;
    return this;
  }

  /**
   * Subject del Certificat utilitzat en la firma
   * @return subjectCert
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SUBJECT_CERT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSubjectCert() {
    return subjectCert;
  }


  @JsonProperty(JSON_PROPERTY_SUBJECT_CERT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSubjectCert(@javax.annotation.Nullable String subjectCert) {
    this.subjectCert = subjectCert;
  }

  public FirmaSimpleSignerInfo additionalInformation(@javax.annotation.Nullable List<KeyValue> additionalInformation) {
    
    this.additionalInformation = additionalInformation;
    return this;
  }

  public FirmaSimpleSignerInfo addAdditionalInformationItem(KeyValue additionalInformationItem) {
    if (this.additionalInformation == null) {
      this.additionalInformation = new ArrayList<>();
    }
    this.additionalInformation.add(additionalInformationItem);
    return this;
  }

  /**
   * Ofrecer cualquier otra información que se  considere útil acerca del firmante.
   * @return additionalInformation
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ADDITIONAL_INFORMATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<KeyValue> getAdditionalInformation() {
    return additionalInformation;
  }


  @JsonProperty(JSON_PROPERTY_ADDITIONAL_INFORMATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setAdditionalInformation(@javax.annotation.Nullable List<KeyValue> additionalInformation) {
    this.additionalInformation = additionalInformation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FirmaSimpleSignerInfo firmaSimpleSignerInfo = (FirmaSimpleSignerInfo) o;
    return Objects.equals(this.eniRolFirma, firmaSimpleSignerInfo.eniRolFirma) &&
        Objects.equals(this.eniSignerName, firmaSimpleSignerInfo.eniSignerName) &&
        Objects.equals(this.eniSignerAdministrationId, firmaSimpleSignerInfo.eniSignerAdministrationId) &&
        Objects.equals(this.eniSignLevel, firmaSimpleSignerInfo.eniSignLevel) &&
        Objects.equals(this.signDate, firmaSimpleSignerInfo.signDate) &&
        Objects.equals(this.serialNumberCert, firmaSimpleSignerInfo.serialNumberCert) &&
        Objects.equals(this.issuerCert, firmaSimpleSignerInfo.issuerCert) &&
        Objects.equals(this.subjectCert, firmaSimpleSignerInfo.subjectCert) &&
        Objects.equals(this.additionalInformation, firmaSimpleSignerInfo.additionalInformation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eniRolFirma, eniSignerName, eniSignerAdministrationId, eniSignLevel, signDate, serialNumberCert, issuerCert, subjectCert, additionalInformation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FirmaSimpleSignerInfo {\n");
    sb.append("    eniRolFirma: ").append(toIndentedString(eniRolFirma)).append("\n");
    sb.append("    eniSignerName: ").append(toIndentedString(eniSignerName)).append("\n");
    sb.append("    eniSignerAdministrationId: ").append(toIndentedString(eniSignerAdministrationId)).append("\n");
    sb.append("    eniSignLevel: ").append(toIndentedString(eniSignLevel)).append("\n");
    sb.append("    signDate: ").append(toIndentedString(signDate)).append("\n");
    sb.append("    serialNumberCert: ").append(toIndentedString(serialNumberCert)).append("\n");
    sb.append("    issuerCert: ").append(toIndentedString(issuerCert)).append("\n");
    sb.append("    subjectCert: ").append(toIndentedString(subjectCert)).append("\n");
    sb.append("    additionalInformation: ").append(toIndentedString(additionalInformation)).append("\n");
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

