
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface BitacolaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_bitacola";


  public static final String _TABLE_MODEL = "bitacola";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField BITACOLAID = new LongField(_TABLE_MODEL, "bitacolaID", "bitacolaid");  // PK
	 public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatid", "entitatid");
	 public static final StringField USUARIID = new StringField(_TABLE_MODEL, "usuariid", "usuariid");
	 public static final TimestampField DATA = new TimestampField(_TABLE_MODEL, "data", "data");
	 public static final IntegerField TIPUSOBJECTE = new IntegerField(_TABLE_MODEL, "tipusObjecte", "tipusobjecte");
	 public static final StringField OBJECTEID = new StringField(_TABLE_MODEL, "objecteid", "objecteid");
	 public static final IntegerField TIPUSOPERACIO = new IntegerField(_TABLE_MODEL, "tipusOperacio", "tipusoperacio");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final StringField OBJECTESERIALITZAT = new StringField(_TABLE_MODEL, "objecteSerialitzat", "objecteserialitzat");


  public static final Field<?>[] ALL_BITACOLA_FIELDS = {
    BITACOLAID,
    ENTITATID,
    USUARIID,
    DATA,
    TIPUSOBJECTE,
    OBJECTEID,
    TIPUSOPERACIO,
    DESCRIPCIO,
    OBJECTESERIALITZAT
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
BITACOLAID
  };
}
