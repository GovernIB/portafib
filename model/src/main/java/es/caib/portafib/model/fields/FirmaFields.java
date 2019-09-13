
package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.BigIntegerField;
import org.fundaciobit.genapp.common.query.BooleanField;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.IntegerField;
import org.fundaciobit.genapp.common.query.LongField;
import org.fundaciobit.genapp.common.query.StringField;
public interface FirmaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_firma";


  public static final String _TABLE_MODEL = "firma";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField FIRMAID = new LongField(_TABLE_MODEL, "firmaID", "firmaid");  // PK
	 public static final StringField DESTINATARIID = new StringField(_TABLE_MODEL, "destinatariID", "destinatariid");
	 public static final LongField BLOCDEFIRMAID = new LongField(_TABLE_MODEL, "blocDeFirmaID", "blocdefirmaid");
	 public static final BooleanField OBLIGATORI = new BooleanField(_TABLE_MODEL, "obligatori", "obligatori");
	 public static final LongField FITXERFIRMATID = new LongField(_TABLE_MODEL, "fitxerFirmatID", "fitxerfirmatid");
	 public static final IntegerField NUMFIRMADOCUMENT = new IntegerField(_TABLE_MODEL, "numFirmaDocument", "numfirmadocument");
	 public static final IntegerField CAIXAPAGINA = new IntegerField(_TABLE_MODEL, "caixaPagina", "caixa_pagina");
	 public static final IntegerField CAIXAX = new IntegerField(_TABLE_MODEL, "caixaX", "caixa_x");
	 public static final IntegerField CAIXAY = new IntegerField(_TABLE_MODEL, "caixaY", "caixa_y");
	 public static final IntegerField CAIXAAMPLE = new IntegerField(_TABLE_MODEL, "caixaAmple", "caixa_ample");
	 public static final IntegerField CAIXAALT = new IntegerField(_TABLE_MODEL, "caixaAlt", "caixa_alt");
	 public static final BigIntegerField NUMEROSERIECERTIFICAT = new BigIntegerField(_TABLE_MODEL, "numeroSerieCertificat", "numeroseriecertificat");
	 public static final StringField EMISSORCERTIFICAT = new StringField(_TABLE_MODEL, "emissorCertificat", "emissorcertificat");
	 public static final StringField NOMCERTIFICAT = new StringField(_TABLE_MODEL, "nomCertificat", "nomcertificat");
	 public static final LongField TIPUSESTATDEFIRMAFINALID = new LongField(_TABLE_MODEL, "tipusEstatDeFirmaFinalID", "tipusestatdefirmafinalid");
	 public static final BooleanField MOSTRARRUBRICA = new BooleanField(_TABLE_MODEL, "mostrarRubrica", "mostrarrubrica");
	 public static final StringField MOTIU = new StringField(_TABLE_MODEL, "motiu", "motiu");
	 public static final IntegerField MINIMDEREVISORS = new IntegerField(_TABLE_MODEL, "minimDeRevisors", "minimderevisors");
	 public static final BooleanField CHECKADMINISTRATIONIDOFSIGNER = new BooleanField(_TABLE_MODEL, "checkAdministrationIdOfSigner", "checkadministrationidofsigner");
	 public static final BooleanField CHECKDOCUMENTMODIFICATIONS = new BooleanField(_TABLE_MODEL, "checkDocumentModifications", "checkdocumentmodifications");
	 public static final BooleanField CHECKVALIDATIONSIGNATURE = new BooleanField(_TABLE_MODEL, "checkValidationSignature", "checkvalidationsignature");
	 public static final StringField PERFILDEFIRMA = new StringField(_TABLE_MODEL, "perfilDeFirma", "perfildefirma");


  public static final Field<?>[] ALL_FIRMA_FIELDS = {
    FIRMAID,
    DESTINATARIID,
    BLOCDEFIRMAID,
    OBLIGATORI,
    FITXERFIRMATID,
    NUMFIRMADOCUMENT,
    CAIXAPAGINA,
    CAIXAX,
    CAIXAY,
    CAIXAAMPLE,
    CAIXAALT,
    NUMEROSERIECERTIFICAT,
    EMISSORCERTIFICAT,
    NOMCERTIFICAT,
    TIPUSESTATDEFIRMAFINALID,
    MOSTRARRUBRICA,
    MOTIU,
    MINIMDEREVISORS,
    CHECKADMINISTRATIONIDOFSIGNER,
    CHECKDOCUMENTMODIFICATIONS,
    CHECKVALIDATIONSIGNATURE,
    PERFILDEFIRMA
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
FIRMAID
  };
}
