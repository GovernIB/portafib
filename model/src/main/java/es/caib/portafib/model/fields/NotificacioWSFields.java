
package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.BooleanField;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.IntegerField;
import org.fundaciobit.genapp.common.query.LongField;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.TimestampField;
public interface NotificacioWSFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_notificacio";


  public static final String _TABLE_MODEL = "notificacioWS";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField NOTIFICACIOID = new LongField(_TABLE_MODEL, "notificacioID", "notificacioid");  // PK
	 public static final LongField PETICIODEFIRMAID = new LongField(_TABLE_MODEL, "peticioDeFirmaID", "peticiodefirmaid");
	 public static final LongField TIPUSNOTIFICACIOID = new LongField(_TABLE_MODEL, "tipusNotificacioID", "tipusnotificacioid");
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio");
	 public static final TimestampField DATAENVIAMENT = new TimestampField(_TABLE_MODEL, "dataEnviament", "dataenviament");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final BooleanField BLOQUEJADA = new BooleanField(_TABLE_MODEL, "bloquejada", "bloquejada");
	 public static final StringField ERROR = new StringField(_TABLE_MODEL, "error", "error");
	 public static final TimestampField DATAERROR = new TimestampField(_TABLE_MODEL, "dataError", "dataerror");
	 public static final IntegerField REINTENTS = new IntegerField(_TABLE_MODEL, "reintents", "reintents");
	 public static final StringField USUARIAPLICACIOID = new StringField(_TABLE_MODEL, "usuariaplicacioid", "usuariaplicacioid");


  public static final Field<?>[] ALL_NOTIFICACIOWS_FIELDS = {
    NOTIFICACIOID,
    PETICIODEFIRMAID,
    TIPUSNOTIFICACIOID,
    DATACREACIO,
    DATAENVIAMENT,
    DESCRIPCIO,
    BLOQUEJADA,
    ERROR,
    DATAERROR,
    REINTENTS,
    USUARIAPLICACIOID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
NOTIFICACIOID
  };
}
