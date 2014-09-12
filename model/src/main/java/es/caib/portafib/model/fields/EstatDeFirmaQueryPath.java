
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EstatDeFirmaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EstatDeFirmaQueryPath() {
  }

  protected EstatDeFirmaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ESTATDEFIRMAID() {
    return new LongField(getQueryPath(), EstatDeFirmaFields.ESTATDEFIRMAID);
  }

  public LongField FIRMAID() {
    return new LongField(getQueryPath(), EstatDeFirmaFields.FIRMAID);
  }

  public StringField USUARIENTITATID() {
    return new StringField(getQueryPath(), EstatDeFirmaFields.USUARIENTITATID);
  }

  public TimestampField DATAINICI() {
    return new TimestampField(getQueryPath(), EstatDeFirmaFields.DATAINICI);
  }

  public TimestampField DATAFI() {
    return new TimestampField(getQueryPath(), EstatDeFirmaFields.DATAFI);
  }

  public LongField TIPUSESTATDEFIRMAINICIALID() {
    return new LongField(getQueryPath(), EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID);
  }

  public LongField TIPUSESTATDEFIRMAFINALID() {
    return new LongField(getQueryPath(), EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID);
  }

  public LongField COLABORACIODELEGACIOID() {
    return new LongField(getQueryPath(), EstatDeFirmaFields.COLABORACIODELEGACIOID);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), EstatDeFirmaFields.DESCRIPCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EstatDeFirmaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public FirmaQueryPath FIRMA() {
    return new FirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EstatDeFirmaQueryPath.this.getQueryPath() + "firma" + ".";
      }
    });
  }

  public UsuariEntitatQueryPath USUARIENTITAT() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EstatDeFirmaQueryPath.this.getQueryPath() + "usuariEntitat" + ".";
      }
    });
  }

  public TipusEstatDeFirmaInicialQueryPath TIPUSESTATDEFIRMAINICIAL() {
    return new TipusEstatDeFirmaInicialQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EstatDeFirmaQueryPath.this.getQueryPath() + "tipusEstatDeFirmaInicial" + ".";
      }
    });
  }

  public TipusEstatDeFirmaFinalQueryPath TIPUSESTATDEFIRMAFINAL() {
    return new TipusEstatDeFirmaFinalQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EstatDeFirmaQueryPath.this.getQueryPath() + "tipusEstatDeFirmaFinal" + ".";
      }
    });
  }

  public ColaboracioDelegacioQueryPath COLABORACIODELEGACIO() {
    return new ColaboracioDelegacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EstatDeFirmaQueryPath.this.getQueryPath() + "colaboracioDelegacio" + ".";
      }
    });
  }

}
