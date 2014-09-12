
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class FluxDeFirmesQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public FluxDeFirmesQueryPath() {
  }

  protected FluxDeFirmesQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField FLUXDEFIRMESID() {
    return new LongField(getQueryPath(), FluxDeFirmesFields.FLUXDEFIRMESID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), FluxDeFirmesFields.NOM);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (FluxDeFirmesFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public BlocDeFirmesQueryPath BLOCDEFIRMESS() {
    return new BlocDeFirmesQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FluxDeFirmesQueryPath.this.getQueryPath() + "blocDeFirmess" + ".";
      }
    });
  }
*/

  public PeticioDeFirmaQueryPath PETICIODEFIRMA() {
    return new PeticioDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FluxDeFirmesQueryPath.this.getQueryPath() + "peticioDeFirma" + ".";
      }
    });
  }

  public PlantillaFluxDeFirmesQueryPath PLANTILLAFLUXDEFIRMES() {
    return new PlantillaFluxDeFirmesQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FluxDeFirmesQueryPath.this.getQueryPath() + "plantillaFluxDeFirmes" + ".";
      }
    });
  }

}
