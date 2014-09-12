
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface CodiBarresFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_codibarres";


  public static final String _TABLE_MODEL = "codiBarres";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final StringField CODIBARRESID = new StringField(_TABLE_MODEL, "codiBarresID", "codibarresid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");


  public static final Field<?>[] ALL_CODIBARRES_FIELDS = {
    CODIBARRESID,
    NOM,
    DESCRIPCIO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
CODIBARRESID
  };
}
