package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_modulfirmapertipusdoc */
public interface ModulDeFirmaPerTipusDeDocumentFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_modulfirmapertipusdoc";

    public static final String _TABLE_MODEL = "modulDeFirmaPerTipusDeDocument";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField ID = new LongField(_TABLE_MODEL, "ID", "id", 19);  // PK
    public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom", 100);
    public static final LongField TIPUSDOCUMENTID = new LongField(_TABLE_MODEL, "tipusDocumentID", "tipusdocumentid", 19);
    public static final LongField PLUGINID = new LongField(_TABLE_MODEL, "pluginID", "pluginid", 19);

    public static final Field<?>[] ALL_MODULDEFIRMAPERTIPUSDEDOCUMENT_FIELDS = {
        ID,
        NOM,
        TIPUSDOCUMENTID,
        PLUGINID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        ID
    };
}
