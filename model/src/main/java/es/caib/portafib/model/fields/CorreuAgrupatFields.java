package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_correuagrupat */
public interface CorreuAgrupatFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_correuagrupat";

    public static final String _TABLE_MODEL = "correuAgrupat";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField CORREUAGRUPATID = new LongField(_TABLE_MODEL, "correuAgrupatID", "correuagrupatid", 19);  // PK
    public static final StringField EMAIL = new StringField(_TABLE_MODEL, "email", "email", 255);
    public static final StringField SUBJECT = new StringField(_TABLE_MODEL, "subject", "subject", 255);
    public static final StringField MESSAGE = new StringField(_TABLE_MODEL, "message", "message", 2147483647);
    public static final BooleanField HTML = new BooleanField(_TABLE_MODEL, "html", "html", 1);
    public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid", 101);
    public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio", 29);
    public static final StringField ERROR = new StringField(_TABLE_MODEL, "error", "error", 2147483647);

    public static final Field<?>[] ALL_CORREUAGRUPAT_FIELDS = {
        CORREUAGRUPATID,
        EMAIL,
        SUBJECT,
        MESSAGE,
        HTML,
        USUARIENTITATID,
        DATACREACIO,
        ERROR
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        CORREUAGRUPATID
    };
}
