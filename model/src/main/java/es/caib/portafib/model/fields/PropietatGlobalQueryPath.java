
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PropietatGlobalQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PropietatGlobalQueryPath() {
  }

  protected PropietatGlobalQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PROPIETATGLOBALID() {
    return new LongField(getQueryPath(), PropietatGlobalFields.PROPIETATGLOBALID);
  }

  public StringField CLAU() {
    return new StringField(getQueryPath(), PropietatGlobalFields.CLAU);
  }

  public StringField VALOR() {
    return new StringField(getQueryPath(), PropietatGlobalFields.VALOR);
  }

  public StringField ENTITATID() {
    return new StringField(getQueryPath(), PropietatGlobalFields.ENTITATID);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), PropietatGlobalFields.DESCRIPCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PropietatGlobalFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PropietatGlobalQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

}
