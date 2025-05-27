package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 * 16 may 2025 12:47:45
 */
@Schema(description = "Estructura de dades per consultar Peticions de Firma existents")
public class SignatureRequestInfo {

    @Schema(description = "Identificador de Peticio de firma", requiredMode = RequiredMode.REQUIRED)
    protected long signatureRequestID;

    @Schema(description = "Idioma en que es vol que es retornin els missatges.", requiredMode = RequiredMode.REQUIRED)

    protected String languageUI;

    /**
     * @param message
     */
    public SignatureRequestInfo() {
        super();
    }

    public SignatureRequestInfo(long signatureRequestID, String languageUI) {
        super();
        this.signatureRequestID = signatureRequestID;
        this.languageUI = languageUI;
    }

    public long getSignatureRequestID() {
        return signatureRequestID;
    }

    public void setSignatureRequestID(long signatureRequestID) {
        this.signatureRequestID = signatureRequestID;
    }

    public String getLanguageUI() {
        return languageUI;
    }

    public void setLanguageUI(String languageUI) {
        this.languageUI = languageUI;
    }

}
