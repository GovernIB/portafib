
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface UsuariEntitatFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_usuarientitat";


  public static final String _TABLE_MODEL = "usuariEntitat";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid");  // PK
	 public static final StringField CARREC = new StringField(_TABLE_MODEL, "carrec", "carrec");
	 public static final StringField USUARIPERSONAID = new StringField(_TABLE_MODEL, "usuariPersonaID", "usuaripersonaid");
	 public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu");
	 public static final StringField EMAIL = new StringField(_TABLE_MODEL, "email", "email");
	 public static final LongField LOGOSEGELLID = new LongField(_TABLE_MODEL, "logoSegellID", "logosegellid");
	 public static final BooleanField PREDETERMINAT = new BooleanField(_TABLE_MODEL, "predeterminat", "predeterminat");
	 public static final BooleanField REBRETOTSELSAVISOS = new BooleanField(_TABLE_MODEL, "rebreTotsElsAvisos", "rebretotselsavisos");
	 public static final BooleanField POTCUSTODIAR = new BooleanField(_TABLE_MODEL, "potCustodiar", "potcustodiar");
	 public static final IntegerField POLITICACUSTODIA = new IntegerField(_TABLE_MODEL, "politicaCustodia", "politicacustodia");
	 public static final IntegerField POLITICADEPLUGINFIRMAWEB = new IntegerField(_TABLE_MODEL, "politicaDePluginFirmaWeb", "politicadepluginfirmaweb");


  public static final Field<?>[] ALL_USUARIENTITAT_FIELDS = {
    USUARIENTITATID,
    CARREC,
    USUARIPERSONAID,
    ENTITATID,
    ACTIU,
    EMAIL,
    LOGOSEGELLID,
    PREDETERMINAT,
    REBRETOTSELSAVISOS,
    POTCUSTODIAR,
    POLITICACUSTODIA,
    POLITICADEPLUGINFIRMAWEB
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
USUARIENTITATID
  };
}
