package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_peticiodefirma */
public interface PeticioDeFirmaFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_peticiodefirma";

    public static final String _TABLE_MODEL = "peticioDeFirma";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField PETICIODEFIRMAID = new LongField(_TABLE_MODEL, "peticioDeFirmaID", "peticiodefirmaid", 19);  // PK
    public static final StringField TITOL = new StringField(_TABLE_MODEL, "titol", "titol", 255);
    public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio", 255);
    public static final StringField MOTIU = new StringField(_TABLE_MODEL, "motiu", "motiu", 255);
    public static final LongField FITXERAFIRMARID = new LongField(_TABLE_MODEL, "fitxerAFirmarID", "fitxerafirmarid", 19);
    public static final LongField FIRMAORIGINALDETACHEDID = new LongField(_TABLE_MODEL, "firmaOriginalDetachedID", "firmaoriginaldetachedid", 19);
    public static final LongField FITXERADAPTATID = new LongField(_TABLE_MODEL, "fitxerAdaptatID", "fitxeradaptatid", 19);
    public static final LongField TIPUSDOCUMENTID = new LongField(_TABLE_MODEL, "tipusDocumentID", "tipusdocumentid", 19);
    public static final StringField DESCRIPCIOTIPUSDOCUMENT = new StringField(_TABLE_MODEL, "descripcioTipusDocument", "descripciotipusdocument", 255);
    public static final TimestampField DATASOLICITUD = new TimestampField(_TABLE_MODEL, "dataSolicitud", "datasolicitud", 29);
    public static final TimestampField DATAFINAL = new TimestampField(_TABLE_MODEL, "dataFinal", "datafinal", 29);
    public static final TimestampField DATACADUCITAT = new TimestampField(_TABLE_MODEL, "dataCaducitat", "datacaducitat", 29);
    public static final IntegerField TIPUSOPERACIOFIRMA = new IntegerField(_TABLE_MODEL, "tipusOperacioFirma", "tipusoperaciofirma", 10);
    public static final IntegerField TIPUSFIRMAID = new IntegerField(_TABLE_MODEL, "tipusFirmaID", "tipusfirmaid", 19);
    public static final IntegerField ALGORISMEDEFIRMAID = new IntegerField(_TABLE_MODEL, "algorismeDeFirmaID", "algorismedefirmaid", 19);
    public static final IntegerField MODEDEFIRMA = new IntegerField(_TABLE_MODEL, "modeDeFirma", "modedefirma", 10);
    public static final IntegerField POSICIOTAULAFIRMESID = new IntegerField(_TABLE_MODEL, "posicioTaulaFirmesID", "posiciotaulafirmesid", 19);
    public static final IntegerField TIPUSESTATPETICIODEFIRMAID = new IntegerField(_TABLE_MODEL, "tipusEstatPeticioDeFirmaID", "tipusestatpeticiodefirmaid", 19);
    public static final StringField MOTIUDEREBUIG = new StringField(_TABLE_MODEL, "motiuDeRebuig", "motiuderebuig", 255);
    public static final StringField IDIOMAID = new StringField(_TABLE_MODEL, "idiomaID", "idiomaid", 5);
    public static final IntegerField PRIORITATID = new IntegerField(_TABLE_MODEL, "prioritatID", "prioritatid", 10);
    public static final LongField FLUXDEFIRMESID = new LongField(_TABLE_MODEL, "fluxDeFirmesID", "fluxdefirmesid", 19);
    public static final StringField SOLICITANTUSUARIAPLICACIOID = new StringField(_TABLE_MODEL, "solicitantUsuariAplicacioID", "usuariaplicacioid", 101);
    public static final StringField REMITENTNOM = new StringField(_TABLE_MODEL, "remitentNom", "remitentnom", 100);
    public static final StringField REMITENTDESCRIPCIO = new StringField(_TABLE_MODEL, "remitentDescripcio", "remitentdescripcio", 500);
    public static final StringField EXPEDIENTCODI = new StringField(_TABLE_MODEL, "expedientCodi", "expedientcodi", 255);
    public static final StringField EXPEDIENTNOM = new StringField(_TABLE_MODEL, "expedientNom", "expedientnom", 255);
    public static final StringField EXPEDIENTURL = new StringField(_TABLE_MODEL, "expedientUrl", "expedienturl", 255);
    public static final StringField PROCEDIMENTCODI = new StringField(_TABLE_MODEL, "procedimentCodi", "procedimentcodi", 255);
    public static final StringField PROCEDIMENTNOM = new StringField(_TABLE_MODEL, "procedimentNom", "procedimentnom", 255);
    public static final StringField INFORMACIOADDICIONAL = new StringField(_TABLE_MODEL, "informacioAddicional", "informacioaddicional", 500);
    public static final DoubleField INFORMACIOADDICIONALAVALUABLE = new DoubleField(_TABLE_MODEL, "informacioAddicionalAvaluable", "informacioaddicionalavaluable", 17);
    public static final LongField LOGOSEGELLID = new LongField(_TABLE_MODEL, "logoSegellID", "logosegellid", 19);
    public static final LongField CUSTODIAINFOID = new LongField(_TABLE_MODEL, "custodiaInfoID", "custodiainfoid", 19);
    public static final StringField SOLICITANTUSUARIENTITAT1ID = new StringField(_TABLE_MODEL, "solicitantUsuariEntitat1ID", "usuarientitatid", 101);
    public static final StringField SOLICITANTUSUARIENTITAT2ID = new StringField(_TABLE_MODEL, "solicitantUsuariEntitat2ID", "solicitantpersona2id", 101);
    public static final StringField SOLICITANTUSUARIENTITAT3ID = new StringField(_TABLE_MODEL, "solicitantUsuariEntitat3ID", "solicitantpersona3id", 101);
    public static final BooleanField AVISWEB = new BooleanField(_TABLE_MODEL, "avisWeb", "avisweb", 1);
    public static final BooleanField SEGELLATDETEMPS = new BooleanField(_TABLE_MODEL, "segellatDeTemps", "segellatdetemps", 1);
    public static final IntegerField ORIGENPETICIODEFIRMA = new IntegerField(_TABLE_MODEL, "origenPeticioDeFirma", "origenpeticiodefirma", 10);
    public static final LongField CONFIGURACIODEFIRMAID = new LongField(_TABLE_MODEL, "configuracioDeFirmaID", "configuraciodefirmaid", 19);

    public static final Field<?>[] ALL_PETICIODEFIRMA_FIELDS = {
        PETICIODEFIRMAID,
        TITOL,
        DESCRIPCIO,
        MOTIU,
        FITXERAFIRMARID,
        FIRMAORIGINALDETACHEDID,
        FITXERADAPTATID,
        TIPUSDOCUMENTID,
        DESCRIPCIOTIPUSDOCUMENT,
        DATASOLICITUD,
        DATAFINAL,
        DATACADUCITAT,
        TIPUSOPERACIOFIRMA,
        TIPUSFIRMAID,
        ALGORISMEDEFIRMAID,
        MODEDEFIRMA,
        POSICIOTAULAFIRMESID,
        TIPUSESTATPETICIODEFIRMAID,
        MOTIUDEREBUIG,
        IDIOMAID,
        PRIORITATID,
        FLUXDEFIRMESID,
        SOLICITANTUSUARIAPLICACIOID,
        REMITENTNOM,
        REMITENTDESCRIPCIO,
        EXPEDIENTCODI,
        EXPEDIENTNOM,
        EXPEDIENTURL,
        PROCEDIMENTCODI,
        PROCEDIMENTNOM,
        INFORMACIOADDICIONAL,
        INFORMACIOADDICIONALAVALUABLE,
        LOGOSEGELLID,
        CUSTODIAINFOID,
        SOLICITANTUSUARIENTITAT1ID,
        SOLICITANTUSUARIENTITAT2ID,
        SOLICITANTUSUARIENTITAT3ID,
        AVISWEB,
        SEGELLATDETEMPS,
        ORIGENPETICIODEFIRMA,
        CONFIGURACIODEFIRMAID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        PETICIODEFIRMAID
    };
}
