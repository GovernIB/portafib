
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface TipusDocumentColaboracioDelegacioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_tipusdocumentcoladele";


  public static final String _TABLE_MODEL = "tipusDocumentColaboracioDelegacio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ID = new LongField(_TABLE_MODEL, "id", "id");  // PK
	 public static final LongField COLABORACIODELEGACIOID = new LongField(_TABLE_MODEL, "colaboracioDelegacioID", "colaboraciodelegacioid");
	 public static final LongField TIPUSDOCUMENTID = new LongField(_TABLE_MODEL, "tipusDocumentID", "tipusdocumentid");


  public static final Field<?>[] ALL_TIPUSDOCUMENTCOLABORACIODELEGACIO_FIELDS = {
    ID,
    COLABORACIODELEGACIOID,
    TIPUSDOCUMENTID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ID
  };
}
