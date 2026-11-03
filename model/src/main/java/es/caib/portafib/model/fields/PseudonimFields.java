package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_pseudonim */
public interface PseudonimFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_pseudonim";

    public static final String _TABLE_MODEL = "pseudonim";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField PSEUDONIMID = new LongField(_TABLE_MODEL, "pseudonimid", "pseudonimid", 19);  // PK
    public static final StringField PSEUDONIM = new StringField(_TABLE_MODEL, "pseudonim", "pseudonim", 255);
    public static final StringField NIF = new StringField(_TABLE_MODEL, "nif", "nif", 255);

    public static final Field<?>[] ALL_PSEUDONIM_FIELDS = {
        PSEUDONIMID,
        PSEUDONIM,
        NIF
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        PSEUDONIMID
    };
}
