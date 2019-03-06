
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class UsuariAplicacioConfiguracioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public UsuariAplicacioConfiguracioQueryPath() {
  }

  protected UsuariAplicacioConfiguracioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField USUARIAPLICACIOCONFIGID() {
    return new LongField(getQueryPath(), UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), UsuariAplicacioConfiguracioFields.NOM);
  }

  public StringField ENTITATID() {
    return new StringField(getQueryPath(), UsuariAplicacioConfiguracioFields.ENTITATID);
  }

  public BooleanField USENFIRMAAPISIMPLESERVIDOR() {
    return new BooleanField(getQueryPath(), UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR);
  }

  public BooleanField USENFIRMAAPISIMPLEWEB() {
    return new BooleanField(getQueryPath(), UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB);
  }

  public BooleanField USENFIRMAWEB() {
    return new BooleanField(getQueryPath(), UsuariAplicacioConfiguracioFields.USENFIRMAWEB);
  }

  public BooleanField USENFIRMAWS2() {
    return new BooleanField(getQueryPath(), UsuariAplicacioConfiguracioFields.USENFIRMAWS2);
  }

  public BooleanField USENFIRMAPASSARELASERVIDOR() {
    return new BooleanField(getQueryPath(), UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR);
  }

  public BooleanField USENFIRMAPASSARELAWEB() {
    return new BooleanField(getQueryPath(), UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB);
  }

  public IntegerField USPOLITICADEFIRMA() {
    return new IntegerField(getQueryPath(), UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA);
  }

  public StringField POLICYIDENTIFIER() {
    return new StringField(getQueryPath(), UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER);
  }

  public StringField POLICYIDENTIFIERHASH() {
    return new StringField(getQueryPath(), UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH);
  }

  public StringField POLICYIDENTIFIERHASHALGORITHM() {
    return new StringField(getQueryPath(), UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM);
  }

  public StringField POLICYURLDOCUMENT() {
    return new StringField(getQueryPath(), UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT);
  }

  public StringField FILTRECERTIFICATS() {
    return new StringField(getQueryPath(), UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS);
  }

  public IntegerField TIPUSOPERACIOFIRMA() {
    return new IntegerField(getQueryPath(), UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA);
  }

  public IntegerField TIPUSFIRMAID() {
    return new IntegerField(getQueryPath(), UsuariAplicacioConfiguracioFields.TIPUSFIRMAID);
  }

  public IntegerField ALGORISMEDEFIRMAID() {
    return new IntegerField(getQueryPath(), UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID);
  }

  public BooleanField MODEDEFIRMA() {
    return new BooleanField(getQueryPath(), UsuariAplicacioConfiguracioFields.MODEDEFIRMA);
  }

  public IntegerField POLITICACUSTODIA() {
    return new IntegerField(getQueryPath(), UsuariAplicacioConfiguracioFields.POLITICACUSTODIA);
  }

  public LongField CUSTODIAINFOID() {
    return new LongField(getQueryPath(), UsuariAplicacioConfiguracioFields.CUSTODIAINFOID);
  }

  public IntegerField POLITICATAULAFIRMES() {
    return new IntegerField(getQueryPath(), UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES);
  }

  public IntegerField POSICIOTAULAFIRMESID() {
    return new IntegerField(getQueryPath(), UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID);
  }

  public LongField FIRMATPERFORMATID() {
    return new LongField(getQueryPath(), UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID);
  }

  public StringField PROPIETATSTAULAFIRMES() {
    return new StringField(getQueryPath(), UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES);
  }

  public LongField MOTIUDELEGACIOID() {
    return new LongField(getQueryPath(), UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID);
  }

  public IntegerField POLITICASEGELLATDETEMPS() {
    return new IntegerField(getQueryPath(), UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS);
  }

  public LongField PLUGINSEGELLATID() {
    return new LongField(getQueryPath(), UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID);
  }

  public StringField HTMLPERLLISTARPLUGINSFIRMAWEB() {
    return new StringField(getQueryPath(), UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB);
  }

  public LongField PLUGINFIRMASERVIDORID() {
    return new LongField(getQueryPath(), UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID);
  }

  public IntegerField UPGRADESIGNFORMAT() {
    return new IntegerField(getQueryPath(), UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT);
  }

  public LongField LOGINCERTIFICATEID() {
    return new LongField(getQueryPath(), UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID);
  }

  public BooleanField COMPROVARNIFFIRMA() {
    return new BooleanField(getQueryPath(), UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA);
  }

  public BooleanField CHECKCANVIATDOCFIRMAT() {
    return new BooleanField(getQueryPath(), UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT);
  }

  public BooleanField VALIDARFIRMA() {
    return new BooleanField(getQueryPath(), UsuariAplicacioConfiguracioFields.VALIDARFIRMA);
  }

  public BooleanField VALIDARCERTIFICAT() {
    return new BooleanField(getQueryPath(), UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (UsuariAplicacioConfiguracioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PerfilDeFirmaQueryPath PERFILDEFIRMA_USRAPPCONFIGURACIO1IDS() {
    return new PerfilDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioConfiguracioQueryPath.this.getQueryPath() + "perfilDeFirma_usrappconfiguracio1ids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PerfilDeFirmaQueryPath PERFILDEFIRMA_USRAPPCONFIGURACIO2IDS() {
    return new PerfilDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioConfiguracioQueryPath.this.getQueryPath() + "perfilDeFirma_usrappconfiguracio2ids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PerfilDeFirmaQueryPath PERFILDEFIRMA_USRAPPCONFIGURACIO3IDS() {
    return new PerfilDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioConfiguracioQueryPath.this.getQueryPath() + "perfilDeFirma_usrappconfiguracio3ids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PerfilDeFirmaQueryPath PERFILDEFIRMA_USRAPPCONFIGURACIO4IDS() {
    return new PerfilDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioConfiguracioQueryPath.this.getQueryPath() + "perfilDeFirma_usrappconfiguracio4ids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PerfilDeFirmaQueryPath PERFILDEFIRMA_USRAPPCONFIGURACIO5IDS() {
    return new PerfilDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioConfiguracioQueryPath.this.getQueryPath() + "perfilDeFirma_usrappconfiguracio5ids" + ".";
      }
    });
  }
*/

  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioConfiguracioQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

  public CustodiaInfoQueryPath CUSTODIAINFO() {
    return new CustodiaInfoQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioConfiguracioQueryPath.this.getQueryPath() + "custodiaInfo" + ".";
      }
    });
  }

  public TraduccioQueryPath FIRMATPERFORMAT() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioConfiguracioQueryPath.this.getQueryPath() + "firmatPerFormat" + ".";
      }
    });
  }

  public TraduccioQueryPath MOTIUDELEGACIO() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioConfiguracioQueryPath.this.getQueryPath() + "motiuDelegacio" + ".";
      }
    });
  }

  public PluginQueryPath PLUGINSEGELLAT() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioConfiguracioQueryPath.this.getQueryPath() + "pluginSegellat" + ".";
      }
    });
  }

  public PluginQueryPath PLUGINFIRMASERVIDOR() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioConfiguracioQueryPath.this.getQueryPath() + "pluginFirmaServidor" + ".";
      }
    });
  }

  public FitxerQueryPath LOGINCERTIFICATE() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioConfiguracioQueryPath.this.getQueryPath() + "loginCertificate" + ".";
      }
    });
  }

}
