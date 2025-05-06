package es.caib.portafib.api.interna.secure.signature.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 6 may 2025 9:06:58
 */
@Schema(
        name = "SignaturesTableLocationConstants",
        description = "Posició de la Taula de firmes:\r\n" + "    • 0: Sense taula de firmes\r\n"
                + "    • 1: Taula de firmes en la 1a pàgina\r\n" + "    • -1: Darrera pàgina",
        format = "int32",
        enumAsRef = true,
        /** Parxe utilitzat per a la generació correcta dels noms dels enums dins de l'openapi.json */
        example = "SIGNATURES_TABLE_LOCATION_WITHOUT(Sense taula de firmes)"
                + "|SIGNATURES_TABLE_LOCATION_FIRSTPAGE(Taula de firmes en la 1a pàgina)"
                + "|SIGNATURES_TABLE_LOCATION_LASTPAGE(Darrera pàgina)")
public enum SignaturesTableLocationConstants {
    SIGNATURES_TABLE_LOCATION_WITHOUT(0), SIGNATURES_TABLE_LOCATION_FIRSTPAGE(1),
    SIGNATURES_TABLE_LOCATION_LASTPAGE(-1);

    public final Integer value;

    SignaturesTableLocationConstants(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static SignaturesTableLocationConstants fromValue(Integer value) {
        for (SignaturesTableLocationConstants b : SignaturesTableLocationConstants.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
