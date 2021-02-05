package es.caib.portafib.back.helper;

import es.caib.portafib.logic.signatures.SignatureValidation;

import java.util.EnumMap;
import java.util.Map;

/**
 * Encapsula lògica necessària per les JSPs
 */
public class SignatureValidationHelper {

    private static final Map<SignatureValidation.Status, String> STATUS_STYLE_MAP
            = new EnumMap<SignatureValidation.Status, String>(SignatureValidation.Status.class);

    static {
        STATUS_STYLE_MAP.put(SignatureValidation.Status.VALID, "text-success");
        STATUS_STYLE_MAP.put(SignatureValidation.Status.INVALID, "text-warning");
        STATUS_STYLE_MAP.put(SignatureValidation.Status.ERROR, "text-error");
    }

    private final SignatureValidation validation;
    private final String statusStyle;

    public SignatureValidationHelper(SignatureValidation validation) {
        this.validation = validation;
        this.statusStyle = STATUS_STYLE_MAP.get(validation.getStatus());
    }

    public SignatureValidation.Status getStatus() {
        return validation.getStatus();
    }

    public String getMessage() {
        return validation.getMessage();
    }

    public String getStatusStyle() {
        return statusStyle;
    }
}
