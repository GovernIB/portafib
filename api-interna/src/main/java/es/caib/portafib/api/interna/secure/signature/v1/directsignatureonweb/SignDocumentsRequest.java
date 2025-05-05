package es.caib.portafib.api.interna.secure.signature.v1.directsignatureonweb;

import es.caib.portafib.api.interna.secure.signature.v1.commons.CommonInfo;
import es.caib.portafib.api.interna.secure.signature.v1.commons.FileInfoSignature;

/**
 * 
 * @author anadal
 *
 */
public class SignDocumentsRequest {

	CommonInfo commonInfo;

	FileInfoSignature[] fileInfoSignatureArray;

	/**
	 * 
	 */
	public SignDocumentsRequest() {
		super();
	}

	public SignDocumentsRequest(CommonInfo commonInfo,
			FileInfoSignature[] fileInfoSignatureArray) {
		super();
		this.commonInfo = commonInfo;
		this.fileInfoSignatureArray = fileInfoSignatureArray;
	}

	public FileInfoSignature[] getFileInfoSignatureArray() {
		return fileInfoSignatureArray;
	}

	public void setFileInfoSignatureArray(FileInfoSignature[] fileInfoSignatureArray) {
		this.fileInfoSignatureArray = fileInfoSignatureArray;
	}

	public CommonInfo getCommonInfo() {
		return commonInfo;
	}

	public void setCommonInfo(CommonInfo commonInfo) {
		this.commonInfo = commonInfo;
	}

}
