package es.caib.portafib.api.interna.secure.apisimple.v1.firmasimpleservidor;


import es.caib.portafib.commons.utils.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

/**
 * 
 * @author anadal
 *
 */

@Schema(name = "ConstantsWs")
public class ConstantsWs {

    @Schema(
            description = "Codi d'estat d'una petició de firma que indica un que s'esta inicialitzant",
            nullable = false,
            defaultValue = "" + Constants.STATUS_INITIALIZING,
            implementation = Integer.class,
            required = true,
            accessMode = AccessMode.READ_ONLY)
    public int STATUS_INITIALIZING;

    @Schema(
            description = "Codi d'estat d'una petició de firma que indica un que esta en procés",
            nullable = false,
            defaultValue = "" + Constants.STATUS_IN_PROGRESS,
            implementation = Integer.class,
            required = true,
            accessMode = AccessMode.READ_ONLY)
    public int STATUS_IN_PROGRESS;

    @Schema(
            description = "Codi d'estat d'una petició de firma que indica que ha finalitzat correctament",
            nullable = false,
            defaultValue = "" + Constants.STATUS_FINAL_OK,
            implementation = Integer.class,
            required = true,
            accessMode = AccessMode.READ_ONLY)
    public int STATUS_FINAL_OK;

    @Schema(
            description = "Codi d'estat d'una petició de firma que indica que ha finalitzat amb errors",
            nullable = false,
            defaultValue = "" + Constants.STATUS_FINAL_ERROR,
            implementation = Integer.class,
            required = true,
            accessMode = AccessMode.READ_ONLY)
    public int STATUS_FINAL_ERROR;

    @Schema(
            description = "Codi d'estat d'una petició de firma que indica que ha sigut cancelada",
            nullable = false,
            defaultValue = "" + Constants.STATUS_CANCELLED,
            implementation = Integer.class,
            required = true,
            accessMode = AccessMode.READ_ONLY)
    public int STATUS_CANCELLED;



}