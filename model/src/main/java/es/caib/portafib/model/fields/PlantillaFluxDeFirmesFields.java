package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_plantillafluxdefirmes */
public interface PlantillaFluxDeFirmesFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_plantillafluxdefirmes";

    public static final String _TABLE_MODEL = "plantillaFluxDeFirmes";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField FLUXDEFIRMESID = new LongField(_TABLE_MODEL, "fluxDeFirmesID", "fluxdefirmesid", 19);  // PK
    public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio", 1000);
    public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid", 101);
    public static final StringField USUARIAPLICACIOID = new StringField(_TABLE_MODEL, "usuariAplicacioID", "usuariaplicacioid", 101);
    public static final BooleanField COMPARTIR = new BooleanField(_TABLE_MODEL, "compartir", "compartir", 1);

    public static final Field<?>[] ALL_PLANTILLAFLUXDEFIRMES_FIELDS = {
        FLUXDEFIRMESID,
        DESCRIPCIO,
        USUARIENTITATID,
        USUARIAPLICACIOID,
        COMPARTIR
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        FLUXDEFIRMESID
    };
}
