
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PrioritatFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_prioritat";


  public static final String _TABLE_MODEL = "prioritat";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final IntegerField PRIORITATID = new IntegerField(_TABLE_MODEL, "prioritatID", "prioritatid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");


  public static final Field<?>[] ALL_PRIORITAT_FIELDS = {
    PRIORITATID,
    NOM
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PRIORITATID
  };
}
