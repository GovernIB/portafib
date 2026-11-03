package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_usuarientitat */
public interface UsuariEntitatFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_usuarientitat";

    public static final String _TABLE_MODEL = "usuariEntitat";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid", 101);  // PK
    public static final StringField CARREC = new StringField(_TABLE_MODEL, "carrec", "carrec", 150);
    public static final StringField USUARIPERSONAID = new StringField(_TABLE_MODEL, "usuariPersonaID", "usuaripersonaid", 50);
    public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatID", "entitatid", 50);
    public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu", 1);
    public static final StringField EMAIL = new StringField(_TABLE_MODEL, "email", "email", 100);
    public static final LongField LOGOSEGELLID = new LongField(_TABLE_MODEL, "logoSegellID", "logosegellid", 19);
    public static final BooleanField PREDETERMINAT = new BooleanField(_TABLE_MODEL, "predeterminat", "predeterminat", 1);
    public static final BooleanField REBRETOTSELSAVISOS = new BooleanField(_TABLE_MODEL, "rebreTotsElsAvisos", "rebretotselsavisos", 1);
    public static final IntegerField POLITICADEPLUGINFIRMAWEB = new IntegerField(_TABLE_MODEL, "politicaDePluginFirmaWeb", "politicadepluginfirmaweb", 10);
    public static final IntegerField POLITICACUSTODIA = new IntegerField(_TABLE_MODEL, "politicaCustodia", "politicacustodia", 10);
    public static final LongField CUSTODIAINFOID = new LongField(_TABLE_MODEL, "custodiaInfoID", "custodiainfoid", 19);

    public static final Field<?>[] ALL_USUARIENTITAT_FIELDS = {
        USUARIENTITATID,
        CARREC,
        USUARIPERSONAID,
        ENTITATID,
        ACTIU,
        EMAIL,
        LOGOSEGELLID,
        PREDETERMINAT,
        REBRETOTSELSAVISOS,
        POLITICADEPLUGINFIRMAWEB,
        POLITICACUSTODIA,
        CUSTODIAINFOID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        USUARIENTITATID
    };
}
