
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface GrupEntitatFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_grupentitat";


  public static final String _TABLE_MODEL = "grupEntitat";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField GRUPENTITATID = new LongField(_TABLE_MODEL, "grupEntitatID", "grupentitatid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatID", "entitatid");


  public static final Field<?>[] ALL_GRUPENTITAT_FIELDS = {
    GRUPENTITATID,
    NOM,
    DESCRIPCIO,
    ENTITATID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
GRUPENTITATID
  };
}
