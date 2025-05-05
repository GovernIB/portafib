package es.caib.portafib.api.interna.secure.signature.v1.directsignatureonweb;

import es.caib.portafib.api.interna.secure.signature.v1.commons.FileInfoSignature;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 *
 */

public class AddFileToSignRequest {
    @Schema(
            description = "Identificador de transacció",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
	String transactionID;
    
    @Schema(
            description = "Document a signar i informació associada a la firma a realitzar",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
	FileInfoSignature fileInfoSignature;

	/**
	 *
	 */
	
	public AddFileToSignRequest() {
		super();
	}

	/**
	 * @param transactionID
	 * @param fileInfoSignature
	 */
	public AddFileToSignRequest(String transactionID, FileInfoSignature fileInfoSignature) {
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

	public FileInfoSignature getFileInfoSignature() {
		return fileInfoSignature;
	}

	public void setFileInfoSignature(FileInfoSignature fileInfoSignature) {
		this.fileInfoSignature = fileInfoSignature;
	}

}
