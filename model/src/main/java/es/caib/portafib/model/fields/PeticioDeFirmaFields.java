
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PeticioDeFirmaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_peticiodefirma";


  public static final String _TABLE_MODEL = "peticioDeFirma";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PETICIODEFIRMAID = new LongField(_TABLE_MODEL, "peticioDeFirmaID", "peticiodefirmaid");  // PK
	 public static final StringField TITOL = new StringField(_TABLE_MODEL, "titol", "titol");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final StringField MOTIU = new StringField(_TABLE_MODEL, "motiu", "motiu");
	 public static final LongField FITXERAFIRMARID = new LongField(_TABLE_MODEL, "fitxerAFirmarID", "fitxerafirmarid");
	 public static final LongField FITXERADAPTATID = new LongField(_TABLE_MODEL, "fitxerAdaptatID", "fitxeradaptatid");
	 public static final LongField TIPUSDOCUMENTID = new LongField(_TABLE_MODEL, "tipusDocumentID", "tipusdocumentid");
	 public static final StringField DESCRIPCIOTIPUSDOCUMENT = new StringField(_TABLE_MODEL, "descripcioTipusDocument", "descripciotipusdocument");
	 public static final IntegerField POSICIOTAULAFIRMESID = new IntegerField(_TABLE_MODEL, "posicioTaulaFirmesID", "posiciotaulafirmesid");
	 public static final TimestampField DATASOLICITUD = new TimestampField(_TABLE_MODEL, "dataSolicitud", "datasolicitud");
	 public static final TimestampField DATAFINAL = new TimestampField(_TABLE_MODEL, "dataFinal", "datafinal");
	 public static final TimestampField DATACADUCITAT = new TimestampField(_TABLE_MODEL, "dataCaducitat", "datacaducitat");
	 public static final IntegerField TIPUSFIRMAID = new IntegerField(_TABLE_MODEL, "tipusFirmaID", "tipusfirmaid");
	 public static final LongField ALGORISMEDEFIRMAID = new LongField(_TABLE_MODEL, "algorismeDeFirmaID", "algorismedefirmaid");
	 public static final BooleanField MODEDEFIRMA = new BooleanField(_TABLE_MODEL, "modeDeFirma", "modedefirma");
	 public static final IntegerField TIPUSESTATPETICIODEFIRMAID = new IntegerField(_TABLE_MODEL, "tipusEstatPeticioDeFirmaID", "tipusestatpeticiodefirmaid");
	 public static final StringField MOTIUDEREBUIG = new StringField(_TABLE_MODEL, "motiuDeRebuig", "motiuderebuig");
	 public static final StringField IDIOMAID = new StringField(_TABLE_MODEL, "idiomaID", "idiomaid");
	 public static final IntegerField PRIORITATID = new IntegerField(_TABLE_MODEL, "prioritatID", "prioritatid");
	 public static final LongField FLUXDEFIRMESID = new LongField(_TABLE_MODEL, "fluxDeFirmesID", "fluxdefirmesid");
	 public static final StringField USUARIAPLICACIOID = new StringField(_TABLE_MODEL, "usuariAplicacioID", "usuariaplicacioid");
	 public static final StringField REMITENTNOM = new StringField(_TABLE_MODEL, "remitentNom", "remitentnom");
	 public static final StringField REMITENTDESCRIPCIO = new StringField(_TABLE_MODEL, "remitentDescripcio", "remitentdescripcio");
	 public static final StringField INFORMACIOADICIONAL = new StringField(_TABLE_MODEL, "informacioAdicional", "informacioaddicional");
	 public static final LongField LOGOSEGELLID = new LongField(_TABLE_MODEL, "logoSegellID", "logosegellid");
	 public static final LongField CUSTODIAINFOID = new LongField(_TABLE_MODEL, "custodiaInfoID", "custodiainfoid");
	 public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid");
	 public static final BooleanField AVISWEB = new BooleanField(_TABLE_MODEL, "avisWeb", "avisweb");


  public static final Field<?>[] ALL_PETICIODEFIRMA_FIELDS = {
    PETICIODEFIRMAID,
    TITOL,
    DESCRIPCIO,
    MOTIU,
    FITXERAFIRMARID,
    FITXERADAPTATID,
    TIPUSDOCUMENTID,
    DESCRIPCIOTIPUSDOCUMENT,
    POSICIOTAULAFIRMESID,
    DATASOLICITUD,
    DATAFINAL,
    DATACADUCITAT,
    TIPUSFIRMAID,
    ALGORISMEDEFIRMAID,
    MODEDEFIRMA,
    TIPUSESTATPETICIODEFIRMAID,
    MOTIUDEREBUIG,
    IDIOMAID,
    PRIORITATID,
    FLUXDEFIRMESID,
    USUARIAPLICACIOID,
    REMITENTNOM,
    REMITENTDESCRIPCIO,
    INFORMACIOADICIONAL,
    LOGOSEGELLID,
    CUSTODIAINFOID,
    USUARIENTITATID,
    AVISWEB
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PETICIODEFIRMAID
  };
}
