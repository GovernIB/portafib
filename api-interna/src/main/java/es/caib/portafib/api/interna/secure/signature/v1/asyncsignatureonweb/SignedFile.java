package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignedFileInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 * 16 may 2025 14:03:57
 */
@Schema(description = "Representa una fitxer firmat i informació associada")
public class SignedFile {

    @Schema(description = "Fitxer Signat", requiredMode = RequiredMode.REQUIRED)
    protected Document signedFile;

    @Schema(description = "Informació del fitxer Signat", requiredMode = RequiredMode.REQUIRED)
    protected SignedFileInfo signedFileInfo;

    public SignedFile() {
        super();
    }

    public SignedFile(Document signedFile, SignedFileInfo signedFileInfo) {
        super();
        this.signedFile = signedFile;
        this.signedFileInfo = signedFileInfo;
    }

    public Document getSignedFile() {
        return signedFile;
    }

    public void setSignedFile(Document signedFile) {
        this.signedFile = signedFile;
    }

    public SignedFileInfo getSignedFileInfo() {
        return signedFileInfo;
    }

    public void setSignedFileInfo(SignedFileInfo signedFileInfo) {
        this.signedFileInfo = signedFileInfo;
    }

}
