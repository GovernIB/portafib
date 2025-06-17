package es.caib.portafib.api.interna.secure.signature.v1.signatureonserver;

import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignedFileInfo;
import es.caib.portafib.api.interna.secure.signature.v1.commons.ProcessStatus;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignedFile;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 * 16 jun 2025 12:25:13
 */
@Schema(description = "Resposta de la petició de firma en servidor")
public class SignatureResponse extends SignedFile {

    @Schema(description = "Identificador de la firma", requiredMode = RequiredMode.REQUIRED)
    protected String signID;

    @Schema(description = "Estat del procés de firma", requiredMode = RequiredMode.REQUIRED)
    protected ProcessStatus status;

    public SignatureResponse() {
        super();
    }

    public SignatureResponse(String signID, ProcessStatus status, Document signedFile, SignedFileInfo signedFileInfo) {
        super(signedFile, signedFileInfo);
        this.signID = signID;
        this.status = status;
    }

    public String getSignID() {
        return signID;
    }

    public void setSignID(String signID) {
        this.signID = signID;
    }

    public ProcessStatus getStatus() {
        return status;
    }

    public void setStatus(ProcessStatus status) {
        this.status = status;
    }

}
