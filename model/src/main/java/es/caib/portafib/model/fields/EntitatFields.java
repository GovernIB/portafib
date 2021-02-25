
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EntitatFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_entitat";


  public static final String _TABLE_MODEL = "entitat";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatID", "entitatid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final BooleanField ACTIVA = new BooleanField(_TABLE_MODEL, "activa", "activa");
	 public static final StringField WEB = new StringField(_TABLE_MODEL, "web", "web");
	 public static final LongField FAVICONID = new LongField(_TABLE_MODEL, "faviconID", "faviconid");
	 public static final LongField LOGOWEBID = new LongField(_TABLE_MODEL, "logoWebID", "logowebid");
	 public static final LongField LOGOWEBPEUID = new LongField(_TABLE_MODEL, "logoWebPeuID", "logowebpeuid");
	 public static final LongField LOGOSEGELLID = new LongField(_TABLE_MODEL, "logoSegellID", "logosegellid");
	 public static final StringField ADREZAHTML = new StringField(_TABLE_MODEL, "adrezaHtml", "adrezahtml");
	 public static final StringField FILTRECERTIFICATS = new StringField(_TABLE_MODEL, "filtreCertificats", "filtrecertificats");
	 public static final LongField PDFAUTORITZACIODELEGACIOID = new LongField(_TABLE_MODEL, "pdfAutoritzacioDelegacioID", "pdfautoritzaciodelegacioid");
	 public static final StringField SUPORTTELEFON = new StringField(_TABLE_MODEL, "suportTelefon", "suporttelefon");
	 public static final StringField SUPORTWEB = new StringField(_TABLE_MODEL, "suportWeb", "suportweb");
	 public static final StringField SUPORTEMAIL = new StringField(_TABLE_MODEL, "suportEmail", "suportemail");
	 public static final StringField USUARIAPLICACIOID = new StringField(_TABLE_MODEL, "usuariAplicacioID", "usuariaplicacioid");
	 public static final LongField MAXUPLOADSIZE = new LongField(_TABLE_MODEL, "maxUploadSize", "maxuploadsize");
	 public static final LongField MAXSIZEFITXERADAPTAT = new LongField(_TABLE_MODEL, "maxSizeFitxerAdaptat", "maxsizefitxeradaptat");
	 public static final IntegerField MAXFILESTOSIGNATSAMETIME = new IntegerField(_TABLE_MODEL, "maxFilesToSignAtSameTime", "maxfilestosignatsametime");
	 public static final IntegerField USPOLITICADEFIRMA = new IntegerField(_TABLE_MODEL, "usPoliticaDeFirma", "uspoliticadefirma");
	 public static final StringField POLICYIDENTIFIER = new StringField(_TABLE_MODEL, "policyIdentifier", "policyidentifier");
	 public static final StringField POLICYIDENTIFIERHASH = new StringField(_TABLE_MODEL, "policyIdentifierHash", "policyidentifierhash");
	 public static final StringField POLICYIDENTIFIERHASHALGORITHM = new StringField(_TABLE_MODEL, "policyIdentifierHashAlgorithm", "policyidentifierhashalgorithm");
	 public static final StringField POLICYURLDOCUMENT = new StringField(_TABLE_MODEL, "policyUrlDocument", "policyurldocument");
	 public static final LongField MOTIUDELEGACIOID = new LongField(_TABLE_MODEL, "motiuDelegacioID", "motiudelegacioid");
	 public static final LongField FIRMATPERFORMATID = new LongField(_TABLE_MODEL, "firmatPerFormatID", "firmatperformatid");
	 public static final IntegerField ALGORISMEDEFIRMAID = new IntegerField(_TABLE_MODEL, "algorismeDeFirmaID", "algorismedefirmaid");
	 public static final IntegerField POLITICACUSTODIA = new IntegerField(_TABLE_MODEL, "politicaCustodia", "politicacustodia");
	 public static final LongField CUSTODIAINFOID = new LongField(_TABLE_MODEL, "custodiaInfoID", "custodiainfoid");
	 public static final IntegerField POLITICATAULAFIRMES = new IntegerField(_TABLE_MODEL, "politicaTaulaFirmes", "politicataulafirmes");
	 public static final IntegerField POSICIOTAULAFIRMES = new IntegerField(_TABLE_MODEL, "posicioTaulaFirmes", "posiciotaulafirmes");
	 public static final StringField PROPIETATSTAULAFIRMES = new StringField(_TABLE_MODEL, "propietatsTaulaFirmes", "propietatstaulafirmes");
	 public static final IntegerField POLITICASEGELLATDETEMPS = new IntegerField(_TABLE_MODEL, "politicaSegellatDeTemps", "segelldetempsviaweb");
	 public static final LongField PLUGINSEGELLTEMPSID = new LongField(_TABLE_MODEL, "pluginSegellTempsID", "pluginid");
	 public static final LongField PLUGINRUBRICAID = new LongField(_TABLE_MODEL, "pluginRubricaID", "pluginrubricaid");
	 public static final BooleanField VALIDARFIRMA = new BooleanField(_TABLE_MODEL, "validarfirma", "validarfirma");
	 public static final BooleanField COMPROVARNIFFIRMA = new BooleanField(_TABLE_MODEL, "comprovarNifFirma", "comprovarniffirma");
	 public static final BooleanField CHECKCANVIATDOCFIRMAT = new BooleanField(_TABLE_MODEL, "checkCanviatDocFirmat", "checkcanviatdocfirmat");
	 public static final LongField PLUGINVALIDAFIRMESID = new LongField(_TABLE_MODEL, "pluginValidaFirmesID", "pluginvalidafirmesid");
	 public static final LongField PLUGINVALIDACERTIFICATID = new LongField(_TABLE_MODEL, "pluginValidaCertificatID", "pluginvalidacertificatid");


  public static final Field<?>[] ALL_ENTITAT_FIELDS = {
    ENTITATID,
    NOM,
    DESCRIPCIO,
    ACTIVA,
    WEB,
    FAVICONID,
    LOGOWEBID,
    LOGOWEBPEUID,
    LOGOSEGELLID,
    ADREZAHTML,
    FILTRECERTIFICATS,
    PDFAUTORITZACIODELEGACIOID,
    SUPORTTELEFON,
    SUPORTWEB,
    SUPORTEMAIL,
    USUARIAPLICACIOID,
    MAXUPLOADSIZE,
    MAXSIZEFITXERADAPTAT,
    MAXFILESTOSIGNATSAMETIME,
    USPOLITICADEFIRMA,
    POLICYIDENTIFIER,
    POLICYIDENTIFIERHASH,
    POLICYIDENTIFIERHASHALGORITHM,
    POLICYURLDOCUMENT,
    MOTIUDELEGACIOID,
    FIRMATPERFORMATID,
    ALGORISMEDEFIRMAID,
    POLITICACUSTODIA,
    CUSTODIAINFOID,
    POLITICATAULAFIRMES,
    POSICIOTAULAFIRMES,
    PROPIETATSTAULAFIRMES,
    POLITICASEGELLATDETEMPS,
    PLUGINSEGELLTEMPSID,
    PLUGINRUBRICAID,
    VALIDARFIRMA,
    COMPROVARNIFFIRMA,
    CHECKCANVIATDOCFIRMAT,
    PLUGINVALIDAFIRMESID,
    PLUGINVALIDACERTIFICATID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ENTITATID
  };
}
