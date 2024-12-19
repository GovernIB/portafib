package es.caib.portafib.api.interna.secure.firma.v1.firmaweb;

/**
 * 
 * @author anadal
 * 18 dic 2024 14:57:03
 */
public class FirmaSimpleGetSignatureResultRequest {

	protected String transactionID;

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
