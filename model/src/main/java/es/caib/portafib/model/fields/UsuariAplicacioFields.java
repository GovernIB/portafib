package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_usuariaplicacio */
public interface UsuariAplicacioFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_usuariaplicacio";

    public static final String _TABLE_MODEL = "usuariAplicacio";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final StringField USUARIAPLICACIOID = new StringField(_TABLE_MODEL, "usuariAplicacioID", "usuariaplicacioid", 101);  // PK
    public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatID", "entitatid", 50);
    public static final StringField EMAILADMIN = new StringField(_TABLE_MODEL, "emailAdmin", "emailadmin", 100);
    public static final IntegerField CALLBACKVERSIO = new IntegerField(_TABLE_MODEL, "callbackVersio", "callbackversio", 10);
    public static final StringField CALLBACKURL = new StringField(_TABLE_MODEL, "callbackURL", "callbackurl", 400);
    public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu", 1);
    public static final StringField IDIOMAID = new StringField(_TABLE_MODEL, "idiomaID", "idiomaid", 5);
    public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio", 255);
    public static final LongField LOGOSEGELLID = new LongField(_TABLE_MODEL, "logoSegellID", "logosegellid", 19);
    public static final IntegerField POLITICADEPLUGINFIRMAWEB = new IntegerField(_TABLE_MODEL, "politicaDePluginFirmaWeb", "politicadepluginfirmaweb", 10);
    public static final IntegerField POLITICACUSTODIA = new IntegerField(_TABLE_MODEL, "politicaCustodia", "politicacustodia", 10);
    public static final LongField CUSTODIAINFOID = new LongField(_TABLE_MODEL, "custodiaInfoID", "custodiainfoid", 19);
    public static final BooleanField CREARUSUARIS = new BooleanField(_TABLE_MODEL, "crearUsuaris", "crearusuaris", 1);
    public static final BooleanField TIPUSREVISORS = new BooleanField(_TABLE_MODEL, "tipusRevisors", "tipusrevisors", 1);

    public static final Field<?>[] ALL_USUARIAPLICACIO_FIELDS = {
        USUARIAPLICACIOID,
        ENTITATID,
        EMAILADMIN,
        CALLBACKVERSIO,
        CALLBACKURL,
        ACTIU,
        IDIOMAID,
        DESCRIPCIO,
        LOGOSEGELLID,
        POLITICADEPLUGINFIRMAWEB,
        POLITICACUSTODIA,
        CUSTODIAINFOID,
        CREARUSUARIS,
        TIPUSREVISORS
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        USUARIAPLICACIOID
    };
}
