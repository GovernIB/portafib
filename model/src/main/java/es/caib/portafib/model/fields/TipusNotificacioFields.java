
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface TipusNotificacioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_tipusnotificacio";


  public static final String _TABLE_MODEL = "tipusNotificacio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField TIPUSNOTIFICACIOID = new LongField(_TABLE_MODEL, "tipusNotificacioID", "tipusnotificacioid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final BooleanField ESAVIS = new BooleanField(_TABLE_MODEL, "esAvis", "esavis");


  public static final Field<?>[] ALL_TIPUSNOTIFICACIO_FIELDS = {
    TIPUSNOTIFICACIOID,
    NOM,
    DESCRIPCIO,
    ESAVIS
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
TIPUSNOTIFICACIOID
  };
}
