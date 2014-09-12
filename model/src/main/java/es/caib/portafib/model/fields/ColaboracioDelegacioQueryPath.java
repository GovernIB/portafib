
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class ColaboracioDelegacioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public ColaboracioDelegacioQueryPath() {
  }

  protected ColaboracioDelegacioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField COLABORACIODELEGACIOID() {
    return new LongField(getQueryPath(), ColaboracioDelegacioFields.COLABORACIODELEGACIOID);
  }

  public StringField DESTINATARIID() {
    return new StringField(getQueryPath(), ColaboracioDelegacioFields.DESTINATARIID);
  }

  public StringField COLABORADORDELEGATID() {
    return new StringField(getQueryPath(), ColaboracioDelegacioFields.COLABORADORDELEGATID);
  }

  public BooleanField ESDELEGAT() {
    return new BooleanField(getQueryPath(), ColaboracioDelegacioFields.ESDELEGAT);
  }

  public StringField MOTIU() {
    return new StringField(getQueryPath(), ColaboracioDelegacioFields.MOTIU);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), ColaboracioDelegacioFields.DESCRIPCIO);
  }

  public TimestampField DATAINICI() {
    return new TimestampField(getQueryPath(), ColaboracioDelegacioFields.DATAINICI);
  }

  public TimestampField DATAFI() {
    return new TimestampField(getQueryPath(), ColaboracioDelegacioFields.DATAFI);
  }

  public BooleanField ACTIVA() {
    return new BooleanField(getQueryPath(), ColaboracioDelegacioFields.ACTIVA);
  }

  public BooleanField REVISOR() {
    return new BooleanField(getQueryPath(), ColaboracioDelegacioFields.REVISOR);
  }

  public StringField MOTIUDESHABILITADA() {
    return new StringField(getQueryPath(), ColaboracioDelegacioFields.MOTIUDESHABILITADA);
  }

  public LongField FITXERAUTORITZACIOID() {
    return new LongField(getQueryPath(), ColaboracioDelegacioFields.FITXERAUTORITZACIOID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (ColaboracioDelegacioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EstatDeFirmaQueryPath ESTATDEFIRMAS() {
    return new EstatDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ColaboracioDelegacioQueryPath.this.getQueryPath() + "estatDeFirmas" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public TipusDocumentColaboracioDelegacioQueryPath TIPUSDOCUMENTCOLABORACIODELEGACIOS() {
    return new TipusDocumentColaboracioDelegacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ColaboracioDelegacioQueryPath.this.getQueryPath() + "tipusDocumentColaboracioDelegacios" + ".";
      }
    });
  }
*/

  public UsuariEntitatQueryPath DESTINATARI() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ColaboracioDelegacioQueryPath.this.getQueryPath() + "destinatari" + ".";
      }
    });
  }

  public UsuariEntitatQueryPath COLABORADORDELEGAT() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ColaboracioDelegacioQueryPath.this.getQueryPath() + "colaboradorDelegat" + ".";
      }
    });
  }

  public FitxerQueryPath FITXERAUTORITZACIO() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ColaboracioDelegacioQueryPath.this.getQueryPath() + "fitxerAutoritzacio" + ".";
      }
    });
  }

}
