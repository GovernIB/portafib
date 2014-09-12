
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class RoleUsuariEntitatQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public RoleUsuariEntitatQueryPath() {
  }

  protected RoleUsuariEntitatQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ID() {
    return new LongField(getQueryPath(), RoleUsuariEntitatFields.ID);
  }

  public StringField ROLEID() {
    return new StringField(getQueryPath(), RoleUsuariEntitatFields.ROLEID);
  }

  public StringField USUARIENTITATID() {
    return new StringField(getQueryPath(), RoleUsuariEntitatFields.USUARIENTITATID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (RoleUsuariEntitatFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public RoleQueryPath ROLE() {
    return new RoleQueryPath(new QueryPath() {
      public String getQueryPath() {
          return RoleUsuariEntitatQueryPath.this.getQueryPath() + "role" + ".";
      }
    });
  }

  public UsuariEntitatQueryPath USUARIENTITAT() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return RoleUsuariEntitatQueryPath.this.getQueryPath() + "usuariEntitat" + ".";
      }
    });
  }

}
