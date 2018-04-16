
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PluginFirmaWebPerUsuariAplicacioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PluginFirmaWebPerUsuariAplicacioQueryPath() {
  }

  protected PluginFirmaWebPerUsuariAplicacioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PLUGINFIRMAWEBPERUSRAPPID() {
    return new LongField(getQueryPath(), PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBPERUSRAPPID);
  }

  public StringField USUARIAPLICACIOID() {
    return new StringField(getQueryPath(), PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID);
  }

  public LongField PLUGINFIRMAWEBID() {
    return new LongField(getQueryPath(), PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID);
  }

  public IntegerField ACCIO() {
    return new IntegerField(getQueryPath(), PluginFirmaWebPerUsuariAplicacioFields.ACCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PluginFirmaWebPerUsuariAplicacioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public UsuariAplicacioQueryPath USUARIAPLICACIO() {
    return new UsuariAplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginFirmaWebPerUsuariAplicacioQueryPath.this.getQueryPath() + "usuariAplicacio" + ".";
      }
    });
  }

  public PluginQueryPath PLUGIN() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginFirmaWebPerUsuariAplicacioQueryPath.this.getQueryPath() + "plugin" + ".";
      }
    });
  }

}
