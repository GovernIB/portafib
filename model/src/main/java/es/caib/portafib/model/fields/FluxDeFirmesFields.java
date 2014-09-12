
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface FluxDeFirmesFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_fluxdefirmes";


  public static final String _TABLE_MODEL = "fluxDeFirmes";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField FLUXDEFIRMESID = new LongField(_TABLE_MODEL, "fluxDeFirmesID", "fluxdefirmesid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");


  public static final Field<?>[] ALL_FLUXDEFIRMES_FIELDS = {
    FLUXDEFIRMESID,
    NOM
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
FLUXDEFIRMESID
  };
}
