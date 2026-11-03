package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_grupentitat */
public interface GrupEntitatFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_grupentitat";

    public static final String _TABLE_MODEL = "grupEntitat";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField GRUPENTITATID = new LongField(_TABLE_MODEL, "grupEntitatID", "grupentitatid", 19);  // PK
    public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom", 100);
    public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio", 255);
    public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatID", "entitatid", 50);

    public static final Field<?>[] ALL_GRUPENTITAT_FIELDS = {
        GRUPENTITATID,
        NOM,
        DESCRIPCIO,
        ENTITATID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        GRUPENTITATID
    };
}
