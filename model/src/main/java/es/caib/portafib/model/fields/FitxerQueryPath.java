
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class FitxerQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public FitxerQueryPath() {
  }

  protected FitxerQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField FITXERID() {
    return new LongField(getQueryPath(), FitxerFields.FITXERID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), FitxerFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), FitxerFields.DESCRIPCIO);
  }

  public LongField TAMANY() {
    return new LongField(getQueryPath(), FitxerFields.TAMANY);
  }

  public StringField MIME() {
    return new StringField(getQueryPath(), FitxerFields.MIME);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (FitxerFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public AnnexQueryPath ANNEXS() {
    return new AnnexQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FitxerQueryPath.this.getQueryPath() + "annexs" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public AnnexFirmatQueryPath ANNEXFIRMATS() {
    return new AnnexFirmatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FitxerQueryPath.this.getQueryPath() + "annexFirmats" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public ColaboracioDelegacioQueryPath COLABORACIODELEGACIOS() {
    return new ColaboracioDelegacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FitxerQueryPath.this.getQueryPath() + "colaboracioDelegacios" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntitatQueryPath ENTITAT_LOGOWEBPEUIDS() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FitxerQueryPath.this.getQueryPath() + "entitat_logowebpeuids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntitatQueryPath ENTITAT_LOGOSEGELLIDS() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FitxerQueryPath.this.getQueryPath() + "entitat_logosegellids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntitatQueryPath ENTITAT_PDFAUTORITZACIODELEGACIOIDS() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FitxerQueryPath.this.getQueryPath() + "entitat_pdfautoritzaciodelegacioids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntitatQueryPath ENTITAT_FAVICONIDS() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FitxerQueryPath.this.getQueryPath() + "entitat_faviconids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntitatQueryPath ENTITAT_LOGOWEBIDS() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FitxerQueryPath.this.getQueryPath() + "entitat_logowebids" + ".";
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
          return FitxerQueryPath.this.getQueryPath() + "firmas" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PeticioDeFirmaQueryPath PETICIODEFIRMA_FITXERADAPTATIDS() {
    return new PeticioDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FitxerQueryPath.this.getQueryPath() + "peticioDeFirma_fitxeradaptatids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PeticioDeFirmaQueryPath PETICIODEFIRMA_FITXERAFIRMARIDS() {
    return new PeticioDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FitxerQueryPath.this.getQueryPath() + "peticioDeFirma_fitxerafirmarids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PeticioDeFirmaQueryPath PETICIODEFIRMA_LOGOSEGELLIDS() {
    return new PeticioDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FitxerQueryPath.this.getQueryPath() + "peticioDeFirma_logosegellids" + ".";
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
          return FitxerQueryPath.this.getQueryPath() + "usuariAplicacios" + ".";
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
          return FitxerQueryPath.this.getQueryPath() + "usuariEntitats" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public UsuariPersonaQueryPath USUARIPERSONAS() {
    return new UsuariPersonaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FitxerQueryPath.this.getQueryPath() + "usuariPersonas" + ".";
      }
    });
  }
*/

}
