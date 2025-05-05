package es.caib.portafib.api.interna.secure.signature.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "SignatureStableLocationConstants",
        description = "Posició de la Taula de firmes:\r\n" + "    • 0: Sense taula de firmes\r\n"
                + "    • 1: Taula de firmes en la 1a pàgina\r\n" + "    • -1: Darrera pàgina",
        format = "int32",
        enumAsRef = true,
        example = "SIGNATURESTABLELOCATION_WITHOUT|SIGNATURESTABLELOCATION_FIRSTPAGE|SIGNATURESTABLELOCATION_LASTPAGE")
public enum SignatureStableLocationConstants {
    SIGNATURE_STABLE_LOCATION_CONSTANTS_WITHOUT(0), SIGNATURE_STABLE_LOCATION_CONSTANTS_FIRSTPAGE(1),
    SIGNATURE_STABLE_LOCATION_CONSTANTS_LASTPAGE(-1);

    public final Integer value;

    SignatureStableLocationConstants(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static SignatureStableLocationConstants fromValue(Integer value) {
        for (SignatureStableLocationConstants b : SignatureStableLocationConstants.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
