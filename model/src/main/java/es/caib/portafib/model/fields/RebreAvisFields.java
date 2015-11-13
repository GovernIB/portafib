
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface RebreAvisFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_rebreavis";


  public static final String _TABLE_MODEL = "rebreAvis";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ID = new LongField(_TABLE_MODEL, "id", "id");  // PK
	 public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid");
	 public static final LongField TIPUSNOTIFICACIOID = new LongField(_TABLE_MODEL, "tipusNotificacioID", "tipusnotificacioid");
	 public static final BooleanField REBREAGRUPAT = new BooleanField(_TABLE_MODEL, "rebreAgrupat", "rebreagrupat");


  public static final Field<?>[] ALL_REBREAVIS_FIELDS = {
    ID,
    USUARIENTITATID,
    TIPUSNOTIFICACIOID,
    REBREAGRUPAT
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ID
  };
}
