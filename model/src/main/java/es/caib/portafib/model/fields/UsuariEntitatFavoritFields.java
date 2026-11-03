package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_usuarientitatfavorit */
public interface UsuariEntitatFavoritFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_usuarientitatfavorit";

    public static final String _TABLE_MODEL = "usuariEntitatFavorit";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField ID = new LongField(_TABLE_MODEL, "ID", "id", 19);  // PK
    public static final StringField ORIGENID = new StringField(_TABLE_MODEL, "origenID", "origenid", 101);
    public static final StringField FAVORITID = new StringField(_TABLE_MODEL, "favoritID", "favoritid", 101);

    public static final Field<?>[] ALL_USUARIENTITATFAVORIT_FIELDS = {
        ID,
        ORIGENID,
        FAVORITID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        ID
    };
}
