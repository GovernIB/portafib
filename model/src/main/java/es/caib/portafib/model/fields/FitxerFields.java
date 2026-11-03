package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_fitxer */
public interface FitxerFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_fitxer";

    public static final String _TABLE_MODEL = "fitxer";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField FITXERID = new LongField(_TABLE_MODEL, "fitxerID", "fitxerid", 19);  // PK
    public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom", 255);
    public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio", 1000);
    public static final LongField TAMANY = new LongField(_TABLE_MODEL, "tamany", "tamany", 19);
    public static final StringField MIME = new StringField(_TABLE_MODEL, "mime", "mime", 255);

    public static final Field<?>[] ALL_FITXER_FIELDS = {
        FITXERID,
        NOM,
        DESCRIPCIO,
        TAMANY,
        MIME
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        FITXERID
    };
}
