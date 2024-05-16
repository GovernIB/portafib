
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class RevisorDeDestinatariQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public RevisorDeDestinatariQueryPath() {
  }

  protected RevisorDeDestinatariQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField REVISORDEDESTINATARIID() {
    return new LongField(getQueryPath(), RevisorDeDestinatariFields.REVISORDEDESTINATARIID);
  }

  public StringField DESTINATARIID() {
    return new StringField(getQueryPath(), RevisorDeDestinatariFields.DESTINATARIID);
  }

  public StringField REVISORID() {
    return new StringField(getQueryPath(), RevisorDeDestinatariFields.REVISORID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (RevisorDeDestinatariFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public UsuariEntitatQueryPath DESTINATARI() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return RevisorDeDestinatariQueryPath.this.getQueryPath() + "destinatari" + ".";
      }
    });
  }

  public UsuariEntitatQueryPath REVISOR() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return RevisorDeDestinatariQueryPath.this.getQueryPath() + "revisor" + ".";
      }
    });
  }

}
