
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface ModulDeFirmaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_moduldefirma";


  public static final String _TABLE_MODEL = "modulDeFirma";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField MODULDEFIRMAID = new LongField(_TABLE_MODEL, "modulDeFirmaID", "moduldefirmaid");  // PK
	 public static final LongField NOMID = new LongField(_TABLE_MODEL, "nomID", "nomid");
	 public static final LongField DESCRIPCIOCURTAID = new LongField(_TABLE_MODEL, "descripcioCurtaID", "descripciocurtaid");
	 public static final StringField CLASSE = new StringField(_TABLE_MODEL, "classe", "classe");
	 public static final StringField PROPERTIESADMIN = new StringField(_TABLE_MODEL, "propertiesAdmin", "propertiesadmin");
	 public static final StringField PROPERTIESENTITAT = new StringField(_TABLE_MODEL, "propertiesEntitat", "propertiesentitat");
	 public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu");


  public static final Field<?>[] ALL_MODULDEFIRMA_FIELDS = {
    MODULDEFIRMAID,
    NOMID,
    DESCRIPCIOCURTAID,
    CLASSE,
    PROPERTIESADMIN,
    PROPERTIESENTITAT,
    ENTITATID,
    ACTIU
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
MODULDEFIRMAID
  };
}
