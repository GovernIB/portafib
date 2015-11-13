
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TipusDocumentQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TipusDocumentQueryPath() {
  }

  protected TipusDocumentQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField TIPUSDOCUMENTID() {
    return new LongField(getQueryPath(), TipusDocumentFields.TIPUSDOCUMENTID);
  }

  public LongField NOMID() {
    return new LongField(getQueryPath(), TipusDocumentFields.NOMID);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), TipusDocumentFields.DESCRIPCIO);
  }

  public StringField USUARIAPLICACIOID() {
    return new StringField(getQueryPath(), TipusDocumentFields.USUARIAPLICACIOID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TipusDocumentFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public ModulDeFirmaPerTipusDeDocumentQueryPath MODULDEFIRMAPERTIPUSDEDOCUMENTS() {
    return new ModulDeFirmaPerTipusDeDocumentQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TipusDocumentQueryPath.this.getQueryPath() + "modulDeFirmaPerTipusDeDocuments" + ".";
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
          return TipusDocumentQueryPath.this.getQueryPath() + "peticioDeFirmas" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public TipusDocumentColaboracioDelegacioQueryPath TIPUSDOCUMENTCOLABORACIODELEGACIOS() {
    return new TipusDocumentColaboracioDelegacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TipusDocumentQueryPath.this.getQueryPath() + "tipusDocumentColaboracioDelegacios" + ".";
      }
    });
  }
*/

  public TraduccioQueryPath NOM() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TipusDocumentQueryPath.this.getQueryPath() + "nom" + ".";
      }
    });
  }

  public UsuariAplicacioQueryPath USUARIAPLICACIO() {
    return new UsuariAplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TipusDocumentQueryPath.this.getQueryPath() + "usuariAplicacio" + ".";
      }
    });
  }

}
