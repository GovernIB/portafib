
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PropietatGlobalFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_propietatglobal";


  public static final String _TABLE_MODEL = "propietatGlobal";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PROPIETATGLOBALID = new LongField(_TABLE_MODEL, "propietatGlobalID", "propietatglobalid");  // PK
	 public static final StringField CLAU = new StringField(_TABLE_MODEL, "clau", "clau");
	 public static final StringField VALOR = new StringField(_TABLE_MODEL, "valor", "valor");
	 public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");


  public static final Field<?>[] ALL_PROPIETATGLOBAL_FIELDS = {
    PROPIETATGLOBALID,
    CLAU,
    VALOR,
    ENTITATID,
    DESCRIPCIO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PROPIETATGLOBALID
  };
}
