
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface BitacolaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_bitacola";


  public static final String _TABLE_MODEL = "bitacola";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField BITACOLAID = new LongField(_TABLE_MODEL, "bitacolaID", "bitacolaid");  // PK
	 public static final TimestampField DATA = new TimestampField(_TABLE_MODEL, "data", "data");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final LongField PETICIODEFIRMAID = new LongField(_TABLE_MODEL, "peticioDeFirmaID", "peticiodefirmaid");
	 public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid");


  public static final Field<?>[] ALL_BITACOLA_FIELDS = {
    BITACOLAID,
    DATA,
    DESCRIPCIO,
    PETICIODEFIRMAID,
    USUARIENTITATID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
BITACOLAID
  };
}
