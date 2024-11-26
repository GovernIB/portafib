package es.caib.portafib.api.interna.secure.firma.v1.commons;

public class FirmaSimpleStartTransactionRequest {

	public static final String VIEW_FULLSCREEN = "fullview";

	public static final String VIEW_IFRAME = "iframe";

	String transactionID;

	String returnUrl;

	String view;

	/**
	 * 
	 */
	public FirmaSimpleStartTransactionRequest() {
		super();
	}

	/**
	 * @param transactionID
	 * @param fileInfoSignatureArray
	 */
	public FirmaSimpleStartTransactionRequest(String transactionID, String returnUrl, String view) {

		this.transactionID = transactionID;
		this.returnUrl = returnUrl;
		this.view = view;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

}
