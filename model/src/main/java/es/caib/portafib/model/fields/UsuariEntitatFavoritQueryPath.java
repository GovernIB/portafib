
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class UsuariEntitatFavoritQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public UsuariEntitatFavoritQueryPath() {
  }

  protected UsuariEntitatFavoritQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ID() {
    return new LongField(getQueryPath(), UsuariEntitatFavoritFields.ID);
  }

  public StringField ORIGENID() {
    return new StringField(getQueryPath(), UsuariEntitatFavoritFields.ORIGENID);
  }

  public StringField FAVORITID() {
    return new StringField(getQueryPath(), UsuariEntitatFavoritFields.FAVORITID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (UsuariEntitatFavoritFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public UsuariEntitatQueryPath ORIGEN() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatFavoritQueryPath.this.getQueryPath() + "origen" + ".";
      }
    });
  }

  public UsuariEntitatQueryPath FAVORIT() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatFavoritQueryPath.this.getQueryPath() + "favorit" + ".";
      }
    });
  }

}
