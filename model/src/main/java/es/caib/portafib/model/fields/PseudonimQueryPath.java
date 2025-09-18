
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PseudonimQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PseudonimQueryPath() {
  }

  protected PseudonimQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PSEUDONIMID() {
    return new LongField(getQueryPath(), PseudonimFields.PSEUDONIMID);
  }

  public StringField PSEUDONIM() {
    return new StringField(getQueryPath(), PseudonimFields.PSEUDONIM);
  }

  public StringField NIF() {
    return new StringField(getQueryPath(), PseudonimFields.NIF);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PseudonimFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


}
