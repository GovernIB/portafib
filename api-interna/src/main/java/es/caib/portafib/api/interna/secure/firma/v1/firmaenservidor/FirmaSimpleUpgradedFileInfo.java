package es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor;

import java.util.List;


import es.caib.portafib.api.interna.secure.firma.v1.commons.KeyValue;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 *
 */
public class FirmaSimpleUpgradedFileInfo {
    
    @Schema(
            description = "Tipus de Firma. Valors possibles:\r\n"
                    + "    • “PAdES” (Constant SIGN_TYPE_PADES)\r\n"
                    + "    • “XAdES” (Constant SIGN_TYPE_XADES)\r\n"
                    + "    • “CAdES” (Constant SIGN_TYPE_CADES)\r\n"
                    + "    • “FacturaE” (Constant SIGN_TYPE_FACTURAE)\r\n"
                    + "    • “OOXML” (Constant SIGN_TYPE_OOXML)\r\n"
                    + "    • “ODF” (Constant SIGN_TYPE_ODF)\r\n"
                    + "    • “SMIME” (Constant SIGN_TYPE_SMIME)\r\n"
                    + "    • “CAdES-ASiC-S” (Constant SIGN_TYPE_CADES_ASIC_S)\r\n"
                    + "    • “XAdES-ASiC-S” (Constant SIGN_TYPE_XADES_ASIC_S)\r\n"
                    + "    • “PKCS#1” (Constant SIGN_TYPE_PKCS1)",
            example = "PAdES",
            requiredMode = RequiredMode.REQUIRED)
	protected String signType;
    
    @Schema(
            description = "Algorisme de Firma. Valors:\r\n"
                    + "    • \"SHA-1\"\r\n"
                    + "    • \"SHA-256\"\r\n"
                    + "    • \"SHA-384\"\r\n"
                    + "    • \"SHA-512\"",
            example = "SHA-1",
            requiredMode = RequiredMode.REQUIRED)
	protected String signAlgorithm;

    @Schema(
            description = "Mode de firma attached (0) o detached (1)",
            example = "0",
            requiredMode = RequiredMode.REQUIRED)
	protected Integer signMode;

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
	protected String eniTipoFirma;

	/**
	 * - eEMGDE.Firma.TipoFirma.PerfilFirma (eEMGDE17.1.2): 1.- Para las firmas
	 * XADES y CADES: EPES, T, C, X, XL, A, BASELINE B-Level, BASELINE T-Level,
	 * BASELINE LT-Level, BASELINE LTA-Level. 2.- Para las firmas PADES: EPES, LTV,
	 * BASELINE B-Level, BASELINE T
	 * 
	 * Perfil empleado en una firma con certificado electrónico. Los posibles
	 * valores asignables son los siguientes: EPES T C X XL A BASELINE B-Level
	 * BASELINE LT-Level BASELINE LTA-Level BASELINE T-Level LTV
	 * 
	 * - MetadataConstants.ENI_PERFIL_FIRMA = "eni:perfil_firma";
	 */
	protected String eniPerfilFirma;

	/**
	 * Informació de les validacions realitzades
	 */
	protected FirmaSimpleValidationInfo validationInfo = null;

	/**
	 * Ofrecer cualquier otra información que se considere útil acerca del firmante
	 * o la actualización de la firma.
	 */
	protected List<KeyValue> additionInformation = null;

	public FirmaSimpleUpgradedFileInfo() {
		super();
	}

	public FirmaSimpleUpgradedFileInfo(String signType, String signAlgorithm, Integer signMode, String eniTipoFirma,
			String eniPerfilFirma, FirmaSimpleValidationInfo validationInfo,
			List<KeyValue> additionInformation) {
		super();
		this.signType = signType;
		this.signAlgorithm = signAlgorithm;
		this.signMode = signMode;
		this.eniTipoFirma = eniTipoFirma;
		this.eniPerfilFirma = eniPerfilFirma;
		this.validationInfo = validationInfo;
		this.additionInformation = additionInformation;
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

	public FirmaSimpleValidationInfo getValidationInfo() {
		return validationInfo;
	}

	public void setValidationInfo(FirmaSimpleValidationInfo validationInfo) {
		this.validationInfo = validationInfo;
	}

	public List<KeyValue> getAdditionInformation() {
		return additionInformation;
	}

	public void setAdditionInformation(List<KeyValue> additionInformation) {
		this.additionInformation = additionInformation;
	}

}
