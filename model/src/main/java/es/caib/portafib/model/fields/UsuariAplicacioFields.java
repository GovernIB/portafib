
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface UsuariAplicacioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_usuariaplicacio";


  public static final String _TABLE_MODEL = "usuariAplicacio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final StringField USUARIAPLICACIOID = new StringField(_TABLE_MODEL, "usuariAplicacioID", "usuariaplicacioid");  // PK
	 public static final StringField CONTRASENYA = new StringField(_TABLE_MODEL, "contrasenya", "contrasenya");
	 public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final StringField EMAILADMIN = new StringField(_TABLE_MODEL, "emailAdmin", "emailadmin");
	 public static final IntegerField CALLBACKVERSIO = new IntegerField(_TABLE_MODEL, "callbackVersio", "callbackversio");
	 public static final StringField CALLBACKURL = new StringField(_TABLE_MODEL, "callbackURL", "callbackurl");
	 public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu");
	 public static final StringField IDIOMAID = new StringField(_TABLE_MODEL, "idiomaID", "idiomaid");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final LongField LOGOSEGELLID = new LongField(_TABLE_MODEL, "logoSegellID", "logosegellid");
	 public static final BooleanField POTCUSTODIAR = new BooleanField(_TABLE_MODEL, "potCustodiar", "potcustodiar");
	 public static final IntegerField POLITICACUSTODIA = new IntegerField(_TABLE_MODEL, "politicaCustodia", "politicacustodia");
	 public static final IntegerField POLITICADEPLUGINFIRMAWEB = new IntegerField(_TABLE_MODEL, "politicaDePluginFirmaWeb", "politicadepluginfirmaweb");


  public static final Field<?>[] ALL_USUARIAPLICACIO_FIELDS = {
    USUARIAPLICACIOID,
    CONTRASENYA,
    ENTITATID,
    EMAILADMIN,
    CALLBACKVERSIO,
    CALLBACKURL,
    ACTIU,
    IDIOMAID,
    DESCRIPCIO,
    LOGOSEGELLID,
    POTCUSTODIAR,
    POLITICACUSTODIA,
    POLITICADEPLUGINFIRMAWEB
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
USUARIAPLICACIOID
  };
}
