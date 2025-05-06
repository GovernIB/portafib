package es.caib.portafib.api.interna.secure.signature.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 6 may 2025 8:55:43
 */
@Schema(
        name = "SignModeConstants",
        description = "Valors:\n"
                + "    • SIGN_MODE_ATTACHED_ENVELOPED(0): El fitxer de dades resultant inclou la firma: PDF, ODT, ...\n"
                + "    • SIGN_MODE_ATTACHED_ENVELOPING(3): El fitxer resultant serà la firma que incloura les dades originals\n"
                + "    • SIGN_MODE_DETACHED(1): El fitxer de firma no inclourà les dades: per separat trobarem un fitxer de firma i el fitxer original"
                + "    • SIGN_MODE_INTERNALLY_DETACHED(4): Firma especial XAdES en que la firma i les dades estan al mateix nivell dins de l'XML: ni la firma inclou les dades ni les dades inclouen la firma",
        format = "int32",
        enumAsRef = true,
        /** Parxe utilitzat per a la generació correcta dels noms dels enums dins de l'openapi.json */
        example = "SIGN_MODE_ATTACHED_ENVELOPED(El fitxer de dades resultant inclou la firma: PDF, ODT, ...)"
                + "|SIGN_MODE_ATTACHED_ENVELOPING(El fitxer resultant serà la firma que incloura les dades originals)"
                + "|SIGN_MODE_DETACHED(El fitxer de firma no inclourà les dades: per separat trobarem un fitxer de firma i el fitxer origina)"
                + "|SIGN_MODE_INTERNALLY_DETACHED(Firma especial XAdES en que la firma i les dades estan al mateix nivell dins de l'XML: ni la firma inclou les dades ni les dades inclouen la firma)")
public enum SignModeConstants {
    SIGN_MODE_ATTACHED_ENVELOPED(0), SIGN_MODE_ATTACHED_ENVELOPING(3), SIGN_MODE_DETACHED(1),
    SIGN_MODE_INTERNALLY_DETACHED(4);

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
