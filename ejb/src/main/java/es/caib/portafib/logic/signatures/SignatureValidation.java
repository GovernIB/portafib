package es.caib.portafib.logic.signatures;

public class SignatureValidation {

    private final Status status;
    private final String message;

    private SignatureValidation(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public static SignatureValidation valid() {
        return new SignatureValidation(Status.VALID, null);
    }

    public static SignatureValidation error(String message) {
        return new SignatureValidation(Status.ERROR, message);
    }

    public static SignatureValidation invalid(String message) {
        return new SignatureValidation(Status.INVALID, message);
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public enum Status {
        VALID,
        INVALID,
        ERROR
    }
}


