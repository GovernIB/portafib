
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface RevisorDeDestinatariFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_revisordedestinatari";


  public static final String _TABLE_MODEL = "revisorDeDestinatari";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField REVISORDEDESTINATARIID = new LongField(_TABLE_MODEL, "revisorDeDestinatariID", "revisordedestinatariid");  // PK
	 public static final StringField DESTINATARIID = new StringField(_TABLE_MODEL, "destinatariID", "destinatariid");
	 public static final StringField REVISORID = new StringField(_TABLE_MODEL, "revisorID", "revisorid");


  public static final Field<?>[] ALL_REVISORDEDESTINATARI_FIELDS = {
    REVISORDEDESTINATARIID,
    DESTINATARIID,
    REVISORID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
REVISORDEDESTINATARIID
  };
}
