
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class UsuariEntitatRevisorQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public UsuariEntitatRevisorQueryPath() {
  }

  protected UsuariEntitatRevisorQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField USUARIENTITATREVISORID() {
    return new LongField(getQueryPath(), UsuariEntitatRevisorFields.USUARIENTITATREVISORID);
  }

  public StringField USUARIENTITATID() {
    return new StringField(getQueryPath(), UsuariEntitatRevisorFields.USUARIENTITATID);
  }

  public BooleanField ACTIU() {
    return new BooleanField(getQueryPath(), UsuariEntitatRevisorFields.ACTIU);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (UsuariEntitatRevisorFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public RevisorDeFirmaQueryPath REVISORDEFIRMAS() {
    return new RevisorDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatRevisorQueryPath.this.getQueryPath() + "revisorDeFirmas" + ".";
      }
    });
  }
*/

  public UsuariEntitatQueryPath USUARIENTITAT() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatRevisorQueryPath.this.getQueryPath() + "usuariEntitat" + ".";
      }
    });
  }

}
