
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class RebreAvisQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public RebreAvisQueryPath() {
  }

  protected RebreAvisQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ID() {
    return new LongField(getQueryPath(), RebreAvisFields.ID);
  }

  public StringField USUARIENTITATID() {
    return new StringField(getQueryPath(), RebreAvisFields.USUARIENTITATID);
  }

  public LongField TIPUSNOTIFICACIOID() {
    return new LongField(getQueryPath(), RebreAvisFields.TIPUSNOTIFICACIOID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (RebreAvisFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public UsuariEntitatQueryPath USUARIENTITAT() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return RebreAvisQueryPath.this.getQueryPath() + "usuariEntitat" + ".";
      }
    });
  }

  public TipusNotificacioQueryPath TIPUSNOTIFICACIO() {
    return new TipusNotificacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return RebreAvisQueryPath.this.getQueryPath() + "tipusNotificacio" + ".";
      }
    });
  }

}
