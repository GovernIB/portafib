package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_grupentitatusuarientitat */
public interface GrupEntitatUsuariEntitatFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_grupentitatusuarientitat";

    public static final String _TABLE_MODEL = "grupEntitatUsuariEntitat";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField GRUPENTITATUSUARIENTITATID = new LongField(_TABLE_MODEL, "grupEntitatUsuariEntitatID", "grupentitatusuarientitatid", 19);  // PK
    public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid", 101);
    public static final LongField GRUPENTITATID = new LongField(_TABLE_MODEL, "grupEntitatID", "grupentitatid", 19);

    public static final Field<?>[] ALL_GRUPENTITATUSUARIENTITAT_FIELDS = {
        GRUPENTITATUSUARIENTITATID,
        USUARIENTITATID,
        GRUPENTITATID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        GRUPENTITATUSUARIENTITATID
    };
}
