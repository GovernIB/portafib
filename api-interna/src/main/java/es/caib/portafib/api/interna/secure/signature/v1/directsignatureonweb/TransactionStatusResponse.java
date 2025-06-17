package es.caib.portafib.api.interna.secure.signature.v1.directsignatureonweb;

import java.util.List;

import es.caib.portafib.api.interna.secure.signature.v1.commons.SignatureStatus;
import es.caib.portafib.api.interna.secure.signature.v1.commons.ProcessStatus;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignPlugin;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Schema(description = "informació de l'estat d'una Transaccio de tipus Firma Web Directe")
public class TransactionStatusResponse {

    @Schema(
            description = "Estat general del procés de firma. Estat de la transacció completa.",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
    protected ProcessStatus transactionStatus;

    @Schema(
            description = "Estat de cada firma. Pot passar que l'estat general sigui que tot"
                    + " ha anat bé, però que totes o algunes de les firmes no.",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
    protected List<SignatureStatus> signaturesStatusList;

    @Schema(
            description = "Informació del Plugin Utilitzat per a la realització de la Firma",
            requiredMode = RequiredMode.NOT_REQUIRED)
    protected SignPlugin signPlugin;

    /**
     * 
     */
    public TransactionStatusResponse() {
        super();
    }

    public TransactionStatusResponse(ProcessStatus transactionStatus, List<SignatureStatus> signaturesStatusList,
            SignPlugin signPlugin) {
        super();
        this.transactionStatus = transactionStatus;
        this.signaturesStatusList = signaturesStatusList;
        this.signPlugin = signPlugin;
    }

    public ProcessStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(ProcessStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public List<SignatureStatus> getSignaturesStatusList() {
        return signaturesStatusList;
    }

    public void setSignaturesStatusList(List<SignatureStatus> signaturesStatusList) {
        this.signaturesStatusList = signaturesStatusList;
    }

    public SignPlugin getSignPlugin() {
        return signPlugin;
    }

    public void setSignPlugin(SignPlugin signPlugin) {
        this.signPlugin = signPlugin;
    }

}
