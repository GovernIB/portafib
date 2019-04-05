
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class UsuariAplicacioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public UsuariAplicacioQueryPath() {
  }

  protected UsuariAplicacioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public StringField USUARIAPLICACIOID() {
    return new StringField(getQueryPath(), UsuariAplicacioFields.USUARIAPLICACIOID);
  }

  public StringField CONTRASENYA() {
    return new StringField(getQueryPath(), UsuariAplicacioFields.CONTRASENYA);
  }

  public StringField ENTITATID() {
    return new StringField(getQueryPath(), UsuariAplicacioFields.ENTITATID);
  }

  public StringField EMAILADMIN() {
    return new StringField(getQueryPath(), UsuariAplicacioFields.EMAILADMIN);
  }

  public IntegerField CALLBACKVERSIO() {
    return new IntegerField(getQueryPath(), UsuariAplicacioFields.CALLBACKVERSIO);
  }

  public StringField CALLBACKURL() {
    return new StringField(getQueryPath(), UsuariAplicacioFields.CALLBACKURL);
  }

  public BooleanField ACTIU() {
    return new BooleanField(getQueryPath(), UsuariAplicacioFields.ACTIU);
  }

  public StringField IDIOMAID() {
    return new StringField(getQueryPath(), UsuariAplicacioFields.IDIOMAID);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), UsuariAplicacioFields.DESCRIPCIO);
  }

  public LongField LOGOSEGELLID() {
    return new LongField(getQueryPath(), UsuariAplicacioFields.LOGOSEGELLID);
  }

  public IntegerField POLITICADEPLUGINFIRMAWEB() {
    return new IntegerField(getQueryPath(), UsuariAplicacioFields.POLITICADEPLUGINFIRMAWEB);
  }

  public IntegerField POLITICACUSTODIA() {
    return new IntegerField(getQueryPath(), UsuariAplicacioFields.POLITICACUSTODIA);
  }

  public LongField CUSTODIAINFOID() {
    return new LongField(getQueryPath(), UsuariAplicacioFields.CUSTODIAINFOID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (UsuariAplicacioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public CustodiaInfoQueryPath CUSTODIAINFOS() {
    return new CustodiaInfoQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioQueryPath.this.getQueryPath() + "custodiaInfos" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntitatQueryPath ENTITATS() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioQueryPath.this.getQueryPath() + "entitats" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PerfilsPerUsuariAplicacioQueryPath PERFILSPERUSUARIAPLICACIOS() {
    return new PerfilsPerUsuariAplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioQueryPath.this.getQueryPath() + "perfilsPerUsuariAplicacios" + ".";
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
          return UsuariAplicacioQueryPath.this.getQueryPath() + "peticioDeFirmas" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PlantillaFluxDeFirmesQueryPath PLANTILLAFLUXDEFIRMESS() {
    return new PlantillaFluxDeFirmesQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioQueryPath.this.getQueryPath() + "plantillaFluxDeFirmess" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PluginFirmaWebPerUsuariAplicacioQueryPath PLUGINFIRMAWEBPERUSUARIAPLICACIOS() {
    return new PluginFirmaWebPerUsuariAplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioQueryPath.this.getQueryPath() + "pluginFirmaWebPerUsuariAplicacios" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public RoleUsuariAplicacioQueryPath ROLEUSUARIAPLICACIOS() {
    return new RoleUsuariAplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioQueryPath.this.getQueryPath() + "roleUsuariAplicacios" + ".";
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
          return UsuariAplicacioQueryPath.this.getQueryPath() + "tipusDocuments" + ".";
      }
    });
  }
*/

  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

  public IdiomaQueryPath IDIOMA() {
    return new IdiomaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioQueryPath.this.getQueryPath() + "idioma" + ".";
      }
    });
  }

  public FitxerQueryPath LOGOSEGELL() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioQueryPath.this.getQueryPath() + "logoSegell" + ".";
      }
    });
  }

  public CustodiaInfoQueryPath CUSTODIAINFO() {
    return new CustodiaInfoQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioQueryPath.this.getQueryPath() + "custodiaInfo" + ".";
      }
    });
  }

}
