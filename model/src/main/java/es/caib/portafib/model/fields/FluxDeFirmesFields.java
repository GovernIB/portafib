package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_fluxdefirmes */
public interface FluxDeFirmesFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_fluxdefirmes";

    public static final String _TABLE_MODEL = "fluxDeFirmes";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField FLUXDEFIRMESID = new LongField(_TABLE_MODEL, "fluxDeFirmesID", "fluxdefirmesid", 19);  // PK
    public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom", 255);

    public static final Field<?>[] ALL_FLUXDEFIRMES_FIELDS = {
        FLUXDEFIRMESID,
        NOM
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        FLUXDEFIRMESID
    };
}
