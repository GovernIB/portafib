package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_custodiainfo */
public interface CustodiaInfoFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_custodiainfo";

    public static final String _TABLE_MODEL = "custodiaInfo";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField CUSTODIAINFOID = new LongField(_TABLE_MODEL, "custodiaInfoID", "custodiainfoid", 19);  // PK
    public static final StringField NOMPLANTILLA = new StringField(_TABLE_MODEL, "nomPlantilla", "nomplantilla", 255);
    public static final StringField CUSTODIADOCUMENTID = new StringField(_TABLE_MODEL, "custodiaDocumentID", "custodiapluginid", 255);
    public static final LongField PLUGINID = new LongField(_TABLE_MODEL, "pluginID", "pluginid", 19);
    public static final StringField CUSTODIAPLUGINPARAMETERS = new StringField(_TABLE_MODEL, "custodiaPluginParameters", "custodiapluginparametres", 3000);
    public static final BooleanField CUSTODIAR = new BooleanField(_TABLE_MODEL, "custodiar", "custodiar", 1);
    public static final StringField PAGINES = new StringField(_TABLE_MODEL, "pagines", "pagines", 255);
    public static final StringField MISSATGE = new StringField(_TABLE_MODEL, "missatge", "missatge", 3000);
    public static final LongField MISSATGEPOSICIOPAGINAID = new LongField(_TABLE_MODEL, "missatgePosicioPaginaID", "missatgeposiciopaginaid", 19);
    public static final StringField CODIBARRESID = new StringField(_TABLE_MODEL, "codiBarresID", "codibarresid", 255);
    public static final LongField CODIBARRESPOSICIOPAGINAID = new LongField(_TABLE_MODEL, "codiBarresPosicioPaginaID", "codibarresposiciopaginaid", 19);
    public static final StringField CODIBARRESTEXT = new StringField(_TABLE_MODEL, "codiBarresText", "codibarrestext", 255);
    public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid", 101);
    public static final StringField USUARIAPLICACIOID = new StringField(_TABLE_MODEL, "usuariAplicacioID", "usuariaplicacioid", 101);
    public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatID", "entitatid", 50);
    public static final StringField TITOLPETICIO = new StringField(_TABLE_MODEL, "titolPeticio", "titolpeticio", 255);
    public static final TimestampField DATACUSTODIA = new TimestampField(_TABLE_MODEL, "dataCustodia", "datacustodia", 29);
    public static final BooleanField EDITABLE = new BooleanField(_TABLE_MODEL, "editable", "editable", 1);
    public static final StringField CSV = new StringField(_TABLE_MODEL, "csv", "csv", 500);
    public static final StringField CSVVALIDATIONWEB = new StringField(_TABLE_MODEL, "csvValidationWeb", "csvvalidationweb", 500);
    public static final StringField CSVGENERATIONDEFINITION = new StringField(_TABLE_MODEL, "csvGenerationDefinition", "csvgenerationdefinition", 500);
    public static final StringField URLFITXERCUSTODIAT = new StringField(_TABLE_MODEL, "urlFitxerCustodiat", "urlfitxercustodiat", 500);
    public static final StringField ORIGINALFILEDIRECTURL = new StringField(_TABLE_MODEL, "originalFileDirectUrl", "originalfiledirecturl", 500);
    public static final StringField PRINTABLEFILEDIRECTURL = new StringField(_TABLE_MODEL, "printableFileDirectUrl", "printablefiledirecturl", 500);
    public static final StringField ENIFILEDIRECTURL = new StringField(_TABLE_MODEL, "eniFileDirectUrl", "enifiledirecturl", 500);
    public static final StringField EXPEDIENTARXIUID = new StringField(_TABLE_MODEL, "expedientArxiuId", "expedientid", 250);
    public static final StringField DOCUMENTARXIUID = new StringField(_TABLE_MODEL, "documentArxiuId", "documentid", 250);

    public static final Field<?>[] ALL_CUSTODIAINFO_FIELDS = {
        CUSTODIAINFOID,
        NOMPLANTILLA,
        CUSTODIADOCUMENTID,
        PLUGINID,
        CUSTODIAPLUGINPARAMETERS,
        CUSTODIAR,
        PAGINES,
        MISSATGE,
        MISSATGEPOSICIOPAGINAID,
        CODIBARRESID,
        CODIBARRESPOSICIOPAGINAID,
        CODIBARRESTEXT,
        USUARIENTITATID,
        USUARIAPLICACIOID,
        ENTITATID,
        TITOLPETICIO,
        DATACUSTODIA,
        EDITABLE,
        CSV,
        CSVVALIDATIONWEB,
        CSVGENERATIONDEFINITION,
        URLFITXERCUSTODIAT,
        ORIGINALFILEDIRECTURL,
        PRINTABLEFILEDIRECTURL,
        ENIFILEDIRECTURL,
        EXPEDIENTARXIUID,
        DOCUMENTARXIUID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        CUSTODIAINFOID
    };
}
