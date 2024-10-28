package es.caib.portafib.api.interna.secure.apisimple.v1.commons;

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

//========================  MODES DE FIRMA =========================
  // Veure https://ec.europa.eu/digital-building-blocks/DSS/webapp-demo/doc/dss-documentation.html#Packaging
  // veure https://ec.europa.eu/digital-building-blocks/DSS/webapp-demo/doc/dss-documentation.html#SignatureProfileGuide

  /** El fitxer de dades resultant inclou la firma: PDF, ODT, ... */
  public static final int SIGN_MODE_ATTACHED_ENVELOPED = 0;

  /** El fitxer resultant serà la firma que incloura les dades originals */
  public static final int SIGN_MODE_ATTACHED_ENVELOPING = 3;

  /** El fitxer de firma no inclourà les dades: per separat trobarem un fitxer de firma i el fitxer original */
  public static final int SIGN_MODE_DETACHED = 1;

  /** Firma especial XAdES en que la firma i les dades estan al mateix nivell dins de l'XML: ni la firma inclou les dades ni les dades inclouen la firma */
  public static final int SIGN_MODE_INTERNALLY_DETACHED = 4;
  
  /*
   * implicit La firma resultante incluirá internamente una copia de los datos firmados. El uso
   * de este valor podría generar firmas de gran tamaño.
   */
  @Deprecated
  public static final int SIGN_MODE_IMPLICIT_ATTACHED = SIGN_MODE_ATTACHED_ENVELOPED;
  /*
   * explicit La firma resultante no incluirá los datos firmados.
   */
  @Deprecated
  public static final int SIGN_MODE_EXPLICIT_DETACHED = SIGN_MODE_DETACHED;

  public static final int SIGNATURESTABLELOCATION_WITHOUT = 0;
  public static final int SIGNATURESTABLELOCATION_FIRSTPAGE = 1;
  public static final int SIGNATURESTABLELOCATION_LASTPAGE = -1;

  // FIRMA
  public static final int SIGN_OPERATION_SIGN = 0;
  // COFIRMA
  public static final int SIGN_OPERATION_COSIGN = 1;
  // CONTRAFIRMA
  public static final int SIGN_OPERATION_COUNTERSIGN = 2;

  public static final String SIGNPROFILE_BES = "AdES-BES";
  public static final String SIGNPROFILE_EPES = "AdES-EPES";
  public static final String SIGNPROFILE_T = "AdES-T";
  public static final String SIGNPROFILE_C = "AdES-C";
  public static final String SIGNPROFILE_X = "AdES-X";
  public static final String SIGNPROFILE_X1 = "AdES-X1";
  public static final String SIGNPROFILE_X2 = "AdES-X2";
  public static final String SIGNPROFILE_XL = "AdES-XL";
  public static final String SIGNPROFILE_XL1 = "AdES-XL1";
  public static final String SIGNPROFILE_XL2 = "AdES-XL2";
  public static final String SIGNPROFILE_A = "AdES-A";
  public static final String SIGNPROFILE_PADES_LTV = "PAdES-LTV";
  public static final String SIGNPROFILE_PADES_BASIC = "PAdES-Basic";

  /**
   * eEMGDE.Firma.Firmante.EnCalidadDe(eEMGDE17.5.3): Firmante; Cofirmante; Contrafirmante
   *
   */
  protected int signOperation;

  protected String signType;

  protected String signAlgorithm;

  protected Integer signMode;

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
  protected String eniTipoFirma;

  /**
   * - eEMGDE.Firma.TipoFirma.PerfilFirma (eEMGDE17.1.2):
   * 
   * Perfil empleado en una firma con certificado electrónico. Ver constates <code>SIGNPROFILE_</code>
   * 
   * - MetadataConstants.ENI_PERFIL_FIRMA = "eni:perfil_firma";
   */
  protected String eniPerfilFirma;

  protected FirmaSimpleSignerInfo signerInfo;

  /**
   * Informacio de Custòdia
   */
  protected FirmaSimpleCustodyInfo custodyInfo = null;

  /**
   * Informació de les validacions realitzades
   */
  protected FirmaSimpleValidationInfo validationInfo = null;

  public FirmaSimpleSignedFileInfo() {
    super();
  }

  public FirmaSimpleSignedFileInfo(int signOperation, String signType, String signAlgorithm,
      Integer signMode, int signaturesTableLocation, boolean timeStampIncluded,
      boolean policyIncluded, String eniTipoFirma, String eniPerfilFirma,
      FirmaSimpleSignerInfo signerInfo, FirmaSimpleCustodyInfo custodyInfo,
      FirmaSimpleValidationInfo validationInfo) {
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
    this.signerInfo = signerInfo;
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

  public FirmaSimpleSignerInfo getSignerInfo() {
    return signerInfo;
  }

  public void setSignerInfo(FirmaSimpleSignerInfo signerInfo) {
    this.signerInfo = signerInfo;
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

  public static String toString(FirmaSimpleSignedFileInfo sfi) {
    StringBuilder str = new StringBuilder("  + INFORMACIO:");

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
    str.append("\n").append("      * Operacio:\t").append(operation);

    str.append("\n").append("      * Tipus:\t").append(sfi.getSignType());

    str.append("\n").append("      * Algorisme:\t").append(sfi.getSignAlgorithm());

    str.append("\n").append("      * Mode:\t");
    if (sfi.getSignMode() == null) {
      str.append("NULL");
    } else {
      str.append((sfi.getSignMode() == FirmaSimpleSignedFileInfo.SIGN_MODE_IMPLICIT_ATTACHED) ? "Attached - Implicit"
          : "Detached- Explicit");
    }

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
    str.append("\n").append("      * Posicio Taula De Firmes:\t").append(posicioTaulaDeFirmes);

    str.append("\n").append("      * Inclou Politica de Firmes(o sigui es EPES):\t").append(sfi.isPolicyIncluded());
    str.append("\n").append("      * Inclou Segell de Temps:\t").append(sfi.isTimeStampIncluded());

    str.append("\n").append("      * eniTipoFirma:\t").append(sfi.getEniTipoFirma());
    str.append("\n").append("      * eniPerfilFirma:\t").append(sfi.getEniPerfilFirma());
    if (sfi.getSignerInfo() != null) {
      str.append("\n").append("      * Informacio del Firmant:\t");
      str.append("\n").append(sfi.getSignerInfo().toString());
    }

    FirmaSimpleCustodyInfo custody = sfi.getCustodyInfo();

    if (custody != null) {

      str.append("\n").append("  + CUSTODIA:");
      str.append("\n").append("      * custodyID: ").append(custody.getCustodyID());
      str.append("\n").append("      * CSV: ").append(custody.getCsv());
      str.append("\n").append("      * CSVValidationWeb: ").append(custody.getCsvValidationWeb());
      str.append("\n").append("      * ValidationFileUrl: ").append(custody.getValidationFileUrl());
      str.append("\n").append("      * CSVGenerationDefinition(eEMGDE17.4): ").append(custody.getCsvGenerationDefinition());
      str.append("\n").append("      * originalFileDirectURL: ").append(custody.getOriginalFileDirectURL());
      str.append("\n").append("      * printableFileDirectUrl: ").append(custody.getPrintableFileDirectUrl());
      str.append("\n").append("      * eniFileDirectUrl: ").append(custody.getEniFileDirectUrl());
    }

    FirmaSimpleValidationInfo validationInfo = sfi.getValidationInfo();
    if (validationInfo != null) {

      str.append("\n").append("  + VALIDACIO:");
      str.append("\n").append("      * CheckAdministrationIDOfSigner: ").append(null2Str(validationInfo.getCheckAdministrationIDOfSigner()));
      str.append("\n").append("      * CheckDocumentModifications: ").append(null2Str(validationInfo.getCheckDocumentModifications()));
      str.append("\n").append("      * CheckValidationSignature: ").append(null2Str(validationInfo.getCheckValidationSignature()));

      if (validationInfo.getNoCheckValidationReason() != null) {
        str.append("\n").append("      * No Validation reason: ").append(validationInfo.getNoCheckValidationReason());
      }

    }

    return str.toString();

  }

  public static String null2Str(Boolean b) {
    if (b == null) {
      return "-";
    }

    return b ? "SI" : "NO";
  }

}
