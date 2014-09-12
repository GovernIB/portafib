
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface AnnexFirmatFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_annexfirmat";


  public static final String _TABLE_MODEL = "annexFirmat";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ANNEXFIRMATID = new LongField(_TABLE_MODEL, "annexfirmatID", "annexfirmatid");  // PK
	 public static final LongField FITXERID = new LongField(_TABLE_MODEL, "fitxerID", "fitxerid");
	 public static final LongField ANNEXID = new LongField(_TABLE_MODEL, "annexID", "annexid");
	 public static final LongField FIRMAID = new LongField(_TABLE_MODEL, "firmaID", "firmaid");


  public static final Field<?>[] ALL_ANNEXFIRMAT_FIELDS = {
    ANNEXFIRMATID,
    FITXERID,
    ANNEXID,
    FIRMAID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ANNEXFIRMATID
  };
}
