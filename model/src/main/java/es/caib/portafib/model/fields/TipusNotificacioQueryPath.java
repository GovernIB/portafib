
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TipusNotificacioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TipusNotificacioQueryPath() {
  }

  protected TipusNotificacioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField TIPUSNOTIFICACIOID() {
    return new LongField(getQueryPath(), TipusNotificacioFields.TIPUSNOTIFICACIOID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), TipusNotificacioFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), TipusNotificacioFields.DESCRIPCIO);
  }

  public BooleanField ESAVIS() {
    return new BooleanField(getQueryPath(), TipusNotificacioFields.ESAVIS);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TipusNotificacioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public NotificacioWSQueryPath NOTIFICACIOWSS() {
    return new NotificacioWSQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TipusNotificacioQueryPath.this.getQueryPath() + "notificacioWSs" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public RebreAvisQueryPath REBREAVISS() {
    return new RebreAvisQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TipusNotificacioQueryPath.this.getQueryPath() + "rebreAviss" + ".";
      }
    });
  }
*/

}
