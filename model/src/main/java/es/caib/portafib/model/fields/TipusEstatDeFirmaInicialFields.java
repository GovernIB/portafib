
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface TipusEstatDeFirmaInicialFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_tipusestatdefirmainicial";


  public static final String _TABLE_MODEL = "tipusEstatDeFirmaInicial";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField TIPUSESTATDEFIRMAINICIALID = new LongField(_TABLE_MODEL, "tipusEstatDeFirmaInicialID", "tipusestatdefirmainicialid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");


  public static final Field<?>[] ALL_TIPUSESTATDEFIRMAINICIAL_FIELDS = {
    TIPUSESTATDEFIRMAINICIALID,
    NOM,
    DESCRIPCIO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
TIPUSESTATDEFIRMAINICIALID
  };
}
