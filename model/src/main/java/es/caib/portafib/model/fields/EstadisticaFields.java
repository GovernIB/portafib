
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EstadisticaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_estadistica";


  public static final String _TABLE_MODEL = "estadistica";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ESTADISTICAID = new LongField(_TABLE_MODEL, "estadisticaID", "estadisticaid");  // PK
	 public static final TimestampField DATA = new TimestampField(_TABLE_MODEL, "data", "data");
	 public static final IntegerField TIPUS = new IntegerField(_TABLE_MODEL, "tipus", "tipus");
	 public static final LongField SUBTIPUS = new LongField(_TABLE_MODEL, "subtipus", "subtipus");
	 public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final DoubleField VALOR = new DoubleField(_TABLE_MODEL, "valor", "valor");
	 public static final StringField PARAMETRES = new StringField(_TABLE_MODEL, "parametres", "parametres");


  public static final Field<?>[] ALL_ESTADISTICA_FIELDS = {
    ESTADISTICAID,
    DATA,
    TIPUS,
    SUBTIPUS,
    ENTITATID,
    VALOR,
    PARAMETRES
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ESTADISTICAID
  };
}
