package es.caib.portafib.api.interna.secure.signature.v1.directsignatureonweb;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 6 may 2025 8:55:43
 */
@Schema(
        name = "ViewConstants",
        description = "Valors:\n"
                + "    • VIEW_FULLSCREEN(\"fullview\"): El proces de firma es realitzarà en pantalla completa\n"
                + "    • VIEW_IFRAME(\"iframe\"): El proces de firma es realitzarà en un iframe\n",
        format = "string",
        enumAsRef = true,
        /** Parxe utilitzat per a la generació correcta dels noms dels enums dins de l'openapi.json */
        example = "VIEW_FULLSCREEN(El proces de firma es realitzarà en pantalla completa)"
                + "|VIEW_IFRAME(El proces de firma es realitzarà en un iframe)")

public enum ViewConstants {
    VIEW_FULLSCREEN("fullview"), VIEW_IFRAME("iframe");

    // public static final String VIEW_FULLSCREEN = "fullview";

    // public static final String VIEW_IFRAME = "iframe";

    public final String value;

    ViewConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}
