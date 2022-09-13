
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PluginQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PluginQueryPath() {
  }

  protected PluginQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PLUGINID() {
    return new LongField(getQueryPath(), PluginFields.PLUGINID);
  }

  public StringField CODI() {
    return new StringField(getQueryPath(), PluginFields.CODI);
  }

  public LongField NOMID() {
    return new LongField(getQueryPath(), PluginFields.NOMID);
  }

  public LongField DESCRIPCIOCURTAID() {
    return new LongField(getQueryPath(), PluginFields.DESCRIPCIOCURTAID);
  }

  public StringField CLASSE() {
    return new StringField(getQueryPath(), PluginFields.CLASSE);
  }

  public IntegerField ORDRE() {
    return new IntegerField(getQueryPath(), PluginFields.ORDRE);
  }

  public IntegerField TIPUS() {
    return new IntegerField(getQueryPath(), PluginFields.TIPUS);
  }

  public StringField PROPERTIESADMIN() {
    return new StringField(getQueryPath(), PluginFields.PROPERTIESADMIN);
  }

  public StringField PROPERTIESENTITAT() {
    return new StringField(getQueryPath(), PluginFields.PROPERTIESENTITAT);
  }

  public IntegerField POLITICADEUS() {
    return new IntegerField(getQueryPath(), PluginFields.POLITICADEUS);
  }

  public StringField ENTITATID() {
    return new StringField(getQueryPath(), PluginFields.ENTITATID);
  }

  public BooleanField ACTIU() {
    return new BooleanField(getQueryPath(), PluginFields.ACTIU);
  }

  public IntegerField POLITICAMOSTRARPROPIETATS() {
    return new IntegerField(getQueryPath(), PluginFields.POLITICAMOSTRARPROPIETATS);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PluginFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public CustodiaInfoQueryPath CUSTODIAINFOS() {
    return new CustodiaInfoQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "custodiaInfos" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntitatQueryPath ENTITAT_PLUGINVALIDACERTIFICATIDS() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "entitat_pluginvalidacertificatids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntitatQueryPath ENTITAT_PLUGINIDS() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "entitat_pluginids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntitatQueryPath ENTITAT_PLUGINRUBRICAIDS() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "entitat_pluginrubricaids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntitatQueryPath ENTITAT_PLUGINVALIDAFIRMESIDS() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "entitat_pluginvalidafirmesids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public ModulDeFirmaPerTipusDeDocumentQueryPath MODULDEFIRMAPERTIPUSDEDOCUMENTS() {
    return new ModulDeFirmaPerTipusDeDocumentQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "modulDeFirmaPerTipusDeDocuments" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PluginCridadaQueryPath PLUGINCRIDADAS() {
    return new PluginCridadaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "pluginCridadas" + ".";
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
          return PluginQueryPath.this.getQueryPath() + "pluginFirmaWebPerUsuariAplicacios" + ".";
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
          return PluginQueryPath.this.getQueryPath() + "pluginFirmaWebPerUsuariEntitats" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public UsuariAplicacioConfiguracioQueryPath USUARIAPLICACIOCONFIGURACIO_PLUGINFIRMASERVIDORIDS() {
    return new UsuariAplicacioConfiguracioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "usuariAplicacioConfiguracio_pluginfirmaservidorids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public UsuariAplicacioConfiguracioQueryPath USUARIAPLICACIOCONFIGURACIO_PLUGINSEGELLATIDS() {
    return new UsuariAplicacioConfiguracioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "usuariAplicacioConfiguracio_pluginsegellatids" + ".";
      }
    });
  }
*/

  public TraduccioQueryPath NOM() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "nom" + ".";
      }
    });
  }

  public TraduccioQueryPath DESCRIPCIOCURTA() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "descripcioCurta" + ".";
      }
    });
  }

  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

}
