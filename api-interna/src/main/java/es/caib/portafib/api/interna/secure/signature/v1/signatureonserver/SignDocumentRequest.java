package es.caib.portafib.api.interna.secure.signature.v1.signatureonserver;

import es.caib.portafib.api.interna.secure.signature.v1.commons.CommonInfo;
import es.caib.portafib.api.interna.secure.signature.v1.commons.FileInfoSignature;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

public class SignDocumentRequest {
    
    @Schema(
            description = "Configuracions generals de firma i identificacio del solicitant i solicitat",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
	protected CommonInfo commonInfo;

    @Schema(
            description = "Informació especifica per a realitzar la firma",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
    protected  FileInfoSignature fileInfoSignature;

	public SignDocumentRequest() {
		super();
	}

	public SignDocumentRequest(CommonInfo commonInfo,
			FileInfoSignature fileInfoSignature) {
		super();
		this.commonInfo = commonInfo;
		this.fileInfoSignature = fileInfoSignature;
	}

	public CommonInfo getCommonInfo() {
		return commonInfo;
	}

	public void setCommonInfo(CommonInfo commonInfo) {
		this.commonInfo = commonInfo;
	}

	public FileInfoSignature getFileInfoSignature() {
		return fileInfoSignature;
	}

	public void setFileInfoSignature(FileInfoSignature fileInfoSignature) {
		this.fileInfoSignature = fileInfoSignature;
	}

}
