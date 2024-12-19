package es.caib.portafib.api.interna.secure.firma.v1.firmaweb;

public class FirmaSimpleStartTransactionRequest {

	public static final String VIEW_FULLSCREEN = "fullview";

	public static final String VIEW_IFRAME = "iframe";

	String transactionID;

	String returnUrl;

	String view;
	
	String language;

	/**
	 * 
	 */
	public FirmaSimpleStartTransactionRequest() {
		super();
	}

	public FirmaSimpleStartTransactionRequest(String transactionID, String returnUrl, String view, String language) {
        super();
        this.transactionID = transactionID;
        this.returnUrl = returnUrl;
        this.view = view;
        this.language = language;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
