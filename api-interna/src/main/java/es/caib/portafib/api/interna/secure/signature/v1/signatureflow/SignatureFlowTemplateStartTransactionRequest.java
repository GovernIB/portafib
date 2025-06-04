package es.caib.portafib.api.interna.secure.signature.v1.signatureflow;

/**
 * 
 * @author anadal
 * 3 jun 2025 11:51:40
 */
public class SignatureFlowTemplateStartTransactionRequest {

    protected String transactionID;

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
