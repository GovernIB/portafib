package es.caib.portafib.api.interna.secure.signature.v1.signatureflowtemplate;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 3 jun 2025 11:51:40
 */
@Schema(description = "Informació requerida per iniciar un procés web de signatura d'una plantilla de flux")
public class SignatureFlowTemplateStartTransactionRequest {

    @Schema(description = "Identificador de la transacción que se va a iniciar")
    protected String transactionID;

    @Schema(description = "URL de retorn a la que s'ha de redirigir a l'usuari una vegada hagi finalizat el procés de firma")
    protected String returnUrl;

    /**
     * 
     */
    public SignatureFlowTemplateStartTransactionRequest() {
        super();
    }

    /**
     * @param transactionID
     * @param fileInfoSignatureArray
     */
    public SignatureFlowTemplateStartTransactionRequest(String transactionID, String returnUrl) {
        this.transactionID = transactionID;
        this.returnUrl = returnUrl;

    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

}
