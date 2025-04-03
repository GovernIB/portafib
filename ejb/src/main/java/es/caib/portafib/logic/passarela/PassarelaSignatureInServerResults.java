package es.caib.portafib.logic.passarela;

import java.util.Map;

import es.caib.portafib.logic.passarela.api.PassarelaFullResults;
import es.caib.portafib.logic.utils.ValidacioCompletaResponse;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class PassarelaSignatureInServerResults {

    protected final PassarelaFullResults passarelaFullResults;

    protected final Map<String, ValidacioCompletaResponse> validacioResponseBySignID;

    protected final Long pluginFirmaEnServidorId;

    @Deprecated
    public PassarelaSignatureInServerResults(PassarelaFullResults passarelaFullResults,
            Map<String, ValidacioCompletaResponse> validacioResponseBySignID) {
        super();
        this.passarelaFullResults = passarelaFullResults;
        this.validacioResponseBySignID = validacioResponseBySignID;
        pluginFirmaEnServidorId = null;
    }

    public PassarelaSignatureInServerResults(PassarelaFullResults passarelaFullResults,
            Map<String, ValidacioCompletaResponse> validacioResponseBySignID, Long pluginFirmaEnServidorId) {
        super();
        this.passarelaFullResults = passarelaFullResults;
        this.validacioResponseBySignID = validacioResponseBySignID;
        this.pluginFirmaEnServidorId = pluginFirmaEnServidorId;
    }

    public PassarelaFullResults getPassarelaFullResults() {
        return passarelaFullResults;
    }

    public Map<String, ValidacioCompletaResponse> getValidacioResponseBySignID() {
        return validacioResponseBySignID;
    }

    public Long getPluginFirmaEnServidorId() {
        return pluginFirmaEnServidorId;
    }

}
