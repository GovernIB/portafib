
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface UsuariEntitatFavoritFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_usuarientitatfavorit";


  public static final String _TABLE_MODEL = "usuariEntitatFavorit";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ID = new LongField(_TABLE_MODEL, "ID", "id");  // PK
	 public static final StringField ORIGENID = new StringField(_TABLE_MODEL, "origenID", "origenid");
	 public static final StringField FAVORITID = new StringField(_TABLE_MODEL, "favoritID", "favoritid");


  public static final Field<?>[] ALL_USUARIENTITATFAVORIT_FIELDS = {
    ID,
    ORIGENID,
    FAVORITID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ID
  };
}
