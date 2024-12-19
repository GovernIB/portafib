package es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 *
 */
@Schema(description = "Informació de les validacions realitzades despres de la firma.", required = false)
public class FirmaSimpleValidationInfo {
    
    @Schema(description = "S’ha verificat que l’identificador del firmant és la del que estava "
            + "previst que firmàs. Valor buit indica que no s'ha realitzat la validació.", required = false)
	protected Boolean checkAdministrationIDOfSigner;

    @Schema(description = "S’ha verificat que no s’hagi modificat el document original."
            + " Valor buit indica que no s'ha realitzat la validació.", required = false)
	protected Boolean checkDocumentModifications;

    @Schema(description = "S’ha verificat que la firma és correcte. Valor buit indica que no s'ha realitzat la validació.", required = false)
	protected Boolean checkValidationSignature;

	/**
	 * Només s'omple si checkValidationSignature val false
	 */
    @Schema(description = "Només s'omple si checkValidationSignature val false", required = false)
	protected String noCheckValidationReason;

	public FirmaSimpleValidationInfo() {
		super();
	}

	public FirmaSimpleValidationInfo(Boolean checkAdministrationIDOfSigner, Boolean checkDocumentModifications,
			Boolean checkValidationSignature, String noCheckValidationReason) {
		super();
		this.checkAdministrationIDOfSigner = checkAdministrationIDOfSigner;
		this.checkDocumentModifications = checkDocumentModifications;
		this.checkValidationSignature = checkValidationSignature;
		this.noCheckValidationReason = noCheckValidationReason;
	}

	public Boolean getCheckAdministrationIDOfSigner() {
		return checkAdministrationIDOfSigner;
	}

	public void setCheckAdministrationIDOfSigner(Boolean checkAdministrationIDOfSigner) {
		this.checkAdministrationIDOfSigner = checkAdministrationIDOfSigner;
	}

	public Boolean getCheckDocumentModifications() {
		return checkDocumentModifications;
	}

	public void setCheckDocumentModifications(Boolean checkDocumentModifications) {
		this.checkDocumentModifications = checkDocumentModifications;
	}

	public Boolean getCheckValidationSignature() {
		return checkValidationSignature;
	}

	public void setCheckValidationSignature(Boolean checkValidationSignature) {
		this.checkValidationSignature = checkValidationSignature;
	}

	public String getNoCheckValidationReason() {
		return noCheckValidationReason;
	}

	public void setNoCheckValidationReason(String noCheckValidationReason) {
		this.noCheckValidationReason = noCheckValidationReason;
	}

}
