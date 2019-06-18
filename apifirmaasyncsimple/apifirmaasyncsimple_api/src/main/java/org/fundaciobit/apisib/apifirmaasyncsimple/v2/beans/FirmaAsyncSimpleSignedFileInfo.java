package org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaAsyncSimpleSignedFileInfo {

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
   * explicit La firma resultante no incluirá los datos firmados.
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

  protected Integer signMode;

  protected int signaturesTableLocation;

  // NO=false o SI=true, null si no ho sap
  protected Boolean timeStampIncluded;

  // BES=false o EPES=true, null si no ho sap
  protected Boolean policyIncluded;

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
  protected String eniPerfilFirma;

  protected List<FirmaAsyncSimpleSignerInfo> signersInfo;

  /**
   * Informacio de Custòdia
   */
  protected FirmaAsyncSimpleCustodyInfo custodyInfo = null;

  /**
   * Informació de les validacions realitzades
   */
  protected FirmaAsyncSimpleValidationInfo validationInfo = null;

  public FirmaAsyncSimpleSignedFileInfo() {
    super();
  }

  public FirmaAsyncSimpleSignedFileInfo(int signOperation, String signType,
      String signAlgorithm, Integer signMode, int signaturesTableLocation,
      Boolean timeStampIncluded, Boolean policyIncluded, String eniTipoFirma,
      String eniPerfilFirma, List<FirmaAsyncSimpleSignerInfo> signersInfo,
      FirmaAsyncSimpleCustodyInfo custodyInfo, FirmaAsyncSimpleValidationInfo validationInfo) {
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
    this.signersInfo = signersInfo;
    this.custodyInfo = custodyInfo;
    this.validationInfo = validationInfo;
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

  public Integer getSignMode() {
    return signMode;
  }

  public void setSignMode(Integer signMode) {
    this.signMode = signMode;
  }

  public int getSignaturesTableLocation() {
    return signaturesTableLocation;
  }

  public void setSignaturesTableLocation(int signaturesTableLocation) {
    this.signaturesTableLocation = signaturesTableLocation;
  }

  public Boolean getTimeStampIncluded() {
    return timeStampIncluded;
  }

  public void setTimeStampIncluded(Boolean timeStampIncluded) {
    this.timeStampIncluded = timeStampIncluded;
  }

  public Boolean getPolicyIncluded() {
    return policyIncluded;
  }

  public void setPolicyIncluded(Boolean policyIncluded) {
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

  public FirmaAsyncSimpleCustodyInfo getCustodyInfo() {
    return custodyInfo;
  }

  public void setCustodyInfo(FirmaAsyncSimpleCustodyInfo custodyInfo) {
    this.custodyInfo = custodyInfo;
  }

  public FirmaAsyncSimpleValidationInfo getValidationInfo() {
    return validationInfo;
  }

  public void setValidationInfo(FirmaAsyncSimpleValidationInfo validationInfo) {
    this.validationInfo = validationInfo;
  }

  public List<FirmaAsyncSimpleSignerInfo> getSignersInfo() {
    return signersInfo;
  }

  public void setSignersInfo(List<FirmaAsyncSimpleSignerInfo> signersInfo) {
    this.signersInfo = signersInfo;
  }

  public static String toString(FirmaAsyncSimpleSignedFileInfo sfi) {
    StringBuffer str = new StringBuffer("  + INFORMACIO:");

    String operation;
    switch (sfi.getSignOperation()) {
      case FirmaAsyncSimpleSignedFileInfo.SIGN_OPERATION_SIGN:
        operation = "FIRMA";
      break;
      case FirmaAsyncSimpleSignedFileInfo.SIGN_OPERATION_COSIGN:
        operation = "COFIRMA";
      break;

      case FirmaAsyncSimpleSignedFileInfo.SIGN_OPERATION_COUNTERSIGN:
        operation = "CONTRAFIRMA";
      break;

      default:
        operation = "DESCONEGUDA (" + sfi.getSignOperation() + ")";
    }
    str.append("\n").append("      * Operacio:\t" + operation);

    str.append("\n").append("      * Tipus:\t" + sfi.getSignType());

    str.append("\n").append("      * Algorisme:\t" + sfi.getSignAlgorithm());

    str.append("\n").append("      * Mode:\t");
    if (sfi.getSignMode() == null) {
      str.append("NULL");
    } else {
      str.append((sfi.getSignMode() == FirmaAsyncSimpleSignedFileInfo.SIGN_MODE_IMPLICIT_ATTACHED) ? "Attached - Implicit"
          : "Detached- Explicit");
    }

    String posicioTaulaDeFirmes;
    switch (sfi.getSignaturesTableLocation()) {

      case FirmaAsyncSimpleSignedFileInfo.SIGNATURESTABLELOCATION_WITHOUT:
        posicioTaulaDeFirmes = "Sense taula de Firmes";
      break;
      case FirmaAsyncSimpleSignedFileInfo.SIGNATURESTABLELOCATION_FIRSTPAGE:
        posicioTaulaDeFirmes = "Taula de Firmes en la primera pagina";
      break;
      case FirmaAsyncSimpleSignedFileInfo.SIGNATURESTABLELOCATION_LASTPAGE:
        posicioTaulaDeFirmes = "Taula de Firmes en la darrera pagina";
      break;

      default:
        posicioTaulaDeFirmes = "Desconeguda(" + sfi.getSignaturesTableLocation() + ")";

    }
    str.append("\n").append("      * Posicio Taula De Firmes:\t" + posicioTaulaDeFirmes);

    str.append("\n").append(
        "      * Inclou Politica de Firmes(es EPES?):\t" + sfi.getPolicyIncluded());
    str.append("\n").append("      * Inclou Segell de Temps:\t" + sfi.getTimeStampIncluded());

    str.append("\n").append("      * eniTipoFirma:\t" + sfi.getEniTipoFirma());
    str.append("\n").append("      * eniPerfilFirma:\t" + sfi.getEniPerfilFirma());

    str.append("\n").append("  + INFORMACIO FIRMANTS:");

    List<FirmaAsyncSimpleSignerInfo> signants = sfi.getSignersInfo();
    if (signants != null && signants.size() != 0) {
      int count = 1;
      for (FirmaAsyncSimpleSignerInfo firmaAsyncSimpleSignerInfo : signants) {
        str.append("\n").append("      * Signant [" + count + "]");
        str.append("\n").append(firmaAsyncSimpleSignerInfo.toString());
      }

    } else {
      str.append("\n").append("      <<< NO HI HA INFO DE FIRMANTS >>>");
    }

    FirmaAsyncSimpleCustodyInfo custody = sfi.getCustodyInfo();

    if (custody != null) {
      str.append("\n").append("  + CUSTODIA:");
      str.append("\n").append("      * custodyID: " + custody.getCustodyID());
      str.append("\n").append("      * CSV: " + custody.getCsv());
      str.append("\n").append("      * CSVValidationWeb: " + custody.getCsvValidationWeb());
      str.append("\n").append("      * ValidationFileUrl: " + custody.getValidationFileUrl());
      str.append("\n").append(
          "      * CSVGenerationDefinition(eEMGDE17.4): "
              + custody.getCsvGenerationDefinition());
      str.append("\n").append(
          "      * originalFileDirectURL: " + custody.getOriginalFileDirectURL());
      str.append("\n").append(
          "      * printableFileDirectUrl: " + custody.getPrintableFileDirectUrl());
      str.append("\n").append("      * eniFileDirectUrl: " + custody.getEniFileDirectUrl()); 
    }

    FirmaAsyncSimpleValidationInfo validationInfo = sfi.getValidationInfo();
    str.append("\n").append("  + VALIDACIO:");
    if (validationInfo == null) {
      str.append("\n").append("      <<< NO HI HA INFO DE VALIDACIO >>>");
    } else {

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

    return str.toString();

  }

  public static String null2Str(Boolean b) {
    if (b == null) {
      return "-";
    } else if (b == true) {
      return "SI";
    } else {
      return "NO";
    }
  }

}
