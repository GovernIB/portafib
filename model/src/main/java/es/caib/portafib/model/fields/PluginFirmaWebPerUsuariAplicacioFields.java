
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PluginFirmaWebPerUsuariAplicacioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_pluginfirmawebperusrapp";


  public static final String _TABLE_MODEL = "pluginFirmaWebPerUsuariAplicacio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PLUGINFIRMAWEBPERUSRAPPID = new LongField(_TABLE_MODEL, "pluginfirmawebperusrappid", "pluginfirmawebperusrappid");  // PK
	 public static final StringField USUARIAPLICACIOID = new StringField(_TABLE_MODEL, "usuariAplicacioID", "usuariaplicacioid");
	 public static final LongField PLUGINFIRMAWEBID = new LongField(_TABLE_MODEL, "pluginFirmaWebID", "pluginfirmawebid");
	 public static final IntegerField ACCIO = new IntegerField(_TABLE_MODEL, "accio", "accio");


  public static final Field<?>[] ALL_PLUGINFIRMAWEBPERUSUARIAPLICACIO_FIELDS = {
    PLUGINFIRMAWEBPERUSRAPPID,
    USUARIAPLICACIOID,
    PLUGINFIRMAWEBID,
    ACCIO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PLUGINFIRMAWEBPERUSRAPPID
  };
}
