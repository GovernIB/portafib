package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_plugincridada */
public interface PluginCridadaFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_plugincridada";

    public static final String _TABLE_MODEL = "pluginCridada";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField PLUGINCRIDADAID = new LongField(_TABLE_MODEL, "pluginCridadaID", "plugincridadaid", 19);  // PK
    public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatID", "entitatid", 50);
    public static final TimestampField DATA = new TimestampField(_TABLE_MODEL, "data", "data", 35);
    public static final LongField PLUGINID = new LongField(_TABLE_MODEL, "pluginID", "pluginid", 19);
    public static final StringField METODEPLUGIN = new StringField(_TABLE_MODEL, "metodePlugin", "metodeplugin", 100);
    public static final StringField PARAMETRESTEXT = new StringField(_TABLE_MODEL, "parametresText", "parametrestext", 2147483647);
    public static final LongField PARAMETRESFITXERID = new LongField(_TABLE_MODEL, "parametresFitxerID", "parametresfitxerid", 19);
    public static final StringField RETORNTEXT = new StringField(_TABLE_MODEL, "retornText", "retorntext", 2147483647);
    public static final LongField RETORNFITXERID = new LongField(_TABLE_MODEL, "retornFitxerID", "retornfitxerid", 19);
    public static final IntegerField TIPUSTESULTAT = new IntegerField(_TABLE_MODEL, "tipusTesultat", "tipusresultat", 10);
    public static final LongField TEMPSEXECUCIO = new LongField(_TABLE_MODEL, "tempsExecucio", "tempsexecucio", 19);

    public static final Field<?>[] ALL_PLUGINCRIDADA_FIELDS = {
        PLUGINCRIDADAID,
        ENTITATID,
        DATA,
        PLUGINID,
        METODEPLUGIN,
        PARAMETRESTEXT,
        PARAMETRESFITXERID,
        RETORNTEXT,
        RETORNFITXERID,
        TIPUSTESULTAT,
        TEMPSEXECUCIO
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        PLUGINCRIDADAID
    };
}
