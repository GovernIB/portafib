
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PermisUsuariPlantillaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_permisusuariplantilla";


  public static final String _TABLE_MODEL = "permisUsuariPlantilla";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PERMISUSUARIPLANTILLAID = new LongField(_TABLE_MODEL, "permisUsuariPlantillaID", "permisusuariplantillaid");  // PK
	 public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid");
	 public static final LongField PLANTILLAFLUXDEFIRMESID = new LongField(_TABLE_MODEL, "plantillaFluxDeFirmesID", "fluxdefirmesid");


  public static final Field<?>[] ALL_PERMISUSUARIPLANTILLA_FIELDS = {
    PERMISUSUARIPLANTILLAID,
    USUARIENTITATID,
    PLANTILLAFLUXDEFIRMESID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PERMISUSUARIPLANTILLAID
  };
}
