
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface UsuariAplicacioConfiguracioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_usuariaplicacioconfig";


  public static final String _TABLE_MODEL = "usuariAplicacioConfiguracio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField USUARIAPLICACIOCONFIGID = new LongField(_TABLE_MODEL, "usuariAplicacioConfigID", "usuariaplicacioconfigid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final BooleanField USENFIRMAAPISIMPLESERVIDOR = new BooleanField(_TABLE_MODEL, "usEnFirmaApiSimpleServidor", "usenfirmaapisimpleservidor");
	 public static final BooleanField USENFIRMAAPISIMPLEWEB = new BooleanField(_TABLE_MODEL, "usEnFirmaApiSimpleWeb", "usenfirmaapisimpleweb");
	 public static final BooleanField USENFIRMAWEB = new BooleanField(_TABLE_MODEL, "usEnFirmaWeb", "usenfirmaweb");
	 public static final BooleanField USENFIRMAWS1 = new BooleanField(_TABLE_MODEL, "usEnFirmaWS1", "usenfirmaws1");
	 public static final BooleanField USENFIRMAASYNCREST2 = new BooleanField(_TABLE_MODEL, "usEnFirmaAsyncRest2", "usenfirmaws2");
	 public static final BooleanField USENFIRMAPASSARELASERVIDOR = new BooleanField(_TABLE_MODEL, "usEnFirmaPassarelaServidor", "usenfirmapassarelaservidor");
	 public static final BooleanField USENFIRMAPASSARELAWEB = new BooleanField(_TABLE_MODEL, "usEnFirmaPassarelaWeb", "usenfirmapassarelaweb");
	 public static final StringField FILTRECERTIFICATS = new StringField(_TABLE_MODEL, "filtreCertificats", "filtrecertificats");
	 public static final IntegerField TIPUSOPERACIOFIRMA = new IntegerField(_TABLE_MODEL, "tipusOperacioFirma", "tipusoperaciofirma");
	 public static final IntegerField TIPUSFIRMAID = new IntegerField(_TABLE_MODEL, "tipusFirmaID", "tipusfirmaid");
	 public static final IntegerField ALGORISMEDEFIRMAID = new IntegerField(_TABLE_MODEL, "algorismeDeFirmaID", "algorismedefirmaid");
	 public static final BooleanField MODEDEFIRMA = new BooleanField(_TABLE_MODEL, "modeDeFirma", "modedefirma");
	 public static final IntegerField USPOLITICADEFIRMA = new IntegerField(_TABLE_MODEL, "usPoliticaDeFirma", "uspoliticadefirma");
	 public static final StringField POLICYIDENTIFIER = new StringField(_TABLE_MODEL, "policyIdentifier", "policyidentifier");
	 public static final StringField POLICYIDENTIFIERHASH = new StringField(_TABLE_MODEL, "policyIdentifierHash", "policyidentifierhash");
	 public static final StringField POLICYIDENTIFIERHASHALGORITHM = new StringField(_TABLE_MODEL, "policyIdentifierHashAlgorithm", "policyidentifierhashalgorithm");
	 public static final StringField POLICYURLDOCUMENT = new StringField(_TABLE_MODEL, "policyUrlDocument", "policyurldocument");
	 public static final IntegerField POLITICATAULAFIRMES = new IntegerField(_TABLE_MODEL, "politicaTaulaFirmes", "politicataulafirmes");
	 public static final IntegerField POSICIOTAULAFIRMESID = new IntegerField(_TABLE_MODEL, "posicioTaulaFirmesID", "posiciotaulafirmesid");
	 public static final LongField FIRMATPERFORMATID = new LongField(_TABLE_MODEL, "firmatPerFormatID", "firmatperformatid");
	 public static final LongField MOTIUDELEGACIOID = new LongField(_TABLE_MODEL, "motiuDelegacioID", "motiudelegacioid");
	 public static final StringField PROPIETATSTAULAFIRMES = new StringField(_TABLE_MODEL, "propietatsTaulaFirmes", "propietatstaulafirmes");
	 public static final IntegerField POLITICASEGELLATDETEMPS = new IntegerField(_TABLE_MODEL, "politicaSegellatDeTemps", "politicasegellatdetemps");
	 public static final LongField PLUGINSEGELLATID = new LongField(_TABLE_MODEL, "pluginSegellatID", "pluginsegellatid");
	 public static final StringField HTMLPERLLISTARPLUGINSFIRMAWEB = new StringField(_TABLE_MODEL, "htmlPerLlistarPluginsFirmaWeb", "htmlperllistarpluginsfirmaweb");
	 public static final LongField PLUGINFIRMASERVIDORID = new LongField(_TABLE_MODEL, "pluginFirmaServidorID", "pluginfirmaservidorid");
	 public static final IntegerField UPGRADESIGNFORMAT = new IntegerField(_TABLE_MODEL, "upgradeSignFormat", "upgradesignformat");
	 public static final BooleanField VALIDARFIRMA = new BooleanField(_TABLE_MODEL, "validarFirma", "validarfirma");
	 public static final BooleanField CHECKCANVIATDOCFIRMAT = new BooleanField(_TABLE_MODEL, "checkCanviatDocFirmat", "checkcanviatdocfirmat");
	 public static final BooleanField COMPROVARNIFFIRMA = new BooleanField(_TABLE_MODEL, "comprovarNifFirma", "comprovarniffirma");
	 public static final BooleanField VALIDARCERTIFICAT = new BooleanField(_TABLE_MODEL, "validarCertificat", "validarcertificat");


  public static final Field<?>[] ALL_USUARIAPLICACIOCONFIGURACIO_FIELDS = {
    USUARIAPLICACIOCONFIGID,
    NOM,
    ENTITATID,
    USENFIRMAAPISIMPLESERVIDOR,
    USENFIRMAAPISIMPLEWEB,
    USENFIRMAWEB,
    USENFIRMAWS1,
    USENFIRMAASYNCREST2,
    USENFIRMAPASSARELASERVIDOR,
    USENFIRMAPASSARELAWEB,
    FILTRECERTIFICATS,
    TIPUSOPERACIOFIRMA,
    TIPUSFIRMAID,
    ALGORISMEDEFIRMAID,
    MODEDEFIRMA,
    USPOLITICADEFIRMA,
    POLICYIDENTIFIER,
    POLICYIDENTIFIERHASH,
    POLICYIDENTIFIERHASHALGORITHM,
    POLICYURLDOCUMENT,
    POLITICATAULAFIRMES,
    POSICIOTAULAFIRMESID,
    FIRMATPERFORMATID,
    MOTIUDELEGACIOID,
    PROPIETATSTAULAFIRMES,
    POLITICASEGELLATDETEMPS,
    PLUGINSEGELLATID,
    HTMLPERLLISTARPLUGINSFIRMAWEB,
    PLUGINFIRMASERVIDORID,
    UPGRADESIGNFORMAT,
    VALIDARFIRMA,
    CHECKCANVIATDOCFIRMAT,
    COMPROVARNIFFIRMA,
    VALIDARCERTIFICAT
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
USUARIAPLICACIOCONFIGID
  };
}
