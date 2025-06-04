package es.caib.portafib.api.interna.secure.signature.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Estat d'algun procés
 * @author anadal
 * 6 may 2025 10:01:59
 */
@Schema(description = "Estat d'algun procés")
public class ProcessStatus {


    // TODO
    protected int status; 


    // TODO
    protected String errorMessage;


    // TODO
    protected String errorStackTrace;

    public ProcessStatus() {
        super();
    }

    public ProcessStatus(int status, String errorMessage, String errorStackTrace) {
        super();
        this.status = status;
        this.errorMessage = errorMessage;
        this.errorStackTrace = errorStackTrace;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
