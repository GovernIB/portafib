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
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleCustodyInfo;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleSignerInfo;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleValidationInfo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Informació del fitxer signat.
 */
@JsonPropertyOrder({
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_M_O_D_E_A_T_T_A_C_H_E_D_E_N_V_E_L_O_P_E_D,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_M_O_D_E_A_T_T_A_C_H_E_D_E_N_V_E_L_O_P_I_N_G,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_M_O_D_E_D_E_T_A_C_H_E_D,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_M_O_D_E_I_N_T_E_R_N_A_L_L_Y_D_E_T_A_C_H_E_D,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_A_T_U_R_E_S_T_A_B_L_E_L_O_C_A_T_I_O_N_W_I_T_H_O_U_T,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_A_T_U_R_E_S_T_A_B_L_E_L_O_C_A_T_I_O_N_F_I_R_S_T_P_A_G_E,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_A_T_U_R_E_S_T_A_B_L_E_L_O_C_A_T_I_O_N_L_A_S_T_P_A_G_E,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_O_P_E_R_A_T_I_O_N_S_I_G_N,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_O_P_E_R_A_T_I_O_N_C_O_S_I_G_N,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_O_P_E_R_A_T_I_O_N_C_O_U_N_T_E_R_S_I_G_N,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_B_E_S,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_E_P_E_S,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_T,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_C,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X1,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X2,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X_L,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X_L1,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X_L2,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_A,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_P_A_D_E_S_L_T_V,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_P_A_D_E_S_B_A_S_I_C,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_SIGN_OPERATION,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_SIGN_TYPE,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_SIGN_ALGORITHM,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_SIGN_MODE,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_SIGNATURES_TABLE_LOCATION,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_TIME_STAMP_INCLUDED,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_POLICY_INCLUDED,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_ENI_TIPO_FIRMA,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_ENI_PERFIL_FIRMA,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_SIGNER_INFO,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_CUSTODY_INFO,
  FirmaSimpleSignedFileInfo.JSON_PROPERTY_VALIDATION_INFO
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class FirmaSimpleSignedFileInfo {
  public static final String JSON_PROPERTY_S_I_G_N_M_O_D_E_A_T_T_A_C_H_E_D_E_N_V_E_L_O_P_E_D = "SIGN_MODE_ATTACHED_ENVELOPED";
  @javax.annotation.Nonnull
  private Integer SIGN_MODE_ATTACHED_ENVELOPED = 0;

  public static final String JSON_PROPERTY_S_I_G_N_M_O_D_E_A_T_T_A_C_H_E_D_E_N_V_E_L_O_P_I_N_G = "SIGN_MODE_ATTACHED_ENVELOPING";
  @javax.annotation.Nonnull
  private Integer SIGN_MODE_ATTACHED_ENVELOPING = 3;

  public static final String JSON_PROPERTY_S_I_G_N_M_O_D_E_D_E_T_A_C_H_E_D = "SIGN_MODE_DETACHED";
  @javax.annotation.Nonnull
  private Integer SIGN_MODE_DETACHED = 1;

  public static final String JSON_PROPERTY_S_I_G_N_M_O_D_E_I_N_T_E_R_N_A_L_L_Y_D_E_T_A_C_H_E_D = "SIGN_MODE_INTERNALLY_DETACHED";
  @javax.annotation.Nonnull
  private Integer SIGN_MODE_INTERNALLY_DETACHED = 4;

  public static final String JSON_PROPERTY_S_I_G_N_A_T_U_R_E_S_T_A_B_L_E_L_O_C_A_T_I_O_N_W_I_T_H_O_U_T = "SIGNATURESTABLELOCATION_WITHOUT";
  @javax.annotation.Nonnull
  private Integer SIGNATURESTABLELOCATION_WITHOUT = 0;

  public static final String JSON_PROPERTY_S_I_G_N_A_T_U_R_E_S_T_A_B_L_E_L_O_C_A_T_I_O_N_F_I_R_S_T_P_A_G_E = "SIGNATURESTABLELOCATION_FIRSTPAGE";
  @javax.annotation.Nonnull
  private Integer SIGNATURESTABLELOCATION_FIRSTPAGE = 1;

  public static final String JSON_PROPERTY_S_I_G_N_A_T_U_R_E_S_T_A_B_L_E_L_O_C_A_T_I_O_N_L_A_S_T_P_A_G_E = "SIGNATURESTABLELOCATION_LASTPAGE";
  @javax.annotation.Nonnull
  private Integer SIGNATURESTABLELOCATION_LASTPAGE = -1;

  public static final String JSON_PROPERTY_S_I_G_N_O_P_E_R_A_T_I_O_N_S_I_G_N = "SIGN_OPERATION_SIGN";
  @javax.annotation.Nonnull
  private Integer SIGN_OPERATION_SIGN = 0;

  public static final String JSON_PROPERTY_S_I_G_N_O_P_E_R_A_T_I_O_N_C_O_S_I_G_N = "SIGN_OPERATION_COSIGN";
  @javax.annotation.Nonnull
  private Integer SIGN_OPERATION_COSIGN = 1;

  public static final String JSON_PROPERTY_S_I_G_N_O_P_E_R_A_T_I_O_N_C_O_U_N_T_E_R_S_I_G_N = "SIGN_OPERATION_COUNTERSIGN";
  @javax.annotation.Nonnull
  private Integer SIGN_OPERATION_COUNTERSIGN = 2;

  public static final String JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_B_E_S = "SIGNPROFILE_BES";
  @javax.annotation.Nonnull
  private String SIGNPROFILE_BES = "AdES-BES";

  public static final String JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_E_P_E_S = "SIGNPROFILE_EPES";
  @javax.annotation.Nonnull
  private String SIGNPROFILE_EPES = "AdES-EPES";

  public static final String JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_T = "SIGNPROFILE_T";
  @javax.annotation.Nonnull
  private String SIGNPROFILE_T = "AdES-T";

  public static final String JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_C = "SIGNPROFILE_C";
  @javax.annotation.Nonnull
  private String SIGNPROFILE_C = "AdES-C";

  public static final String JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X = "SIGNPROFILE_X";
  @javax.annotation.Nonnull
  private String SIGNPROFILE_X = "AdES-X";

  public static final String JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X1 = "SIGNPROFILE_X1";
  @javax.annotation.Nonnull
  private String SIGNPROFILE_X1 = "AdES-X1";

  public static final String JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X2 = "SIGNPROFILE_X2";
  @javax.annotation.Nonnull
  private String SIGNPROFILE_X2 = "AdES-X2";

  public static final String JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X_L = "SIGNPROFILE_XL";
  @javax.annotation.Nonnull
  private String SIGNPROFILE_XL = "AdES-XL";

  public static final String JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X_L1 = "SIGNPROFILE_XL1";
  @javax.annotation.Nonnull
  private String SIGNPROFILE_XL1 = "AdES-XL1";

  public static final String JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X_L2 = "SIGNPROFILE_XL2";
  @javax.annotation.Nonnull
  private String SIGNPROFILE_XL2 = "AdES-XL2";

  public static final String JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_A = "SIGNPROFILE_A";
  @javax.annotation.Nonnull
  private String SIGNPROFILE_A = "AdES-A";

  public static final String JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_P_A_D_E_S_L_T_V = "SIGNPROFILE_PADES_LTV";
  @javax.annotation.Nonnull
  private String SIGNPROFILE_PADES_LTV = "PAdES-LTV";

  public static final String JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_P_A_D_E_S_B_A_S_I_C = "SIGNPROFILE_PADES_BASIC";
  @javax.annotation.Nonnull
  private String SIGNPROFILE_PADES_BASIC = "PAdES-Basic";

  public static final String JSON_PROPERTY_SIGN_OPERATION = "signOperation";
  @javax.annotation.Nonnull
  private Integer signOperation;

  public static final String JSON_PROPERTY_SIGN_TYPE = "signType";
  @javax.annotation.Nonnull
  private String signType;

  public static final String JSON_PROPERTY_SIGN_ALGORITHM = "signAlgorithm";
  @javax.annotation.Nonnull
  private String signAlgorithm;

  public static final String JSON_PROPERTY_SIGN_MODE = "signMode";
  @javax.annotation.Nonnull
  private Integer signMode;

  public static final String JSON_PROPERTY_SIGNATURES_TABLE_LOCATION = "signaturesTableLocation";
  @javax.annotation.Nonnull
  private Integer signaturesTableLocation;

  public static final String JSON_PROPERTY_TIME_STAMP_INCLUDED = "timeStampIncluded";
  @javax.annotation.Nonnull
  private Boolean timeStampIncluded;

  public static final String JSON_PROPERTY_POLICY_INCLUDED = "policyIncluded";
  @javax.annotation.Nonnull
  private Boolean policyIncluded;

  public static final String JSON_PROPERTY_ENI_TIPO_FIRMA = "eniTipoFirma";
  @javax.annotation.Nullable
  private String eniTipoFirma;

  public static final String JSON_PROPERTY_ENI_PERFIL_FIRMA = "eniPerfilFirma";
  @javax.annotation.Nullable
  private String eniPerfilFirma;

  public static final String JSON_PROPERTY_SIGNER_INFO = "signerInfo";
  @javax.annotation.Nonnull
  private FirmaSimpleSignerInfo signerInfo;

  public static final String JSON_PROPERTY_CUSTODY_INFO = "custodyInfo";
  @javax.annotation.Nullable
  private FirmaSimpleCustodyInfo custodyInfo;

  public static final String JSON_PROPERTY_VALIDATION_INFO = "validationInfo";
  @javax.annotation.Nullable
  private FirmaSimpleValidationInfo validationInfo;

  public FirmaSimpleSignedFileInfo() {
  }
  /**
   * Constructor with only readonly parameters
   */
  @JsonCreator
  public FirmaSimpleSignedFileInfo(
    @JsonProperty(JSON_PROPERTY_S_I_G_N_M_O_D_E_A_T_T_A_C_H_E_D_E_N_V_E_L_O_P_E_D) Integer SIGN_MODE_ATTACHED_ENVELOPED, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_M_O_D_E_A_T_T_A_C_H_E_D_E_N_V_E_L_O_P_I_N_G) Integer SIGN_MODE_ATTACHED_ENVELOPING, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_M_O_D_E_D_E_T_A_C_H_E_D) Integer SIGN_MODE_DETACHED, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_M_O_D_E_I_N_T_E_R_N_A_L_L_Y_D_E_T_A_C_H_E_D) Integer SIGN_MODE_INTERNALLY_DETACHED, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_A_T_U_R_E_S_T_A_B_L_E_L_O_C_A_T_I_O_N_W_I_T_H_O_U_T) Integer SIGNATURESTABLELOCATION_WITHOUT, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_A_T_U_R_E_S_T_A_B_L_E_L_O_C_A_T_I_O_N_F_I_R_S_T_P_A_G_E) Integer SIGNATURESTABLELOCATION_FIRSTPAGE, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_A_T_U_R_E_S_T_A_B_L_E_L_O_C_A_T_I_O_N_L_A_S_T_P_A_G_E) Integer SIGNATURESTABLELOCATION_LASTPAGE, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_O_P_E_R_A_T_I_O_N_S_I_G_N) Integer SIGN_OPERATION_SIGN, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_O_P_E_R_A_T_I_O_N_C_O_S_I_G_N) Integer SIGN_OPERATION_COSIGN, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_O_P_E_R_A_T_I_O_N_C_O_U_N_T_E_R_S_I_G_N) Integer SIGN_OPERATION_COUNTERSIGN, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_B_E_S) String SIGNPROFILE_BES, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_E_P_E_S) String SIGNPROFILE_EPES, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_T) String SIGNPROFILE_T, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_C) String SIGNPROFILE_C, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X) String SIGNPROFILE_X, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X1) String SIGNPROFILE_X1, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X2) String SIGNPROFILE_X2, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X_L) String SIGNPROFILE_XL, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X_L1) String SIGNPROFILE_XL1, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X_L2) String SIGNPROFILE_XL2, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_A) String SIGNPROFILE_A, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_P_A_D_E_S_L_T_V) String SIGNPROFILE_PADES_LTV, 
    @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_P_A_D_E_S_B_A_S_I_C) String SIGNPROFILE_PADES_BASIC
  ) {
    this();
    this.SIGN_MODE_ATTACHED_ENVELOPED = SIGN_MODE_ATTACHED_ENVELOPED;
    this.SIGN_MODE_ATTACHED_ENVELOPING = SIGN_MODE_ATTACHED_ENVELOPING;
    this.SIGN_MODE_DETACHED = SIGN_MODE_DETACHED;
    this.SIGN_MODE_INTERNALLY_DETACHED = SIGN_MODE_INTERNALLY_DETACHED;
    this.SIGNATURESTABLELOCATION_WITHOUT = SIGNATURESTABLELOCATION_WITHOUT;
    this.SIGNATURESTABLELOCATION_FIRSTPAGE = SIGNATURESTABLELOCATION_FIRSTPAGE;
    this.SIGNATURESTABLELOCATION_LASTPAGE = SIGNATURESTABLELOCATION_LASTPAGE;
    this.SIGN_OPERATION_SIGN = SIGN_OPERATION_SIGN;
    this.SIGN_OPERATION_COSIGN = SIGN_OPERATION_COSIGN;
    this.SIGN_OPERATION_COUNTERSIGN = SIGN_OPERATION_COUNTERSIGN;
    this.SIGNPROFILE_BES = SIGNPROFILE_BES;
    this.SIGNPROFILE_EPES = SIGNPROFILE_EPES;
    this.SIGNPROFILE_T = SIGNPROFILE_T;
    this.SIGNPROFILE_C = SIGNPROFILE_C;
    this.SIGNPROFILE_X = SIGNPROFILE_X;
    this.SIGNPROFILE_X1 = SIGNPROFILE_X1;
    this.SIGNPROFILE_X2 = SIGNPROFILE_X2;
    this.SIGNPROFILE_XL = SIGNPROFILE_XL;
    this.SIGNPROFILE_XL1 = SIGNPROFILE_XL1;
    this.SIGNPROFILE_XL2 = SIGNPROFILE_XL2;
    this.SIGNPROFILE_A = SIGNPROFILE_A;
    this.SIGNPROFILE_PADES_LTV = SIGNPROFILE_PADES_LTV;
    this.SIGNPROFILE_PADES_BASIC = SIGNPROFILE_PADES_BASIC;
  }

  /**
   * El fitxer de dades resultant inclou la firma: PDF, ODT, ...
   * @return SIGN_MODE_ATTACHED_ENVELOPED
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_M_O_D_E_A_T_T_A_C_H_E_D_E_N_V_E_L_O_P_E_D)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getSIGNMODEATTACHEDENVELOPED() {
    return SIGN_MODE_ATTACHED_ENVELOPED;
  }



  /**
   * El fitxer resultant serà la firma que incloura les dades originals
   * @return SIGN_MODE_ATTACHED_ENVELOPING
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_M_O_D_E_A_T_T_A_C_H_E_D_E_N_V_E_L_O_P_I_N_G)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getSIGNMODEATTACHEDENVELOPING() {
    return SIGN_MODE_ATTACHED_ENVELOPING;
  }



  /**
   * El fitxer de firma no inclourà les dades: per separat trobarem un fitxer de firma i el fitxer original
   * @return SIGN_MODE_DETACHED
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_M_O_D_E_D_E_T_A_C_H_E_D)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getSIGNMODEDETACHED() {
    return SIGN_MODE_DETACHED;
  }



  /**
   * Firma especial XAdES en que la firma i les dades estan al mateix nivell dins de l&#39;XML: ni la firma inclou les dades ni les dades inclouen la firma
   * @return SIGN_MODE_INTERNALLY_DETACHED
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_M_O_D_E_I_N_T_E_R_N_A_L_L_Y_D_E_T_A_C_H_E_D)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getSIGNMODEINTERNALLYDETACHED() {
    return SIGN_MODE_INTERNALLY_DETACHED;
  }



  /**
   * Localitzador de la signatura al document. (Sense signatura visible)
   * @return SIGNATURESTABLELOCATION_WITHOUT
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_A_T_U_R_E_S_T_A_B_L_E_L_O_C_A_T_I_O_N_W_I_T_H_O_U_T)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getSIGNATURESTABLELOCATIONWITHOUT() {
    return SIGNATURESTABLELOCATION_WITHOUT;
  }



  /**
   * Localitzador de la signatura al document. (Primera pagina)
   * @return SIGNATURESTABLELOCATION_FIRSTPAGE
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_A_T_U_R_E_S_T_A_B_L_E_L_O_C_A_T_I_O_N_F_I_R_S_T_P_A_G_E)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getSIGNATURESTABLELOCATIONFIRSTPAGE() {
    return SIGNATURESTABLELOCATION_FIRSTPAGE;
  }



  /**
   * Localitzador de la signatura al document. (Darrera pagina)
   * @return SIGNATURESTABLELOCATION_LASTPAGE
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_A_T_U_R_E_S_T_A_B_L_E_L_O_C_A_T_I_O_N_L_A_S_T_P_A_G_E)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getSIGNATURESTABLELOCATIONLASTPAGE() {
    return SIGNATURESTABLELOCATION_LASTPAGE;
  }



  /**
   * Identificador d&#39;operació per Firma
   * @return SIGN_OPERATION_SIGN
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_O_P_E_R_A_T_I_O_N_S_I_G_N)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getSIGNOPERATIONSIGN() {
    return SIGN_OPERATION_SIGN;
  }



  /**
   * Identificador d&#39;operació per Cofirma
   * @return SIGN_OPERATION_COSIGN
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_O_P_E_R_A_T_I_O_N_C_O_S_I_G_N)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getSIGNOPERATIONCOSIGN() {
    return SIGN_OPERATION_COSIGN;
  }



  /**
   * Identificador d&#39;operació per Contrafirma
   * @return SIGN_OPERATION_COUNTERSIGN
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_O_P_E_R_A_T_I_O_N_C_O_U_N_T_E_R_S_I_G_N)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getSIGNOPERATIONCOUNTERSIGN() {
    return SIGN_OPERATION_COUNTERSIGN;
  }



  /**
   * Perfil de firma AdES-BES
   * @return SIGNPROFILE_BES
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_B_E_S)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getSIGNPROFILEBES() {
    return SIGNPROFILE_BES;
  }



  /**
   * Perfil de firma AdES-EPES
   * @return SIGNPROFILE_EPES
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_E_P_E_S)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getSIGNPROFILEEPES() {
    return SIGNPROFILE_EPES;
  }



  /**
   * Perfil de firma AdES-T
   * @return SIGNPROFILE_T
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_T)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getSIGNPROFILET() {
    return SIGNPROFILE_T;
  }



  /**
   * Perfil de firma AdES-C
   * @return SIGNPROFILE_C
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_C)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getSIGNPROFILEC() {
    return SIGNPROFILE_C;
  }



  /**
   * Perfil de firma AdES-X
   * @return SIGNPROFILE_X
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getSIGNPROFILEX() {
    return SIGNPROFILE_X;
  }



  /**
   * Perfil de firma AdES-X1
   * @return SIGNPROFILE_X1
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X1)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getSIGNPROFILEX1() {
    return SIGNPROFILE_X1;
  }



  /**
   * Perfil de firma AdES-X2
   * @return SIGNPROFILE_X2
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X2)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getSIGNPROFILEX2() {
    return SIGNPROFILE_X2;
  }



  /**
   * Perfil de firma AdES-XL
   * @return SIGNPROFILE_XL
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X_L)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getSIGNPROFILEXL() {
    return SIGNPROFILE_XL;
  }



  /**
   * Perfil de firma AdES-XL1
   * @return SIGNPROFILE_XL1
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X_L1)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getSIGNPROFILEXL1() {
    return SIGNPROFILE_XL1;
  }



  /**
   * Perfil de firma AdES-XL2
   * @return SIGNPROFILE_XL2
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_X_L2)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getSIGNPROFILEXL2() {
    return SIGNPROFILE_XL2;
  }



  /**
   * Perfil de firma AdES-A
   * @return SIGNPROFILE_A
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_A)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getSIGNPROFILEA() {
    return SIGNPROFILE_A;
  }



  /**
   * Perfil de firma PAdES-LTV
   * @return SIGNPROFILE_PADES_LTV
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_P_A_D_E_S_L_T_V)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getSIGNPROFILEPADESLTV() {
    return SIGNPROFILE_PADES_LTV;
  }



  /**
   * Perfil de firma PAdES-Basic
   * @return SIGNPROFILE_PADES_BASIC
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_S_I_G_N_P_R_O_F_I_L_E_P_A_D_E_S_B_A_S_I_C)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getSIGNPROFILEPADESBASIC() {
    return SIGNPROFILE_PADES_BASIC;
  }



  public FirmaSimpleSignedFileInfo signOperation(@javax.annotation.Nonnull Integer signOperation) {
    
    this.signOperation = signOperation;
    return this;
  }

  /**
   * Operació de firma realitzada: Firma (0), Cofirma (1) o Contrafirma (2).
   * @return signOperation
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_SIGN_OPERATION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getSignOperation() {
    return signOperation;
  }


  @JsonProperty(JSON_PROPERTY_SIGN_OPERATION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setSignOperation(@javax.annotation.Nonnull Integer signOperation) {
    this.signOperation = signOperation;
  }

  public FirmaSimpleSignedFileInfo signType(@javax.annotation.Nonnull String signType) {
    
    this.signType = signType;
    return this;
  }

  /**
   * Tipus de Firma. Valors possibles:      
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

  public FirmaSimpleSignedFileInfo signAlgorithm(@javax.annotation.Nonnull String signAlgorithm) {
    
    this.signAlgorithm = signAlgorithm;
    return this;
  }

  /**
   * Algorisme de Firma. Valors:       - \&quot;SHA-1\&quot;      - \&quot;SHA-256\&quot;      - \&quot;SHA-384\&quot;      - \&quot;SHA-512\&quot;
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

  public FirmaSimpleSignedFileInfo signMode(@javax.annotation.Nonnull Integer signMode) {
    
    this.signMode = signMode;
    return this;
  }

  /**
   * Valors:      - 0: Implicit o Attached. La firma resultante incluye internamente una copia de los datos firmados.       - 1: Explicit o Detached: La firma resultante no incluye los datos firmados. 
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

  public FirmaSimpleSignedFileInfo signaturesTableLocation(@javax.annotation.Nonnull Integer signaturesTableLocation) {
    
    this.signaturesTableLocation = signaturesTableLocation;
    return this;
  }

  /**
   * Posició de la Taula de firmes:      - 0: Sense taula de firmes      - 1: Taula de firmes en la 1a pàgina      - -1: Darrera pàgina
   * @return signaturesTableLocation
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_SIGNATURES_TABLE_LOCATION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getSignaturesTableLocation() {
    return signaturesTableLocation;
  }


  @JsonProperty(JSON_PROPERTY_SIGNATURES_TABLE_LOCATION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setSignaturesTableLocation(@javax.annotation.Nonnull Integer signaturesTableLocation) {
    this.signaturesTableLocation = signaturesTableLocation;
  }

  public FirmaSimpleSignedFileInfo timeStampIncluded(@javax.annotation.Nonnull Boolean timeStampIncluded) {
    
    this.timeStampIncluded = timeStampIncluded;
    return this;
  }

  /**
   * Indica si s&#39;ha afegit un segell de Temps durant la firma
   * @return timeStampIncluded
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_TIME_STAMP_INCLUDED)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Boolean getTimeStampIncluded() {
    return timeStampIncluded;
  }


  @JsonProperty(JSON_PROPERTY_TIME_STAMP_INCLUDED)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTimeStampIncluded(@javax.annotation.Nonnull Boolean timeStampIncluded) {
    this.timeStampIncluded = timeStampIncluded;
  }

  public FirmaSimpleSignedFileInfo policyIncluded(@javax.annotation.Nonnull Boolean policyIncluded) {
    
    this.policyIncluded = policyIncluded;
    return this;
  }

  /**
   * Indica si inclou política de firma (true, EPES) o no (false)
   * @return policyIncluded
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_POLICY_INCLUDED)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Boolean getPolicyIncluded() {
    return policyIncluded;
  }


  @JsonProperty(JSON_PROPERTY_POLICY_INCLUDED)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setPolicyIncluded(@javax.annotation.Nonnull Boolean policyIncluded) {
    this.policyIncluded = policyIncluded;
  }

  public FirmaSimpleSignedFileInfo eniTipoFirma(@javax.annotation.Nullable String eniTipoFirma) {
    
    this.eniTipoFirma = eniTipoFirma;
    return this;
  }

  /**
   * Denominación normalizada del tipo de firma. Los posibles valores asignables son los siguientes:       - TF01 - CSV       - TF02 - XAdES internally detached signature\&quot;);       - TF03 - XAdES enveloped signature.      - TF04 - CAdES detached/explicit signature.      - TF05 – CadES attached/implicit signature.      - TF06 - PAdES. El tipo TF04 será establecido por defecto para documentos firmados, exceptuando los documentos en formato PDF o PDF/A, cuyo tipo será TF06.
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

  public FirmaSimpleSignedFileInfo eniPerfilFirma(@javax.annotation.Nullable String eniPerfilFirma) {
    
    this.eniPerfilFirma = eniPerfilFirma;
    return this;
  }

  /**
   * Perfil empleado en una firma con certificado electrónico. Los posibles valores asignables son los siguientes:    AdES-BES    AdES-EPES    AdES-T    AdES-C    AdES-X    AdES-X1    AdES-X2    AdES-XL    AdES-XL1    AdES-XL2    AdES-A    PAdES-LTV    PAdES-Basic
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

  public FirmaSimpleSignedFileInfo signerInfo(@javax.annotation.Nonnull FirmaSimpleSignerInfo signerInfo) {
    
    this.signerInfo = signerInfo;
    return this;
  }

  /**
   * Get signerInfo
   * @return signerInfo
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_SIGNER_INFO)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public FirmaSimpleSignerInfo getSignerInfo() {
    return signerInfo;
  }


  @JsonProperty(JSON_PROPERTY_SIGNER_INFO)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setSignerInfo(@javax.annotation.Nonnull FirmaSimpleSignerInfo signerInfo) {
    this.signerInfo = signerInfo;
  }

  public FirmaSimpleSignedFileInfo custodyInfo(@javax.annotation.Nullable FirmaSimpleCustodyInfo custodyInfo) {
    
    this.custodyInfo = custodyInfo;
    return this;
  }

  /**
   * Get custodyInfo
   * @return custodyInfo
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CUSTODY_INFO)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FirmaSimpleCustodyInfo getCustodyInfo() {
    return custodyInfo;
  }


  @JsonProperty(JSON_PROPERTY_CUSTODY_INFO)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCustodyInfo(@javax.annotation.Nullable FirmaSimpleCustodyInfo custodyInfo) {
    this.custodyInfo = custodyInfo;
  }

  public FirmaSimpleSignedFileInfo validationInfo(@javax.annotation.Nullable FirmaSimpleValidationInfo validationInfo) {
    
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FirmaSimpleSignedFileInfo firmaSimpleSignedFileInfo = (FirmaSimpleSignedFileInfo) o;
    return Objects.equals(this.SIGN_MODE_ATTACHED_ENVELOPED, firmaSimpleSignedFileInfo.SIGN_MODE_ATTACHED_ENVELOPED) &&
        Objects.equals(this.SIGN_MODE_ATTACHED_ENVELOPING, firmaSimpleSignedFileInfo.SIGN_MODE_ATTACHED_ENVELOPING) &&
        Objects.equals(this.SIGN_MODE_DETACHED, firmaSimpleSignedFileInfo.SIGN_MODE_DETACHED) &&
        Objects.equals(this.SIGN_MODE_INTERNALLY_DETACHED, firmaSimpleSignedFileInfo.SIGN_MODE_INTERNALLY_DETACHED) &&
        Objects.equals(this.SIGNATURESTABLELOCATION_WITHOUT, firmaSimpleSignedFileInfo.SIGNATURESTABLELOCATION_WITHOUT) &&
        Objects.equals(this.SIGNATURESTABLELOCATION_FIRSTPAGE, firmaSimpleSignedFileInfo.SIGNATURESTABLELOCATION_FIRSTPAGE) &&
        Objects.equals(this.SIGNATURESTABLELOCATION_LASTPAGE, firmaSimpleSignedFileInfo.SIGNATURESTABLELOCATION_LASTPAGE) &&
        Objects.equals(this.SIGN_OPERATION_SIGN, firmaSimpleSignedFileInfo.SIGN_OPERATION_SIGN) &&
        Objects.equals(this.SIGN_OPERATION_COSIGN, firmaSimpleSignedFileInfo.SIGN_OPERATION_COSIGN) &&
        Objects.equals(this.SIGN_OPERATION_COUNTERSIGN, firmaSimpleSignedFileInfo.SIGN_OPERATION_COUNTERSIGN) &&
        Objects.equals(this.SIGNPROFILE_BES, firmaSimpleSignedFileInfo.SIGNPROFILE_BES) &&
        Objects.equals(this.SIGNPROFILE_EPES, firmaSimpleSignedFileInfo.SIGNPROFILE_EPES) &&
        Objects.equals(this.SIGNPROFILE_T, firmaSimpleSignedFileInfo.SIGNPROFILE_T) &&
        Objects.equals(this.SIGNPROFILE_C, firmaSimpleSignedFileInfo.SIGNPROFILE_C) &&
        Objects.equals(this.SIGNPROFILE_X, firmaSimpleSignedFileInfo.SIGNPROFILE_X) &&
        Objects.equals(this.SIGNPROFILE_X1, firmaSimpleSignedFileInfo.SIGNPROFILE_X1) &&
        Objects.equals(this.SIGNPROFILE_X2, firmaSimpleSignedFileInfo.SIGNPROFILE_X2) &&
        Objects.equals(this.SIGNPROFILE_XL, firmaSimpleSignedFileInfo.SIGNPROFILE_XL) &&
        Objects.equals(this.SIGNPROFILE_XL1, firmaSimpleSignedFileInfo.SIGNPROFILE_XL1) &&
        Objects.equals(this.SIGNPROFILE_XL2, firmaSimpleSignedFileInfo.SIGNPROFILE_XL2) &&
        Objects.equals(this.SIGNPROFILE_A, firmaSimpleSignedFileInfo.SIGNPROFILE_A) &&
        Objects.equals(this.SIGNPROFILE_PADES_LTV, firmaSimpleSignedFileInfo.SIGNPROFILE_PADES_LTV) &&
        Objects.equals(this.SIGNPROFILE_PADES_BASIC, firmaSimpleSignedFileInfo.SIGNPROFILE_PADES_BASIC) &&
        Objects.equals(this.signOperation, firmaSimpleSignedFileInfo.signOperation) &&
        Objects.equals(this.signType, firmaSimpleSignedFileInfo.signType) &&
        Objects.equals(this.signAlgorithm, firmaSimpleSignedFileInfo.signAlgorithm) &&
        Objects.equals(this.signMode, firmaSimpleSignedFileInfo.signMode) &&
        Objects.equals(this.signaturesTableLocation, firmaSimpleSignedFileInfo.signaturesTableLocation) &&
        Objects.equals(this.timeStampIncluded, firmaSimpleSignedFileInfo.timeStampIncluded) &&
        Objects.equals(this.policyIncluded, firmaSimpleSignedFileInfo.policyIncluded) &&
        Objects.equals(this.eniTipoFirma, firmaSimpleSignedFileInfo.eniTipoFirma) &&
        Objects.equals(this.eniPerfilFirma, firmaSimpleSignedFileInfo.eniPerfilFirma) &&
        Objects.equals(this.signerInfo, firmaSimpleSignedFileInfo.signerInfo) &&
        Objects.equals(this.custodyInfo, firmaSimpleSignedFileInfo.custodyInfo) &&
        Objects.equals(this.validationInfo, firmaSimpleSignedFileInfo.validationInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(SIGN_MODE_ATTACHED_ENVELOPED, SIGN_MODE_ATTACHED_ENVELOPING, SIGN_MODE_DETACHED, SIGN_MODE_INTERNALLY_DETACHED, SIGNATURESTABLELOCATION_WITHOUT, SIGNATURESTABLELOCATION_FIRSTPAGE, SIGNATURESTABLELOCATION_LASTPAGE, SIGN_OPERATION_SIGN, SIGN_OPERATION_COSIGN, SIGN_OPERATION_COUNTERSIGN, SIGNPROFILE_BES, SIGNPROFILE_EPES, SIGNPROFILE_T, SIGNPROFILE_C, SIGNPROFILE_X, SIGNPROFILE_X1, SIGNPROFILE_X2, SIGNPROFILE_XL, SIGNPROFILE_XL1, SIGNPROFILE_XL2, SIGNPROFILE_A, SIGNPROFILE_PADES_LTV, SIGNPROFILE_PADES_BASIC, signOperation, signType, signAlgorithm, signMode, signaturesTableLocation, timeStampIncluded, policyIncluded, eniTipoFirma, eniPerfilFirma, signerInfo, custodyInfo, validationInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FirmaSimpleSignedFileInfo {\n");
    sb.append("    SIGN_MODE_ATTACHED_ENVELOPED: ").append(toIndentedString(SIGN_MODE_ATTACHED_ENVELOPED)).append("\n");
    sb.append("    SIGN_MODE_ATTACHED_ENVELOPING: ").append(toIndentedString(SIGN_MODE_ATTACHED_ENVELOPING)).append("\n");
    sb.append("    SIGN_MODE_DETACHED: ").append(toIndentedString(SIGN_MODE_DETACHED)).append("\n");
    sb.append("    SIGN_MODE_INTERNALLY_DETACHED: ").append(toIndentedString(SIGN_MODE_INTERNALLY_DETACHED)).append("\n");
    sb.append("    SIGNATURESTABLELOCATION_WITHOUT: ").append(toIndentedString(SIGNATURESTABLELOCATION_WITHOUT)).append("\n");
    sb.append("    SIGNATURESTABLELOCATION_FIRSTPAGE: ").append(toIndentedString(SIGNATURESTABLELOCATION_FIRSTPAGE)).append("\n");
    sb.append("    SIGNATURESTABLELOCATION_LASTPAGE: ").append(toIndentedString(SIGNATURESTABLELOCATION_LASTPAGE)).append("\n");
    sb.append("    SIGN_OPERATION_SIGN: ").append(toIndentedString(SIGN_OPERATION_SIGN)).append("\n");
    sb.append("    SIGN_OPERATION_COSIGN: ").append(toIndentedString(SIGN_OPERATION_COSIGN)).append("\n");
    sb.append("    SIGN_OPERATION_COUNTERSIGN: ").append(toIndentedString(SIGN_OPERATION_COUNTERSIGN)).append("\n");
    sb.append("    SIGNPROFILE_BES: ").append(toIndentedString(SIGNPROFILE_BES)).append("\n");
    sb.append("    SIGNPROFILE_EPES: ").append(toIndentedString(SIGNPROFILE_EPES)).append("\n");
    sb.append("    SIGNPROFILE_T: ").append(toIndentedString(SIGNPROFILE_T)).append("\n");
    sb.append("    SIGNPROFILE_C: ").append(toIndentedString(SIGNPROFILE_C)).append("\n");
    sb.append("    SIGNPROFILE_X: ").append(toIndentedString(SIGNPROFILE_X)).append("\n");
    sb.append("    SIGNPROFILE_X1: ").append(toIndentedString(SIGNPROFILE_X1)).append("\n");
    sb.append("    SIGNPROFILE_X2: ").append(toIndentedString(SIGNPROFILE_X2)).append("\n");
    sb.append("    SIGNPROFILE_XL: ").append(toIndentedString(SIGNPROFILE_XL)).append("\n");
    sb.append("    SIGNPROFILE_XL1: ").append(toIndentedString(SIGNPROFILE_XL1)).append("\n");
    sb.append("    SIGNPROFILE_XL2: ").append(toIndentedString(SIGNPROFILE_XL2)).append("\n");
    sb.append("    SIGNPROFILE_A: ").append(toIndentedString(SIGNPROFILE_A)).append("\n");
    sb.append("    SIGNPROFILE_PADES_LTV: ").append(toIndentedString(SIGNPROFILE_PADES_LTV)).append("\n");
    sb.append("    SIGNPROFILE_PADES_BASIC: ").append(toIndentedString(SIGNPROFILE_PADES_BASIC)).append("\n");
    sb.append("    signOperation: ").append(toIndentedString(signOperation)).append("\n");
    sb.append("    signType: ").append(toIndentedString(signType)).append("\n");
    sb.append("    signAlgorithm: ").append(toIndentedString(signAlgorithm)).append("\n");
    sb.append("    signMode: ").append(toIndentedString(signMode)).append("\n");
    sb.append("    signaturesTableLocation: ").append(toIndentedString(signaturesTableLocation)).append("\n");
    sb.append("    timeStampIncluded: ").append(toIndentedString(timeStampIncluded)).append("\n");
    sb.append("    policyIncluded: ").append(toIndentedString(policyIncluded)).append("\n");
    sb.append("    eniTipoFirma: ").append(toIndentedString(eniTipoFirma)).append("\n");
    sb.append("    eniPerfilFirma: ").append(toIndentedString(eniPerfilFirma)).append("\n");
    sb.append("    signerInfo: ").append(toIndentedString(signerInfo)).append("\n");
    sb.append("    custodyInfo: ").append(toIndentedString(custodyInfo)).append("\n");
    sb.append("    validationInfo: ").append(toIndentedString(validationInfo)).append("\n");
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

