package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_permisusuariplantilla */
public interface PermisUsuariPlantillaFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_permisusuariplantilla";

    public static final String _TABLE_MODEL = "permisUsuariPlantilla";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField PERMISUSUARIPLANTILLAID = new LongField(_TABLE_MODEL, "permisUsuariPlantillaID", "permisusuariplantillaid", 19);  // PK
    public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid", 101);
    public static final LongField PLANTILLAFLUXDEFIRMESID = new LongField(_TABLE_MODEL, "plantillaFluxDeFirmesID", "fluxdefirmesid", 19);

    public static final Field<?>[] ALL_PERMISUSUARIPLANTILLA_FIELDS = {
        PERMISUSUARIPLANTILLAID,
        USUARIENTITATID,
        PLANTILLAFLUXDEFIRMESID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        PERMISUSUARIPLANTILLAID
    };
}
