
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TipusEstatPeticioDeFirmaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TipusEstatPeticioDeFirmaQueryPath() {
  }

  protected TipusEstatPeticioDeFirmaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public IntegerField TIPUSESTATPETICIODEFIRMAID() {
    return new IntegerField(getQueryPath(), TipusEstatPeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), TipusEstatPeticioDeFirmaFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), TipusEstatPeticioDeFirmaFields.DESCRIPCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TipusEstatPeticioDeFirmaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PeticioDeFirmaQueryPath PETICIODEFIRMAS() {
    return new PeticioDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TipusEstatPeticioDeFirmaQueryPath.this.getQueryPath() + "peticioDeFirmas" + ".";
      }
    });
  }
*/

}
