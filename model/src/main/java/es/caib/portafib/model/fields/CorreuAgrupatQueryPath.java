
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class CorreuAgrupatQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public CorreuAgrupatQueryPath() {
  }

  protected CorreuAgrupatQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField CORREUAGRUPATID() {
    return new LongField(getQueryPath(), CorreuAgrupatFields.CORREUAGRUPATID);
  }

  public StringField EMAIL() {
    return new StringField(getQueryPath(), CorreuAgrupatFields.EMAIL);
  }

  public StringField SUBJECT() {
    return new StringField(getQueryPath(), CorreuAgrupatFields.SUBJECT);
  }

  public StringField MESSAGE() {
    return new StringField(getQueryPath(), CorreuAgrupatFields.MESSAGE);
  }

  public BooleanField HTML() {
    return new BooleanField(getQueryPath(), CorreuAgrupatFields.HTML);
  }

  public StringField USUARIENTITATID() {
    return new StringField(getQueryPath(), CorreuAgrupatFields.USUARIENTITATID);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), CorreuAgrupatFields.DATACREACIO);
  }

  public StringField ERROR() {
    return new StringField(getQueryPath(), CorreuAgrupatFields.ERROR);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (CorreuAgrupatFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public UsuariEntitatQueryPath USUARIENTITAT() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return CorreuAgrupatQueryPath.this.getQueryPath() + "usuariEntitat" + ".";
      }
    });
  }

}
