
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EstatDeFirmaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_estatdefirma";


  public static final String _TABLE_MODEL = "estatDeFirma";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ESTATDEFIRMAID = new LongField(_TABLE_MODEL, "estatDeFirmaID", "estatdefirmaid");  // PK
	 public static final LongField FIRMAID = new LongField(_TABLE_MODEL, "firmaID", "firmaid");
	 public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid");
	 public static final TimestampField DATAINICI = new TimestampField(_TABLE_MODEL, "dataInici", "datainici");
	 public static final TimestampField DATAFI = new TimestampField(_TABLE_MODEL, "dataFi", "datafi");
	 public static final LongField TIPUSESTATDEFIRMAINICIALID = new LongField(_TABLE_MODEL, "tipusEstatDeFirmaInicialID", "tipusestatdefirmainicialid");
	 public static final LongField TIPUSESTATDEFIRMAFINALID = new LongField(_TABLE_MODEL, "tipusEstatDeFirmaFinalID", "tipusestatdefirmafinalid");
	 public static final LongField COLABORACIODELEGACIOID = new LongField(_TABLE_MODEL, "colaboracioDelegacioID", "colaboraciodelegacioid");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");


  public static final Field<?>[] ALL_ESTATDEFIRMA_FIELDS = {
    ESTATDEFIRMAID,
    FIRMAID,
    USUARIENTITATID,
    DATAINICI,
    DATAFI,
    TIPUSESTATDEFIRMAINICIALID,
    TIPUSESTATDEFIRMAFINALID,
    COLABORACIODELEGACIOID,
    DESCRIPCIO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ESTATDEFIRMAID
  };
}
