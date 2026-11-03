package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_estadistica */
public interface EstadisticaFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_estadistica";

    public static final String _TABLE_MODEL = "estadistica";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField ESTADISTICAID = new LongField(_TABLE_MODEL, "estadisticaID", "estadisticaid", 19);  // PK
    public static final TimestampField DATA = new TimestampField(_TABLE_MODEL, "data", "data", 29);
    public static final IntegerField TIPUS = new IntegerField(_TABLE_MODEL, "tipus", "tipus", 10);
    public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatID", "entitatid", 50);
    public static final DoubleField VALOR = new DoubleField(_TABLE_MODEL, "valor", "valor", 17);
    public static final StringField USUARIAPLICACIOID = new StringField(_TABLE_MODEL, "usuariAplicacioID", "usuariaplicacioid", 101);
    public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid", 101);
    public static final StringField PARAMETRES = new StringField(_TABLE_MODEL, "parametres", "parametres", 3000);

    public static final Field<?>[] ALL_ESTADISTICA_FIELDS = {
        ESTADISTICAID,
        DATA,
        TIPUS,
        ENTITATID,
        VALOR,
        USUARIAPLICACIOID,
        USUARIENTITATID,
        PARAMETRES
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        ESTADISTICAID
    };
}
