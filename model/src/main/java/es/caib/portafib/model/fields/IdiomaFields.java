package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_idioma */
public interface IdiomaFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_idioma";

    public static final String _TABLE_MODEL = "idioma";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final StringField IDIOMAID = new StringField(_TABLE_MODEL, "idiomaID", "idiomaid", 5);  // PK
    public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom", 50);
    public static final BooleanField SUPORTAT = new BooleanField(_TABLE_MODEL, "suportat", "suportat", 1);
    public static final IntegerField ORDRE = new IntegerField(_TABLE_MODEL, "ordre", "ordre", 10);

    public static final Field<?>[] ALL_IDIOMA_FIELDS = {
        IDIOMAID,
        NOM,
        SUPORTAT,
        ORDRE
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        IDIOMAID
    };
}
