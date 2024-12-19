package es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor;

import java.util.List;

import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleSignedFileInfo;
import es.caib.portafib.api.interna.secure.firma.v1.commons.KeyValue;

/**
 * 
 * @author anadal
 *
 */
public class FirmaSimpleUpgradedFileInfo {

	protected String signType;

	protected String signAlgorithm;

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

	public static String toString(FirmaSimpleUpgradedFileInfo sfi) {
		StringBuffer str = new StringBuffer("  + INFORMACIO:");

		str.append("\n").append("      * Tipus:\t" + sfi.getSignType());

		str.append("\n").append("      * Algorisme:\t" + sfi.getSignAlgorithm());

		str.append("\n").append("      * Mode:\t");
		if (sfi.getSignMode() == null) {
			str.append("NULL");
		} else {
			str.append((sfi.getSignMode() == FirmaSimpleSignedFileInfo.SIGN_MODE_ATTACHED_ENVELOPED)
					? "Attached - Implicit"
					: "Detached- Explicit");
		}

		str.append("\n").append("      * eniTipoFirma:\t" + sfi.getEniTipoFirma());
		str.append("\n").append("      * eniPerfilFirma:\t" + sfi.getEniPerfilFirma());

		FirmaSimpleValidationInfo validationInfo = sfi.getValidationInfo();
		if (validationInfo != null) {

			str.append("\n").append("  + VALIDACIO:");
			str.append("\n").append("      * CheckAdministrationIDOfSigner: "
					+ FirmaSimpleSignedFileInfo.null2Str(validationInfo.getCheckAdministrationIDOfSigner()));
			str.append("\n").append("      * CheckDocumentModifications: "
					+ FirmaSimpleSignedFileInfo.null2Str(validationInfo.getCheckDocumentModifications()));
			str.append("\n").append("      * CheckValidationSignature: "
					+ FirmaSimpleSignedFileInfo.null2Str(validationInfo.getCheckValidationSignature()));
		}

		List<KeyValue> additionInformation = sfi.getAdditionInformation();

		if (additionInformation != null && additionInformation.size() != 0) {
			str.append("\n").append("  + INFORMACIO ADDICIONAL:");
			for (KeyValue firmaSimpleKeyValue : additionInformation) {
				str.append("\n")
						.append("      * KEY[" + firmaSimpleKeyValue.getKey() + "]: " + firmaSimpleKeyValue.getValue());
			}
		}

		return str.toString();

	}

}
