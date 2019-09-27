
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class BitacolaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public BitacolaQueryPath() {
  }

  protected BitacolaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField BITACOLAID() {
    return new LongField(getQueryPath(), BitacolaFields.BITACOLAID);
  }

  public StringField ENTITATID() {
    return new StringField(getQueryPath(), BitacolaFields.ENTITATID);
  }

  public StringField USUARIID() {
    return new StringField(getQueryPath(), BitacolaFields.USUARIID);
  }

  public TimestampField DATA() {
    return new TimestampField(getQueryPath(), BitacolaFields.DATA);
  }

  public IntegerField TIPUSOBJECTE() {
    return new IntegerField(getQueryPath(), BitacolaFields.TIPUSOBJECTE);
  }

  public StringField OBJECTEID() {
    return new StringField(getQueryPath(), BitacolaFields.OBJECTEID);
  }

  public IntegerField TIPUSOPERACIO() {
    return new IntegerField(getQueryPath(), BitacolaFields.TIPUSOPERACIO);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), BitacolaFields.DESCRIPCIO);
  }

  public StringField OBJECTESERIALITZAT() {
    return new StringField(getQueryPath(), BitacolaFields.OBJECTESERIALITZAT);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (BitacolaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


}
