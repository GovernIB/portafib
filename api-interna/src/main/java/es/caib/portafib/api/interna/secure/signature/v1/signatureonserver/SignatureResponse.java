package es.caib.portafib.api.interna.secure.signature.v1.signatureonserver;

import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignedFileInfo;
import es.caib.portafib.api.interna.secure.signature.v1.commons.ProcessStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Schema(description = "Resposta de la petició de firma en servidor")
public class SignatureResponse {
    
    @Schema(description = "Identificador de la firma",  requiredMode = RequiredMode.REQUIRED)
	protected String signID;
    
    @Schema(description = "Estat del procés de firma",  requiredMode = RequiredMode.REQUIRED)
	protected ProcessStatus status;

    @Schema(description = "Fitxer signat.", requiredMode = RequiredMode.NOT_REQUIRED)
	protected Document signedFile;

    @Schema(description = "Informació del fitxer signat.", requiredMode = RequiredMode.NOT_REQUIRED)
	protected SignedFileInfo signedFileInfo;

	public SignatureResponse() {
		super();
	}

	public SignatureResponse(String signID, ProcessStatus status, Document signedFile,
			SignedFileInfo signedFileInfo) {
		super();
		this.signID = signID;
		this.status = status;
		this.signedFile = signedFile;
		this.signedFileInfo = signedFileInfo;
	}

	public Document getSignedFile() {
		return signedFile;
	}

	public void setSignedFile(Document signedFile) {
		this.signedFile = signedFile;
	}

	public String getSignID() {
		return signID;
	}

	public void setSignID(String signID) {
		this.signID = signID;
	}

	public ProcessStatus getStatus() {
		return status;
	}

	public void setStatus(ProcessStatus status) {
		this.status = status;
	}

	public SignedFileInfo getSignedFileInfo() {
		return signedFileInfo;
	}

	public void setSignedFileInfo(SignedFileInfo signedFileInfo) {
		this.signedFileInfo = signedFileInfo;
	}

}
