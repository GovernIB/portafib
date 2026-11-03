package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_usuariaplicacioperfil */
public interface PerfilDeFirmaFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_usuariaplicacioperfil";

    public static final String _TABLE_MODEL = "perfilDeFirma";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField USUARIAPLICACIOPERFILID = new LongField(_TABLE_MODEL, "usuariAplicacioPerfilID", "usuariaplicacioperfilid", 19);  // PK
    public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom", 255);
    public static final StringField CODI = new StringField(_TABLE_MODEL, "codi", "codi", 100);
    public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio", 500);
    public static final StringField CONDICIO = new StringField(_TABLE_MODEL, "condicio", "condicio", 4000);
    public static final LongField CONFIGURACIODEFIRMA1ID = new LongField(_TABLE_MODEL, "configuracioDeFirma1ID", "usrappconfiguracio1id", 19);
    public static final LongField CONFIGURACIODEFIRMA2ID = new LongField(_TABLE_MODEL, "configuracioDeFirma2ID", "usrappconfiguracio2id", 19);
    public static final LongField CONFIGURACIODEFIRMA3ID = new LongField(_TABLE_MODEL, "configuracioDeFirma3ID", "usrappconfiguracio3id", 19);
    public static final LongField CONFIGURACIODEFIRMA4ID = new LongField(_TABLE_MODEL, "configuracioDeFirma4ID", "usrappconfiguracio4id", 19);
    public static final LongField CONFIGURACIODEFIRMA5ID = new LongField(_TABLE_MODEL, "configuracioDeFirma5ID", "usrappconfiguracio5id", 19);
    public static final StringField URLBASE = new StringField(_TABLE_MODEL, "urlBase", "urlbase", 255);

    public static final Field<?>[] ALL_PERFILDEFIRMA_FIELDS = {
        USUARIAPLICACIOPERFILID,
        NOM,
        CODI,
        DESCRIPCIO,
        CONDICIO,
        CONFIGURACIODEFIRMA1ID,
        CONFIGURACIODEFIRMA2ID,
        CONFIGURACIODEFIRMA3ID,
        CONFIGURACIODEFIRMA4ID,
        CONFIGURACIODEFIRMA5ID,
        URLBASE
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        USUARIAPLICACIOPERFILID
    };
}
