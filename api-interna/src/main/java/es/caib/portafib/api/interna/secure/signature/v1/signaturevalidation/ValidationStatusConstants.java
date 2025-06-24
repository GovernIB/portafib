package es.caib.portafib.api.interna.secure.signature.v1.signaturevalidation;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 6 may 2025 9:06:58
 */
@Schema(
        description = "Estats de la validació d'una firma:\n" + "    • \"SIGNATURE_VALID\"\n" + "    • \"SIGNATURE_ERROR\"\n",
        enumAsRef = true,
        format = "int32",
        /** Parxe utilitzat per a la generació correcta dels noms dels enums dins de l'openapi.json */
        example = "SIGNATURE_VALID|SIGNATURE_ERROR|SIGNATURE_INVALID")
public enum ValidationStatusConstants {
    
    /*
    public static final int SIGNATURE_VALID = 1;

    public static final int SIGNATURE_ERROR = -1;

    public static final int SIGNATURE_INVALID = -2;
    */
    
    SIGNATURE_VALID(1), SIGNATURE_ERROR(-1), SIGNATURE_INVALID(-2);

    public final Integer value;

    ValidationStatusConstants(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}