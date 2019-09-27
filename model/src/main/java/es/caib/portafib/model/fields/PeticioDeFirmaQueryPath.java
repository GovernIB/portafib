
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PeticioDeFirmaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PeticioDeFirmaQueryPath() {
  }

  protected PeticioDeFirmaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PETICIODEFIRMAID() {
    return new LongField(getQueryPath(), PeticioDeFirmaFields.PETICIODEFIRMAID);
  }

  public StringField TITOL() {
    return new StringField(getQueryPath(), PeticioDeFirmaFields.TITOL);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), PeticioDeFirmaFields.DESCRIPCIO);
  }

  public StringField MOTIU() {
    return new StringField(getQueryPath(), PeticioDeFirmaFields.MOTIU);
  }

  public LongField FITXERAFIRMARID() {
    return new LongField(getQueryPath(), PeticioDeFirmaFields.FITXERAFIRMARID);
  }

  public LongField FIRMAORIGINALDETACHEDID() {
    return new LongField(getQueryPath(), PeticioDeFirmaFields.FIRMAORIGINALDETACHEDID);
  }

  public LongField FITXERADAPTATID() {
    return new LongField(getQueryPath(), PeticioDeFirmaFields.FITXERADAPTATID);
  }

  public LongField TIPUSDOCUMENTID() {
    return new LongField(getQueryPath(), PeticioDeFirmaFields.TIPUSDOCUMENTID);
  }

  public StringField DESCRIPCIOTIPUSDOCUMENT() {
    return new StringField(getQueryPath(), PeticioDeFirmaFields.DESCRIPCIOTIPUSDOCUMENT);
  }

  public TimestampField DATASOLICITUD() {
    return new TimestampField(getQueryPath(), PeticioDeFirmaFields.DATASOLICITUD);
  }

  public TimestampField DATAFINAL() {
    return new TimestampField(getQueryPath(), PeticioDeFirmaFields.DATAFINAL);
  }

  public TimestampField DATACADUCITAT() {
    return new TimestampField(getQueryPath(), PeticioDeFirmaFields.DATACADUCITAT);
  }

  public IntegerField TIPUSOPERACIOFIRMA() {
    return new IntegerField(getQueryPath(), PeticioDeFirmaFields.TIPUSOPERACIOFIRMA);
  }

  public IntegerField TIPUSFIRMAID() {
    return new IntegerField(getQueryPath(), PeticioDeFirmaFields.TIPUSFIRMAID);
  }

  public IntegerField ALGORISMEDEFIRMAID() {
    return new IntegerField(getQueryPath(), PeticioDeFirmaFields.ALGORISMEDEFIRMAID);
  }

  public BooleanField MODEDEFIRMA() {
    return new BooleanField(getQueryPath(), PeticioDeFirmaFields.MODEDEFIRMA);
  }

  public IntegerField POSICIOTAULAFIRMESID() {
    return new IntegerField(getQueryPath(), PeticioDeFirmaFields.POSICIOTAULAFIRMESID);
  }

  public IntegerField TIPUSESTATPETICIODEFIRMAID() {
    return new IntegerField(getQueryPath(), PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID);
  }

  public StringField MOTIUDEREBUIG() {
    return new StringField(getQueryPath(), PeticioDeFirmaFields.MOTIUDEREBUIG);
  }

  public StringField IDIOMAID() {
    return new StringField(getQueryPath(), PeticioDeFirmaFields.IDIOMAID);
  }

  public IntegerField PRIORITATID() {
    return new IntegerField(getQueryPath(), PeticioDeFirmaFields.PRIORITATID);
  }

  public LongField FLUXDEFIRMESID() {
    return new LongField(getQueryPath(), PeticioDeFirmaFields.FLUXDEFIRMESID);
  }

  public StringField SOLICITANTUSUARIAPLICACIOID() {
    return new StringField(getQueryPath(), PeticioDeFirmaFields.SOLICITANTUSUARIAPLICACIOID);
  }

  public StringField REMITENTNOM() {
    return new StringField(getQueryPath(), PeticioDeFirmaFields.REMITENTNOM);
  }

  public StringField REMITENTDESCRIPCIO() {
    return new StringField(getQueryPath(), PeticioDeFirmaFields.REMITENTDESCRIPCIO);
  }

  public StringField EXPEDIENTCODI() {
    return new StringField(getQueryPath(), PeticioDeFirmaFields.EXPEDIENTCODI);
  }

  public StringField EXPEDIENTNOM() {
    return new StringField(getQueryPath(), PeticioDeFirmaFields.EXPEDIENTNOM);
  }

  public StringField EXPEDIENTURL() {
    return new StringField(getQueryPath(), PeticioDeFirmaFields.EXPEDIENTURL);
  }

  public StringField PROCEDIMENTCODI() {
    return new StringField(getQueryPath(), PeticioDeFirmaFields.PROCEDIMENTCODI);
  }

  public StringField PROCEDIMENTNOM() {
    return new StringField(getQueryPath(), PeticioDeFirmaFields.PROCEDIMENTNOM);
  }

  public StringField INFORMACIOADDICIONAL() {
    return new StringField(getQueryPath(), PeticioDeFirmaFields.INFORMACIOADDICIONAL);
  }

  public DoubleField INFORMACIOADDICIONALAVALUABLE() {
    return new DoubleField(getQueryPath(), PeticioDeFirmaFields.INFORMACIOADDICIONALAVALUABLE);
  }

  public LongField LOGOSEGELLID() {
    return new LongField(getQueryPath(), PeticioDeFirmaFields.LOGOSEGELLID);
  }

  public LongField CUSTODIAINFOID() {
    return new LongField(getQueryPath(), PeticioDeFirmaFields.CUSTODIAINFOID);
  }

  public StringField SOLICITANTUSUARIENTITAT1ID() {
    return new StringField(getQueryPath(), PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT1ID);
  }

  public StringField SOLICITANTUSUARIENTITAT2ID() {
    return new StringField(getQueryPath(), PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT2ID);
  }

  public StringField SOLICITANTUSUARIENTITAT3ID() {
    return new StringField(getQueryPath(), PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT3ID);
  }

  public BooleanField AVISWEB() {
    return new BooleanField(getQueryPath(), PeticioDeFirmaFields.AVISWEB);
  }

  public BooleanField SEGELLATDETEMPS() {
    return new BooleanField(getQueryPath(), PeticioDeFirmaFields.SEGELLATDETEMPS);
  }

  public IntegerField ORIGENPETICIODEFIRMA() {
    return new IntegerField(getQueryPath(), PeticioDeFirmaFields.ORIGENPETICIODEFIRMA);
  }

  public LongField CONFIGURACIODEFIRMAID() {
    return new LongField(getQueryPath(), PeticioDeFirmaFields.CONFIGURACIODEFIRMAID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PeticioDeFirmaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public AnnexQueryPath ANNEXS() {
    return new AnnexQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioDeFirmaQueryPath.this.getQueryPath() + "annexs" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public MetadadaQueryPath METADADAS() {
    return new MetadadaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioDeFirmaQueryPath.this.getQueryPath() + "metadadas" + ".";
      }
    });
  }
*/

  public FitxerQueryPath FITXERAFIRMAR() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioDeFirmaQueryPath.this.getQueryPath() + "fitxerAFirmar" + ".";
      }
    });
  }

  public FitxerQueryPath FIRMAORIGINALDETACHED() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioDeFirmaQueryPath.this.getQueryPath() + "firmaOriginalDetached" + ".";
      }
    });
  }

  public FitxerQueryPath FITXERADAPTAT() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioDeFirmaQueryPath.this.getQueryPath() + "fitxerAdaptat" + ".";
      }
    });
  }

  public TipusDocumentQueryPath TIPUSDOCUMENT() {
    return new TipusDocumentQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioDeFirmaQueryPath.this.getQueryPath() + "tipusDocument" + ".";
      }
    });
  }

  public IdiomaQueryPath IDIOMA() {
    return new IdiomaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioDeFirmaQueryPath.this.getQueryPath() + "idioma" + ".";
      }
    });
  }

  public FluxDeFirmesQueryPath FLUXDEFIRMES() {
    return new FluxDeFirmesQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioDeFirmaQueryPath.this.getQueryPath() + "fluxDeFirmes" + ".";
      }
    });
  }

  public UsuariAplicacioQueryPath USUARIAPLICACIO() {
    return new UsuariAplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioDeFirmaQueryPath.this.getQueryPath() + "usuariAplicacio" + ".";
      }
    });
  }

  public FitxerQueryPath LOGOSEGELL() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioDeFirmaQueryPath.this.getQueryPath() + "logoSegell" + ".";
      }
    });
  }

  public CustodiaInfoQueryPath CUSTODIAINFO() {
    return new CustodiaInfoQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioDeFirmaQueryPath.this.getQueryPath() + "custodiaInfo" + ".";
      }
    });
  }

  public UsuariEntitatQueryPath SOLICITANTUSUARIENTITAT1() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioDeFirmaQueryPath.this.getQueryPath() + "solicitantUsuariEntitat1" + ".";
      }
    });
  }

  public UsuariEntitatQueryPath SOLICITANTUSUARIENTITAT2() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioDeFirmaQueryPath.this.getQueryPath() + "solicitantUsuariEntitat2" + ".";
      }
    });
  }

  public UsuariEntitatQueryPath SOLICITANTUSUARIENTITAT3() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioDeFirmaQueryPath.this.getQueryPath() + "solicitantUsuariEntitat3" + ".";
      }
    });
  }

  public UsuariAplicacioConfiguracioQueryPath USUARIAPLICACIOCONFIGURACIO() {
    return new UsuariAplicacioConfiguracioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioDeFirmaQueryPath.this.getQueryPath() + "usuariAplicacioConfiguracio" + ".";
      }
    });
  }

}
