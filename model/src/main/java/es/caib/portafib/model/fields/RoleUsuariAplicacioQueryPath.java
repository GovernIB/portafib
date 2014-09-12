
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class RoleUsuariAplicacioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public RoleUsuariAplicacioQueryPath() {
  }

  protected RoleUsuariAplicacioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ID() {
    return new LongField(getQueryPath(), RoleUsuariAplicacioFields.ID);
  }

  public StringField ROLEID() {
    return new StringField(getQueryPath(), RoleUsuariAplicacioFields.ROLEID);
  }

  public StringField USUARIAPLICACIOID() {
    return new StringField(getQueryPath(), RoleUsuariAplicacioFields.USUARIAPLICACIOID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (RoleUsuariAplicacioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public RoleQueryPath ROLE() {
    return new RoleQueryPath(new QueryPath() {
      public String getQueryPath() {
          return RoleUsuariAplicacioQueryPath.this.getQueryPath() + "role" + ".";
      }
    });
  }

  public UsuariAplicacioQueryPath USUARIAPLICACIO() {
    return new UsuariAplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return RoleUsuariAplicacioQueryPath.this.getQueryPath() + "usuariAplicacio" + ".";
      }
    });
  }

}
