
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface CustodiaInfoFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_custodiainfo";


  public static final String _TABLE_MODEL = "custodiaInfo";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField CUSTODIAINFOID = new LongField(_TABLE_MODEL, "custodiaInfoID", "custodiainfoid");  // PK
	 public static final StringField CUSTODIAPLUGINID = new StringField(_TABLE_MODEL, "custodiaPluginID", "custodiapluginid");
	 public static final StringField CUSTODIAPLUGINCLASSID = new StringField(_TABLE_MODEL, "custodiaPluginClassID", "custodiapluginclassid");
	 public static final StringField CUSTODIAPLUGINPARAMETERS = new StringField(_TABLE_MODEL, "custodiaPluginParameters", "custodiapluginparametres");
	 public static final StringField NOMPLANTILLA = new StringField(_TABLE_MODEL, "nomPlantilla", "nomplantilla");
	 public static final BooleanField CUSTODIAR = new BooleanField(_TABLE_MODEL, "custodiar", "custodiar");
	 public static final StringField URLFITXERCUSTODIAT = new StringField(_TABLE_MODEL, "urlFitxerCustodiat", "urlfitxercustodiat");
	 public static final StringField PAGINES = new StringField(_TABLE_MODEL, "pagines", "pagines");
	 public static final StringField MISSATGE = new StringField(_TABLE_MODEL, "missatge", "missatge");
	 public static final LongField MISSATGEPOSICIOPAGINAID = new LongField(_TABLE_MODEL, "missatgePosicioPaginaID", "missatgeposiciopaginaid");
	 public static final StringField CODIBARRESID = new StringField(_TABLE_MODEL, "codiBarresID", "codibarresid");
	 public static final LongField CODIBARRESPOSICIOPAGINAID = new LongField(_TABLE_MODEL, "codiBarresPosicioPaginaID", "codibarresposiciopaginaid");
	 public static final StringField CODIBARRESTEXT = new StringField(_TABLE_MODEL, "codiBarresText", "codibarrestext");
	 public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid");
	 public static final StringField USUARIAPLICACIOID = new StringField(_TABLE_MODEL, "usuariAplicacioID", "usuariaplicacioid");
	 public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final StringField TITOLPETICIO = new StringField(_TABLE_MODEL, "titolPeticio", "titolpeticio");
	 public static final TimestampField DATACUSTODIA = new TimestampField(_TABLE_MODEL, "dataCustodia", "datacustodia");
	 public static final BooleanField EDITABLE = new BooleanField(_TABLE_MODEL, "editable", "editable");


  public static final Field<?>[] ALL_CUSTODIAINFO_FIELDS = {
    CUSTODIAINFOID,
    CUSTODIAPLUGINID,
    CUSTODIAPLUGINCLASSID,
    CUSTODIAPLUGINPARAMETERS,
    NOMPLANTILLA,
    CUSTODIAR,
    URLFITXERCUSTODIAT,
    PAGINES,
    MISSATGE,
    MISSATGEPOSICIOPAGINAID,
    CODIBARRESID,
    CODIBARRESPOSICIOPAGINAID,
    CODIBARRESTEXT,
    USUARIENTITATID,
    USUARIAPLICACIOID,
    ENTITATID,
    TITOLPETICIO,
    DATACUSTODIA,
    EDITABLE
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
CUSTODIAINFOID
  };
}
