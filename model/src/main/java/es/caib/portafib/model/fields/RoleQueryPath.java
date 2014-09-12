
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class RoleQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public RoleQueryPath() {
  }

  protected RoleQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public StringField ROLEID() {
    return new StringField(getQueryPath(), RoleFields.ROLEID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), RoleFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), RoleFields.DESCRIPCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (RoleFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public RoleUsuariAplicacioQueryPath ROLEUSUARIAPLICACIOS() {
    return new RoleUsuariAplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return RoleQueryPath.this.getQueryPath() + "roleUsuariAplicacios" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public RoleUsuariEntitatQueryPath ROLEUSUARIENTITATS() {
    return new RoleUsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return RoleQueryPath.this.getQueryPath() + "roleUsuariEntitats" + ".";
      }
    });
  }
*/

}
