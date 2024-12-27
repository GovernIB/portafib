package es.caib.portafib.api.interna.secure.firma.v1.firmaweb;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 * 18 dic 2024 14:57:03
 */
public class FirmaSimpleGetSignatureResultRequest {
    
    @Schema(
            description = "Identificador de la transaccio",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
	protected String transactionID;
    
    @Schema(
            description = "Identificador de la firma",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
	protected String signID;

	/**
	 * 
	 */
	public FirmaSimpleGetSignatureResultRequest() {
		super();
	}

	/**
	 * @param transactionID
	 * @param signID
	 */
	public FirmaSimpleGetSignatureResultRequest(String transactionID, String signID) {
		super();
		this.transactionID = transactionID;
		this.signID = signID;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getSignID() {
		return signID;
	}

	public void setSignID(String signID) {
		this.signID = signID;
	}

}
