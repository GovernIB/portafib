
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class ModulDeFirmaPerTipusDeDocumentQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public ModulDeFirmaPerTipusDeDocumentQueryPath() {
  }

  protected ModulDeFirmaPerTipusDeDocumentQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ID() {
    return new LongField(getQueryPath(), ModulDeFirmaPerTipusDeDocumentFields.ID);
  }

  public LongField TIPUSDOCUMENTID() {
    return new LongField(getQueryPath(), ModulDeFirmaPerTipusDeDocumentFields.TIPUSDOCUMENTID);
  }

  public LongField PLUGINID() {
    return new LongField(getQueryPath(), ModulDeFirmaPerTipusDeDocumentFields.PLUGINID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), ModulDeFirmaPerTipusDeDocumentFields.NOM);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (ModulDeFirmaPerTipusDeDocumentFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public TipusDocumentQueryPath TIPUSDOCUMENT() {
    return new TipusDocumentQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ModulDeFirmaPerTipusDeDocumentQueryPath.this.getQueryPath() + "tipusDocument" + ".";
      }
    });
  }

  public PluginQueryPath PLUGIN() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ModulDeFirmaPerTipusDeDocumentQueryPath.this.getQueryPath() + "plugin" + ".";
      }
    });
  }

}
