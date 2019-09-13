
package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.BooleanField;
import org.fundaciobit.genapp.common.query.DoubleField;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.IntegerField;
import org.fundaciobit.genapp.common.query.LongField;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.TimestampField;
public interface PeticioDeFirmaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_peticiodefirma";


  public static final String _TABLE_MODEL = "peticioDeFirma";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PETICIODEFIRMAID = new LongField(_TABLE_MODEL, "peticioDeFirmaID", "peticiodefirmaid");  // PK
	 public static final StringField TITOL = new StringField(_TABLE_MODEL, "titol", "titol");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final StringField MOTIU = new StringField(_TABLE_MODEL, "motiu", "motiu");
	 public static final LongField FITXERAFIRMARID = new LongField(_TABLE_MODEL, "fitxerAFirmarID", "fitxerafirmarid");
	 public static final LongField FIRMAORIGINALDETACHEDID = new LongField(_TABLE_MODEL, "firmaOriginalDetachedID", "firmaoriginaldetachedid");
	 public static final LongField FITXERADAPTATID = new LongField(_TABLE_MODEL, "fitxerAdaptatID", "fitxeradaptatid");
	 public static final LongField TIPUSDOCUMENTID = new LongField(_TABLE_MODEL, "tipusDocumentID", "tipusdocumentid");
	 public static final StringField DESCRIPCIOTIPUSDOCUMENT = new StringField(_TABLE_MODEL, "descripcioTipusDocument", "descripciotipusdocument");
	 public static final IntegerField POSICIOTAULAFIRMESID = new IntegerField(_TABLE_MODEL, "posicioTaulaFirmesID", "posiciotaulafirmesid");
	 public static final TimestampField DATASOLICITUD = new TimestampField(_TABLE_MODEL, "dataSolicitud", "datasolicitud");
	 public static final TimestampField DATAFINAL = new TimestampField(_TABLE_MODEL, "dataFinal", "datafinal");
	 public static final TimestampField DATACADUCITAT = new TimestampField(_TABLE_MODEL, "dataCaducitat", "datacaducitat");
	 public static final IntegerField TIPUSOPERACIOFIRMA = new IntegerField(_TABLE_MODEL, "tipusOperacioFirma", "tipusoperaciofirma");
	 public static final IntegerField TIPUSFIRMAID = new IntegerField(_TABLE_MODEL, "tipusFirmaID", "tipusfirmaid");
	 public static final IntegerField ALGORISMEDEFIRMAID = new IntegerField(_TABLE_MODEL, "algorismeDeFirmaID", "algorismedefirmaid");
	 public static final BooleanField MODEDEFIRMA = new BooleanField(_TABLE_MODEL, "modeDeFirma", "modedefirma");
	 public static final IntegerField TIPUSESTATPETICIODEFIRMAID = new IntegerField(_TABLE_MODEL, "tipusEstatPeticioDeFirmaID", "tipusestatpeticiodefirmaid");
	 public static final StringField MOTIUDEREBUIG = new StringField(_TABLE_MODEL, "motiuDeRebuig", "motiuderebuig");
	 public static final StringField IDIOMAID = new StringField(_TABLE_MODEL, "idiomaID", "idiomaid");
	 public static final IntegerField PRIORITATID = new IntegerField(_TABLE_MODEL, "prioritatID", "prioritatid");
	 public static final LongField FLUXDEFIRMESID = new LongField(_TABLE_MODEL, "fluxDeFirmesID", "fluxdefirmesid");
	 public static final StringField SOLICITANTUSUARIAPLICACIOID = new StringField(_TABLE_MODEL, "solicitantUsuariAplicacioID", "usuariaplicacioid");
	 public static final StringField REMITENTNOM = new StringField(_TABLE_MODEL, "remitentNom", "remitentnom");
	 public static final StringField REMITENTDESCRIPCIO = new StringField(_TABLE_MODEL, "remitentDescripcio", "remitentdescripcio");
	 public static final StringField EXPEDIENTCODI = new StringField(_TABLE_MODEL, "expedientCodi", "expedientcodi");
	 public static final StringField EXPEDIENTNOM = new StringField(_TABLE_MODEL, "expedientNom", "expedientnom");
	 public static final StringField EXPEDIENTURL = new StringField(_TABLE_MODEL, "expedientUrl", "expedienturl");
	 public static final StringField PROCEDIMENTCODI = new StringField(_TABLE_MODEL, "procedimentCodi", "procedimentcodi");
	 public static final StringField PROCEDIMENTNOM = new StringField(_TABLE_MODEL, "procedimentNom", "procedimentnom");
	 public static final StringField INFORMACIOADDICIONAL = new StringField(_TABLE_MODEL, "informacioAddicional", "informacioaddicional");
	 public static final DoubleField INFORMACIOADDICIONALAVALUABLE = new DoubleField(_TABLE_MODEL, "informacioAddicionalAvaluable", "informacioaddicionalavaluable");
	 public static final LongField LOGOSEGELLID = new LongField(_TABLE_MODEL, "logoSegellID", "logosegellid");
	 public static final LongField CUSTODIAINFOID = new LongField(_TABLE_MODEL, "custodiaInfoID", "custodiainfoid");
	 public static final StringField SOLICITANTUSUARIENTITAT1ID = new StringField(_TABLE_MODEL, "solicitantUsuariEntitat1ID", "usuarientitatid");
	 public static final StringField SOLICITANTUSUARIENTITAT2ID = new StringField(_TABLE_MODEL, "solicitantUsuariEntitat2ID", "solicitantpersona2id");
	 public static final StringField SOLICITANTUSUARIENTITAT3ID = new StringField(_TABLE_MODEL, "solicitantUsuariEntitat3ID", "solicitantpersona3id");
	 public static final BooleanField AVISWEB = new BooleanField(_TABLE_MODEL, "avisWeb", "avisweb");
	 public static final BooleanField SEGELLATDETEMPS = new BooleanField(_TABLE_MODEL, "segellatDeTemps", "segellatdetemps");
	 public static final IntegerField ORIGENPETICIODEFIRMA = new IntegerField(_TABLE_MODEL, "origenPeticioDeFirma", "origenpeticiodefirma");
	 public static final LongField CONFIGURACIODEFIRMAID = new LongField(_TABLE_MODEL, "configuracioDeFirmaID", "configuraciodefirmaid");


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
    POSICIOTAULAFIRMESID,
    DATASOLICITUD,
    DATAFINAL,
    DATACADUCITAT,
    TIPUSOPERACIOFIRMA,
    TIPUSFIRMAID,
    ALGORISMEDEFIRMAID,
    MODEDEFIRMA,
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
