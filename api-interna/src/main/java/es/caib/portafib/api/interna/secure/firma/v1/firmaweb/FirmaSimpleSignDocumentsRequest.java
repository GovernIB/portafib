package es.caib.portafib.api.interna.secure.firma.v1.firmaweb;

import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleCommonInfo;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleFileInfoSignature;

/**
 * 
 * @author anadal
 *
 */
public class FirmaSimpleSignDocumentsRequest {

	FirmaSimpleCommonInfo commonInfo;

	FirmaSimpleFileInfoSignature[] fileInfoSignatureArray;

	/**
	 * 
	 */
	public FirmaSimpleSignDocumentsRequest() {
		super();
	}

	public FirmaSimpleSignDocumentsRequest(FirmaSimpleCommonInfo commonInfo,
			FirmaSimpleFileInfoSignature[] fileInfoSignatureArray) {
		super();
		this.commonInfo = commonInfo;
		this.fileInfoSignatureArray = fileInfoSignatureArray;
	}

	public FirmaSimpleFileInfoSignature[] getFileInfoSignatureArray() {
		return fileInfoSignatureArray;
	}

	public void setFileInfoSignatureArray(FirmaSimpleFileInfoSignature[] fileInfoSignatureArray) {
		this.fileInfoSignatureArray = fileInfoSignatureArray;
	}

	public FirmaSimpleCommonInfo getCommonInfo() {
		return commonInfo;
	}

	public void setCommonInfo(FirmaSimpleCommonInfo commonInfo) {
		this.commonInfo = commonInfo;
	}

}
