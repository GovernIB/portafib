
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class UsuariEntitatQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public UsuariEntitatQueryPath() {
  }

  protected UsuariEntitatQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public StringField USUARIENTITATID() {
    return new StringField(getQueryPath(), UsuariEntitatFields.USUARIENTITATID);
  }

  public StringField CARREC() {
    return new StringField(getQueryPath(), UsuariEntitatFields.CARREC);
  }

  public StringField USUARIPERSONAID() {
    return new StringField(getQueryPath(), UsuariEntitatFields.USUARIPERSONAID);
  }

  public StringField ENTITATID() {
    return new StringField(getQueryPath(), UsuariEntitatFields.ENTITATID);
  }

  public BooleanField ACTIU() {
    return new BooleanField(getQueryPath(), UsuariEntitatFields.ACTIU);
  }

  public StringField EMAIL() {
    return new StringField(getQueryPath(), UsuariEntitatFields.EMAIL);
  }

  public LongField LOGOSEGELLID() {
    return new LongField(getQueryPath(), UsuariEntitatFields.LOGOSEGELLID);
  }

  public BooleanField PREDETERMINAT() {
    return new BooleanField(getQueryPath(), UsuariEntitatFields.PREDETERMINAT);
  }

  public BooleanField REBRETOTSELSAVISOS() {
    return new BooleanField(getQueryPath(), UsuariEntitatFields.REBRETOTSELSAVISOS);
  }

  public IntegerField POLITICADEPLUGINFIRMAWEB() {
    return new IntegerField(getQueryPath(), UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB);
  }

  public IntegerField POLITICACUSTODIA() {
    return new IntegerField(getQueryPath(), UsuariEntitatFields.POLITICACUSTODIA);
  }

  public LongField CUSTODIAINFOID() {
    return new LongField(getQueryPath(), UsuariEntitatFields.CUSTODIAINFOID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (UsuariEntitatFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public ColaboracioDelegacioQueryPath COLABORACIODELEGACIO_COLABORADORDELEGATIDS() {
    return new ColaboracioDelegacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "colaboracioDelegacio_colaboradordelegatids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public ColaboracioDelegacioQueryPath COLABORACIODELEGACIO_DESTINATARIIDS() {
    return new ColaboracioDelegacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "colaboracioDelegacio_destinatariids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public CustodiaInfoQueryPath CUSTODIAINFOS() {
    return new CustodiaInfoQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "custodiaInfos" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EstatDeFirmaQueryPath ESTATDEFIRMAS() {
    return new EstatDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "estatDeFirmas" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public FirmaQueryPath FIRMAS() {
    return new FirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "firmas" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public GrupEntitatUsuariEntitatQueryPath GRUPENTITATUSUARIENTITATS() {
    return new GrupEntitatUsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "grupEntitatUsuariEntitats" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PermisUsuariPlantillaQueryPath PERMISUSUARIPLANTILLAS() {
    return new PermisUsuariPlantillaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "permisUsuariPlantillas" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PeticioDeFirmaQueryPath PETICIODEFIRMA_SOLICITANTPERSONA2IDS() {
    return new PeticioDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "peticioDeFirma_solicitantpersona2ids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PeticioDeFirmaQueryPath PETICIODEFIRMA_SOLICITANTPERSONA3IDS() {
    return new PeticioDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "peticioDeFirma_solicitantpersona3ids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PeticioDeFirmaQueryPath PETICIODEFIRMA_USUARIENTITATIDS() {
    return new PeticioDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "peticioDeFirma_usuarientitatids" + ".";
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
          return UsuariEntitatQueryPath.this.getQueryPath() + "plantillaFluxDeFirmess" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PluginFirmaWebPerUsuariEntitatQueryPath PLUGINFIRMAWEBPERUSUARIENTITATS() {
    return new PluginFirmaWebPerUsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "pluginFirmaWebPerUsuariEntitats" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public RebreAvisQueryPath REBREAVISS() {
    return new RebreAvisQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "rebreAviss" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public RevisorDeFirmaQueryPath REVISORDEFIRMAS() {
    return new RevisorDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "revisorDeFirmas" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public RoleUsuariEntitatQueryPath ROLEUSUARIENTITATS() {
    return new RoleUsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "roleUsuariEntitats" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public UsuariEntitatFavoritQueryPath USUARIENTITATFAVORIT_FAVORITIDS() {
    return new UsuariEntitatFavoritQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "usuariEntitatFavorit_favoritids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public UsuariEntitatFavoritQueryPath USUARIENTITATFAVORIT_ORIGENIDS() {
    return new UsuariEntitatFavoritQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "usuariEntitatFavorit_origenids" + ".";
      }
    });
  }
*/

  public UsuariPersonaQueryPath USUARIPERSONA() {
    return new UsuariPersonaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "usuariPersona" + ".";
      }
    });
  }

  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

  public FitxerQueryPath LOGOSEGELL() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "logoSegell" + ".";
      }
    });
  }

  public CustodiaInfoQueryPath CUSTODIAINFO() {
    return new CustodiaInfoQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "custodiaInfo" + ".";
      }
    });
  }

}
