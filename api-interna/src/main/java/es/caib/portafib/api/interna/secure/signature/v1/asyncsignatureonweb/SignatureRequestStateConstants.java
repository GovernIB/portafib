package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import es.caib.portafib.utils.ConstantsV2;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 16 may 2025 12:30:53
 */
@Schema(
        name="SignatureRequestStateConstants",
        description = "Valors:\r\n"
                        + "* NOTSTARTED(Codi d'estat d'una petició de firma que indica que no s'ha posat en marxa.)"
                        + "* RUNNING(Codi d'estat d'una petició de firma que indica un que esta en procés.)"
                        + "* SIGNED(Codi d'estat d'una petició de firma que indica que ha finalitzat correctament)"
                        + "* PAUSED(Codi d'estat d'una petició de firma que indica que ha sigut pausada temporalment)"
                        + "* REJECTED(Codi d'estat d'una petició de firma que indica que ha sigut rebutjada)",
        format = "int32",
        enumAsRef = true,
        /** Parxe utilitzat per a la generació correcta dels noms dels enums dins de l'openapi.json */
        example = "NOTSTARTED(Codi d'estat d'una petició de firma que indica que no s'ha posat en marxa.)"
                + "|RUNNING(Codi d'estat d'una petició de firma que indica un que esta en procés.)"
                + "|PAUSED(Codi d'estat d'una petició de firma que indica que ha sigut pausada temporalment)"
                + "|REJECTED(Codi d'estat d'una petició de firma que indica que ha sigut rebutjada)"
                + "|SIGNED(Codi d'estat d'una petició de firma que indica que ha finalitzat correctament)")
public enum SignatureRequestStateConstants {
/*
    public static final int SIGNATURE_REQUEST_STATE_NOTSTARTED = 0;
    public static final int SIGNATURE_REQUEST_STATE_RUNNING = 1;
    public static final int SIGNATURE_REQUEST_STATE_PAUSED = 2;
    public static final int SIGNATURE_REQUEST_STATE_REJECTED = 3;
    public static final int SIGNATURE_REQUEST_STATE_SIGNED = 4;
*/
    NOTSTARTED(ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT),
    RUNNING(ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES),  
    PAUSED(ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT),
    REJECTED(ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT),
    SIGNED(ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT);
    
    public final Integer value;

    SignatureRequestStateConstants(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static SignatureRequestStateConstants fromValue(Integer value) {
        for (SignatureRequestStateConstants b : SignatureRequestStateConstants.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "' for StatusConstants.");
    }
}
