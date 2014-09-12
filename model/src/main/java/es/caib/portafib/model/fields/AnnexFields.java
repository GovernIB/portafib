
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface AnnexFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_annex";


  public static final String _TABLE_MODEL = "annex";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ANNEXID = new LongField(_TABLE_MODEL, "annexID", "annexid");  // PK
	 public static final LongField PETICIODEFIRMAID = new LongField(_TABLE_MODEL, "peticioDeFirmaID", "peticiodefirmaid");
	 public static final LongField FITXERID = new LongField(_TABLE_MODEL, "fitxerID", "fitxerid");
	 public static final BooleanField ADJUNTAR = new BooleanField(_TABLE_MODEL, "adjuntar", "adjuntar");
	 public static final BooleanField FIRMAR = new BooleanField(_TABLE_MODEL, "firmar", "firmar");


  public static final Field<?>[] ALL_ANNEX_FIELDS = {
    ANNEXID,
    PETICIODEFIRMAID,
    FITXERID,
    ADJUNTAR,
    FIRMAR
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ANNEXID
  };
}
