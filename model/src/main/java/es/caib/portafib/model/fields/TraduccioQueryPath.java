
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TraduccioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TraduccioQueryPath() {
  }

  protected TraduccioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField TRADUCCIOID() {
    return new LongField(getQueryPath(), TraduccioFields.TRADUCCIOID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TraduccioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntitatQueryPath ENTITAT_FIRMATPERFORMATIDS() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TraduccioQueryPath.this.getQueryPath() + "entitat_firmatperformatids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntitatQueryPath ENTITAT_MOTIUDELEGACIOIDS() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TraduccioQueryPath.this.getQueryPath() + "entitat_motiudelegacioids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PluginQueryPath PLUGIN_DESCRIPCIOCURTAIDS() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TraduccioQueryPath.this.getQueryPath() + "plugin_descripciocurtaids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PluginQueryPath PLUGIN_NOMIDS() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TraduccioQueryPath.this.getQueryPath() + "plugin_nomids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public TipusDocumentQueryPath TIPUSDOCUMENTS() {
    return new TipusDocumentQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TraduccioQueryPath.this.getQueryPath() + "tipusDocuments" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public UsuariAplicacioConfiguracioQueryPath USUARIAPLICACIOCONFIGURACIO_FIRMATPERFORMATIDS() {
    return new UsuariAplicacioConfiguracioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TraduccioQueryPath.this.getQueryPath() + "usuariAplicacioConfiguracio_firmatperformatids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public UsuariAplicacioConfiguracioQueryPath USUARIAPLICACIOCONFIGURACIO_MOTIUDELEGACIOIDS() {
    return new UsuariAplicacioConfiguracioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TraduccioQueryPath.this.getQueryPath() + "usuariAplicacioConfiguracio_motiudelegacioids" + ".";
      }
    });
  }
*/

}
