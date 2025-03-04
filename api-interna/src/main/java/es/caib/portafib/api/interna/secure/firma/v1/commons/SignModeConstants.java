package es.caib.portafib.api.interna.secure.firma.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "SignModeConstants",
        description = "Valors:\r\n"
                + "    • 0: Implicit o Attached. La firma resultante incluye internamente una copia de los datos firmados. \r\n"
                + "    • 1: Explicit o Detached: La firma resultante no incluye los datos firmados.",
        format = "int32",
        enumAsRef = true,
        example = "SIGN_MODE_ATTACHED_ENVELOPED|SIGN_MODE_ATTACHED_ENVELOPING|SIGN_MODE_DETACHED|SIGN_MODE_INTERNALLY_DETACHED")
public enum SignModeConstants {
    SIGN_MODE_CONSTANTS_ATTACHED_ENVELOPED(0), SIGN_MODE_CONSTANTS_ATTACHED_ENVELOPING(3),
    SIGN_MODE_CONSTANTS_DETACHED(1), SIGN_MODE_CONSTANTS_INTERNALLY_DETACHED(4);

    public final Integer value;

    SignModeConstants(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
