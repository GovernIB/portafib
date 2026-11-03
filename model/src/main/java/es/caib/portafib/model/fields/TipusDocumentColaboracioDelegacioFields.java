package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_tipusdocumentcoladele */
public interface TipusDocumentColaboracioDelegacioFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_tipusdocumentcoladele";

    public static final String _TABLE_MODEL = "tipusDocumentColaboracioDelegacio";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField ID = new LongField(_TABLE_MODEL, "id", "id", 19);  // PK
    public static final LongField COLABORACIODELEGACIOID = new LongField(_TABLE_MODEL, "colaboracioDelegacioID", "colaboraciodelegacioid", 19);
    public static final LongField TIPUSDOCUMENTID = new LongField(_TABLE_MODEL, "tipusDocumentID", "tipusdocumentid", 19);

    public static final Field<?>[] ALL_TIPUSDOCUMENTCOLABORACIODELEGACIO_FIELDS = {
        ID,
        COLABORACIODELEGACIOID,
        TIPUSDOCUMENTID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        ID
    };
}
