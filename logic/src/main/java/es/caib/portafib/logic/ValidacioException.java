package es.caib.portafib.logic;

public class ValidacioException extends Exception {

    public ValidacioException(String message) {
        super(message);
    }

    public ValidacioException(String message, Throwable cause) {
        super(message, cause);
    }
}
