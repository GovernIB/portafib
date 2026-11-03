package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_annex */
public interface AnnexFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_annex";

    public static final String _TABLE_MODEL = "annex";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField ANNEXID = new LongField(_TABLE_MODEL, "annexID", "annexid", 19);  // PK
    public static final LongField PETICIODEFIRMAID = new LongField(_TABLE_MODEL, "peticioDeFirmaID", "peticiodefirmaid", 19);
    public static final LongField FITXERID = new LongField(_TABLE_MODEL, "fitxerID", "fitxerid", 19);
    public static final BooleanField ADJUNTAR = new BooleanField(_TABLE_MODEL, "adjuntar", "adjuntar", 1);
    public static final BooleanField FIRMAR = new BooleanField(_TABLE_MODEL, "firmar", "firmar", 1);

    public static final Field<?>[] ALL_ANNEX_FIELDS = {
        ANNEXID,
        PETICIODEFIRMAID,
        FITXERID,
        ADJUNTAR,
        FIRMAR
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        ANNEXID
    };
}
