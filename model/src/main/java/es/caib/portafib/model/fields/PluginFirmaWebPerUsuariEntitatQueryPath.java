
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PluginFirmaWebPerUsuariEntitatQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PluginFirmaWebPerUsuariEntitatQueryPath() {
  }

  protected PluginFirmaWebPerUsuariEntitatQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PLUGINFIRMAWEBPERUSRENTID() {
    return new LongField(getQueryPath(), PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBPERUSRENTID);
  }

  public StringField USUARIENTITATID() {
    return new StringField(getQueryPath(), PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID);
  }

  public LongField PLUGINFIRMAWEBID() {
    return new LongField(getQueryPath(), PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID);
  }

  public IntegerField ACCIO() {
    return new IntegerField(getQueryPath(), PluginFirmaWebPerUsuariEntitatFields.ACCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PluginFirmaWebPerUsuariEntitatFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public UsuariEntitatQueryPath USUARIENTITAT() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginFirmaWebPerUsuariEntitatQueryPath.this.getQueryPath() + "usuariEntitat" + ".";
      }
    });
  }

  public PluginQueryPath PLUGIN() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginFirmaWebPerUsuariEntitatQueryPath.this.getQueryPath() + "plugin" + ".";
      }
    });
  }

}
