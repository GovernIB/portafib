package es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor;

import java.util.List;

import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 *
 */
public class FirmaSimpleSignDocumentsResponse {

    @Schema(
            description = "Estat general del procés de firma. En Firma web, pot passar que aquest estat digui que tot ha anat bé, però que l'estat "
                    + "intern d'alguna de les firmes no hagi anat bé (Veure FirmaSimpleSignatureResult)",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
    protected FirmaSimpleStatus statusSignatureProcess;
    
    
    @Schema(
            description = "Resposta de la petició de firma en servidor",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
    protected List<FirmaSimpleSignatureResponse> results;

    /**
     * 
     */
    public FirmaSimpleSignDocumentsResponse() {
        super();
    }

    /**
     * @param results
     */
    public FirmaSimpleSignDocumentsResponse(FirmaSimpleStatus statusSignatureProcess,
            List<FirmaSimpleSignatureResponse> results) {
        super();
        this.statusSignatureProcess = statusSignatureProcess;
        this.results = results;
    }

    public List<FirmaSimpleSignatureResponse> getResults() {
        return results;
    }

    public void setResults(List<FirmaSimpleSignatureResponse> results) {
        this.results = results;
    }

    public FirmaSimpleStatus getStatusSignatureProcess() {
        return statusSignatureProcess;
    }

    public void setStatusSignatureProcess(FirmaSimpleStatus statusSignatureProcess) {
        this.statusSignatureProcess = statusSignatureProcess;
    }

}