
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PerfilDeFirmaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PerfilDeFirmaQueryPath() {
  }

  protected PerfilDeFirmaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField USUARIAPLICACIOPERFILID() {
    return new LongField(getQueryPath(), PerfilDeFirmaFields.USUARIAPLICACIOPERFILID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), PerfilDeFirmaFields.NOM);
  }

  public StringField CODI() {
    return new StringField(getQueryPath(), PerfilDeFirmaFields.CODI);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), PerfilDeFirmaFields.DESCRIPCIO);
  }

  public StringField CONDICIO() {
    return new StringField(getQueryPath(), PerfilDeFirmaFields.CONDICIO);
  }

  public LongField CONFIGURACIODEFIRMA1ID() {
    return new LongField(getQueryPath(), PerfilDeFirmaFields.CONFIGURACIODEFIRMA1ID);
  }

  public LongField CONFIGURACIODEFIRMA2ID() {
    return new LongField(getQueryPath(), PerfilDeFirmaFields.CONFIGURACIODEFIRMA2ID);
  }

  public LongField CONFIGURACIODEFIRMA3ID() {
    return new LongField(getQueryPath(), PerfilDeFirmaFields.CONFIGURACIODEFIRMA3ID);
  }

  public LongField CONFIGURACIODEFIRMA4ID() {
    return new LongField(getQueryPath(), PerfilDeFirmaFields.CONFIGURACIODEFIRMA4ID);
  }

  public LongField CONFIGURACIODEFIRMA5ID() {
    return new LongField(getQueryPath(), PerfilDeFirmaFields.CONFIGURACIODEFIRMA5ID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PerfilDeFirmaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PerfilsPerUsuariAplicacioQueryPath PERFILSPERUSUARIAPLICACIOS() {
    return new PerfilsPerUsuariAplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PerfilDeFirmaQueryPath.this.getQueryPath() + "perfilsPerUsuariAplicacios" + ".";
      }
    });
  }
*/

  public UsuariAplicacioConfiguracioQueryPath CONFIGURACIODEFIRMA1() {
    return new UsuariAplicacioConfiguracioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PerfilDeFirmaQueryPath.this.getQueryPath() + "configuracioDeFirma1" + ".";
      }
    });
  }

  public UsuariAplicacioConfiguracioQueryPath CONFIGURACIODEFIRMA2() {
    return new UsuariAplicacioConfiguracioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PerfilDeFirmaQueryPath.this.getQueryPath() + "configuracioDeFirma2" + ".";
      }
    });
  }

  public UsuariAplicacioConfiguracioQueryPath CONFIGURACIODEFIRMA3() {
    return new UsuariAplicacioConfiguracioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PerfilDeFirmaQueryPath.this.getQueryPath() + "configuracioDeFirma3" + ".";
      }
    });
  }

  public UsuariAplicacioConfiguracioQueryPath CONFIGURACIODEFIRMA4() {
    return new UsuariAplicacioConfiguracioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PerfilDeFirmaQueryPath.this.getQueryPath() + "configuracioDeFirma4" + ".";
      }
    });
  }

  public UsuariAplicacioConfiguracioQueryPath CONFIGURACIODEFIRMA5() {
    return new UsuariAplicacioConfiguracioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PerfilDeFirmaQueryPath.this.getQueryPath() + "configuracioDeFirma5" + ".";
      }
    });
  }

}
