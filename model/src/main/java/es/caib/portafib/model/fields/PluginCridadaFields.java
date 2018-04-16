
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PluginCridadaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_plugincridada";


  public static final String _TABLE_MODEL = "pluginCridada";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PLUGINCRIDADAID = new LongField(_TABLE_MODEL, "pluginCridadaID", "plugincridadaid");  // PK
	 public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final TimestampField DATA = new TimestampField(_TABLE_MODEL, "data", "data");
	 public static final IntegerField TIPUSPLUGIN = new IntegerField(_TABLE_MODEL, "tipusPlugin", "tipusplugin");
	 public static final StringField DADESPLUGIN = new StringField(_TABLE_MODEL, "dadesPlugin", "dadesplugin");
	 public static final StringField METODEPLUGIN = new StringField(_TABLE_MODEL, "metodePlugin", "metodeplugin");
	 public static final StringField DADESCRIDADA = new StringField(_TABLE_MODEL, "dadesCridada", "dadescridada");
	 public static final IntegerField TIPUSTESULTAT = new IntegerField(_TABLE_MODEL, "tipusTesultat", "tipusresultat");
	 public static final StringField RESULTAT = new StringField(_TABLE_MODEL, "resultat", "resultat");
	 public static final LongField TEMPSEXECUCIO = new LongField(_TABLE_MODEL, "tempsExecucio", "tempsexecucio");


  public static final Field<?>[] ALL_PLUGINCRIDADA_FIELDS = {
    PLUGINCRIDADAID,
    ENTITATID,
    DATA,
    TIPUSPLUGIN,
    DADESPLUGIN,
    METODEPLUGIN,
    DADESCRIDADA,
    TIPUSTESULTAT,
    RESULTAT,
    TEMPSEXECUCIO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PLUGINCRIDADAID
  };
}
