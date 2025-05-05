package es.caib.portafib.api.interna.secure.signature.v1.signatureonserver;

import java.util.List;

import es.caib.portafib.api.interna.secure.signature.v1.commons.ProcessStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 *
 */
public class SignDocumentsResponse {

    @Schema(
            description = "Estat general del procés de firma. En Firma web, pot passar que aquest estat digui que tot ha anat bé, però que l'estat "
                    + "intern d'alguna de les firmes no hagi anat bé (Veure FirmaSimpleSignatureResult)",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
    protected ProcessStatus statusSignatureProcess;
    
    
    @Schema(
            description = "Resposta de la petició de firma en servidor",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
    protected List<SignatureResponse> results;

    /**
     * 
     */
    public SignDocumentsResponse() {
        super();
    }

    /**
     * @param results
     */
    public SignDocumentsResponse(ProcessStatus statusSignatureProcess,
            List<SignatureResponse> results) {
        super();
        this.statusSignatureProcess = statusSignatureProcess;
        this.results = results;
    }

    public List<SignatureResponse> getResults() {
        return results;
    }

    public void setResults(List<SignatureResponse> results) {
        this.results = results;
    }

    public ProcessStatus getStatusSignatureProcess() {
        return statusSignatureProcess;
    }

    public void setStatusSignatureProcess(ProcessStatus statusSignatureProcess) {
        this.statusSignatureProcess = statusSignatureProcess;
    }

}