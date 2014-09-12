
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface TipusDocumentFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_tipusdocument";


  public static final String _TABLE_MODEL = "tipusDocument";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField TIPUSDOCUMENTID = new LongField(_TABLE_MODEL, "tipusDocumentID", "tipusdocumentid");  // PK
	 public static final LongField NOMID = new LongField(_TABLE_MODEL, "nomID", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final StringField USUARIAPLICACIOID = new StringField(_TABLE_MODEL, "usuariAplicacioID", "usuariaplicacioid");


  public static final Field<?>[] ALL_TIPUSDOCUMENT_FIELDS = {
    TIPUSDOCUMENTID,
    NOMID,
    DESCRIPCIO,
    USUARIAPLICACIOID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
TIPUSDOCUMENTID
  };
}
