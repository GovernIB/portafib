
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PluginFirmaWebPerUsuariEntitatFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_pluginfirmawebperusrent";


  public static final String _TABLE_MODEL = "pluginFirmaWebPerUsuariEntitat";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PLUGINFIRMAWEBPERUSRENTID = new LongField(_TABLE_MODEL, "pluginFirmaWebPerUsrEntID", "pluginfirmawebperusrentid");  // PK
	 public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid");
	 public static final LongField PLUGINFIRMAWEBID = new LongField(_TABLE_MODEL, "pluginFirmaWebID", "pluginfirmawebid");
	 public static final IntegerField ACCIO = new IntegerField(_TABLE_MODEL, "accio", "accio");


  public static final Field<?>[] ALL_PLUGINFIRMAWEBPERUSUARIENTITAT_FIELDS = {
    PLUGINFIRMAWEBPERUSRENTID,
    USUARIENTITATID,
    PLUGINFIRMAWEBID,
    ACCIO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PLUGINFIRMAWEBPERUSRENTID
  };
}
