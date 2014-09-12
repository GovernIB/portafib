
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class NotificacioWSQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public NotificacioWSQueryPath() {
  }

  protected NotificacioWSQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField NOTIFICACIOID() {
    return new LongField(getQueryPath(), NotificacioWSFields.NOTIFICACIOID);
  }

  public LongField PETICIODEFIRMAID() {
    return new LongField(getQueryPath(), NotificacioWSFields.PETICIODEFIRMAID);
  }

  public LongField TIPUSNOTIFICACIOID() {
    return new LongField(getQueryPath(), NotificacioWSFields.TIPUSNOTIFICACIOID);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), NotificacioWSFields.DATACREACIO);
  }

  public TimestampField DATAENVIAMENT() {
    return new TimestampField(getQueryPath(), NotificacioWSFields.DATAENVIAMENT);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), NotificacioWSFields.DESCRIPCIO);
  }

  public BooleanField BLOQUEJADA() {
    return new BooleanField(getQueryPath(), NotificacioWSFields.BLOQUEJADA);
  }

  public StringField ERROR() {
    return new StringField(getQueryPath(), NotificacioWSFields.ERROR);
  }

  public TimestampField DATAERROR() {
    return new TimestampField(getQueryPath(), NotificacioWSFields.DATAERROR);
  }

  public IntegerField REINTENTS() {
    return new IntegerField(getQueryPath(), NotificacioWSFields.REINTENTS);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (NotificacioWSFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public PeticioDeFirmaQueryPath PETICIODEFIRMA() {
    return new PeticioDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return NotificacioWSQueryPath.this.getQueryPath() + "peticioDeFirma" + ".";
      }
    });
  }

  public TipusNotificacioQueryPath TIPUSNOTIFICACIO() {
    return new TipusNotificacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return NotificacioWSQueryPath.this.getQueryPath() + "tipusNotificacio" + ".";
      }
    });
  }

}
