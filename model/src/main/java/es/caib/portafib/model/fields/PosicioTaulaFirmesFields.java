
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PosicioTaulaFirmesFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_posiciotaulafirmes";


  public static final String _TABLE_MODEL = "posicioTaulaFirmes";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final IntegerField POSICIOTAULAFIRMESID = new IntegerField(_TABLE_MODEL, "posicioTaulaFirmesID", "posiciotaulafirmesid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final BooleanField SUPORTADA = new BooleanField(_TABLE_MODEL, "suportada", "suportada");


  public static final Field<?>[] ALL_POSICIOTAULAFIRMES_FIELDS = {
    POSICIOTAULAFIRMESID,
    NOM,
    DESCRIPCIO,
    SUPORTADA
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
POSICIOTAULAFIRMESID
  };
}
