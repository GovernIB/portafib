package es.caib.portafib.api.interna.secure.firma.v1.commons;

import es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor.FirmaSimpleCustodyInfo;
import es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor.FirmaSimpleSignerInfo;
import es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor.FirmaSimpleValidationInfo;
import es.caib.portafib.commons.utils.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Schema(description = "Informació del fitxer signat.")
public class FirmaSimpleSignedFileInfo {

    @Schema(
            description = "Identificador de la firma PAdES.",
            nullable = false,
            defaultValue = "" + Constants.SIGN_TYPE_PADES,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGN_TYPE_PADES = Constants.SIGN_TYPE_PADES;
    
    
    @Schema(
            description = "Identificador de la firma XAdES por defecto.",
            nullable = false,
            defaultValue = "" + Constants.SIGN_TYPE_XADES,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGN_TYPE_XADES = Constants.SIGN_TYPE_XADES;
    
    @Schema(
            description = "Identificador de la firma CAdES.",
            nullable = false,
            defaultValue = "" + Constants.SIGN_TYPE_CADES,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGN_TYPE_CADES = Constants.SIGN_TYPE_CADES;
    
    @Schema(
            description = "Identificador de la firma Factura-e (derivado de XAdES-EPES).",
            nullable = false,
            defaultValue = "" + Constants.SIGN_TYPE_FACTURAE,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGN_TYPE_FACTURAE = Constants.SIGN_TYPE_FACTURAE;
    
    @Schema(
            description = "Identificador de la firma OOXML (<i>Office Open XML</i>).",
            nullable = false,
            defaultValue = "" + Constants.SIGN_TYPE_OOXML,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGN_TYPE_OOXML = Constants.SIGN_TYPE_OOXML;
    
    @Schema(
            description = "Identificador de la firma ODF (<i>Open Document Format</i>).",
            nullable = false,
            defaultValue = "" + Constants.SIGN_TYPE_ODF,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGN_TYPE_ODF = Constants.SIGN_TYPE_ODF;
    
    @Schema(
            description = "Identificador de Firma SMIME",
            nullable = false,
            defaultValue = "" + Constants.SIGN_TYPE_SMIME,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGN_TYPE_SMIME = Constants.SIGN_TYPE_SMIME;
    
    @Schema(
            description = "CAdES-ASiC-S: Formato de firma avanzada ASiC de tipo CAdES.",
            nullable = false,
            defaultValue = "" + Constants.SIGN_TYPE_CADES_ASIC_S,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGN_TYPE_CADES_ASIC_S = Constants.SIGN_TYPE_CADES_ASIC_S;
    
    @Schema(
            description = "XAdES-ASiC-S: Formato de firma avanzada ASiC de tipo XAdES.",
            nullable = false,
            defaultValue = "" + Constants.SIGN_TYPE_XADES_ASIC_S,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGN_TYPE_XADES_ASIC_S = Constants.SIGN_TYPE_XADES_ASIC_S;
    
    @Schema(
            description = "NONE: Firma PKCS#1",
            nullable = false,
            defaultValue = "" + Constants.SIGN_TYPE_PKCS1,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGN_TYPE_PKCS1 = Constants.SIGN_TYPE_PKCS1;
    
    @Schema(
            description = "Identificador d'algoritme de firma SHA-1",
            nullable = false,
            defaultValue = "" + Constants.SIGN_ALGORITHM_SHA1,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGN_ALGORITHM_SHA1 = Constants.SIGN_ALGORITHM_SHA1;
    @Schema(
            description = "Identificador d'algoritme de firma SHA-256",
            nullable = false,
            defaultValue = "" + Constants.SIGN_ALGORITHM_SHA256,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGN_ALGORITHM_SHA256 = Constants.SIGN_ALGORITHM_SHA256;
    @Schema(
            description = "Identificador d'algoritme de firma SHA-384",
            nullable = false,
            defaultValue = "" + Constants.SIGN_ALGORITHM_SHA384,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGN_ALGORITHM_SHA384 = Constants.SIGN_ALGORITHM_SHA384;
    @Schema(
            description = "Identificador d'algoritme de firma SHA-512",
            nullable = false,
            defaultValue = "" + Constants.SIGN_ALGORITHM_SHA512,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGN_ALGORITHM_SHA512 = Constants.SIGN_ALGORITHM_SHA512;

    //========================  MODES DE FIRMA =========================
    // Veure
    // https://ec.europa.eu/digital-building-blocks/DSS/webapp-demo/doc/dss-documentation.html#Packaging
    // veure
    // https://ec.europa.eu/digital-building-blocks/DSS/webapp-demo/doc/dss-documentation.html#SignatureProfileGuide
    @Schema(
            description = "El fitxer de dades resultant inclou la firma: PDF, ODT, ...",
            nullable = false,
            defaultValue = "" + Constants.SIGN_MODE_ATTACHED_ENVELOPED,
            implementation = Integer.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final int SIGN_MODE_ATTACHED_ENVELOPED = Constants.SIGN_MODE_ATTACHED_ENVELOPED;

    @Schema(
            description = "El fitxer resultant serà la firma que incloura les dades originals",
            nullable = false,
            defaultValue = "" + Constants.SIGN_MODE_ATTACHED_ENVELOPING,
            implementation = Integer.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final int SIGN_MODE_ATTACHED_ENVELOPING = Constants.SIGN_MODE_ATTACHED_ENVELOPING;

    
    @Schema(
            description = "El fitxer de firma no inclourà les dades: per separat trobarem un fitxer de firma i el fitxer original",
            nullable = false,
            defaultValue = "" + Constants.SIGN_MODE_DETACHED,
            implementation = Integer.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final int SIGN_MODE_DETACHED = Constants.SIGN_MODE_DETACHED;

    @Schema(
            description = "Firma especial XAdES en que la firma i les dades estan al mateix nivell dins de l'XML: ni la firma inclou les dades ni les dades inclouen la firma",
            nullable = false,
            defaultValue = "" + Constants.SIGN_MODE_INTERNALLY_DETACHED,
            implementation = Integer.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final int SIGN_MODE_INTERNALLY_DETACHED = Constants.SIGN_MODE_INTERNALLY_DETACHED;
    
    @Schema(
            description = "Localitzador de la signatura al document. (Sense signatura visible)",
            nullable = false,
            defaultValue = "" + Constants.SIGNATURESTABLELOCATION_WITHOUT,
            implementation = Integer.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final int SIGNATURESTABLELOCATION_WITHOUT = Constants.SIGNATURESTABLELOCATION_WITHOUT;
    
    @Schema(
            description = "Localitzador de la signatura al document. (Primera pagina)",
            nullable = false,
            defaultValue = "" + Constants.SIGNATURESTABLELOCATION_FIRSTPAGE,
            implementation = Integer.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final int SIGNATURESTABLELOCATION_FIRSTPAGE = Constants.SIGNATURESTABLELOCATION_FIRSTPAGE;
    
    @Schema(
            description = "Localitzador de la signatura al document. (Darrera pagina)",
            nullable = false,
            defaultValue = "" + Constants.SIGNATURESTABLELOCATION_LASTPAGE,
            implementation = Integer.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final int SIGNATURESTABLELOCATION_LASTPAGE = Constants.SIGNATURESTABLELOCATION_LASTPAGE;

    // FIRMA
    @Schema(
            description = "Identificador d'operació per Firma",
            nullable = false,
            defaultValue = "" + Constants.SIGN_OPERATION_SIGN,
            implementation = Integer.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final int SIGN_OPERATION_SIGN = Constants.SIGN_OPERATION_SIGN;
    // COFIRMA
    @Schema(
            description = "Identificador d'operació per Cofirma",
            nullable = false,
            defaultValue = "" + Constants.SIGN_OPERATION_COSIGN,
            implementation = Integer.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final int SIGN_OPERATION_COSIGN = Constants.SIGN_OPERATION_COSIGN;
    // CONTRAFIRMA
    @Schema(
            description = "Identificador d'operació per Contrafirma",
            nullable = false,
            defaultValue = "" + Constants.SIGN_OPERATION_COUNTERSIGN,
            implementation = Integer.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final int SIGN_OPERATION_COUNTERSIGN = Constants.SIGN_OPERATION_COUNTERSIGN;
    
    @Schema(
            description = "Perfil de firma AdES-BES",
            nullable = false,
            defaultValue = "" + Constants.SIGNPROFILE_BES,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGNPROFILE_BES = Constants.SIGNPROFILE_BES;
    @Schema(
            description = "Perfil de firma AdES-EPES",
            nullable = false,
            defaultValue = "" + Constants.SIGNPROFILE_EPES,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGNPROFILE_EPES = Constants.SIGNPROFILE_EPES;
    @Schema(
            description = "Perfil de firma AdES-T",
            nullable = false,
            defaultValue = "" + Constants.SIGNPROFILE_T,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGNPROFILE_T = Constants.SIGNPROFILE_T;
    @Schema(
            description = "Perfil de firma AdES-C",
            nullable = false,
            defaultValue = "" + Constants.SIGNPROFILE_C,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGNPROFILE_C = Constants.SIGNPROFILE_C;
    @Schema(
            description = "Perfil de firma AdES-X",
            nullable = false,
            defaultValue = "" + Constants.SIGNPROFILE_X,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGNPROFILE_X = Constants.SIGNPROFILE_X;
    @Schema(
            description = "Perfil de firma AdES-X1",
            nullable = false,
            defaultValue = "" + Constants.SIGNPROFILE_X1,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGNPROFILE_X1 = Constants.SIGNPROFILE_X1;
    @Schema(
            description = "Perfil de firma AdES-X2",
            nullable = false,
            defaultValue = "" + Constants.SIGNPROFILE_X2,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGNPROFILE_X2 = Constants.SIGNPROFILE_X2;
    @Schema(
            description = "Perfil de firma AdES-XL",
            nullable = false,
            defaultValue = "" + Constants.SIGNPROFILE_XL,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGNPROFILE_XL = Constants.SIGNPROFILE_XL;
    @Schema(
            description = "Perfil de firma AdES-XL1",
            nullable = false,
            defaultValue = "" + Constants.SIGNPROFILE_XL1,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGNPROFILE_XL1 = Constants.SIGNPROFILE_XL1;
    @Schema(
            description = "Perfil de firma AdES-XL2",
            nullable = false,
            defaultValue = "" + Constants.SIGNPROFILE_XL2,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGNPROFILE_XL2 = Constants.SIGNPROFILE_XL2;
    @Schema(
            description = "Perfil de firma AdES-A",
            nullable = false,
            defaultValue = "" + Constants.SIGNPROFILE_A,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGNPROFILE_A = Constants.SIGNPROFILE_A;
    @Schema(
            description = "Perfil de firma PAdES-LTV",
            nullable = false,
            defaultValue = "" + Constants.SIGNPROFILE_PADES_LTV,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGNPROFILE_PADES_LTV = Constants.SIGNPROFILE_PADES_LTV;
    @Schema(
            description = "Perfil de firma PAdES-Basic",
            nullable = false,
            defaultValue = "" + Constants.SIGNPROFILE_PADES_BASIC,
            implementation = String.class,
            requiredMode = RequiredMode.REQUIRED,
            accessMode = AccessMode.READ_ONLY)
    public final String SIGNPROFILE_PADES_BASIC = Constants.SIGNPROFILE_PADES_BASIC;

    /**
     * eEMGDE.Firma.Firmante.EnCalidadDe(eEMGDE17.5.3): Firmante; Cofirmante;
     * Contrafirmante
     *
     */

    @Schema(
            description = "Operació de firma realitzada: Firma (0), Cofirma (1) o Contrafirma (2).",
            example = "1",
             requiredMode = RequiredMode.REQUIRED)
    protected int signOperation;

    @Schema(
            description = "Tipus de Firma. Valors possibles:\r\n" + "    - “PAdES” (Constant SIGN_TYPE_PADES)\r\n"
                    + "    - “XAdES” (Constant SIGN_TYPE_XADES)\r\n" + "    - “CAdES” (Constant SIGN_TYPE_CADES)\r\n"
                    + "    - “FacturaE” (Constant SIGN_TYPE_FACTURAE)\r\n"
                    + "    - “OOXML” (Constant SIGN_TYPE_OOXML)\r\n" + "    - “ODF” (Constant SIGN_TYPE_ODF)\r\n"
                    + "    - “SMIME” (Constant SIGN_TYPE_SMIME)\r\n"
                    + "    - “CAdES-ASiC-S” (Constant SIGN_TYPE_CADES_ASIC_S)\r\n"
                    + "    - “XAdES-ASiC-S” (Constant SIGN_TYPE_XADES_ASIC_S)\r\n"
                    + "    - “PKCS#1” (Constant SIGN_TYPE_PKCS1)",
            example = "PAdES",
             requiredMode = RequiredMode.REQUIRED)
    protected String signType;

    @Schema(
            description = "Algorisme de Firma. Valors: \r\n" + "    - \"SHA-1\"\r\n" + "    - \"SHA-256\"\r\n"
                    + "    - \"SHA-384\"\r\n" + "    - \"SHA-512\"",
            example = "SHA-1",
             requiredMode = RequiredMode.REQUIRED)
    protected String signAlgorithm;

    @Schema(
            description = "Valors:\r\n"
                    + "    - 0: Implicit o Attached. La firma resultante incluye internamente una copia de los datos firmados. \r\n"
                    + "    - 1: Explicit o Detached: La firma resultante no incluye los datos firmados. ",
            example = "0",
             requiredMode = RequiredMode.REQUIRED)
    protected Integer signMode;

    @Schema(
            description = "Posició de la Taula de firmes:\r\n" + "    - 0: Sense taula de firmes\r\n"
                    + "    - 1: Taula de firmes en la 1a pàgina\r\n" + "    - -1: Darrera pàgina",
            example = "1",
             requiredMode = RequiredMode.REQUIRED)
    protected int signaturesTableLocation;

    @Schema(description = "Indica si s'ha afegit un segell de Temps durant la firma", example = "True",  requiredMode = RequiredMode.REQUIRED)
    protected boolean timeStampIncluded;

    // BES o EPES
    @Schema(
            description = "Indica si inclou política de firma (true, EPES) o no (false)",
            example = "True",
             requiredMode = RequiredMode.REQUIRED)
    protected boolean policyIncluded;

    /**
     * eEMGDE.Firma.TipoFirma.FormatoFirma (eEMGDE17.1.1): TF01 (CSV), TF02 (XAdES
     * internally detached signature), TF03 (XAdES enveloped signature), TF04 (CAdES
     * detached/explicit signature), TF05 (CAdES attached/implicit signature), TF06
     * (PAdES)
     * 
     * 
     * Denominación normalizada del tipo de firma. Los posibles valores asignables
     * son los siguientes: TF01 - CSV TF02 - XAdES internally detached signature");
     * TF03 - XAdES enveloped signature. TF04 - CAdES detached/explicit signature.
     * TF05 - CAdES attached/implicit signature. TF06 - PAdES. El tipo TF04 será
     * establecido por defecto para documentos firmados, exceptuando los documentos
     * en formato PDF o PDF/A, cuyo tipo será TF06. MetadataConstants.ENI_TIPO_FIRMA
     * = "eni:tipoFirma";
     * 
     */
    @Schema(
            description = "Denominación normalizada del tipo de firma. Los posibles valores asignables son los siguientes: \r\n"
                    + "    - TF01 - CSV \r\n" + "    - TF02 - XAdES internally detached signature\"); \r\n"
                    + "    - TF03 - XAdES enveloped signature.\r\n"
                    + "    - TF04 - CAdES detached/explicit signature.\r\n"
                    + "    - TF05 – CadES attached/implicit signature.\r\n"
                    + "    - TF06 - PAdES. El tipo TF04 será establecido por defecto para documentos firmados, exceptuando los documentos en formato PDF o PDF/A, cuyo tipo será TF06.",
            example = "TF01",
            requiredMode = RequiredMode.NOT_REQUIRED)
    protected String eniTipoFirma;

    /**
     * - eEMGDE.Firma.TipoFirma.PerfilFirma (eEMGDE17.1.2):
     * 
     * Perfil empleado en una firma con certificado electrónico. Ver constates
     * <code>SIGNPROFILE_</code>
     * 
     * - MetadataConstants.ENI_PERFIL_FIRMA = "eni:perfil_firma";
     */
    @Schema(
            description = "Perfil empleado en una firma con certificado electrónico. Los posibles valores asignables son los siguientes:\r\n"
                    + "  AdES-BES\r\n" + "  AdES-EPES\r\n" + "  AdES-T\r\n" + "  AdES-C\r\n" + "  AdES-X\r\n"
                    + "  AdES-X1\r\n" + "  AdES-X2\r\n" + "  AdES-XL\r\n" + "  AdES-XL1\r\n" + "  AdES-XL2\r\n"
                    + "  AdES-A\r\n" + "  PAdES-LTV\r\n" + "  PAdES-Basic",
            example = "AdES-BES",
            requiredMode = RequiredMode.NOT_REQUIRED)
    protected String eniPerfilFirma;

    @Schema(description = "Informació del signant", requiredMode = RequiredMode.NOT_REQUIRED)
    protected FirmaSimpleSignerInfo signerInfo;

    /**
     * Informacio de Custòdia
     */
    @Schema(description = "Informació de Custòdia", requiredMode = RequiredMode.NOT_REQUIRED)
    protected FirmaSimpleCustodyInfo custodyInfo = null;

    /**
     * Informació de les validacions realitzades
     */
    @Schema(description = "Informació de les validacions realitzades despres de la firma.", requiredMode = RequiredMode.NOT_REQUIRED)
    protected FirmaSimpleValidationInfo validationInfo = null;

    public FirmaSimpleSignedFileInfo() {
        super();
    }

    public FirmaSimpleSignedFileInfo(int signOperation, String signType, String signAlgorithm, Integer signMode,
            int signaturesTableLocation, boolean timeStampIncluded, boolean policyIncluded, String eniTipoFirma,
            String eniPerfilFirma, FirmaSimpleSignerInfo signerInfo, FirmaSimpleCustodyInfo custodyInfo,
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

}
