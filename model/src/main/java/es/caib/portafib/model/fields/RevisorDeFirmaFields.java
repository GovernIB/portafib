
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface RevisorDeFirmaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_revisordefirma";


  public static final String _TABLE_MODEL = "revisorDeFirma";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField REVISORDEFIRMAID = new LongField(_TABLE_MODEL, "revisorDeFirmaID", "revisordefirmaid");  // PK
	 public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid");
	 public static final LongField FIRMAID = new LongField(_TABLE_MODEL, "firmaID", "firmaid");
	 public static final BooleanField OBLIGATORI = new BooleanField(_TABLE_MODEL, "obligatori", "obligatori");


  public static final Field<?>[] ALL_REVISORDEFIRMA_FIELDS = {
    REVISORDEFIRMAID,
    USUARIENTITATID,
    FIRMAID,
    OBLIGATORI
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
REVISORDEFIRMAID
  };
}
