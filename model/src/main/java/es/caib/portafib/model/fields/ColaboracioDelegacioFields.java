package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_colaboraciodelegacio */
public interface ColaboracioDelegacioFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_colaboraciodelegacio";

    public static final String _TABLE_MODEL = "colaboracioDelegacio";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField COLABORACIODELEGACIOID = new LongField(_TABLE_MODEL, "colaboracioDelegacioID", "colaboraciodelegacioid", 19);  // PK
    public static final StringField DESTINATARIID = new StringField(_TABLE_MODEL, "destinatariID", "destinatariid", 101);
    public static final StringField COLABORADORDELEGATID = new StringField(_TABLE_MODEL, "colaboradorDelegatID", "colaboradordelegatid", 101);
    public static final BooleanField ESDELEGAT = new BooleanField(_TABLE_MODEL, "esDelegat", "esdelegat", 1);
    public static final StringField MOTIU = new StringField(_TABLE_MODEL, "motiu", "motiu", 60);
    public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio", 255);
    public static final TimestampField DATAINICI = new TimestampField(_TABLE_MODEL, "dataInici", "datainici", 29);
    public static final TimestampField DATAFI = new TimestampField(_TABLE_MODEL, "dataFi", "datafi", 29);
    public static final BooleanField ACTIVA = new BooleanField(_TABLE_MODEL, "activa", "activa", 1);
    public static final BooleanField REVISOR = new BooleanField(_TABLE_MODEL, "revisor", "revisor", 1);
    public static final StringField MOTIUDESHABILITADA = new StringField(_TABLE_MODEL, "motiuDeshabilitada", "motiudeshabilitada", 255);
    public static final LongField FITXERAUTORITZACIOID = new LongField(_TABLE_MODEL, "fitxerAutoritzacioID", "fitxerautoritzacioid", 19);

    public static final Field<?>[] ALL_COLABORACIODELEGACIO_FIELDS = {
        COLABORACIODELEGACIOID,
        DESTINATARIID,
        COLABORADORDELEGATID,
        ESDELEGAT,
        MOTIU,
        DESCRIPCIO,
        DATAINICI,
        DATAFI,
        ACTIVA,
        REVISOR,
        MOTIUDESHABILITADA,
        FITXERAUTORITZACIOID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        COLABORACIODELEGACIOID
    };
}
