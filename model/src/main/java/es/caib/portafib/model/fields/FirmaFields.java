package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_firma */
public interface FirmaFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_firma";

    public static final String _TABLE_MODEL = "firma";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField FIRMAID = new LongField(_TABLE_MODEL, "firmaID", "firmaid", 19);  // PK
    public static final StringField DESTINATARIID = new StringField(_TABLE_MODEL, "destinatariID", "destinatariid", 101);
    public static final LongField BLOCDEFIRMAID = new LongField(_TABLE_MODEL, "blocDeFirmaID", "blocdefirmaid", 19);
    public static final BooleanField OBLIGATORI = new BooleanField(_TABLE_MODEL, "obligatori", "obligatori", 1);
    public static final LongField FITXERFIRMATID = new LongField(_TABLE_MODEL, "fitxerFirmatID", "fitxerfirmatid", 19);
    public static final IntegerField NUMFIRMADOCUMENT = new IntegerField(_TABLE_MODEL, "numFirmaDocument", "numfirmadocument", 10);
    public static final IntegerField CAIXAPAGINA = new IntegerField(_TABLE_MODEL, "caixaPagina", "caixa_pagina", 10);
    public static final IntegerField CAIXAX = new IntegerField(_TABLE_MODEL, "caixaX", "caixa_x", 10);
    public static final IntegerField CAIXAY = new IntegerField(_TABLE_MODEL, "caixaY", "caixa_y", 10);
    public static final IntegerField CAIXAAMPLE = new IntegerField(_TABLE_MODEL, "caixaAmple", "caixa_ample", 10);
    public static final IntegerField CAIXAALT = new IntegerField(_TABLE_MODEL, "caixaAlt", "caixa_alt", 10);
    public static final BigIntegerField NUMEROSERIECERTIFICAT = new BigIntegerField(_TABLE_MODEL, "numeroSerieCertificat", "numeroseriecertificat", 131089);
    public static final StringField EMISSORCERTIFICAT = new StringField(_TABLE_MODEL, "emissorCertificat", "emissorcertificat", 1000);
    public static final StringField NOMCERTIFICAT = new StringField(_TABLE_MODEL, "nomCertificat", "nomcertificat", 1000);
    public static final LongField TIPUSESTATDEFIRMAFINALID = new LongField(_TABLE_MODEL, "tipusEstatDeFirmaFinalID", "tipusestatdefirmafinalid", 19);
    public static final BooleanField MOSTRARRUBRICA = new BooleanField(_TABLE_MODEL, "mostrarRubrica", "mostrarrubrica", 1);
    public static final StringField MOTIU = new StringField(_TABLE_MODEL, "motiu", "motiu", 255);
    public static final IntegerField MINIMDEREVISORS = new IntegerField(_TABLE_MODEL, "minimDeRevisors", "minimderevisors", 10);
    public static final BooleanField CHECKADMINISTRATIONIDOFSIGNER = new BooleanField(_TABLE_MODEL, "checkAdministrationIdOfSigner", "checkadministrationidofsigner", 1);
    public static final BooleanField CHECKDOCUMENTMODIFICATIONS = new BooleanField(_TABLE_MODEL, "checkDocumentModifications", "checkdocumentmodifications", 1);
    public static final BooleanField CHECKVALIDATIONSIGNATURE = new BooleanField(_TABLE_MODEL, "checkValidationSignature", "checkvalidationsignature", 1);
    public static final StringField PERFILDEFIRMA = new StringField(_TABLE_MODEL, "perfilDeFirma", "perfildefirma", 50);
    public static final StringField USUARIEXTERNNOM = new StringField(_TABLE_MODEL, "usuariExternNom", "extern_nom", 100);
    public static final StringField USUARIEXTERNLLINATGES = new StringField(_TABLE_MODEL, "usuariExternLlinatges", "extern_llinatges", 255);
    public static final StringField USUARIEXTERNEMAIL = new StringField(_TABLE_MODEL, "usuariExternEmail", "extern_email", 255);
    public static final StringField USUARIEXTERNIDIOMA = new StringField(_TABLE_MODEL, "usuariExternIdioma", "extern_idioma", 2);
    public static final StringField USUARIEXTERNTOKEN = new StringField(_TABLE_MODEL, "usuariExternToken", "extern_token", 255);
    public static final IntegerField USUARIEXTERNNIVELLSEGURETAT = new IntegerField(_TABLE_MODEL, "usuariExternNivellSeguretat", "extern_nivellseguretat", 10);
    public static final LongField SIGNATUREPLUGINID = new LongField(_TABLE_MODEL, "signaturePluginId", "signaturepluginid", 19);

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
        PERFILDEFIRMA,
        USUARIEXTERNNOM,
        USUARIEXTERNLLINATGES,
        USUARIEXTERNEMAIL,
        USUARIEXTERNIDIOMA,
        USUARIEXTERNTOKEN,
        USUARIEXTERNNIVELLSEGURETAT,
        SIGNATUREPLUGINID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        FIRMAID
    };
}
