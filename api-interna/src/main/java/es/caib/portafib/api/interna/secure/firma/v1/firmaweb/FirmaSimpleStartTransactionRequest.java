package es.caib.portafib.api.interna.secure.firma.v1.firmaweb;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

public class FirmaSimpleStartTransactionRequest {

	public static final String VIEW_FULLSCREEN = "fullview";

	public static final String VIEW_IFRAME = "iframe";
	@Schema(
            description = "Identificador de transacció",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
	String transactionID;
	
	@Schema(
            description = "Adreça web on retornar una vegada finalitzat tot el procés de firma.",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
	String returnUrl;
	
	@Schema(
            description = "Indica si la presentació de la firma es farà a pantalla completa o dins d'un iframe:\r\n"
                    + "    • \"fullview\" (Constant VIEW_FULLSCREEN)\r\n"
                    + "    • \"iframe\" (Constant VIEW_IFRAME)",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
	String view;
	
	@Schema(
            description = "Idioma seleccionat",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
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
