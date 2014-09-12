
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface GrupEntitatUsuariEntitatFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_grupentitatusuarientitat";


  public static final String _TABLE_MODEL = "grupEntitatUsuariEntitat";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField GRUPENTITATUSUARIENTITATID = new LongField(_TABLE_MODEL, "grupEntitatUsuariEntitatID", "grupentitatusuarientitatid");  // PK
	 public static final StringField USUARIENTITATID = new StringField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid");
	 public static final LongField GRUPENTITATID = new LongField(_TABLE_MODEL, "grupEntitatID", "grupentitatid");


  public static final Field<?>[] ALL_GRUPENTITATUSUARIENTITAT_FIELDS = {
    GRUPENTITATUSUARIENTITATID,
    USUARIENTITATID,
    GRUPENTITATID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
GRUPENTITATUSUARIENTITATID
  };
}
