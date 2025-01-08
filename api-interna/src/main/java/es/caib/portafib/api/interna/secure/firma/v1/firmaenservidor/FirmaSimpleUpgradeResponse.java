package es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor;

import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleFile;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * Resultat d'una actualització de firma
 * 
 * @author anadal
 *
 */
public class FirmaSimpleUpgradeResponse {
    
    @Schema(
            description = "Firma actualitzada",
            example = "",
            requiredMode = RequiredMode.NOT_REQUIRED)
	protected FirmaSimpleFile upgradedFile;
    
    @Schema(
            description = "Informació de la firma actualitzada",
            example = "",
            requiredMode = RequiredMode.NOT_REQUIRED)
	protected FirmaSimpleUpgradedFileInfo upgradedFileInfo;

	/**
	 * 
	 */
	public FirmaSimpleUpgradeResponse() {
		super();
	}

	public FirmaSimpleUpgradeResponse(FirmaSimpleFile upgradedFile, FirmaSimpleUpgradedFileInfo upgradedFileInfo) {
		super();
		this.upgradedFile = upgradedFile;
		this.upgradedFileInfo = upgradedFileInfo;
	}

	public FirmaSimpleFile getUpgradedFile() {
		return upgradedFile;
	}

	public void setUpgradedFile(FirmaSimpleFile upgradedFile) {
		this.upgradedFile = upgradedFile;
	}

	public FirmaSimpleUpgradedFileInfo getUpgradedFileInfo() {
		return upgradedFileInfo;
	}

	public void setUpgradedFileInfo(FirmaSimpleUpgradedFileInfo upgradedFileInfo) {
		this.upgradedFileInfo = upgradedFileInfo;
	}

}
