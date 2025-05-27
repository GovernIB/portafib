package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 * 16 may 2025 12:30:53
 */
@Schema(
        description = "Informació de l'estat d'una Petició de Firma. Valors:\r\n"
                + "    • SignatureRequestStateConstants.NOTSTARTET.getValue()=0\r\n"
                + "    • SignatureRequestStateConstants.RUNNING.getValue()=1\r\n"
                + "    • SignatureRequestStateConstants.PAUSED.getValue()=2\r\n"
                + "    • SignatureRequestStateConstants.REJECTED.getValue()=3\r\n"
                + "    • SignatureRequestStateConstants.SIGNED.getValue()=4")
public class SignatureRequestState {

    @Schema(
            description = "Estat de la Peticio de firma. Veure classe SignatureRequestStateConstants.",
            requiredMode = RequiredMode.REQUIRED)
    protected int state;

    /**
     * Rao de rebuig de la petició.
     */
    @Schema(description = "Si l'estat de la Petició de Firma és rebutjat llavors inclou raó de rebuig de la petició.")
    protected String rejectedReason;

    public SignatureRequestState() {
        super();
    }

    public SignatureRequestState(int state, String rejectedReason) {
        super();
        this.state = state;
        this.rejectedReason = rejectedReason;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRejectedReason() {
        return rejectedReason;
    }

    public void setRejectedReason(String rejectedReason) {
        this.rejectedReason = rejectedReason;
    }

}
