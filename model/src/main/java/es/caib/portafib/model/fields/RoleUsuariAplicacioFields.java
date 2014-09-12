
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface RoleUsuariAplicacioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_roleusuariaplicacio";


  public static final String _TABLE_MODEL = "roleUsuariAplicacio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ID = new LongField(_TABLE_MODEL, "id", "id");  // PK
	 public static final StringField ROLEID = new StringField(_TABLE_MODEL, "roleID", "roleid");
	 public static final StringField USUARIAPLICACIOID = new StringField(_TABLE_MODEL, "usuariAplicacioID", "usuariaplicacioid");


  public static final Field<?>[] ALL_ROLEUSUARIAPLICACIO_FIELDS = {
    ID,
    ROLEID,
    USUARIAPLICACIOID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ID
  };
}
