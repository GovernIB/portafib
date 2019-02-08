package org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaSimpleSignedFileInfo {

  /** Identificador de la firma PAdES. */
  public static final String SIGN_TYPE_PADES = "PAdES";
  /** Identificador de la firma XAdES por defecto. */
  public static final String SIGN_TYPE_XADES = "XAdES";
  /** Identificador de la firma CAdES. */
  public static final String SIGN_TYPE_CADES = "CAdES";
  /** Identificador de la firma Factura-e (derivado de XAdES-EPES). */
  public static final String SIGN_TYPE_FACTURAE = "FacturaE";
  /** Identificador de la firma OOXML (<i>Office Open XML</i>). */
  public static final String SIGN_TYPE_OOXML = "OOXML";
  /** Identificador de la firma ODF (<i>Open Document Format</i>). */
  public static final String SIGN_TYPE_ODF = "ODF";
  /** Identificador de Firma SMIME */
  public static final String SIGN_TYPE_SMIME = "SMIME";
  /** CAdES-ASiC-S: Formato de firma avanzada ASiC de tipo CAdES. */
  public static final String SIGN_TYPE_CADES_ASIC_S = "CAdES-ASiC-S";
  /** XAdES-ASiC-S: Formato de firma avanzada ASiC de tipo XAdES. */
  public static final String SIGN_TYPE_XADES_ASIC_S = "XAdES-ASiC-S";
  /** NONE: Firma PKCS#1. **/
  public static final String SIGN_TYPE_PKCS1 = "PKCS#1";

  public static final String SIGN_ALGORITHM_SHA1 = "SHA-1";
  public static final String SIGN_ALGORITHM_SHA256 = "SHA-256";
  public static final String SIGN_ALGORITHM_SHA384 = "SHA-384";
  public static final String SIGN_ALGORITHM_SHA512 = "SHA-512";

  /*
   * implicit La firma resultante incluirá internamente una copia de los datos firmados. El uso
   * de este valor podría generar firmas de gran tamaño.
   */
  public static final int SIGN_MODE_IMPLICIT_ATTACHED = 0;
  /*
   * explicit La firma resultante no incluirá los datos firmados. Si no se indica el parámetro
   * mode se configura automáticamente este comportamiento.
   */
  public static final int SIGN_MODE_EXPLICIT_DETACHED = 1;

  public static final int SIGNATURESTABLELOCATION_WITHOUT = 0;
  public static final int SIGNATURESTABLELOCATION_FIRSTPAGE = 1;
  public static final int SIGNATURESTABLELOCATION_LASTPAGE = -1;

  // FIRMA
  public static final int SIGN_OPERATION_SIGN = 0;
  // COFIRMA
  public static final int SIGN_OPERATION_COSIGN = 1;
  // CONTRAFIRMA
  public static final int SIGN_OPERATION_COUNTERSIGN = 2;

  /**
   * eEMGDE.Firma.Firmante.EnCalidadDe(eEMGDE17.5.3): Firmante; Cofirmante; Contrafirmante
   * 
   */
  protected int signOperation;

  protected String signType;

  protected String signAlgorithm;

  protected int signMode;

  protected int signaturesTableLocation;

  protected boolean timeStampIncluded;

  // BES o EPES
  protected boolean policyIncluded;

  /**
   * eEMGDE.Firma.TipoFirma.FormatoFirma (eEMGDE17.1.1): TF01 (CSV), TF02 (XAdES internally
   * detached signature), TF03 (XAdES enveloped signature), TF04 (CAdES detached/explicit
   * signature), TF05 (CAdES attached/implicit signature), TF06 (PAdES)
   * 
   * 
   * Denominación normalizada del tipo de firma. Los posibles valores asignables son los
   * siguientes: TF01 - CSV TF02 - XAdES internally detached signature"); TF03 - XAdES
   * enveloped signature. TF04 - CAdES detached/explicit signature. TF05 - CAdES
   * attached/implicit signature. TF06 - PAdES. El tipo TF04 será establecido por defecto para
   * documentos firmados, exceptuando los documentos en formato PDF o PDF/A, cuyo tipo será
   * TF06. MetadataConstants.ENI_TIPO_FIRMA = "eni:tipoFirma";
   * 
   */
  @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
  protected String eniTipoFirma;

  /**
   * - eEMGDE.Firma.TipoFirma.PerfilFirma (eEMGDE17.1.2): 1.- Para las firmas XADES y CADES:
   * EPES, T, C, X, XL, A, BASELINE B-Level, BASELINE T-Level, BASELINE LT-Level, BASELINE
   * LTA-Level. 2.- Para las firmas PADES: EPES, LTV, BASELINE B-Level, BASELINE T
   * 
   * Perfil empleado en una firma con certificado electrónico. Los posibles valores asignables
   * son los siguientes: EPES T C X XL A BASELINE B-Level BASELINE LT-Level BASELINE LTA-Level
   * BASELINE T-Level LTV
   * 
   * - MetadataConstants.ENI_PERFIL_FIRMA = "eni:perfil_firma";
   */
  @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
  protected String eniPerfilFirma;

  /**
   * - eEMGDE.Firma.RolFirma (eEMGDE17.2): Esquemas desarrollados a nivel local y que pueden
   * incluir valores como válida, autentica, refrenda, visa, representa, testimonia, etc..
   */
  @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
  protected String eniRolFirma;

  /**
   * eEMGDE.Firma.Firmante.NombreApellidos (eEMGDE17.5.1): Texto libre. Nombre o razón social
   * de los firmantes.
   */
  @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
  protected String eniSignerName;

  /**
   * eEMGDE.Firma.Firmante (eEMGDE17.5.2). NúmeroIdentificacionFirmantes
   */
  @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
  protected String eniSignerAdministrationId;

  /**
   * eEMGDE.Firma.NivelFirma (eEMGDE17.5.4) Indicador normalizado que refleja el grado de
   * confianza de la firma utilizado. Ejemplos: Nick, PIN ciudadano, Firma electrónica
   * avanzada, Claves concertadas, Firma electrónica avanzada basada en certificados, CSV, ..
   */
  @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
  protected String eniSignLevel;

  /**
   * Informacio de Custòdia
   */
  @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
  protected FirmaSimpleCustodyInfo custodyInfo = null;

  /**
   * Informació de les validacions realitzades
   */
  @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
  protected FirmaSimpleValidationInfo validationInfo = null;

  /**
   * eEMGDE.Firma.InformacionAdicional (eEMGDE17.5.5) Ofrecer cualquier otra información que se
   * considere útil acerca del firmante.
   * */
  @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
  protected List<FirmaSimpleKeyValue> additionInformation = null;

  public FirmaSimpleSignedFileInfo() {
    super();
  }

  public FirmaSimpleSignedFileInfo(int signOperation, String signType, String signAlgorithm,
      int signMode, int signaturesTableLocation, boolean timeStampIncluded,
      boolean policyIncluded, String eniTipoFirma, String eniPerfilFirma, String eniRolFirma,
      String eniSignerName, String eniSignerAdministrationId, String eniSignLevel,
      FirmaSimpleCustodyInfo custodyInfo, FirmaSimpleValidationInfo validationInfo,
      List<FirmaSimpleKeyValue> additionInformation) {
    super();
    this.signOperation = signOperation;
    this.signType = signType;
    this.signAlgorithm = signAlgorithm;
    this.signMode = signMode;
    this.signaturesTableLocation = signaturesTableLocation;
    this.timeStampIncluded = timeStampIncluded;
    this.policyIncluded = policyIncluded;
    this.eniTipoFirma = eniTipoFirma;
    this.eniPerfilFirma = eniPerfilFirma;
    this.eniRolFirma = eniRolFirma;
    this.eniSignerName = eniSignerName;
    this.eniSignerAdministrationId = eniSignerAdministrationId;
    this.eniSignLevel = eniSignLevel;
    this.custodyInfo = custodyInfo;
    this.validationInfo = validationInfo;
    this.additionInformation = additionInformation;
  }

  public int getSignOperation() {
    return signOperation;
  }

  public void setSignOperation(int signOperation) {
    this.signOperation = signOperation;
  }

  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }

  public String getSignAlgorithm() {
    return signAlgorithm;
  }

  public void setSignAlgorithm(String signAlgorithm) {
    this.signAlgorithm = signAlgorithm;
  }

  public int getSignMode() {
    return signMode;
  }

  public void setSignMode(int signMode) {
    this.signMode = signMode;
  }

  public int getSignaturesTableLocation() {
    return signaturesTableLocation;
  }

  public void setSignaturesTableLocation(int signaturesTableLocation) {
    this.signaturesTableLocation = signaturesTableLocation;
  }

  public boolean isTimeStampIncluded() {
    return timeStampIncluded;
  }

  public void setTimeStampIncluded(boolean timeStampIncluded) {
    this.timeStampIncluded = timeStampIncluded;
  }

  public boolean isPolicyIncluded() {
    return policyIncluded;
  }

  public void setPolicyIncluded(boolean policyIncluded) {
    this.policyIncluded = policyIncluded;
  }

  public String getEniTipoFirma() {
    return eniTipoFirma;
  }

  public void setEniTipoFirma(String eniTipoFirma) {
    this.eniTipoFirma = eniTipoFirma;
  }

  public String getEniPerfilFirma() {
    return eniPerfilFirma;
  }

  public void setEniPerfilFirma(String eniPerfilFirma) {
    this.eniPerfilFirma = eniPerfilFirma;
  }

  public String getEniRolFirma() {
    return eniRolFirma;
  }

  public void setEniRolFirma(String eniRolFirma) {
    this.eniRolFirma = eniRolFirma;
  }

  public String getEniSignerName() {
    return eniSignerName;
  }

  public void setEniSignerName(String eniSignerName) {
    this.eniSignerName = eniSignerName;
  }

  public String getEniSignerAdministrationId() {
    return eniSignerAdministrationId;
  }

  public void setEniSignerAdministrationId(String eniSignerAdministrationId) {
    this.eniSignerAdministrationId = eniSignerAdministrationId;
  }

  public String getEniSignLevel() {
    return eniSignLevel;
  }

  public void setEniSignLevel(String eniSignLevel) {
    this.eniSignLevel = eniSignLevel;
  }

  public FirmaSimpleCustodyInfo getCustodyInfo() {
    return custodyInfo;
  }

  public void setCustodyInfo(FirmaSimpleCustodyInfo custodyInfo) {
    this.custodyInfo = custodyInfo;
  }

  public FirmaSimpleValidationInfo getValidationInfo() {
    return validationInfo;
  }

  public void setValidationInfo(FirmaSimpleValidationInfo validationInfo) {
    this.validationInfo = validationInfo;
  }

  public List<FirmaSimpleKeyValue> getAdditionInformation() {
    return additionInformation;
  }

  public void setAdditionInformation(List<FirmaSimpleKeyValue> additionInformation) {
    this.additionInformation = additionInformation;
  }

  public static String toString(FirmaSimpleSignedFileInfo sfi) {
    StringBuffer str = new StringBuffer("  + INFORMACIO:");

    String operation;
    switch (sfi.getSignOperation()) {
    case FirmaSimpleSignedFileInfo.SIGN_OPERATION_SIGN:
      operation = "FIRMA";
      break;
    case FirmaSimpleSignedFileInfo.SIGN_OPERATION_COSIGN:
      operation = "COFIRMA";
      break;

    case FirmaSimpleSignedFileInfo.SIGN_OPERATION_COUNTERSIGN:
      operation = "CONTRAFIRMA";
      break;

    default:
      operation = "DESCONEGUDA (" + sfi.getSignOperation() + ")";
    }
    str.append("\n").append("      * Operacio:\t" + operation);

    str.append("\n").append("      * Tipus:\t" + sfi.getSignType());

    str.append("\n").append("      * Algorisme:\t" + sfi.getSignAlgorithm());

    str.append("\n").append("      * Mode:\t"
            + ((sfi.getSignMode() == FirmaSimpleSignedFileInfo.SIGN_MODE_IMPLICIT_ATTACHED) ? "Attached - Implicit"
                : "Detached- Explicit"));

    String posicioTaulaDeFirmes;
    switch (sfi.getSignaturesTableLocation()) {

    case FirmaSimpleSignedFileInfo.SIGNATURESTABLELOCATION_WITHOUT:
      posicioTaulaDeFirmes = "Sense taula de Firmes";
      break;
    case FirmaSimpleSignedFileInfo.SIGNATURESTABLELOCATION_FIRSTPAGE:
      posicioTaulaDeFirmes = "Taula de Firmes en la primera pagina";
      break;
    case FirmaSimpleSignedFileInfo.SIGNATURESTABLELOCATION_LASTPAGE:
      posicioTaulaDeFirmes = "Taula de Firmes en la darrera pagina";
      break;

    default:
      posicioTaulaDeFirmes = "Desconeguda(" + sfi.getSignaturesTableLocation() + ")";

    }
    str.append("\n").append("      * Posicio Taula De Firmes:\t" + posicioTaulaDeFirmes);

    str.append("\n").append(
        "      * Inclou Politica de Firmes(o sigui es EPES):\t" + sfi.isPolicyIncluded());
    str.append("\n").append("      * Inclou Segell de Temps:\t" + sfi.isTimeStampIncluded());

    str.append("\n").append("      * eniTipoFirma:\t" + sfi.getEniTipoFirma());
    str.append("\n").append("      * eniPerfilFirma:\t" + sfi.getEniPerfilFirma());
    str.append("\n").append("      * eniRolFirma:\t" + sfi.getEniRolFirma());
    str.append("\n").append("      * eniSignerName:\t" + sfi.getEniSignerName());
    str.append("\n").append(
        "      * eniSignerAdministrationId:\t" + sfi.getEniSignerAdministrationId());
    str.append("\n").append("      * eniSignLevel:\t" + sfi.getEniSignLevel());

    FirmaSimpleCustodyInfo custody = sfi.getCustodyInfo();

    if (custody != null) {

      str.append("\n").append("  + CUSTODIA:");
      str.append("\n").append("      * custodyFileID: " + custody.getCustodyFileID());
      str.append("\n").append("      * custodyFileURL: " + custody.getCustodyFileURL());
      str.append("\n").append("      * custodyFileCSV: " + custody.getCustodyFileCSV());
      str.append("\n").append(
          "      * custodyFileCSVValidationWeb: " + custody.getCustodyFileCSVValidationWeb());
      str.append("\n").append(
          "      * custodyFileCSVGenerationDefinition: "
              + custody.getCustodyFileCSVGenerationDefinition());
    }

    FirmaSimpleValidationInfo validationInfo = sfi.getValidationInfo();
    if (validationInfo != null) {

      str.append("\n").append("  + VALIDACIO:");
      str.append("\n").append(
          "      * CheckAdministrationIDOfSigner: "
              + null2Str(validationInfo.getCheckAdministrationIDOfSigner()));
      str.append("\n").append(
          "      * CheckDocumentModifications: "
              + null2Str(validationInfo.getCheckDocumentModifications()));
      str.append("\n").append(
          "      * CheckValidationSignature: "
              + null2Str(validationInfo.getCheckValidationSignature()));
    }

    List<FirmaSimpleKeyValue> additionInformation = sfi.getAdditionInformation();

    if (additionInformation != null && additionInformation.size() != 0) {
      str.append("\n").append("  + INFORMACIO ADDICIONAL:");
      for (FirmaSimpleKeyValue firmaSimpleKeyValue : additionInformation) {
        str.append("\n").append(
            "      * KEY[" + firmaSimpleKeyValue.getKey() + "]: "
                + firmaSimpleKeyValue.getValue());
      }
    }

    return str.toString();

  }

  private static String null2Str(Boolean b) {
    if (b == null) {
      return "-";
    } else if (b == true) {
      return "SI";
    } else {
      return "NO";
    }
  }

}
