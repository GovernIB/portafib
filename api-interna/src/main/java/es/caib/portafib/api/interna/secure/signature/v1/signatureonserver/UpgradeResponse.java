package es.caib.portafib.api.interna.secure.signature.v1.signatureonserver;

import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * Resultat d'una actualització de firma
 * 
 * @author anadal
 *
 */
public class UpgradeResponse {
    
    @Schema(
            description = "Firma actualitzada",
            example = "",
            requiredMode = RequiredMode.NOT_REQUIRED)
	protected Document upgradedFile;
    
    @Schema(
            description = "Informació de la firma actualitzada",
            example = "",
            requiredMode = RequiredMode.NOT_REQUIRED)
	protected UpgradedFileInfo upgradedFileInfo;

	/**
	 * 
	 */
	public UpgradeResponse() {
		super();
	}

	public UpgradeResponse(Document upgradedFile, UpgradedFileInfo upgradedFileInfo) {
		super();
		this.upgradedFile = upgradedFile;
		this.upgradedFileInfo = upgradedFileInfo;
	}

	public Document getUpgradedFile() {
		return upgradedFile;
	}

	public void setUpgradedFile(Document upgradedFile) {
		this.upgradedFile = upgradedFile;
	}

	public UpgradedFileInfo getUpgradedFileInfo() {
		return upgradedFileInfo;
	}

	public void setUpgradedFileInfo(UpgradedFileInfo upgradedFileInfo) {
		this.upgradedFileInfo = upgradedFileInfo;
	}

}
