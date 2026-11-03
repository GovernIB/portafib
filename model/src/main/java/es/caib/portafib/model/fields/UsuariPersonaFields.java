package es.caib.portafib.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD pfi_usuaripersona */
public interface UsuariPersonaFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "pfi_usuaripersona";

    public static final String _TABLE_MODEL = "usuariPersona";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final StringField USUARIPERSONAID = new StringField(_TABLE_MODEL, "usuariPersonaID", "usuaripersonaid", 50);  // PK
    public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom", 50);
    public static final StringField LLINATGES = new StringField(_TABLE_MODEL, "llinatges", "llinatges", 100);
    public static final StringField EMAIL = new StringField(_TABLE_MODEL, "email", "email", 100);
    public static final StringField NIF = new StringField(_TABLE_MODEL, "nif", "nif", 9);
    public static final StringField IDIOMAID = new StringField(_TABLE_MODEL, "idiomaID", "idiomaid", 5);
    public static final LongField RUBRICAID = new LongField(_TABLE_MODEL, "rubricaID", "rubricaid", 19);
    public static final BooleanField USUARIINTERN = new BooleanField(_TABLE_MODEL, "usuariIntern", "usuariintern", 1);
    public static final StringField CONTRASENYA = new StringField(_TABLE_MODEL, "contrasenya", "contrasenya", 255);

    public static final Field<?>[] ALL_USUARIPERSONA_FIELDS = {
        USUARIPERSONAID,
        NOM,
        LLINATGES,
        EMAIL,
        NIF,
        IDIOMAID,
        RUBRICAID,
        USUARIINTERN,
        CONTRASENYA
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        USUARIPERSONAID
    };
}
