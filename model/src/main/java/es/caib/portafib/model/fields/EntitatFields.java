package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_entitat */
public interface EntitatFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_entitat";

    public static final String _TABLE_MODEL = "entitat";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatID", "entitatid", 50);  // PK
    public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom", 50);
    public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio", 255);
    public static final BooleanField ACTIVA = new BooleanField(_TABLE_MODEL, "activa", "activa", 1);
    public static final StringField WEB = new StringField(_TABLE_MODEL, "web", "web", 250);
    public static final LongField FAVICONID = new LongField(_TABLE_MODEL, "faviconID", "faviconid", 19);
    public static final LongField LOGOWEBID = new LongField(_TABLE_MODEL, "logoWebID", "logowebid", 19);
    public static final LongField LOGOWEBPEUID = new LongField(_TABLE_MODEL, "logoWebPeuID", "logowebpeuid", 19);
    public static final LongField LOGOSEGELLID = new LongField(_TABLE_MODEL, "logoSegellID", "logosegellid", 19);
    public static final StringField ADREZAHTML = new StringField(_TABLE_MODEL, "adrezaHtml", "adrezahtml", 2000);
    public static final StringField FILTRECERTIFICATS = new StringField(_TABLE_MODEL, "filtreCertificats", "filtrecertificats", 2147483647);
    public static final LongField PDFAUTORITZACIODELEGACIOID = new LongField(_TABLE_MODEL, "pdfAutoritzacioDelegacioID", "pdfautoritzaciodelegacioid", 19);
    public static final StringField SUPORTTELEFON = new StringField(_TABLE_MODEL, "suportTelefon", "suporttelefon", 50);
    public static final StringField SUPORTWEB = new StringField(_TABLE_MODEL, "suportWeb", "suportweb", 250);
    public static final StringField SUPORTEMAIL = new StringField(_TABLE_MODEL, "suportEmail", "suportemail", 100);
    public static final StringField USUARIAPLICACIOID = new StringField(_TABLE_MODEL, "usuariAplicacioID", "usuariaplicacioid", 101);
    public static final LongField MAXUPLOADSIZE = new LongField(_TABLE_MODEL, "maxUploadSize", "maxuploadsize", 19);
    public static final LongField MAXSIZEFITXERADAPTAT = new LongField(_TABLE_MODEL, "maxSizeFitxerAdaptat", "maxsizefitxeradaptat", 19);
    public static final IntegerField MAXFILESTOSIGNATSAMETIME = new IntegerField(_TABLE_MODEL, "maxFilesToSignAtSameTime", "maxfilestosignatsametime", 10);
    public static final IntegerField USPOLITICADEFIRMA = new IntegerField(_TABLE_MODEL, "usPoliticaDeFirma", "uspoliticadefirma", 10);
    public static final StringField POLICYIDENTIFIER = new StringField(_TABLE_MODEL, "policyIdentifier", "policyidentifier", 100);
    public static final StringField POLICYIDENTIFIERHASH = new StringField(_TABLE_MODEL, "policyIdentifierHash", "policyidentifierhash", 2147483647);
    public static final StringField POLICYIDENTIFIERHASHALGORITHM = new StringField(_TABLE_MODEL, "policyIdentifierHashAlgorithm", "policyidentifierhashalgorithm", 50);
    public static final StringField POLICYURLDOCUMENT = new StringField(_TABLE_MODEL, "policyUrlDocument", "policyurldocument", 255);
    public static final LongField MOTIUDELEGACIOID = new LongField(_TABLE_MODEL, "motiuDelegacioID", "motiudelegacioid", 19);
    public static final LongField FIRMATPERFORMATID = new LongField(_TABLE_MODEL, "firmatPerFormatID", "firmatperformatid", 19);
    public static final IntegerField ALGORISMEDEFIRMAID = new IntegerField(_TABLE_MODEL, "algorismeDeFirmaID", "algorismedefirmaid", 19);
    public static final IntegerField POLITICACUSTODIA = new IntegerField(_TABLE_MODEL, "politicaCustodia", "politicacustodia", 10);
    public static final LongField CUSTODIAINFOID = new LongField(_TABLE_MODEL, "custodiaInfoID", "custodiainfoid", 19);
    public static final IntegerField POLITICATAULAFIRMES = new IntegerField(_TABLE_MODEL, "politicaTaulaFirmes", "politicataulafirmes", 10);
    public static final IntegerField POSICIOTAULAFIRMES = new IntegerField(_TABLE_MODEL, "posicioTaulaFirmes", "posiciotaulafirmes", 10);
    public static final StringField PROPIETATSTAULAFIRMES = new StringField(_TABLE_MODEL, "propietatsTaulaFirmes", "propietatstaulafirmes", 2147483647);
    public static final IntegerField POLITICASEGELLATDETEMPS = new IntegerField(_TABLE_MODEL, "politicaSegellatDeTemps", "segelldetempsviaweb", 10);
    public static final LongField PLUGINSEGELLTEMPSID = new LongField(_TABLE_MODEL, "pluginSegellTempsID", "pluginid", 19);
    public static final LongField PLUGINRUBRICAID = new LongField(_TABLE_MODEL, "pluginRubricaID", "pluginrubricaid", 19);
    public static final BooleanField VALIDARFIRMA = new BooleanField(_TABLE_MODEL, "validarfirma", "validarfirma", 1);
    public static final BooleanField COMPROVARNIFFIRMA = new BooleanField(_TABLE_MODEL, "comprovarNifFirma", "comprovarniffirma", 1);
    public static final BooleanField CHECKCANVIATDOCFIRMAT = new BooleanField(_TABLE_MODEL, "checkCanviatDocFirmat", "checkcanviatdocfirmat", 1);
    public static final LongField PLUGINVALIDAFIRMESID = new LongField(_TABLE_MODEL, "pluginValidaFirmesID", "pluginvalidafirmesid", 19);
    public static final LongField PLUGINVALIDACERTIFICATID = new LongField(_TABLE_MODEL, "pluginValidaCertificatID", "pluginvalidacertificatid", 19);

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
