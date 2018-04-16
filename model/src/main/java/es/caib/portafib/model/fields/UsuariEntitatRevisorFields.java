
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface UsuariEntitatRevisorFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_usuarientitatrevisor";


  public static final String _TABLE_MODEL = "usuariEntitatRevisor";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField USUARIENTITATREVISORID = new LongField(_TABLE_MODEL, "usuariEntitatRevisorId", "usuarientitatrevisorid");  // PK
	 public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid");
	 public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu");


  public static final Field<?>[] ALL_USUARIENTITATREVISOR_FIELDS = {
    USUARIENTITATREVISORID,
    USUARIENTITATID,
    ACTIU
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
USUARIENTITATREVISORID
  };
}
