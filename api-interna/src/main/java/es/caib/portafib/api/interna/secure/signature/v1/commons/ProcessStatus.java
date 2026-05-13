package es.caib.portafib.api.interna.secure.signature.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Estat d'algun procés
 * @author anadal
 * 6 may 2025 10:01:59
 */
@Schema(description = "Estat d'algun procés")
public class ProcessStatus {

    @Schema(
            description = "Codi de l'estat del procés. StatusConstants.STATUS_FINAL_OK si tot ha anat bé, qualsevol "
                    + "altre valor indica un error o cancel·lació. Pels valors d'aquest camp veure classe StatusConstants",
            example = "0",
            requiredMode = Schema.RequiredMode.REQUIRED)
    protected int status;

    @Schema(
            description = "En cas d'error, codi d'error encara que fins i tot en el cas d'error normalment valdrà null."
                    + " Els valors d'aquest camp són enviats pels diferents plugins de firma, per tant poden variar "
                    + "molt en funció del plugin que s'hagi utilitzat. Per a més informació sobre els valors d'aquest"
                    + " camp, consultar la documentació del plugin de firma que s'hagi utilitzat.",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    // Estudiar la possibilitat de retornar CODI d'Error en API Firma Síncrona     #1157
    protected String errorCode;

    @Schema(
            description = "En cas d'error, missatge d'error",
            example = "Error al validar el certificat",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    protected String errorMessage;

    @Schema(
            description = "En cas d'error, stack trace de l'error en format String",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    protected String errorStackTrace;

    public ProcessStatus() {
        super();
    }

    public ProcessStatus(int status, String errorCode, String errorMessage, String errorStackTrace) {
        super();
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorStackTrace = errorStackTrace;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorStackTrace() {
        return errorStackTrace;
    }

    public void setErrorStackTrace(String errorStackTrace) {
        this.errorStackTrace = errorStackTrace;
    }

}
