
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface RoleFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_role";


  public static final String _TABLE_MODEL = "role";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final StringField ROLEID = new StringField(_TABLE_MODEL, "roleID", "roleid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");


  public static final Field<?>[] ALL_ROLE_FIELDS = {
    ROLEID,
    NOM,
    DESCRIPCIO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ROLEID
  };
}
