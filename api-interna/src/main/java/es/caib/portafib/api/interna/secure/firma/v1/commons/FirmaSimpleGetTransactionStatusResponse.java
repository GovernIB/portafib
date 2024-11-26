package es.caib.portafib.api.interna.secure.firma.v1.commons;

import java.util.List;

public class FirmaSimpleGetTransactionStatusResponse {

	FirmaSimpleStatus transactionStatus;

	List<FirmaSimpleSignatureStatus> signaturesStatusList;

	/**
	 * 
	 */
	public FirmaSimpleGetTransactionStatusResponse() {
		super();
	}

	/**
	 * @param transactionStatus
	 * @param signaturesStatusMap
	 */
	public FirmaSimpleGetTransactionStatusResponse(FirmaSimpleStatus transactionStatus,
			List<FirmaSimpleSignatureStatus> signaturesStatusList) {
		super();
		this.transactionStatus = transactionStatus;
		this.signaturesStatusList = signaturesStatusList;
	}

	public FirmaSimpleStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(FirmaSimpleStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public List<FirmaSimpleSignatureStatus> getSignaturesStatusList() {
		return signaturesStatusList;
	}

	public void setSignaturesStatusList(List<FirmaSimpleSignatureStatus> signaturesStatusList) {
		this.signaturesStatusList = signaturesStatusList;
	}

}
