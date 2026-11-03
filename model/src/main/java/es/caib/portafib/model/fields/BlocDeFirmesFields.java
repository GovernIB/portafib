package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_blocdefirmes */
public interface BlocDeFirmesFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_blocdefirmes";

    public static final String _TABLE_MODEL = "blocDeFirmes";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField BLOCDEFIRMESID = new LongField(_TABLE_MODEL, "blocDeFirmesID", "blocdefirmesid", 19);  // PK
    public static final IntegerField ORDRE = new IntegerField(_TABLE_MODEL, "ordre", "ordre", 10);
    public static final TimestampField DATAFINALITZACIO = new TimestampField(_TABLE_MODEL, "dataFinalitzacio", "datafinalitzacio", 29);
    public static final LongField FLUXDEFIRMESID = new LongField(_TABLE_MODEL, "fluxDeFirmesID", "fluxdefirmesid", 19);
    public static final IntegerField MINIMDEFIRMES = new IntegerField(_TABLE_MODEL, "minimDeFirmes", "minimdefirmes", 10);

    public static final Field<?>[] ALL_BLOCDEFIRMES_FIELDS = {
        BLOCDEFIRMESID,
        ORDRE,
        DATAFINALITZACIO,
        FLUXDEFIRMESID,
        MINIMDEFIRMES
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        BLOCDEFIRMESID
    };
}
