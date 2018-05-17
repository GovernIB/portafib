
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface UsuariAplicacioConfiguracioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_usuariaplicacioconfig";


  public static final String _TABLE_MODEL = "usuariAplicacioConfiguracio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField USUARIAPLICACIOCONFIGID = new LongField(_TABLE_MODEL, "usuariAplicacioConfigID", "usuariaplicacioconfigid");  // PK
	 public static final StringField USUARIAPLICACIOID = new StringField(_TABLE_MODEL, "usuariAplicacioID", "usuariaplicacioid");
	 public static final IntegerField USPOLITICADEFIRMA = new IntegerField(_TABLE_MODEL, "usPoliticaDeFirma", "uspoliticadefirma");
	 public static final StringField POLICYIDENTIFIER = new StringField(_TABLE_MODEL, "policyIdentifier", "policyidentifier");
	 public static final StringField POLICYIDENTIFIERHASH = new StringField(_TABLE_MODEL, "policyIdentifierHash", "policyidentifierhash");
	 public static final StringField POLICYIDENTIFIERHASHALGORITHM = new StringField(_TABLE_MODEL, "policyIdentifierHashAlgorithm", "policyidentifierhashalgorithm");
	 public static final StringField POLICYURLDOCUMENT = new StringField(_TABLE_MODEL, "policyUrlDocument", "policyurldocument");
	 public static final StringField FILTRECERTIFICATS = new StringField(_TABLE_MODEL, "filtreCertificats", "filtrecertificats");
	 public static final IntegerField TIPUSOPERACIOFIRMA = new IntegerField(_TABLE_MODEL, "tipusOperacioFirma", "tipusoperaciofirma");
	 public static final IntegerField TIPUSFIRMAID = new IntegerField(_TABLE_MODEL, "tipusFirmaID", "tipusfirmaid");
	 public static final IntegerField ALGORISMEDEFIRMAID = new IntegerField(_TABLE_MODEL, "algorismeDeFirmaID", "algorismedefirmaid");
	 public static final BooleanField MODEDEFIRMA = new BooleanField(_TABLE_MODEL, "modeDeFirma", "modedefirma");
	 public static final IntegerField POLITICACUSTODIA = new IntegerField(_TABLE_MODEL, "politicaCustodia", "politicacustodia");
	 public static final LongField CUSTODIAINFOID = new LongField(_TABLE_MODEL, "custodiaInfoID", "custodiainfoid");
	 public static final IntegerField POLITICATAULAFIRMES = new IntegerField(_TABLE_MODEL, "politicaTaulaFirmes", "politicataulafirmes");
	 public static final IntegerField POSICIOTAULAFIRMESID = new IntegerField(_TABLE_MODEL, "posicioTaulaFirmesID", "posiciotaulafirmesid");
	 public static final LongField FIRMATPERFORMATID = new LongField(_TABLE_MODEL, "firmatPerFormatID", "firmatperformatid");
	 public static final LongField MOTIUDELEGACIOID = new LongField(_TABLE_MODEL, "motiuDelegacioID", "motiudelegacioid");
	 public static final StringField PROPIETATSTAULAFIRMES = new StringField(_TABLE_MODEL, "propietatsTaulaFirmes", "propietatstaulafirmes");
	 public static final IntegerField POLITICASEGELLATDETEMPS = new IntegerField(_TABLE_MODEL, "politicaSegellatDeTemps", "politicasegellatdetemps");
	 public static final LongField PLUGINSEGELLATID = new LongField(_TABLE_MODEL, "pluginSegellatID", "pluginsegellatid");
	 public static final StringField HTMLPERLLISTARPLUGINSFIRMAWEB = new StringField(_TABLE_MODEL, "htmlPerLlistarPluginsFirmaWeb", "htmlperllistarpluginsfirmaweb");
	 public static final LongField PLUGINFIRMASERVIDORID = new LongField(_TABLE_MODEL, "pluginFirmaServidorID", "pluginfirmaservidorid");
	 public static final IntegerField MAXFIRMESENSERVIDOR = new IntegerField(_TABLE_MODEL, "maxFirmesEnServidor", "maxfirmesenservidor");
	 public static final LongField LOGINCERTIFICATEID = new LongField(_TABLE_MODEL, "loginCertificateID", "logincertificateid");
	 public static final BooleanField COMPROVARNIFFIRMA = new BooleanField(_TABLE_MODEL, "comprovarNifFirma", "comprovarniffirma");
	 public static final BooleanField CHECKCANVIATDOCFIRMAT = new BooleanField(_TABLE_MODEL, "checkCanviatDocFirmat", "checkcanviatdocfirmat");
	 public static final BooleanField VALIDARFIRMA = new BooleanField(_TABLE_MODEL, "validarFirma", "validarfirma");
	 public static final BooleanField VALIDARCERTIFICAT = new BooleanField(_TABLE_MODEL, "validarCertificat", "validarcertificat");


  public static final Field<?>[] ALL_USUARIAPLICACIOCONFIGURACIO_FIELDS = {
    USUARIAPLICACIOCONFIGID,
    USUARIAPLICACIOID,
    USPOLITICADEFIRMA,
    POLICYIDENTIFIER,
    POLICYIDENTIFIERHASH,
    POLICYIDENTIFIERHASHALGORITHM,
    POLICYURLDOCUMENT,
    FILTRECERTIFICATS,
    TIPUSOPERACIOFIRMA,
    TIPUSFIRMAID,
    ALGORISMEDEFIRMAID,
    MODEDEFIRMA,
    POLITICACUSTODIA,
    CUSTODIAINFOID,
    POLITICATAULAFIRMES,
    POSICIOTAULAFIRMESID,
    FIRMATPERFORMATID,
    MOTIUDELEGACIOID,
    PROPIETATSTAULAFIRMES,
    POLITICASEGELLATDETEMPS,
    PLUGINSEGELLATID,
    HTMLPERLLISTARPLUGINSFIRMAWEB,
    PLUGINFIRMASERVIDORID,
    MAXFIRMESENSERVIDOR,
    LOGINCERTIFICATEID,
    COMPROVARNIFFIRMA,
    CHECKCANVIATDOCFIRMAT,
    VALIDARFIRMA,
    VALIDARCERTIFICAT
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
USUARIAPLICACIOCONFIGID
  };
}
