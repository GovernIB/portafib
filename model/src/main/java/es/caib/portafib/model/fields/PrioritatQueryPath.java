
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PrioritatQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PrioritatQueryPath() {
  }

  protected PrioritatQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public IntegerField PRIORITATID() {
    return new IntegerField(getQueryPath(), PrioritatFields.PRIORITATID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), PrioritatFields.NOM);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PrioritatFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PeticioDeFirmaQueryPath PETICIODEFIRMAS() {
    return new PeticioDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PrioritatQueryPath.this.getQueryPath() + "peticioDeFirmas" + ".";
      }
    });
  }
*/

}
