package es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor;

import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleFile;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleSignedFileInfo;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Schema(description = "Resposta de la petició de firma en servidor")
public class FirmaSimpleSignatureResponse {
    
    @Schema(description = "Identificador de la firma",  requiredMode = RequiredMode.REQUIRED)
	protected String signID;
    
    @Schema(description = "Estat del procés de firma",  requiredMode = RequiredMode.REQUIRED)
	protected FirmaSimpleStatus status;

    @Schema(description = "Fitxer signat.", requiredMode = RequiredMode.NOT_REQUIRED)
	protected FirmaSimpleFile signedFile;

    @Schema(description = "Informació del fitxer signat.", requiredMode = RequiredMode.NOT_REQUIRED)
	protected FirmaSimpleSignedFileInfo signedFileInfo;

	public FirmaSimpleSignatureResponse() {
		super();
	}

	public FirmaSimpleSignatureResponse(String signID, FirmaSimpleStatus status, FirmaSimpleFile signedFile,
			FirmaSimpleSignedFileInfo signedFileInfo) {
		super();
		this.signID = signID;
		this.status = status;
		this.signedFile = signedFile;
		this.signedFileInfo = signedFileInfo;
	}

	public FirmaSimpleFile getSignedFile() {
		return signedFile;
	}

	public void setSignedFile(FirmaSimpleFile signedFile) {
		this.signedFile = signedFile;
	}

	public String getSignID() {
		return signID;
	}

	public void setSignID(String signID) {
		this.signID = signID;
	}

	public FirmaSimpleStatus getStatus() {
		return status;
	}

	public void setStatus(FirmaSimpleStatus status) {
		this.status = status;
	}

	public FirmaSimpleSignedFileInfo getSignedFileInfo() {
		return signedFileInfo;
	}

	public void setSignedFileInfo(FirmaSimpleSignedFileInfo signedFileInfo) {
		this.signedFileInfo = signedFileInfo;
	}

}
