
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface ModulDeFirmaPerTipusDeDocumentFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_modulfirmapertipusdoc";


  public static final String _TABLE_MODEL = "modulDeFirmaPerTipusDeDocument";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ID = new LongField(_TABLE_MODEL, "ID", "id");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final LongField TIPUSDOCUMENTID = new LongField(_TABLE_MODEL, "tipusDocumentID", "tipusdocumentid");
	 public static final LongField PLUGINID = new LongField(_TABLE_MODEL, "pluginID", "pluginid");


  public static final Field<?>[] ALL_MODULDEFIRMAPERTIPUSDEDOCUMENT_FIELDS = {
    ID,
    NOM,
    TIPUSDOCUMENTID,
    PLUGINID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ID
  };
}
