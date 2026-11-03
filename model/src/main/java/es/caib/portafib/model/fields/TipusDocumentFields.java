package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_tipusdocument */
public interface TipusDocumentFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_tipusdocument";

    public static final String _TABLE_MODEL = "tipusDocument";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField TIPUSDOCUMENTID = new LongField(_TABLE_MODEL, "tipusDocumentID", "tipusdocumentid", 19);  // PK
    public static final LongField NOMID = new LongField(_TABLE_MODEL, "nomID", "nom", 19);
    public static final LongField TIPUSDOCUMENTBASEID = new LongField(_TABLE_MODEL, "tipusDocumentBaseID", "tipusdocumentbaseid", 19);
    public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio", 1000);
    public static final StringField USUARIAPLICACIOID = new StringField(_TABLE_MODEL, "usuariAplicacioID", "usuariaplicacioid", 50);

    public static final Field<?>[] ALL_TIPUSDOCUMENT_FIELDS = {
        TIPUSDOCUMENTID,
        NOMID,
        TIPUSDOCUMENTBASEID,
        DESCRIPCIO,
        USUARIAPLICACIOID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        TIPUSDOCUMENTID
    };
}
