package es.caib.portafib.api.interna.secure.signature.v1.commons;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Schema(description = "Informació del fitxer signat.")
public class SignedFileInfo {

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
            description = "Tipus de Firma. Valors possibles veure SignTypeConstants.",
            requiredMode = RequiredMode.REQUIRED)
    protected String signType;

    @Schema(
            description = "Algorisme de Firma. Valors: \r\n" + "    - \"SHA-1\"\r\n" + "    - \"SHA-256\"\r\n"
                    + "    - \"SHA-384\"\r\n" + "    - \"SHA-512\"",
            example = "SHA-1",
            requiredMode = RequiredMode.REQUIRED)
    protected String signAlgorithm;

    /**   TODO XYZ ZZZ  Actualitzar Informacio !!!!!!   **/
    @Schema(
            description = "Mode de firma. Valors veure SignModeConstants. Exemple SignModeConstants.SIGN_MODE_ATTACHED_ENVELOPED.value()",
            example = "0",
            requiredMode = RequiredMode.REQUIRED)
    protected int signMode;

    @Schema(
            description = "Posició de la Taula de firmes:Veure classe SignaturesTableLocationConstants",
            example = "1",
            requiredMode = RequiredMode.REQUIRED)
    protected int signaturesTableLocation;

    @Schema(
            description = "Indica si s'ha afegit un segell de Temps durant la firma",
            example = "True",
            requiredMode = RequiredMode.REQUIRED)
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

    @Schema(description = "Informació del signant o signants", requiredMode = RequiredMode.NOT_REQUIRED)
    protected List<SignerInfo> signers;

    /**
     * Informacio de Custòdia
     */
    @Schema(description = "Informació de Custòdia", requiredMode = RequiredMode.NOT_REQUIRED)
    protected CustodyInfo custodyInfo = null;

    /**
     * Informació de les validacions realitzades
     */
    @Schema(
            description = "Informació de les validacions realitzades despres de la firma.",
            requiredMode = RequiredMode.NOT_REQUIRED)
    protected ValidationInfo validationInfo = null;

    public SignedFileInfo() {
        super();
    }

    public SignedFileInfo(int signOperation, String signType, String signAlgorithm, int signMode,
            int signaturesTableLocation, boolean timeStampIncluded, boolean policyIncluded, String eniTipoFirma,
            String eniPerfilFirma, SignerInfo signerInfo, CustodyInfo custodyInfo, ValidationInfo validationInfo) {
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
        this.signers = new ArrayList<SignerInfo>();
        this.signers.add(signerInfo);
        this.custodyInfo = custodyInfo;
        this.validationInfo = validationInfo;
    }

    public SignedFileInfo(int signOperation, String signType, String signAlgorithm, int signMode,
            int signaturesTableLocation, boolean timeStampIncluded, boolean policyIncluded, String eniTipoFirma,
            String eniPerfilFirma, List<SignerInfo> signers, CustodyInfo custodyInfo, ValidationInfo validationInfo) {
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
        this.signers = signers;
        this.custodyInfo = custodyInfo;
        this.validationInfo = validationInfo;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public int getSignOperation() {
        return signOperation;
    }

    public void setSignOperation(int signOperation) {
        this.signOperation = signOperation;
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

    public List<SignerInfo> getSigners() {
        return signers;
    }

    public void setSigners(List<SignerInfo> signers) {
        this.signers = signers;
    }

    public CustodyInfo getCustodyInfo() {
        return custodyInfo;
    }

    public void setCustodyInfo(CustodyInfo custodyInfo) {
        this.custodyInfo = custodyInfo;
    }

    public ValidationInfo getValidationInfo() {
        return validationInfo;
    }

    public void setValidationInfo(ValidationInfo validationInfo) {
        this.validationInfo = validationInfo;
    }

}
