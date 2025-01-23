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
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleValidationInfo;
import es.caib.portafib.apiinterna.client.firma.v1.model.KeyValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Informació de la firma actualitzada
 */
@JsonPropertyOrder({
  FirmaSimpleUpgradedFileInfo.JSON_PROPERTY_SIGN_TYPE,
  FirmaSimpleUpgradedFileInfo.JSON_PROPERTY_SIGN_ALGORITHM,
  FirmaSimpleUpgradedFileInfo.JSON_PROPERTY_SIGN_MODE,
  FirmaSimpleUpgradedFileInfo.JSON_PROPERTY_ENI_TIPO_FIRMA,
  FirmaSimpleUpgradedFileInfo.JSON_PROPERTY_ENI_PERFIL_FIRMA,
  FirmaSimpleUpgradedFileInfo.JSON_PROPERTY_VALIDATION_INFO,
  FirmaSimpleUpgradedFileInfo.JSON_PROPERTY_ADDITION_INFORMATION
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class FirmaSimpleUpgradedFileInfo {
  public static final String JSON_PROPERTY_SIGN_TYPE = "signType";
  @javax.annotation.Nonnull
  private String signType;

  public static final String JSON_PROPERTY_SIGN_ALGORITHM = "signAlgorithm";
  @javax.annotation.Nonnull
  private String signAlgorithm;

  public static final String JSON_PROPERTY_SIGN_MODE = "signMode";
  @javax.annotation.Nonnull
  private Integer signMode;

  public static final String JSON_PROPERTY_ENI_TIPO_FIRMA = "eniTipoFirma";
  @javax.annotation.Nullable
  private String eniTipoFirma;

  public static final String JSON_PROPERTY_ENI_PERFIL_FIRMA = "eniPerfilFirma";
  @javax.annotation.Nullable
  private String eniPerfilFirma;

  public static final String JSON_PROPERTY_VALIDATION_INFO = "validationInfo";
  @javax.annotation.Nullable
  private FirmaSimpleValidationInfo validationInfo;

  public static final String JSON_PROPERTY_ADDITION_INFORMATION = "additionInformation";
  @javax.annotation.Nullable
  private List<KeyValue> additionInformation = new ArrayList<>();

  public FirmaSimpleUpgradedFileInfo() {
  }

  public FirmaSimpleUpgradedFileInfo signType(@javax.annotation.Nonnull String signType) {
    
    this.signType = signType;
    return this;
  }

  /**
   * Tipus de Firma. Valors possibles:      • “PAdES” (Constant SIGN_TYPE_PADES)      • “XAdES” (Constant SIGN_TYPE_XADES)      • “CAdES” (Constant SIGN_TYPE_CADES)      • “FacturaE” (Constant SIGN_TYPE_FACTURAE)      • “OOXML” (Constant SIGN_TYPE_OOXML)      • “ODF” (Constant SIGN_TYPE_ODF)      • “SMIME” (Constant SIGN_TYPE_SMIME)      • “CAdES-ASiC-S” (Constant SIGN_TYPE_CADES_ASIC_S)      • “XAdES-ASiC-S” (Constant SIGN_TYPE_XADES_ASIC_S)      • “PKCS#1” (Constant SIGN_TYPE_PKCS1)
   * @return signType
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_SIGN_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getSignType() {
    return signType;
  }


  @JsonProperty(JSON_PROPERTY_SIGN_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setSignType(@javax.annotation.Nonnull String signType) {
    this.signType = signType;
  }

  public FirmaSimpleUpgradedFileInfo signAlgorithm(@javax.annotation.Nonnull String signAlgorithm) {
    
    this.signAlgorithm = signAlgorithm;
    return this;
  }

  /**
   * Algorisme de Firma. Valors:      • \&quot;SHA-1\&quot;      • \&quot;SHA-256\&quot;      • \&quot;SHA-384\&quot;      • \&quot;SHA-512\&quot;
   * @return signAlgorithm
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_SIGN_ALGORITHM)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getSignAlgorithm() {
    return signAlgorithm;
  }


  @JsonProperty(JSON_PROPERTY_SIGN_ALGORITHM)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setSignAlgorithm(@javax.annotation.Nonnull String signAlgorithm) {
    this.signAlgorithm = signAlgorithm;
  }

  public FirmaSimpleUpgradedFileInfo signMode(@javax.annotation.Nonnull Integer signMode) {
    
    this.signMode = signMode;
    return this;
  }

  /**
   * Mode de firma attached (0) o detached (1)
   * @return signMode
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_SIGN_MODE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getSignMode() {
    return signMode;
  }


  @JsonProperty(JSON_PROPERTY_SIGN_MODE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setSignMode(@javax.annotation.Nonnull Integer signMode) {
    this.signMode = signMode;
  }

  public FirmaSimpleUpgradedFileInfo eniTipoFirma(@javax.annotation.Nullable String eniTipoFirma) {
    
    this.eniTipoFirma = eniTipoFirma;
    return this;
  }

  /**
   * Get eniTipoFirma
   * @return eniTipoFirma
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ENI_TIPO_FIRMA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getEniTipoFirma() {
    return eniTipoFirma;
  }


  @JsonProperty(JSON_PROPERTY_ENI_TIPO_FIRMA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEniTipoFirma(@javax.annotation.Nullable String eniTipoFirma) {
    this.eniTipoFirma = eniTipoFirma;
  }

  public FirmaSimpleUpgradedFileInfo eniPerfilFirma(@javax.annotation.Nullable String eniPerfilFirma) {
    
    this.eniPerfilFirma = eniPerfilFirma;
    return this;
  }

  /**
   * Get eniPerfilFirma
   * @return eniPerfilFirma
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ENI_PERFIL_FIRMA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getEniPerfilFirma() {
    return eniPerfilFirma;
  }


  @JsonProperty(JSON_PROPERTY_ENI_PERFIL_FIRMA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEniPerfilFirma(@javax.annotation.Nullable String eniPerfilFirma) {
    this.eniPerfilFirma = eniPerfilFirma;
  }

  public FirmaSimpleUpgradedFileInfo validationInfo(@javax.annotation.Nullable FirmaSimpleValidationInfo validationInfo) {
    
    this.validationInfo = validationInfo;
    return this;
  }

  /**
   * Get validationInfo
   * @return validationInfo
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_VALIDATION_INFO)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FirmaSimpleValidationInfo getValidationInfo() {
    return validationInfo;
  }


  @JsonProperty(JSON_PROPERTY_VALIDATION_INFO)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setValidationInfo(@javax.annotation.Nullable FirmaSimpleValidationInfo validationInfo) {
    this.validationInfo = validationInfo;
  }

  public FirmaSimpleUpgradedFileInfo additionInformation(@javax.annotation.Nullable List<KeyValue> additionInformation) {
    
    this.additionInformation = additionInformation;
    return this;
  }

  public FirmaSimpleUpgradedFileInfo addAdditionInformationItem(KeyValue additionInformationItem) {
    if (this.additionInformation == null) {
      this.additionInformation = new ArrayList<>();
    }
    this.additionInformation.add(additionInformationItem);
    return this;
  }

  /**
   * Get additionInformation
   * @return additionInformation
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ADDITION_INFORMATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<KeyValue> getAdditionInformation() {
    return additionInformation;
  }


  @JsonProperty(JSON_PROPERTY_ADDITION_INFORMATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setAdditionInformation(@javax.annotation.Nullable List<KeyValue> additionInformation) {
    this.additionInformation = additionInformation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FirmaSimpleUpgradedFileInfo firmaSimpleUpgradedFileInfo = (FirmaSimpleUpgradedFileInfo) o;
    return Objects.equals(this.signType, firmaSimpleUpgradedFileInfo.signType) &&
        Objects.equals(this.signAlgorithm, firmaSimpleUpgradedFileInfo.signAlgorithm) &&
        Objects.equals(this.signMode, firmaSimpleUpgradedFileInfo.signMode) &&
        Objects.equals(this.eniTipoFirma, firmaSimpleUpgradedFileInfo.eniTipoFirma) &&
        Objects.equals(this.eniPerfilFirma, firmaSimpleUpgradedFileInfo.eniPerfilFirma) &&
        Objects.equals(this.validationInfo, firmaSimpleUpgradedFileInfo.validationInfo) &&
        Objects.equals(this.additionInformation, firmaSimpleUpgradedFileInfo.additionInformation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(signType, signAlgorithm, signMode, eniTipoFirma, eniPerfilFirma, validationInfo, additionInformation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FirmaSimpleUpgradedFileInfo {\n");
    sb.append("    signType: ").append(toIndentedString(signType)).append("\n");
    sb.append("    signAlgorithm: ").append(toIndentedString(signAlgorithm)).append("\n");
    sb.append("    signMode: ").append(toIndentedString(signMode)).append("\n");
    sb.append("    eniTipoFirma: ").append(toIndentedString(eniTipoFirma)).append("\n");
    sb.append("    eniPerfilFirma: ").append(toIndentedString(eniPerfilFirma)).append("\n");
    sb.append("    validationInfo: ").append(toIndentedString(validationInfo)).append("\n");
    sb.append("    additionInformation: ").append(toIndentedString(additionInformation)).append("\n");
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

