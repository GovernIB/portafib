
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

  public TimestampField DATA() {
    return new TimestampField(getQueryPath(), BitacolaFields.DATA);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), BitacolaFields.DESCRIPCIO);
  }

  public LongField PETICIODEFIRMAID() {
    return new LongField(getQueryPath(), BitacolaFields.PETICIODEFIRMAID);
  }

  public StringField USUARIENTITATID() {
    return new StringField(getQueryPath(), BitacolaFields.USUARIENTITATID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (BitacolaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public PeticioDeFirmaQueryPath PETICIODEFIRMA() {
    return new PeticioDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return BitacolaQueryPath.this.getQueryPath() + "peticioDeFirma" + ".";
      }
    });
  }

  public UsuariEntitatQueryPath USUARIENTITAT() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return BitacolaQueryPath.this.getQueryPath() + "usuariEntitat" + ".";
      }
    });
  }

}
