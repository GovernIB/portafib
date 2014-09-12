
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PermisGrupPlantillaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_permisgrupplantilla";


  public static final String _TABLE_MODEL = "permisGrupPlantilla";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PERMISGRUPPLANTILLAID = new LongField(_TABLE_MODEL, "permisGrupPlantillaID", "permisgrupplantillaid");  // PK
	 public static final LongField GRUPENTITATID = new LongField(_TABLE_MODEL, "grupEntitatID", "grupentitatid");
	 public static final LongField PLANTILLAFLUXDEFIRMESID = new LongField(_TABLE_MODEL, "plantillaFluxDeFirmesID", "fluxdefirmesid");


  public static final Field<?>[] ALL_PERMISGRUPPLANTILLA_FIELDS = {
    PERMISGRUPPLANTILLAID,
    GRUPENTITATID,
    PLANTILLAFLUXDEFIRMESID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PERMISGRUPPLANTILLAID
  };
}
