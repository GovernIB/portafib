
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface TipusMetadadaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_tipusmetadada";


  public static final String _TABLE_MODEL = "tipusMetadada";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final IntegerField TIPUSMETADADAID = new IntegerField(_TABLE_MODEL, "tipusMetadadaID", "tipusmetadadaid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");


  public static final Field<?>[] ALL_TIPUSMETADADA_FIELDS = {
    TIPUSMETADADAID,
    NOM,
    DESCRIPCIO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
TIPUSMETADADAID
  };
}
