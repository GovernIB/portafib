
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PerfilDeFirmaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pfi_usuariaplicacioperfil";


  public static final String _TABLE_MODEL = "perfilDeFirma";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField USUARIAPLICACIOPERFILID = new LongField(_TABLE_MODEL, "usuariAplicacioPerfilID", "usuariaplicacioperfilid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField CODI = new StringField(_TABLE_MODEL, "codi", "codi");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final StringField CONDICIO = new StringField(_TABLE_MODEL, "condicio", "condicio");
	 public static final LongField CONFIGURACIODEFIRMA1ID = new LongField(_TABLE_MODEL, "configuracioDeFirma1ID", "usrappconfiguracio1id");
	 public static final LongField CONFIGURACIODEFIRMA2ID = new LongField(_TABLE_MODEL, "configuracioDeFirma2ID", "usrappconfiguracio2id");
	 public static final LongField CONFIGURACIODEFIRMA3ID = new LongField(_TABLE_MODEL, "configuracioDeFirma3ID", "usrappconfiguracio3id");


  public static final Field<?>[] ALL_PERFILDEFIRMA_FIELDS = {
    USUARIAPLICACIOPERFILID,
    NOM,
    CODI,
    DESCRIPCIO,
    CONDICIO,
    CONFIGURACIODEFIRMA1ID,
    CONFIGURACIODEFIRMA2ID,
    CONFIGURACIODEFIRMA3ID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
USUARIAPLICACIOPERFILID
  };
}
