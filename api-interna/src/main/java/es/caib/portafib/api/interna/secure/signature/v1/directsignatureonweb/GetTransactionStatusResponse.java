package es.caib.portafib.api.interna.secure.signature.v1.directsignatureonweb;

import java.util.List;

import es.caib.portafib.api.interna.secure.signature.v1.commons.SignatureStatus;
import es.caib.portafib.api.interna.secure.signature.v1.commons.ProcessStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

public class GetTransactionStatusResponse {

    @Schema(
            description = "Estat general del procés de firma. Estat de la transacció completa.",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
    ProcessStatus transactionStatus;

    @Schema(
            description = "Estat de cada firma. Pot passar que l'estat general sigui que tot"
                    + " ha anat bé, però que totes o algunes de les firmes no.",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
    List<SignatureStatus> signaturesStatusList;

    /**
     * 
     */
    public GetTransactionStatusResponse() {
        super();
    }

    /**
     * @param transactionStatus
     * @param signaturesStatusMap
     */
    public GetTransactionStatusResponse(ProcessStatus transactionStatus, List<SignatureStatus> signaturesStatusList) {
        super();
        this.transactionStatus = transactionStatus;
        this.signaturesStatusList = signaturesStatusList;
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

}
