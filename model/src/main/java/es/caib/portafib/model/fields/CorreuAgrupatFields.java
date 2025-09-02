
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface CorreuAgrupatFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_correuagrupat";


  public static final String _TABLE_MODEL = "correuAgrupat";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField CORREUAGRUPATID = new LongField(_TABLE_MODEL, "correuAgrupatID", "correuagrupatid");  // PK
	 public static final StringField EMAIL = new StringField(_TABLE_MODEL, "email", "email");
	 public static final StringField SUBJECT = new StringField(_TABLE_MODEL, "subject", "subject");
	 public static final StringField MESSAGE = new StringField(_TABLE_MODEL, "message", "message");
	 public static final BooleanField HTML = new BooleanField(_TABLE_MODEL, "html", "html");
	 public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid");
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio");
	 public static final StringField ERROR = new StringField(_TABLE_MODEL, "error", "error");


  public static final Field<?>[] ALL_CORREUAGRUPAT_FIELDS = {
    CORREUAGRUPATID,
    EMAIL,
    SUBJECT,
    MESSAGE,
    HTML,
    USUARIENTITATID,
    DATACREACIO,
    ERROR
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
CORREUAGRUPATID
  };
}
