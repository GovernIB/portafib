
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface TipusEstatDeFirmaFinalFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_tipusestatdefirmafinal";


  public static final String _TABLE_MODEL = "tipusEstatDeFirmaFinal";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField TIPUSESTATDEFIRMAFINALID = new LongField(_TABLE_MODEL, "tipusEstatDeFirmaFinalID", "tipusestatdefirmafinalid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");


  public static final Field<?>[] ALL_TIPUSESTATDEFIRMAFINAL_FIELDS = {
    TIPUSESTATDEFIRMAFINALID,
    NOM,
    DESCRIPCIO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
TIPUSESTATDEFIRMAFINALID
  };
}
