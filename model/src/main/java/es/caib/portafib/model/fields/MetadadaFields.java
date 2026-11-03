package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_metadada */
public interface MetadadaFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_metadada";

    public static final String _TABLE_MODEL = "metadada";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField METADADAID = new LongField(_TABLE_MODEL, "metadadaID", "metadadaid", 19);  // PK
    public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom", 50);
    public static final StringField VALOR = new StringField(_TABLE_MODEL, "valor", "valor", 2147483647);
    public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio", 1000);
    public static final LongField PETICIODEFIRMAID = new LongField(_TABLE_MODEL, "peticioDeFirmaID", "peticiodefirmaid", 19);
    public static final IntegerField TIPUSMETADADAID = new IntegerField(_TABLE_MODEL, "tipusMetadadaID", "tipusmetadadaid", 10);

    public static final Field<?>[] ALL_METADADA_FIELDS = {
        METADADAID,
        NOM,
        VALOR,
        DESCRIPCIO,
        PETICIODEFIRMAID,
        TIPUSMETADADAID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        METADADAID
    };
}
