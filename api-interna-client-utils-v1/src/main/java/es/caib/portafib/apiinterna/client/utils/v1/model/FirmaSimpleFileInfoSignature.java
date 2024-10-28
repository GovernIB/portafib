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


package es.caib.portafib.apiinterna.client.utils.v1.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import es.caib.portafib.apiinterna.client.utils.v1.model.FirmaSimpleFile;
import es.caib.portafib.apiinterna.client.utils.v1.model.FirmaSimpleKeyValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * FirmaSimpleFileInfoSignature
 */
@JsonPropertyOrder({
  FirmaSimpleFileInfoSignature.JSON_PROPERTY_FILE_TO_SIGN,
  FirmaSimpleFileInfoSignature.JSON_PROPERTY_PREVIUS_SIGNATURE_DETACHED_FILE,
  FirmaSimpleFileInfoSignature.JSON_PROPERTY_SIGN_I_D,
  FirmaSimpleFileInfoSignature.JSON_PROPERTY_NAME,
  FirmaSimpleFileInfoSignature.JSON_PROPERTY_REASON,
  FirmaSimpleFileInfoSignature.JSON_PROPERTY_LOCATION,
  FirmaSimpleFileInfoSignature.JSON_PROPERTY_SIGN_NUMBER,
  FirmaSimpleFileInfoSignature.JSON_PROPERTY_LANGUAGE_SIGN,
  FirmaSimpleFileInfoSignature.JSON_PROPERTY_EXPEDIENT_CODI,
  FirmaSimpleFileInfoSignature.JSON_PROPERTY_EXPEDIENT_NOM,
  FirmaSimpleFileInfoSignature.JSON_PROPERTY_EXPEDIENT_URL,
  FirmaSimpleFileInfoSignature.JSON_PROPERTY_PROCEDIMENT_CODI,
  FirmaSimpleFileInfoSignature.JSON_PROPERTY_PROCEDIMENT_NOM,
  FirmaSimpleFileInfoSignature.JSON_PROPERTY_DOCUMENT_TYPE,
  FirmaSimpleFileInfoSignature.JSON_PROPERTY_ADDITIONAL_INFORMATION
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class FirmaSimpleFileInfoSignature {
  public static final String JSON_PROPERTY_FILE_TO_SIGN = "fileToSign";
  private FirmaSimpleFile fileToSign;

  public static final String JSON_PROPERTY_PREVIUS_SIGNATURE_DETACHED_FILE = "previusSignatureDetachedFile";
  private FirmaSimpleFile previusSignatureDetachedFile;

  public static final String JSON_PROPERTY_SIGN_I_D = "signID";
  private String signID;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_REASON = "reason";
  private String reason;

  public static final String JSON_PROPERTY_LOCATION = "location";
  private String location;

  public static final String JSON_PROPERTY_SIGN_NUMBER = "signNumber";
  private Integer signNumber;

  public static final String JSON_PROPERTY_LANGUAGE_SIGN = "languageSign";
  private String languageSign;

  public static final String JSON_PROPERTY_EXPEDIENT_CODI = "expedientCodi";
  private String expedientCodi;

  public static final String JSON_PROPERTY_EXPEDIENT_NOM = "expedientNom";
  private String expedientNom;

  public static final String JSON_PROPERTY_EXPEDIENT_URL = "expedientUrl";
  private String expedientUrl;

  public static final String JSON_PROPERTY_PROCEDIMENT_CODI = "procedimentCodi";
  private String procedimentCodi;

  public static final String JSON_PROPERTY_PROCEDIMENT_NOM = "procedimentNom";
  private String procedimentNom;

  public static final String JSON_PROPERTY_DOCUMENT_TYPE = "documentType";
  private Long documentType;

  public static final String JSON_PROPERTY_ADDITIONAL_INFORMATION = "additionalInformation";
  private List<FirmaSimpleKeyValue> additionalInformation;

  public FirmaSimpleFileInfoSignature() {
  }

  public FirmaSimpleFileInfoSignature fileToSign(FirmaSimpleFile fileToSign) {
    
    this.fileToSign = fileToSign;
    return this;
  }

   /**
   * Get fileToSign
   * @return fileToSign
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_FILE_TO_SIGN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FirmaSimpleFile getFileToSign() {
    return fileToSign;
  }


  @JsonProperty(JSON_PROPERTY_FILE_TO_SIGN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setFileToSign(FirmaSimpleFile fileToSign) {
    this.fileToSign = fileToSign;
  }


  public FirmaSimpleFileInfoSignature previusSignatureDetachedFile(FirmaSimpleFile previusSignatureDetachedFile) {
    
    this.previusSignatureDetachedFile = previusSignatureDetachedFile;
    return this;
  }

   /**
   * Get previusSignatureDetachedFile
   * @return previusSignatureDetachedFile
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PREVIUS_SIGNATURE_DETACHED_FILE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FirmaSimpleFile getPreviusSignatureDetachedFile() {
    return previusSignatureDetachedFile;
  }


  @JsonProperty(JSON_PROPERTY_PREVIUS_SIGNATURE_DETACHED_FILE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPreviusSignatureDetachedFile(FirmaSimpleFile previusSignatureDetachedFile) {
    this.previusSignatureDetachedFile = previusSignatureDetachedFile;
  }


  public FirmaSimpleFileInfoSignature signID(String signID) {
    
    this.signID = signID;
    return this;
  }

   /**
   * Get signID
   * @return signID
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SIGN_I_D)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSignID() {
    return signID;
  }


  @JsonProperty(JSON_PROPERTY_SIGN_I_D)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSignID(String signID) {
    this.signID = signID;
  }


  public FirmaSimpleFileInfoSignature name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getName() {
    return name;
  }


  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setName(String name) {
    this.name = name;
  }


  public FirmaSimpleFileInfoSignature reason(String reason) {
    
    this.reason = reason;
    return this;
  }

   /**
   * Get reason
   * @return reason
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_REASON)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getReason() {
    return reason;
  }


  @JsonProperty(JSON_PROPERTY_REASON)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setReason(String reason) {
    this.reason = reason;
  }


  public FirmaSimpleFileInfoSignature location(String location) {
    
    this.location = location;
    return this;
  }

   /**
   * Get location
   * @return location
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LOCATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getLocation() {
    return location;
  }


  @JsonProperty(JSON_PROPERTY_LOCATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setLocation(String location) {
    this.location = location;
  }


  public FirmaSimpleFileInfoSignature signNumber(Integer signNumber) {
    
    this.signNumber = signNumber;
    return this;
  }

   /**
   * Get signNumber
   * @return signNumber
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SIGN_NUMBER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getSignNumber() {
    return signNumber;
  }


  @JsonProperty(JSON_PROPERTY_SIGN_NUMBER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSignNumber(Integer signNumber) {
    this.signNumber = signNumber;
  }


  public FirmaSimpleFileInfoSignature languageSign(String languageSign) {
    
    this.languageSign = languageSign;
    return this;
  }

   /**
   * Get languageSign
   * @return languageSign
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LANGUAGE_SIGN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getLanguageSign() {
    return languageSign;
  }


  @JsonProperty(JSON_PROPERTY_LANGUAGE_SIGN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setLanguageSign(String languageSign) {
    this.languageSign = languageSign;
  }


  public FirmaSimpleFileInfoSignature expedientCodi(String expedientCodi) {
    
    this.expedientCodi = expedientCodi;
    return this;
  }

   /**
   * Get expedientCodi
   * @return expedientCodi
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_EXPEDIENT_CODI)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getExpedientCodi() {
    return expedientCodi;
  }


  @JsonProperty(JSON_PROPERTY_EXPEDIENT_CODI)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setExpedientCodi(String expedientCodi) {
    this.expedientCodi = expedientCodi;
  }


  public FirmaSimpleFileInfoSignature expedientNom(String expedientNom) {
    
    this.expedientNom = expedientNom;
    return this;
  }

   /**
   * Get expedientNom
   * @return expedientNom
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_EXPEDIENT_NOM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getExpedientNom() {
    return expedientNom;
  }


  @JsonProperty(JSON_PROPERTY_EXPEDIENT_NOM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setExpedientNom(String expedientNom) {
    this.expedientNom = expedientNom;
  }


  public FirmaSimpleFileInfoSignature expedientUrl(String expedientUrl) {
    
    this.expedientUrl = expedientUrl;
    return this;
  }

   /**
   * Get expedientUrl
   * @return expedientUrl
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_EXPEDIENT_URL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getExpedientUrl() {
    return expedientUrl;
  }


  @JsonProperty(JSON_PROPERTY_EXPEDIENT_URL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setExpedientUrl(String expedientUrl) {
    this.expedientUrl = expedientUrl;
  }


  public FirmaSimpleFileInfoSignature procedimentCodi(String procedimentCodi) {
    
    this.procedimentCodi = procedimentCodi;
    return this;
  }

   /**
   * Get procedimentCodi
   * @return procedimentCodi
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PROCEDIMENT_CODI)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getProcedimentCodi() {
    return procedimentCodi;
  }


  @JsonProperty(JSON_PROPERTY_PROCEDIMENT_CODI)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setProcedimentCodi(String procedimentCodi) {
    this.procedimentCodi = procedimentCodi;
  }


  public FirmaSimpleFileInfoSignature procedimentNom(String procedimentNom) {
    
    this.procedimentNom = procedimentNom;
    return this;
  }

   /**
   * Get procedimentNom
   * @return procedimentNom
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PROCEDIMENT_NOM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getProcedimentNom() {
    return procedimentNom;
  }


  @JsonProperty(JSON_PROPERTY_PROCEDIMENT_NOM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setProcedimentNom(String procedimentNom) {
    this.procedimentNom = procedimentNom;
  }


  public FirmaSimpleFileInfoSignature documentType(Long documentType) {
    
    this.documentType = documentType;
    return this;
  }

   /**
   * Get documentType
   * @return documentType
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DOCUMENT_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Long getDocumentType() {
    return documentType;
  }


  @JsonProperty(JSON_PROPERTY_DOCUMENT_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDocumentType(Long documentType) {
    this.documentType = documentType;
  }


  public FirmaSimpleFileInfoSignature additionalInformation(List<FirmaSimpleKeyValue> additionalInformation) {
    
    this.additionalInformation = additionalInformation;
    return this;
  }

  public FirmaSimpleFileInfoSignature addAdditionalInformationItem(FirmaSimpleKeyValue additionalInformationItem) {
    if (this.additionalInformation == null) {
      this.additionalInformation = new ArrayList<>();
    }
    this.additionalInformation.add(additionalInformationItem);
    return this;
  }

   /**
   * Get additionalInformation
   * @return additionalInformation
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ADDITIONAL_INFORMATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<FirmaSimpleKeyValue> getAdditionalInformation() {
    return additionalInformation;
  }


  @JsonProperty(JSON_PROPERTY_ADDITIONAL_INFORMATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setAdditionalInformation(List<FirmaSimpleKeyValue> additionalInformation) {
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
    FirmaSimpleFileInfoSignature firmaSimpleFileInfoSignature = (FirmaSimpleFileInfoSignature) o;
    return Objects.equals(this.fileToSign, firmaSimpleFileInfoSignature.fileToSign) &&
        Objects.equals(this.previusSignatureDetachedFile, firmaSimpleFileInfoSignature.previusSignatureDetachedFile) &&
        Objects.equals(this.signID, firmaSimpleFileInfoSignature.signID) &&
        Objects.equals(this.name, firmaSimpleFileInfoSignature.name) &&
        Objects.equals(this.reason, firmaSimpleFileInfoSignature.reason) &&
        Objects.equals(this.location, firmaSimpleFileInfoSignature.location) &&
        Objects.equals(this.signNumber, firmaSimpleFileInfoSignature.signNumber) &&
        Objects.equals(this.languageSign, firmaSimpleFileInfoSignature.languageSign) &&
        Objects.equals(this.expedientCodi, firmaSimpleFileInfoSignature.expedientCodi) &&
        Objects.equals(this.expedientNom, firmaSimpleFileInfoSignature.expedientNom) &&
        Objects.equals(this.expedientUrl, firmaSimpleFileInfoSignature.expedientUrl) &&
        Objects.equals(this.procedimentCodi, firmaSimpleFileInfoSignature.procedimentCodi) &&
        Objects.equals(this.procedimentNom, firmaSimpleFileInfoSignature.procedimentNom) &&
        Objects.equals(this.documentType, firmaSimpleFileInfoSignature.documentType) &&
        Objects.equals(this.additionalInformation, firmaSimpleFileInfoSignature.additionalInformation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileToSign, previusSignatureDetachedFile, signID, name, reason, location, signNumber, languageSign, expedientCodi, expedientNom, expedientUrl, procedimentCodi, procedimentNom, documentType, additionalInformation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FirmaSimpleFileInfoSignature {\n");
    sb.append("    fileToSign: ").append(toIndentedString(fileToSign)).append("\n");
    sb.append("    previusSignatureDetachedFile: ").append(toIndentedString(previusSignatureDetachedFile)).append("\n");
    sb.append("    signID: ").append(toIndentedString(signID)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    signNumber: ").append(toIndentedString(signNumber)).append("\n");
    sb.append("    languageSign: ").append(toIndentedString(languageSign)).append("\n");
    sb.append("    expedientCodi: ").append(toIndentedString(expedientCodi)).append("\n");
    sb.append("    expedientNom: ").append(toIndentedString(expedientNom)).append("\n");
    sb.append("    expedientUrl: ").append(toIndentedString(expedientUrl)).append("\n");
    sb.append("    procedimentCodi: ").append(toIndentedString(procedimentCodi)).append("\n");
    sb.append("    procedimentNom: ").append(toIndentedString(procedimentNom)).append("\n");
    sb.append("    documentType: ").append(toIndentedString(documentType)).append("\n");
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

