
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface MetadadaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_metadada";


  public static final String _TABLE_MODEL = "metadada";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField METADADAID = new LongField(_TABLE_MODEL, "metadadaID", "metadadaid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField VALOR = new StringField(_TABLE_MODEL, "valor", "valor");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final LongField PETICIODEFIRMAID = new LongField(_TABLE_MODEL, "peticioDeFirmaID", "peticiodefirmaid");
	 public static final IntegerField TIPUSMETADADAID = new IntegerField(_TABLE_MODEL, "tipusMetadadaID", "tipusmetadadaid");


  public static final Field<?>[] ALL_METADADA_FIELDS = {
    METADADAID,
    NOM,
    VALOR,
    DESCRIPCIO,
    PETICIODEFIRMAID,
    TIPUSMETADADAID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
METADADAID
  };
}
