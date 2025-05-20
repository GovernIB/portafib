package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;


/**
 * 
 * @author anadal
 * 16 may 2025 12:30:53
 */
public class SignatureRequestState {
/*
    public static final int SIGNATURE_REQUEST_STATE_NOTSTARTET = 0;
    public static final int SIGNATURE_REQUEST_STATE_RUNNING = 1;
    public static final int SIGNATURE_REQUEST_STATE_PAUSED = 2;
    public static final int SIGNATURE_REQUEST_STATE_REJECTED = 3;
    public static final int SIGNATURE_REQUEST_STATE_SIGNED = 4;
*/
    protected int state;

    /**
     * Rao de rebuig de la petició.
     */
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
