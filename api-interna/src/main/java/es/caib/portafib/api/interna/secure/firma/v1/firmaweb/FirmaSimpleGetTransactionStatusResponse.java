package es.caib.portafib.api.interna.secure.firma.v1.firmaweb;

import java.util.List;

import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleSignatureStatus;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

public class FirmaSimpleGetTransactionStatusResponse {
    
    @Schema(
            description = "Estat general del procés de firma. Estat de la transacció completa.",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
	FirmaSimpleStatus transactionStatus;
    
    @Schema(
            description = "Estat de cada firma. Pot passar que l'estat general sigui que tot ha anat bé, però que totes o algunes de les firmes no.",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
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
