package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_traducciomap */
public interface TraduccioMapFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_traducciomap";

    public static final String _TABLE_MODEL = "traduccioMap";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField TRADUCCIOMAPID = new LongField(_TABLE_MODEL, "traduccioMapID", "traducciomapid", 19);  // PK
    public static final StringField IDIOMAID = new StringField(_TABLE_MODEL, "idiomaID", "idiomaid", 5);  // PK
    public static final StringField VALOR = new StringField(_TABLE_MODEL, "valor", "valor", 4000);

    public static final Field<?>[] ALL_TRADUCCIOMAP_FIELDS = {
        TRADUCCIOMAPID,
        IDIOMAID,
        VALOR
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        TRADUCCIOMAPID, IDIOMAID
    };
}
