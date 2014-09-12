
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface BlocDeFirmesFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_blocdefirmes";


  public static final String _TABLE_MODEL = "blocDeFirmes";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField BLOCDEFIRMESID = new LongField(_TABLE_MODEL, "blocDeFirmesID", "blocdefirmesid");  // PK
	 public static final IntegerField ORDRE = new IntegerField(_TABLE_MODEL, "ordre", "ordre");
	 public static final TimestampField DATAFINALITZACIO = new TimestampField(_TABLE_MODEL, "dataFinalitzacio", "datafinalitzacio");
	 public static final LongField FLUXDEFIRMESID = new LongField(_TABLE_MODEL, "fluxDeFirmesID", "fluxdefirmesid");
	 public static final IntegerField MINIMDEFIRMES = new IntegerField(_TABLE_MODEL, "minimDeFirmes", "minimdefirmes");


  public static final Field<?>[] ALL_BLOCDEFIRMES_FIELDS = {
    BLOCDEFIRMESID,
    ORDRE,
    DATAFINALITZACIO,
    FLUXDEFIRMESID,
    MINIMDEFIRMES
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
BLOCDEFIRMESID
  };
}
