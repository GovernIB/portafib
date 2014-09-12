
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface ColaboracioDelegacioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_colaboraciodelegacio";


  public static final String _TABLE_MODEL = "colaboracioDelegacio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField COLABORACIODELEGACIOID = new LongField(_TABLE_MODEL, "colaboracioDelegacioID", "colaboraciodelegacioid");  // PK
	 public static final StringField DESTINATARIID = new StringField(_TABLE_MODEL, "destinatariID", "destinatariid");
	 public static final StringField COLABORADORDELEGATID = new StringField(_TABLE_MODEL, "colaboradorDelegatID", "colaboradordelegatid");
	 public static final BooleanField ESDELEGAT = new BooleanField(_TABLE_MODEL, "esDelegat", "esdelegat");
	 public static final StringField MOTIU = new StringField(_TABLE_MODEL, "motiu", "motiu");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final TimestampField DATAINICI = new TimestampField(_TABLE_MODEL, "dataInici", "datainici");
	 public static final TimestampField DATAFI = new TimestampField(_TABLE_MODEL, "dataFi", "datafi");
	 public static final BooleanField ACTIVA = new BooleanField(_TABLE_MODEL, "activa", "activa");
	 public static final BooleanField REVISOR = new BooleanField(_TABLE_MODEL, "revisor", "revisor");
	 public static final StringField MOTIUDESHABILITADA = new StringField(_TABLE_MODEL, "motiuDeshabilitada", "motiudeshabilitada");
	 public static final LongField FITXERAUTORITZACIOID = new LongField(_TABLE_MODEL, "fitxerAutoritzacioID", "fitxerautoritzacioid");


  public static final Field<?>[] ALL_COLABORACIODELEGACIO_FIELDS = {
    COLABORACIODELEGACIOID,
    DESTINATARIID,
    COLABORADORDELEGATID,
    ESDELEGAT,
    MOTIU,
    DESCRIPCIO,
    DATAINICI,
    DATAFI,
    ACTIVA,
    REVISOR,
    MOTIUDESHABILITADA,
    FITXERAUTORITZACIOID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
COLABORACIODELEGACIOID
  };
}
