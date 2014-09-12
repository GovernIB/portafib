
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface TipusFirmaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_tipusfirma";


  public static final String _TABLE_MODEL = "tipusFirma";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final IntegerField TIPUSFIRMAID = new IntegerField(_TABLE_MODEL, "tipusFirmaID", "tipusfirmaid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final BooleanField SUPORTADA = new BooleanField(_TABLE_MODEL, "suportada", "suportada");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");


  public static final Field<?>[] ALL_TIPUSFIRMA_FIELDS = {
    TIPUSFIRMAID,
    NOM,
    SUPORTADA,
    DESCRIPCIO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
TIPUSFIRMAID
  };
}
