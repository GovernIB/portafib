
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PerfilsPerUsuariAplicacioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PerfilsPerUsuariAplicacioQueryPath() {
  }

  protected PerfilsPerUsuariAplicacioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PERFILSPERUSRAPPID() {
    return new LongField(getQueryPath(), PerfilsPerUsuariAplicacioFields.PERFILSPERUSRAPPID);
  }

  public LongField PERFILDEFIRMAID() {
    return new LongField(getQueryPath(), PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID);
  }

  public StringField USUARIAPLICACIOID() {
    return new StringField(getQueryPath(), PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PerfilsPerUsuariAplicacioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public PerfilDeFirmaQueryPath PERFILDEFIRMA() {
    return new PerfilDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PerfilsPerUsuariAplicacioQueryPath.this.getQueryPath() + "perfilDeFirma" + ".";
      }
    });
  }

  public UsuariAplicacioQueryPath USUARIAPLICACIO() {
    return new UsuariAplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PerfilsPerUsuariAplicacioQueryPath.this.getQueryPath() + "usuariAplicacio" + ".";
      }
    });
  }

}
