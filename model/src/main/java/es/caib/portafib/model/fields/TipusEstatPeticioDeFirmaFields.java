
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface TipusEstatPeticioDeFirmaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_tipusestatpeticiodefirma";


  public static final String _TABLE_MODEL = "tipusEstatPeticioDeFirma";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final IntegerField TIPUSESTATPETICIODEFIRMAID = new IntegerField(_TABLE_MODEL, "tipusEstatPeticioDeFirmaID", "tipusestatpeticiodefirmaid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");


  public static final Field<?>[] ALL_TIPUSESTATPETICIODEFIRMA_FIELDS = {
    TIPUSESTATPETICIODEFIRMAID,
    NOM,
    DESCRIPCIO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
TIPUSESTATPETICIODEFIRMAID
  };
}
