
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class CustodiaInfoQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public CustodiaInfoQueryPath() {
  }

  protected CustodiaInfoQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField CUSTODIAINFOID() {
    return new LongField(getQueryPath(), CustodiaInfoFields.CUSTODIAINFOID);
  }

  public StringField NOMPLANTILLA() {
    return new StringField(getQueryPath(), CustodiaInfoFields.NOMPLANTILLA);
  }

  public StringField CUSTODIADOCUMENTID() {
    return new StringField(getQueryPath(), CustodiaInfoFields.CUSTODIADOCUMENTID);
  }

  public LongField PLUGINID() {
    return new LongField(getQueryPath(), CustodiaInfoFields.PLUGINID);
  }

  public StringField CUSTODIAPLUGINPARAMETERS() {
    return new StringField(getQueryPath(), CustodiaInfoFields.CUSTODIAPLUGINPARAMETERS);
  }

  public BooleanField CUSTODIAR() {
    return new BooleanField(getQueryPath(), CustodiaInfoFields.CUSTODIAR);
  }

  public StringField URLFITXERCUSTODIAT() {
    return new StringField(getQueryPath(), CustodiaInfoFields.URLFITXERCUSTODIAT);
  }

  public StringField PAGINES() {
    return new StringField(getQueryPath(), CustodiaInfoFields.PAGINES);
  }

  public StringField MISSATGE() {
    return new StringField(getQueryPath(), CustodiaInfoFields.MISSATGE);
  }

  public LongField MISSATGEPOSICIOPAGINAID() {
    return new LongField(getQueryPath(), CustodiaInfoFields.MISSATGEPOSICIOPAGINAID);
  }

  public StringField CODIBARRESID() {
    return new StringField(getQueryPath(), CustodiaInfoFields.CODIBARRESID);
  }

  public LongField CODIBARRESPOSICIOPAGINAID() {
    return new LongField(getQueryPath(), CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID);
  }

  public StringField CODIBARRESTEXT() {
    return new StringField(getQueryPath(), CustodiaInfoFields.CODIBARRESTEXT);
  }

  public StringField USUARIENTITATID() {
    return new StringField(getQueryPath(), CustodiaInfoFields.USUARIENTITATID);
  }

  public StringField USUARIAPLICACIOID() {
    return new StringField(getQueryPath(), CustodiaInfoFields.USUARIAPLICACIOID);
  }

  public StringField ENTITATID() {
    return new StringField(getQueryPath(), CustodiaInfoFields.ENTITATID);
  }

  public StringField TITOLPETICIO() {
    return new StringField(getQueryPath(), CustodiaInfoFields.TITOLPETICIO);
  }

  public TimestampField DATACUSTODIA() {
    return new TimestampField(getQueryPath(), CustodiaInfoFields.DATACUSTODIA);
  }

  public BooleanField EDITABLE() {
    return new BooleanField(getQueryPath(), CustodiaInfoFields.EDITABLE);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (CustodiaInfoFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntitatQueryPath ENTITATS() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return CustodiaInfoQueryPath.this.getQueryPath() + "entitats" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PeticioDeFirmaQueryPath PETICIODEFIRMAS() {
    return new PeticioDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return CustodiaInfoQueryPath.this.getQueryPath() + "peticioDeFirmas" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public UsuariAplicacioConfiguracioQueryPath USUARIAPLICACIOCONFIGURACIOS() {
    return new UsuariAplicacioConfiguracioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return CustodiaInfoQueryPath.this.getQueryPath() + "usuariAplicacioConfiguracios" + ".";
      }
    });
  }
*/

  public PluginQueryPath PLUGIN() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return CustodiaInfoQueryPath.this.getQueryPath() + "plugin" + ".";
      }
    });
  }

  public CodiBarresQueryPath CODIBARRES() {
    return new CodiBarresQueryPath(new QueryPath() {
      public String getQueryPath() {
          return CustodiaInfoQueryPath.this.getQueryPath() + "codiBarres" + ".";
      }
    });
  }

  public UsuariEntitatQueryPath USUARIENTITAT() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return CustodiaInfoQueryPath.this.getQueryPath() + "usuariEntitat" + ".";
      }
    });
  }

  public UsuariAplicacioQueryPath USUARIAPLICACIO() {
    return new UsuariAplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return CustodiaInfoQueryPath.this.getQueryPath() + "usuariAplicacio" + ".";
      }
    });
  }

  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return CustodiaInfoQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

}
