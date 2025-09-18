
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PseudonimFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_pseudonim";


  public static final String _TABLE_MODEL = "pseudonim";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PSEUDONIMID = new LongField(_TABLE_MODEL, "pseudonimid", "pseudonimid");  // PK
	 public static final StringField PSEUDONIM = new StringField(_TABLE_MODEL, "pseudonim", "pseudonim");
	 public static final StringField NIF = new StringField(_TABLE_MODEL, "nif", "nif");


  public static final Field<?>[] ALL_PSEUDONIM_FIELDS = {
    PSEUDONIMID,
    PSEUDONIM,
    NIF
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PSEUDONIMID
  };
}
