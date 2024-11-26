package es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor;

import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleCommonInfo;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleFileInfoSignature;

public class FirmaSimpleSignDocumentRequest {

	FirmaSimpleCommonInfo commonInfo;

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
