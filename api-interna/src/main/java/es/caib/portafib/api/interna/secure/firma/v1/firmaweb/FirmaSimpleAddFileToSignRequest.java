package es.caib.portafib.api.interna.secure.firma.v1.firmaweb;

import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleFileInfoSignature;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 *
 */

public class FirmaSimpleAddFileToSignRequest {
    @Schema(
            description = "Identificador de transacció",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
	String transactionID;
    
    @Schema(
            description = "Document a signar i informació associada a la firma a realitzar",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
	FirmaSimpleFileInfoSignature fileInfoSignature;

	/**
	 *
	 */
	
	public FirmaSimpleAddFileToSignRequest() {
		super();
	}

	/**
	 * @param transactionID
	 * @param fileInfoSignature
	 */
	public FirmaSimpleAddFileToSignRequest(String transactionID, FirmaSimpleFileInfoSignature fileInfoSignature) {
		super();
		this.transactionID = transactionID;
		this.fileInfoSignature = fileInfoSignature;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public FirmaSimpleFileInfoSignature getFileInfoSignature() {
		return fileInfoSignature;
	}

	public void setFileInfoSignature(FirmaSimpleFileInfoSignature fileInfoSignature) {
		this.fileInfoSignature = fileInfoSignature;
	}

}
