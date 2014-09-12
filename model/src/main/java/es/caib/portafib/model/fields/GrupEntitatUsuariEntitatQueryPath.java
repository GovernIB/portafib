
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class GrupEntitatUsuariEntitatQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public GrupEntitatUsuariEntitatQueryPath() {
  }

  protected GrupEntitatUsuariEntitatQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField GRUPENTITATUSUARIENTITATID() {
    return new LongField(getQueryPath(), GrupEntitatUsuariEntitatFields.GRUPENTITATUSUARIENTITATID);
  }

  public StringField USUARIENTITATID() {
    return new StringField(getQueryPath(), GrupEntitatUsuariEntitatFields.USUARIENTITATID);
  }

  public LongField GRUPENTITATID() {
    return new LongField(getQueryPath(), GrupEntitatUsuariEntitatFields.GRUPENTITATID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (GrupEntitatUsuariEntitatFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public UsuariEntitatQueryPath USUARIENTITAT() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return GrupEntitatUsuariEntitatQueryPath.this.getQueryPath() + "usuariEntitat" + ".";
      }
    });
  }

  public GrupEntitatQueryPath GRUPENTITAT() {
    return new GrupEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return GrupEntitatUsuariEntitatQueryPath.this.getQueryPath() + "grupEntitat" + ".";
      }
    });
  }

}
