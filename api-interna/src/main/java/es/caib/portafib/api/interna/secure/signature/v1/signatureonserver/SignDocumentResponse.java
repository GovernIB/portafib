package es.caib.portafib.api.interna.secure.signature.v1.signatureonserver;

import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;
import es.caib.portafib.api.interna.secure.signature.v1.commons.ProcessStatus;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignPlugin;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignedFileInfo;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 17 jun 2025 12:13:16
 */
@Schema(description = "Resposta de la petició de firma en servidor")
public class SignDocumentResponse extends SignatureResponse {

    @Schema(description = "Informació del Plugin Utilitzat per a la realització de la Firma")
    protected SignPlugin signPlugin;

    public SignDocumentResponse() {
        super();
    }

    public SignDocumentResponse(SignatureResponse sr, SignPlugin signPlugin) {
        super(sr.getSignID(), sr.getStatus(), sr.getSignedFile(), sr.getSignedFileInfo());
        this.signPlugin = signPlugin;
    }

    public SignDocumentResponse(String signID, ProcessStatus status, Document signedFile, SignedFileInfo signedFileInfo,
            SignPlugin signPlugin) {
        super(signID, status, signedFile, signedFileInfo);
        this.signPlugin = signPlugin;
    }

    public SignPlugin getSignPlugin() {
        return signPlugin;
    }

    public void setSignPlugin(SignPlugin signPlugin) {
        this.signPlugin = signPlugin;
    }

}
