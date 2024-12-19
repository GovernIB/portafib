package es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 *
 */
@Schema(description = "Informació de Custòdia")
public class FirmaSimpleCustodyInfo {
    
    @Schema(description = "Identificador del sistema de custòdia",  requiredMode = RequiredMode.REQUIRED)
	protected String custodyID = null;

	// String getCsv
	/** eEMGDE.Firma.FormatoFirma.ValorCSV (eEMGDE17.3) */
    @Schema(description = "URL on descarregar directament el fitxer signat", requiredMode = RequiredMode.NOT_REQUIRED)
	protected String csv;

	// String getCsvValidationWeb
    @Schema(description = "Codi Segur de Validació d'aquest document custodiat.", requiredMode = RequiredMode.NOT_REQUIRED)
	protected String csvValidationWeb;

	// String getValidationFileUrl
    @Schema(description = "URL on descarregar-se la definició de com es genera el CSV", requiredMode = RequiredMode.NOT_REQUIRED)
	protected String validationFileUrl;

	// getCsvGenerationDefinition
	/**
	 * eEMGDE.DefinicionGeneracionCSV (eEMGDE17.4):
	 */
    @Schema(description = "Pàgina web on validar el document, normalment a partir de CSV", requiredMode = RequiredMode.NOT_REQUIRED)
	protected String csvGenerationDefinition;

	// String getOriginalFileUrl
    @Schema(description = "URL al document original", requiredMode = RequiredMode.NOT_REQUIRED)
	protected String originalFileDirectURL; //

	// String getPrintableFileUrl
    @Schema(description = "URL al document en format per imprimir", requiredMode = RequiredMode.NOT_REQUIRED)
	protected String printableFileDirectUrl;

	// String getEniFileUrl
    @Schema(description = "Url al eni File", requiredMode = RequiredMode.NOT_REQUIRED)
	protected String eniFileDirectUrl;

	// Futura Integració amb Api d'Arxiu
    @Schema(description = "Id del expedient si és un expedient o esta relacionat amb un expedient.", requiredMode = RequiredMode.NOT_REQUIRED)
	protected String expedientID = null;

	// Futura Integració amb Api d'Arxiu
    @Schema(description = "Id del document a signar", requiredMode = RequiredMode.NOT_REQUIRED)
	protected String documentID = null;

	public FirmaSimpleCustodyInfo() {
		super();
	}

	public FirmaSimpleCustodyInfo(String custodyID, String csv, String csvValidationWeb, String validationFileUrl,
			String csvGenerationDefinition, String originalFileDirectURL, String printableFileDirectUrl,
			String eniFileDirectUrl) {
		super();
		this.custodyID = custodyID;
		this.csv = csv;
		this.csvValidationWeb = csvValidationWeb;
		this.validationFileUrl = validationFileUrl;
		this.csvGenerationDefinition = csvGenerationDefinition;
		this.originalFileDirectURL = originalFileDirectURL;
		this.printableFileDirectUrl = printableFileDirectUrl;
		this.eniFileDirectUrl = eniFileDirectUrl;
	}

	public FirmaSimpleCustodyInfo(String custodyID, String csv, String csvValidationWeb, String validationFileUrl,
			String csvGenerationDefinition, String originalFileDirectURL, String printableFileDirectUrl,
			String eniFileDirectUrl, String expedientID, String documentID) {
		super();
		this.custodyID = custodyID;
		this.csv = csv;
		this.csvValidationWeb = csvValidationWeb;
		this.validationFileUrl = validationFileUrl;
		this.csvGenerationDefinition = csvGenerationDefinition;
		this.originalFileDirectURL = originalFileDirectURL;
		this.printableFileDirectUrl = printableFileDirectUrl;
		this.eniFileDirectUrl = eniFileDirectUrl;
		this.expedientID = expedientID;
		this.documentID = documentID;
	}

	public String getCustodyID() {
		return custodyID;
	}

	public void setCustodyID(String custodyID) {
		this.custodyID = custodyID;
	}

	public String getCsv() {
		return csv;
	}

	public void setCsv(String csv) {
		this.csv = csv;
	}

	public String getCsvValidationWeb() {
		return csvValidationWeb;
	}

	public void setCsvValidationWeb(String csvValidationWeb) {
		this.csvValidationWeb = csvValidationWeb;
	}

	public String getValidationFileUrl() {
		return validationFileUrl;
	}

	public void setValidationFileUrl(String validationFileUrl) {
		this.validationFileUrl = validationFileUrl;
	}

	public String getCsvGenerationDefinition() {
		return csvGenerationDefinition;
	}

	public void setCsvGenerationDefinition(String csvGenerationDefinition) {
		this.csvGenerationDefinition = csvGenerationDefinition;
	}

	public String getOriginalFileDirectURL() {
		return originalFileDirectURL;
	}

	public void setOriginalFileDirectURL(String originalFileDirectURL) {
		this.originalFileDirectURL = originalFileDirectURL;
	}

	public String getPrintableFileDirectUrl() {
		return printableFileDirectUrl;
	}

	public void setPrintableFileDirectUrl(String printableFileDirectUrl) {
		this.printableFileDirectUrl = printableFileDirectUrl;
	}

	public String getEniFileDirectUrl() {
		return eniFileDirectUrl;
	}

	public void setEniFileDirectUrl(String eniFileDirectUrl) {
		this.eniFileDirectUrl = eniFileDirectUrl;
	}

	public String getExpedientID() {
		return expedientID;
	}

	public void setExpedientID(String expedientID) {
		this.expedientID = expedientID;
	}

	public String getDocumentID() {
		return documentID;
	}

	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}

}
