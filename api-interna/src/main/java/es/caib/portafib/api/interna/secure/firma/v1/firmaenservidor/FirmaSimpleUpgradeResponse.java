package es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor;

import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleFile;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleUpgradedFileInfo;

/**
 * Resultat d'una actualització de firma
 * 
 * @author anadal
 *
 */
public class FirmaSimpleUpgradeResponse {

	protected FirmaSimpleFile upgradedFile;

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
