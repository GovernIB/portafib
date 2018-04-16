
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PluginCridadaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PluginCridadaQueryPath() {
  }

  protected PluginCridadaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PLUGINCRIDADAID() {
    return new LongField(getQueryPath(), PluginCridadaFields.PLUGINCRIDADAID);
  }

  public StringField ENTITATID() {
    return new StringField(getQueryPath(), PluginCridadaFields.ENTITATID);
  }

  public TimestampField DATA() {
    return new TimestampField(getQueryPath(), PluginCridadaFields.DATA);
  }

  public IntegerField TIPUSPLUGIN() {
    return new IntegerField(getQueryPath(), PluginCridadaFields.TIPUSPLUGIN);
  }

  public StringField DADESPLUGIN() {
    return new StringField(getQueryPath(), PluginCridadaFields.DADESPLUGIN);
  }

  public StringField METODEPLUGIN() {
    return new StringField(getQueryPath(), PluginCridadaFields.METODEPLUGIN);
  }

  public StringField DADESCRIDADA() {
    return new StringField(getQueryPath(), PluginCridadaFields.DADESCRIDADA);
  }

  public IntegerField TIPUSTESULTAT() {
    return new IntegerField(getQueryPath(), PluginCridadaFields.TIPUSTESULTAT);
  }

  public StringField RESULTAT() {
    return new StringField(getQueryPath(), PluginCridadaFields.RESULTAT);
  }

  public LongField TEMPSEXECUCIO() {
    return new LongField(getQueryPath(), PluginCridadaFields.TEMPSEXECUCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PluginCridadaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginCridadaQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

}
