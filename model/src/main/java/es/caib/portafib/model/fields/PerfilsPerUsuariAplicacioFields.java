package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_perfilsperusrapp */
public interface PerfilsPerUsuariAplicacioFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_perfilsperusrapp";

    public static final String _TABLE_MODEL = "perfilsPerUsuariAplicacio";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField PERFILSPERUSRAPPID = new LongField(_TABLE_MODEL, "perfilsPerUsrAppID", "perfilsperusrappid", 19);  // PK
    public static final LongField PERFILDEFIRMAID = new LongField(_TABLE_MODEL, "perfilDeFirmaID", "usuariaplicacioperfilid", 19);
    public static final StringField USUARIAPLICACIOID = new StringField(_TABLE_MODEL, "usuariAplicacioID", "usuariaplicacioid", 50);

    public static final Field<?>[] ALL_PERFILSPERUSUARIAPLICACIO_FIELDS = {
        PERFILSPERUSRAPPID,
        PERFILDEFIRMAID,
        USUARIAPLICACIOID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        PERFILSPERUSRAPPID
    };
}
