package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_bitacola */
public interface BitacolaFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_bitacola";

    public static final String _TABLE_MODEL = "bitacola";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField BITACOLAID = new LongField(_TABLE_MODEL, "bitacolaID", "bitacolaid", 19);  // PK
    public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatid", "entitatid", 50);
    public static final StringField USUARIID = new StringField(_TABLE_MODEL, "usuariid", "usuariid", 101);
    public static final TimestampField DATA = new TimestampField(_TABLE_MODEL, "data", "data", 29);
    public static final IntegerField TIPUSOBJECTE = new IntegerField(_TABLE_MODEL, "tipusObjecte", "tipusobjecte", 10);
    public static final StringField OBJECTEID = new StringField(_TABLE_MODEL, "objecteid", "objecteid", 100);
    public static final IntegerField TIPUSOPERACIO = new IntegerField(_TABLE_MODEL, "tipusOperacio", "tipusoperacio", 10);
    public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio", 255);
    public static final StringField OBJECTESERIALITZAT = new StringField(_TABLE_MODEL, "objecteSerialitzat", "objecteserialitzat", 2147483647);

    public static final Field<?>[] ALL_BITACOLA_FIELDS = {
        BITACOLAID,
        ENTITATID,
        USUARIID,
        DATA,
        TIPUSOBJECTE,
        OBJECTEID,
        TIPUSOPERACIO,
        DESCRIPCIO,
        OBJECTESERIALITZAT
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        BITACOLAID
    };
}
