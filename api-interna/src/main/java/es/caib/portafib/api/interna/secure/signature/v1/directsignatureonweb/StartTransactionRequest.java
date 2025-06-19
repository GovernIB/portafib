package es.caib.portafib.api.interna.secure.signature.v1.directsignatureonweb;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 * 18 jun 2025 11:39:27
 */
@Schema(
        description = "Paràmetres per iniciar una transacció de firma electrònica directa via web.",
        requiredMode = RequiredMode.REQUIRED)
public class StartTransactionRequest {

    public static final String VIEW_FULLSCREEN = "fullview";

    public static final String VIEW_IFRAME = "iframe";
    @Schema(description = "Identificador de transacció", example = "", requiredMode = RequiredMode.REQUIRED)
    protected String transactionID;

    @Schema(
            description = "Adreça web on retornar una vegada finalitzat tot el procés de firma.",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
    protected String returnUrl;

    @Schema(
            description = "Indica si la presentació de la firma es farà a pantalla completa o dins d'un iframe:\r\n"
                    + "    • \"fullview\" (Constant VIEW_FULLSCREEN)\r\n" + "    • \"iframe\" (Constant VIEW_IFRAME)",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
    protected String view;

    /**
     * 
     */
    public StartTransactionRequest() {
        super();
    }

    public StartTransactionRequest(String transactionID, String returnUrl, String view) {
        super();
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
