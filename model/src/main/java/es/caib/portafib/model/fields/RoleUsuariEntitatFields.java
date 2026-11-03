package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_roleusuarientitat */
public interface RoleUsuariEntitatFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_roleusuarientitat";

    public static final String _TABLE_MODEL = "roleUsuariEntitat";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField ID = new LongField(_TABLE_MODEL, "id", "id", 19);  // PK
    public static final StringField ROLEID = new StringField(_TABLE_MODEL, "roleID", "roleid", 50);
    public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid", 101);

    public static final Field<?>[] ALL_ROLEUSUARIENTITAT_FIELDS = {
        ID,
        ROLEID,
        USUARIENTITATID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        ID
    };
}
