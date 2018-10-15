
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

  public LongField PLUGINID() {
    return new LongField(getQueryPath(), PluginCridadaFields.PLUGINID);
  }

  public StringField METODEPLUGIN() {
    return new StringField(getQueryPath(), PluginCridadaFields.METODEPLUGIN);
  }

  public StringField PARAMETRESTEXT() {
    return new StringField(getQueryPath(), PluginCridadaFields.PARAMETRESTEXT);
  }

  public LongField PARAMETRESFITXERID() {
    return new LongField(getQueryPath(), PluginCridadaFields.PARAMETRESFITXERID);
  }

  public StringField RETORNTEXT() {
    return new StringField(getQueryPath(), PluginCridadaFields.RETORNTEXT);
  }

  public LongField RETORNFITXERID() {
    return new LongField(getQueryPath(), PluginCridadaFields.RETORNFITXERID);
  }

  public IntegerField TIPUSTESULTAT() {
    return new IntegerField(getQueryPath(), PluginCridadaFields.TIPUSTESULTAT);
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

  public PluginQueryPath PLUGIN() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginCridadaQueryPath.this.getQueryPath() + "plugin" + ".";
      }
    });
  }

  public FitxerQueryPath PARAMETRESFITXER() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginCridadaQueryPath.this.getQueryPath() + "parametresFitxer" + ".";
      }
    });
  }

  public FitxerQueryPath RETORNFITXER() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginCridadaQueryPath.this.getQueryPath() + "retornFitxer" + ".";
      }
    });
  }

}
