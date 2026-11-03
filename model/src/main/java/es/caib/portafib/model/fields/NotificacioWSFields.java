package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_notificacio */
public interface NotificacioWSFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_notificacio";

    public static final String _TABLE_MODEL = "notificacioWS";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField NOTIFICACIOID = new LongField(_TABLE_MODEL, "notificacioID", "notificacioid", 19);  // PK
    public static final LongField PETICIODEFIRMAID = new LongField(_TABLE_MODEL, "peticioDeFirmaID", "peticiodefirmaid", 19);
    public static final LongField TIPUSNOTIFICACIOID = new LongField(_TABLE_MODEL, "tipusNotificacioID", "tipusnotificacioid", 19);
    public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio", 29);
    public static final TimestampField DATAENVIAMENT = new TimestampField(_TABLE_MODEL, "dataEnviament", "dataenviament", 29);
    public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio", 2147483647);
    public static final BooleanField BLOQUEJADA = new BooleanField(_TABLE_MODEL, "bloquejada", "bloquejada", 1);
    public static final StringField ERROR = new StringField(_TABLE_MODEL, "error", "error", 2147483647);
    public static final TimestampField DATAERROR = new TimestampField(_TABLE_MODEL, "dataError", "dataerror", 35);
    public static final IntegerField REINTENTS = new IntegerField(_TABLE_MODEL, "reintents", "reintents", 10);
    public static final StringField USUARIAPLICACIOID = new StringField(_TABLE_MODEL, "usuariAplicacioID", "usuariaplicacioid", 101);

    public static final Field<?>[] ALL_NOTIFICACIOWS_FIELDS = {
        NOTIFICACIOID,
        PETICIODEFIRMAID,
        TIPUSNOTIFICACIOID,
        DATACREACIO,
        DATAENVIAMENT,
        DESCRIPCIO,
        BLOQUEJADA,
        ERROR,
        DATAERROR,
        REINTENTS,
        USUARIAPLICACIOID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        NOTIFICACIOID
    };
}
