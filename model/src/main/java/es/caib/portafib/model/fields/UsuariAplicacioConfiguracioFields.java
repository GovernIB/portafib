package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_usuariaplicacioconfig */
public interface UsuariAplicacioConfiguracioFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_usuariaplicacioconfig";

    public static final String _TABLE_MODEL = "usuariAplicacioConfiguracio";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField USUARIAPLICACIOCONFIGID = new LongField(_TABLE_MODEL, "usuariAplicacioConfigID", "usuariaplicacioconfigid", 19);  // PK
    public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom", 255);
    public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatID", "entitatid", 50);
    public static final BooleanField USENFIRMAAPISIMPLESERVIDOR = new BooleanField(_TABLE_MODEL, "usEnFirmaApiSimpleServidor", "usenfirmaapisimpleservidor", 1);
    public static final BooleanField USENFIRMAAPISIMPLEWEB = new BooleanField(_TABLE_MODEL, "usEnFirmaApiSimpleWeb", "usenfirmaapisimpleweb", 1);
    public static final BooleanField USENFIRMAWEB = new BooleanField(_TABLE_MODEL, "usEnFirmaWeb", "usenfirmaweb", 1);
    public static final BooleanField USENFIRMAWS1 = new BooleanField(_TABLE_MODEL, "usEnFirmaWS1", "usenfirmaws1", 1);
    public static final BooleanField USENFIRMAASYNCREST2 = new BooleanField(_TABLE_MODEL, "usEnFirmaAsyncRest2", "usenfirmaws2", 1);
    public static final BooleanField USENFIRMAPASSARELASERVIDOR = new BooleanField(_TABLE_MODEL, "usEnFirmaPassarelaServidor", "usenfirmapassarelaservidor", 1);
    public static final BooleanField USENFIRMAPASSARELAWEB = new BooleanField(_TABLE_MODEL, "usEnFirmaPassarelaWeb", "usenfirmapassarelaweb", 1);
    public static final StringField FILTRECERTIFICATS = new StringField(_TABLE_MODEL, "filtreCertificats", "filtrecertificats", 2147483647);
    public static final IntegerField TIPUSOPERACIOFIRMA = new IntegerField(_TABLE_MODEL, "tipusOperacioFirma", "tipusoperaciofirma", 10);
    public static final IntegerField TIPUSFIRMAID = new IntegerField(_TABLE_MODEL, "tipusFirmaID", "tipusfirmaid", 10);
    public static final IntegerField ALGORISMEDEFIRMAID = new IntegerField(_TABLE_MODEL, "algorismeDeFirmaID", "algorismedefirmaid", 10);
    public static final IntegerField MODEDEFIRMA = new IntegerField(_TABLE_MODEL, "modeDeFirma", "modedefirma", 10);
    public static final IntegerField USPOLITICADEFIRMA = new IntegerField(_TABLE_MODEL, "usPoliticaDeFirma", "uspoliticadefirma", 10);
    public static final StringField POLICYIDENTIFIER = new StringField(_TABLE_MODEL, "policyIdentifier", "policyidentifier", 100);
    public static final StringField POLICYIDENTIFIERHASH = new StringField(_TABLE_MODEL, "policyIdentifierHash", "policyidentifierhash", 256);
    public static final StringField POLICYIDENTIFIERHASHALGORITHM = new StringField(_TABLE_MODEL, "policyIdentifierHashAlgorithm", "policyidentifierhashalgorithm", 50);
    public static final StringField POLICYURLDOCUMENT = new StringField(_TABLE_MODEL, "policyUrlDocument", "policyurldocument", 255);
    public static final LongField MOTIUDELEGACIOID = new LongField(_TABLE_MODEL, "motiuDelegacioID", "motiudelegacioid", 19);
    public static final IntegerField POLITICATAULAFIRMES = new IntegerField(_TABLE_MODEL, "politicaTaulaFirmes", "politicataulafirmes", 10);
    public static final IntegerField POSICIOTAULAFIRMESID = new IntegerField(_TABLE_MODEL, "posicioTaulaFirmesID", "posiciotaulafirmesid", 10);
    public static final LongField FIRMATPERFORMATID = new LongField(_TABLE_MODEL, "firmatPerFormatID", "firmatperformatid", 19);
    public static final StringField PROPIETATSTAULAFIRMES = new StringField(_TABLE_MODEL, "propietatsTaulaFirmes", "propietatstaulafirmes", 2147483647);
    public static final IntegerField POLITICASEGELLATDETEMPS = new IntegerField(_TABLE_MODEL, "politicaSegellatDeTemps", "politicasegellatdetemps", 10);
    public static final LongField PLUGINSEGELLATID = new LongField(_TABLE_MODEL, "pluginSegellatID", "pluginsegellatid", 19);
    public static final StringField HTMLPERLLISTARPLUGINSFIRMAWEB = new StringField(_TABLE_MODEL, "htmlPerLlistarPluginsFirmaWeb", "htmlperllistarpluginsfirmaweb", 2147483647);
    public static final LongField PLUGINFIRMASERVIDORID = new LongField(_TABLE_MODEL, "pluginFirmaServidorID", "pluginfirmaservidorid", 19);
    public static final IntegerField UPGRADESIGNFORMAT = new IntegerField(_TABLE_MODEL, "upgradeSignFormat", "upgradesignformat", 10);
    public static final BooleanField VALIDARFIRMA = new BooleanField(_TABLE_MODEL, "validarFirma", "validarfirma", 1);
    public static final BooleanField CHECKCANVIATDOCFIRMAT = new BooleanField(_TABLE_MODEL, "checkCanviatDocFirmat", "checkcanviatdocfirmat", 1);
    public static final BooleanField COMPROVARNIFFIRMA = new BooleanField(_TABLE_MODEL, "comprovarNifFirma", "comprovarniffirma", 1);
    public static final BooleanField VALIDARCERTIFICAT = new BooleanField(_TABLE_MODEL, "validarCertificat", "validarcertificat", 1);
    public static final BooleanField ESDEPETICIO = new BooleanField(_TABLE_MODEL, "esDePeticio", "esdepeticio", 1);

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
        MOTIUDELEGACIOID,
        POLITICATAULAFIRMES,
        POSICIOTAULAFIRMESID,
        FIRMATPERFORMATID,
        PROPIETATSTAULAFIRMES,
        POLITICASEGELLATDETEMPS,
        PLUGINSEGELLATID,
        HTMLPERLLISTARPLUGINSFIRMAWEB,
        PLUGINFIRMASERVIDORID,
        UPGRADESIGNFORMAT,
        VALIDARFIRMA,
        CHECKCANVIATDOCFIRMAT,
        COMPROVARNIFFIRMA,
        VALIDARCERTIFICAT,
        ESDEPETICIO
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        USUARIAPLICACIOCONFIGID
    };
}
