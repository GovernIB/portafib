package es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor;

import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleCommonInfo;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleFileInfoSignature;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

public class FirmaSimpleSignDocumentRequest {
    
    @Schema(
            description = "Configuracions generals de firma i identificacio del solicitant i solicitat",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
	FirmaSimpleCommonInfo commonInfo;

    @Schema(
            description = "Informació especifica per a realitzar la firma",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
	FirmaSimpleFileInfoSignature fileInfoSignature;

	public FirmaSimpleSignDocumentRequest() {
		super();
	}

	public FirmaSimpleSignDocumentRequest(FirmaSimpleCommonInfo commonInfo,
			FirmaSimpleFileInfoSignature fileInfoSignature) {
		super();
		this.commonInfo = commonInfo;
		this.fileInfoSignature = fileInfoSignature;
	}

	public FirmaSimpleCommonInfo getCommonInfo() {
		return commonInfo;
	}

	public void setCommonInfo(FirmaSimpleCommonInfo commonInfo) {
		this.commonInfo = commonInfo;
	}

	public FirmaSimpleFileInfoSignature getFileInfoSignature() {
		return fileInfoSignature;
	}

	public void setFileInfoSignature(FirmaSimpleFileInfoSignature fileInfoSignature) {
		this.fileInfoSignature = fileInfoSignature;
	}

}
