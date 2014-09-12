
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface UsuariPersonaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_usuaripersona";


  public static final String _TABLE_MODEL = "usuariPersona";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final StringField USUARIPERSONAID = new StringField(_TABLE_MODEL, "usuariPersonaID", "usuaripersonaid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField LLINATGES = new StringField(_TABLE_MODEL, "llinatges", "llinatges");
	 public static final StringField EMAIL = new StringField(_TABLE_MODEL, "email", "email");
	 public static final StringField NIF = new StringField(_TABLE_MODEL, "nif", "nif");
	 public static final StringField IDIOMAID = new StringField(_TABLE_MODEL, "idiomaID", "idiomaid");
	 public static final LongField RUBRICAID = new LongField(_TABLE_MODEL, "rubricaID", "rubricaid");


  public static final Field<?>[] ALL_USUARIPERSONA_FIELDS = {
    USUARIPERSONAID,
    NOM,
    LLINATGES,
    EMAIL,
    NIF,
    IDIOMAID,
    RUBRICAID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
USUARIPERSONAID
  };
}
