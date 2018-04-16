
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

  public LongField USUARIENTITATREVISORID() {
    return new LongField(getQueryPath(), RevisorDeFirmaFields.USUARIENTITATREVISORID);
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


  public UsuariEntitatRevisorQueryPath USUARIENTITATREVISOR() {
    return new UsuariEntitatRevisorQueryPath(new QueryPath() {
      public String getQueryPath() {
          return RevisorDeFirmaQueryPath.this.getQueryPath() + "usuariEntitatRevisor" + ".";
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
