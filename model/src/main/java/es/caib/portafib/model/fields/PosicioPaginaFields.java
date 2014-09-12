
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PosicioPaginaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_posiciopagina";


  public static final String _TABLE_MODEL = "posicioPagina";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField POSICIOPAGINAID = new LongField(_TABLE_MODEL, "posicioPaginaID", "posiciopaginaid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");


  public static final Field<?>[] ALL_POSICIOPAGINA_FIELDS = {
    POSICIOPAGINAID,
    NOM
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
POSICIOPAGINAID
  };
}
