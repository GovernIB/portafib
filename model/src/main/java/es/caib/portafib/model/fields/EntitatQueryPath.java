
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EntitatQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EntitatQueryPath() {
  }

  protected EntitatQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public StringField ENTITATID() {
    return new StringField(getQueryPath(), EntitatFields.ENTITATID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), EntitatFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), EntitatFields.DESCRIPCIO);
  }

  public BooleanField ACTIVA() {
    return new BooleanField(getQueryPath(), EntitatFields.ACTIVA);
  }

  public StringField WEB() {
    return new StringField(getQueryPath(), EntitatFields.WEB);
  }

  public LongField FAVICONID() {
    return new LongField(getQueryPath(), EntitatFields.FAVICONID);
  }

  public LongField LOGOWEBID() {
    return new LongField(getQueryPath(), EntitatFields.LOGOWEBID);
  }

  public LongField LOGOWEBPEUID() {
    return new LongField(getQueryPath(), EntitatFields.LOGOWEBPEUID);
  }

  public LongField LOGOSEGELLID() {
    return new LongField(getQueryPath(), EntitatFields.LOGOSEGELLID);
  }

  public StringField ADREZAHTML() {
    return new StringField(getQueryPath(), EntitatFields.ADREZAHTML);
  }

  public StringField FILTRECERTIFICATS() {
    return new StringField(getQueryPath(), EntitatFields.FILTRECERTIFICATS);
  }

  public LongField PDFAUTORITZACIODELEGACIOID() {
    return new LongField(getQueryPath(), EntitatFields.PDFAUTORITZACIODELEGACIOID);
  }

  public StringField SUPORTTELEFON() {
    return new StringField(getQueryPath(), EntitatFields.SUPORTTELEFON);
  }

  public StringField SUPORTWEB() {
    return new StringField(getQueryPath(), EntitatFields.SUPORTWEB);
  }

  public StringField SUPORTEMAIL() {
    return new StringField(getQueryPath(), EntitatFields.SUPORTEMAIL);
  }

  public StringField USUARIAPLICACIOID() {
    return new StringField(getQueryPath(), EntitatFields.USUARIAPLICACIOID);
  }

  public LongField MAXUPLOADSIZE() {
    return new LongField(getQueryPath(), EntitatFields.MAXUPLOADSIZE);
  }

  public LongField MAXSIZEFITXERADAPTAT() {
    return new LongField(getQueryPath(), EntitatFields.MAXSIZEFITXERADAPTAT);
  }

  public IntegerField MAXFILESTOSIGNATSAMETIME() {
    return new IntegerField(getQueryPath(), EntitatFields.MAXFILESTOSIGNATSAMETIME);
  }

  public IntegerField USPOLITICADEFIRMA() {
    return new IntegerField(getQueryPath(), EntitatFields.USPOLITICADEFIRMA);
  }

  public StringField POLICYIDENTIFIER() {
    return new StringField(getQueryPath(), EntitatFields.POLICYIDENTIFIER);
  }

  public StringField POLICYIDENTIFIERHASH() {
    return new StringField(getQueryPath(), EntitatFields.POLICYIDENTIFIERHASH);
  }

  public StringField POLICYIDENTIFIERHASHALGORITHM() {
    return new StringField(getQueryPath(), EntitatFields.POLICYIDENTIFIERHASHALGORITHM);
  }

  public StringField POLICYURLDOCUMENT() {
    return new StringField(getQueryPath(), EntitatFields.POLICYURLDOCUMENT);
  }

  public LongField MOTIUDELEGACIOID() {
    return new LongField(getQueryPath(), EntitatFields.MOTIUDELEGACIOID);
  }

  public LongField FIRMATPERFORMATID() {
    return new LongField(getQueryPath(), EntitatFields.FIRMATPERFORMATID);
  }

  public IntegerField ALGORISMEDEFIRMAID() {
    return new IntegerField(getQueryPath(), EntitatFields.ALGORISMEDEFIRMAID);
  }

  public IntegerField POLITICACUSTODIA() {
    return new IntegerField(getQueryPath(), EntitatFields.POLITICACUSTODIA);
  }

  public LongField CUSTODIAINFOID() {
    return new LongField(getQueryPath(), EntitatFields.CUSTODIAINFOID);
  }

  public IntegerField POLITICATAULAFIRMES() {
    return new IntegerField(getQueryPath(), EntitatFields.POLITICATAULAFIRMES);
  }

  public IntegerField POSICIOTAULAFIRMES() {
    return new IntegerField(getQueryPath(), EntitatFields.POSICIOTAULAFIRMES);
  }

  public StringField PROPIETATSTAULAFIRMES() {
    return new StringField(getQueryPath(), EntitatFields.PROPIETATSTAULAFIRMES);
  }

  public IntegerField POLITICASEGELLATDETEMPS() {
    return new IntegerField(getQueryPath(), EntitatFields.POLITICASEGELLATDETEMPS);
  }

  public LongField PLUGINID() {
    return new LongField(getQueryPath(), EntitatFields.PLUGINID);
  }

  public LongField PLUGINRUBRICAID() {
    return new LongField(getQueryPath(), EntitatFields.PLUGINRUBRICAID);
  }

  public BooleanField COMPROVARNIFFIRMA() {
    return new BooleanField(getQueryPath(), EntitatFields.COMPROVARNIFFIRMA);
  }

  public BooleanField CHECKCANVIATDOCFIRMAT() {
    return new BooleanField(getQueryPath(), EntitatFields.CHECKCANVIATDOCFIRMAT);
  }

  public LongField PLUGINVALIDAFIRMESID() {
    return new LongField(getQueryPath(), EntitatFields.PLUGINVALIDAFIRMESID);
  }

  public LongField PLUGINVALIDACERTIFICATID() {
    return new LongField(getQueryPath(), EntitatFields.PLUGINVALIDACERTIFICATID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EntitatFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public CustodiaInfoQueryPath CUSTODIAINFOS() {
    return new CustodiaInfoQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "custodiaInfos" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EstadisticaQueryPath ESTADISTICAS() {
    return new EstadisticaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "estadisticas" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public GrupEntitatQueryPath GRUPENTITATS() {
    return new GrupEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "grupEntitats" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PluginQueryPath PLUGINS() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "plugins" + ".";
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
          return EntitatQueryPath.this.getQueryPath() + "pluginCridadas" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PropietatGlobalQueryPath PROPIETATGLOBALS() {
    return new PropietatGlobalQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "propietatGlobals" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public UsuariAplicacioQueryPath USUARIAPLICACIOS() {
    return new UsuariAplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "usuariAplicacios" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public UsuariEntitatQueryPath USUARIENTITATS() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "usuariEntitats" + ".";
      }
    });
  }
*/

  public FitxerQueryPath FAVICON() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "favicon" + ".";
      }
    });
  }

  public FitxerQueryPath LOGOWEB() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "logoWeb" + ".";
      }
    });
  }

  public FitxerQueryPath LOGOWEBPEU() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "logoWebPeu" + ".";
      }
    });
  }

  public FitxerQueryPath LOGOSEGELL() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "logoSegell" + ".";
      }
    });
  }

  public FitxerQueryPath PDFAUTORITZACIODELEGACIO() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "pdfAutoritzacioDelegacio" + ".";
      }
    });
  }

  public UsuariAplicacioQueryPath USUARIAPLICACIO() {
    return new UsuariAplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "usuariAplicacio" + ".";
      }
    });
  }

  public TraduccioQueryPath MOTIUDELEGACIO() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "motiuDelegacio" + ".";
      }
    });
  }

  public TraduccioQueryPath FIRMATPERFORMAT() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "firmatPerFormat" + ".";
      }
    });
  }

  public CustodiaInfoQueryPath CUSTODIAINFO() {
    return new CustodiaInfoQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "custodiaInfo" + ".";
      }
    });
  }

  public PluginQueryPath PLUGIN() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "plugin" + ".";
      }
    });
  }

  public PluginQueryPath PLUGINRUBRICA() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "pluginRubrica" + ".";
      }
    });
  }

  public PluginQueryPath PLUGINVALIDAFIRMES() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "pluginValidaFirmes" + ".";
      }
    });
  }

  public PluginQueryPath PLUGINVALIDACERTIFICAT() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "pluginValidaCertificat" + ".";
      }
    });
  }

}
