package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_plugin */
public interface PluginFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_plugin";

    public static final String _TABLE_MODEL = "plugin";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField PLUGINID = new LongField(_TABLE_MODEL, "pluginID", "pluginid", 19);  // PK
    public static final StringField CODI = new StringField(_TABLE_MODEL, "codi", "codi", 255);
    public static final LongField NOMID = new LongField(_TABLE_MODEL, "nomID", "nomid", 19);
    public static final LongField DESCRIPCIOCURTAID = new LongField(_TABLE_MODEL, "descripcioCurtaID", "descripciocurtaid", 19);
    public static final StringField CLASSE = new StringField(_TABLE_MODEL, "classe", "classe", 255);
    public static final IntegerField ORDRE = new IntegerField(_TABLE_MODEL, "ordre", "ordre", 10);
    public static final IntegerField TIPUS = new IntegerField(_TABLE_MODEL, "tipus", "tipus", 10);
    public static final StringField PROPERTIESADMIN = new StringField(_TABLE_MODEL, "propertiesAdmin", "propertiesadmin", 2147483647);
    public static final StringField PROPERTIESENTITAT = new StringField(_TABLE_MODEL, "propertiesEntitat", "propertiesentitat", 2147483647);
    public static final IntegerField POLITICADEUS = new IntegerField(_TABLE_MODEL, "politicaDeUs", "politicadeus", 10);
    public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatID", "entitatid", 50);
    public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu", 1);
    public static final IntegerField POLITICAMOSTRARPROPIETATS = new IntegerField(_TABLE_MODEL, "politicaMostrarPropietats", "politicamostrarpropietats", 10);
    public static final LongField ICONAID = new LongField(_TABLE_MODEL, "iconaID", "iconaid", 19);

    public static final Field<?>[] ALL_PLUGIN_FIELDS = {
        PLUGINID,
        CODI,
        NOMID,
        DESCRIPCIOCURTAID,
        CLASSE,
        ORDRE,
        TIPUS,
        PROPERTIESADMIN,
        PROPERTIESENTITAT,
        POLITICADEUS,
        ENTITATID,
        ACTIU,
        POLITICAMOSTRARPROPIETATS,
        ICONAID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        PLUGINID
    };
}
