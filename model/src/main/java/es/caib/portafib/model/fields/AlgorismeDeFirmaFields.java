
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface AlgorismeDeFirmaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_algorismedefirma";


  public static final String _TABLE_MODEL = "algorismeDeFirma";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final IntegerField ALGORISMEDEFIRMAID = new IntegerField(_TABLE_MODEL, "algorismeDeFirmaID", "algorismedefirmaid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final BooleanField SUPORTAT = new BooleanField(_TABLE_MODEL, "suportat", "suportat");


  public static final Field<?>[] ALL_ALGORISMEDEFIRMA_FIELDS = {
    ALGORISMEDEFIRMAID,
    NOM,
    DESCRIPCIO,
    SUPORTAT
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ALGORISMEDEFIRMAID
  };
}
