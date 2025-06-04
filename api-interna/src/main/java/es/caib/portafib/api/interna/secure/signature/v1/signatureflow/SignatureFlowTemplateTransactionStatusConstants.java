package es.caib.portafib.api.interna.secure.signature.v1.signatureflow;

import es.caib.portafib.commons.utils.Constants;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 5 may 2025 15:06:23
 */
@Schema(
        description = "Valors:\r\n"
                + "    • STATUS_RESERVED_ID: Codi d'estat de la creació d'un flux de firmes que indica que s'ha reservat ID\n"
                + "    • STATUS_IN_PROGRESS: Codi d'estat de la creació d'un flux de firmes que indica un que esta en procés.\n"
                + "    • STATUS_FINAL_OK: Codi d'estat de la creació d'un flux de firmes que indica que ha finalitzat correctament\n"
                + "    • STATUS_FINAL_ERROR: Codi d'estat de la creació d'un flux de firmes que indica que ha finalitzat amb errors\n"
                + "    • STATUS_CANCELLED: Codi d'estat de la creació d'un flux de firmes que indica que ha sigut cancelada\n",
        format = "int32",
        enumAsRef = true,
        /** Parxe utilitzat per a la generació correcta dels noms dels enums dins de l'openapi.json */
        example = "STATUS_RESERVED_ID(Codi d'estat de la creació d'un flux de firmes que indica que s'ha reservat ID)"
                + "|STATUS_IN_PROGRESS(Codi d'estat de la creació d'un flux de firmes que indica un que esta en procés.)"
                + "|STATUS_FINAL_OK(Codi d'estat de la creació d'un flux de firmes que indica que ha finalitzat correctament)"
                + "|STATUS_FINAL_ERROR(Codi d'estat de la creació d'un flux de firmes que indica que ha finalitzat amb errors)"
                + "|STATUS_CANCELLED(Codi d'estat de la creació d'un flux de firmes que indica que ha sigut cancelada)")
public enum SignatureFlowTemplateTransactionStatusConstants {

    STATUS_RESERVED_ID(Constants.STATUS_INITIALIZING), STATUS_IN_PROGRESS(Constants.STATUS_IN_PROGRESS),
    STATUS_FINAL_OK(Constants.STATUS_FINAL_OK), STATUS_FINAL_ERROR(Constants.STATUS_FINAL_ERROR),
    STATUS_CANCELLED(Constants.STATUS_CANCELLED);

    public final Integer value;

    SignatureFlowTemplateTransactionStatusConstants(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static SignatureFlowTemplateTransactionStatusConstants fromValue(Integer value) {
        for (SignatureFlowTemplateTransactionStatusConstants b : SignatureFlowTemplateTransactionStatusConstants.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "' for StatusConstants.");
    }
}
