
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class ModulDeFirmaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public ModulDeFirmaQueryPath() {
  }

  protected ModulDeFirmaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField MODULDEFIRMAID() {
    return new LongField(getQueryPath(), ModulDeFirmaFields.MODULDEFIRMAID);
  }

  public LongField NOMID() {
    return new LongField(getQueryPath(), ModulDeFirmaFields.NOMID);
  }

  public LongField DESCRIPCIOCURTAID() {
    return new LongField(getQueryPath(), ModulDeFirmaFields.DESCRIPCIOCURTAID);
  }

  public StringField CLASSE() {
    return new StringField(getQueryPath(), ModulDeFirmaFields.CLASSE);
  }

  public StringField PROPERTIESADMIN() {
    return new StringField(getQueryPath(), ModulDeFirmaFields.PROPERTIESADMIN);
  }

  public StringField PROPERTIESENTITAT() {
    return new StringField(getQueryPath(), ModulDeFirmaFields.PROPERTIESENTITAT);
  }

  public StringField ENTITATID() {
    return new StringField(getQueryPath(), ModulDeFirmaFields.ENTITATID);
  }

  public BooleanField ACTIU() {
    return new BooleanField(getQueryPath(), ModulDeFirmaFields.ACTIU);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (ModulDeFirmaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public ModulDeFirmaPerTipusDeDocumentQueryPath MODULDEFIRMAPERTIPUSDEDOCUMENTS() {
    return new ModulDeFirmaPerTipusDeDocumentQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ModulDeFirmaQueryPath.this.getQueryPath() + "modulDeFirmaPerTipusDeDocuments" + ".";
      }
    });
  }
*/

  public TraduccioQueryPath NOM() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ModulDeFirmaQueryPath.this.getQueryPath() + "nom" + ".";
      }
    });
  }

  public TraduccioQueryPath DESCRIPCIOCURTA() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ModulDeFirmaQueryPath.this.getQueryPath() + "descripcioCurta" + ".";
      }
    });
  }

  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ModulDeFirmaQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

}
