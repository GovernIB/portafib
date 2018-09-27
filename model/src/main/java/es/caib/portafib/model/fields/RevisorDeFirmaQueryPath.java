
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class RevisorDeFirmaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public RevisorDeFirmaQueryPath() {
  }

  protected RevisorDeFirmaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField REVISORDEFIRMAID() {
    return new LongField(getQueryPath(), RevisorDeFirmaFields.REVISORDEFIRMAID);
  }

  public StringField USUARIENTITATID() {
    return new StringField(getQueryPath(), RevisorDeFirmaFields.USUARIENTITATID);
  }

  public LongField FIRMAID() {
    return new LongField(getQueryPath(), RevisorDeFirmaFields.FIRMAID);
  }

  public BooleanField OBLIGATORI() {
    return new BooleanField(getQueryPath(), RevisorDeFirmaFields.OBLIGATORI);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (RevisorDeFirmaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public UsuariEntitatQueryPath USUARIENTITAT() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return RevisorDeFirmaQueryPath.this.getQueryPath() + "usuariEntitat" + ".";
      }
    });
  }

  public FirmaQueryPath FIRMA() {
    return new FirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return RevisorDeFirmaQueryPath.this.getQueryPath() + "firma" + ".";
      }
    });
  }

}
