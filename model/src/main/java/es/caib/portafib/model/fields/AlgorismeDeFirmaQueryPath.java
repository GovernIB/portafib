
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class AlgorismeDeFirmaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public AlgorismeDeFirmaQueryPath() {
  }

  protected AlgorismeDeFirmaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public IntegerField ALGORISMEDEFIRMAID() {
    return new IntegerField(getQueryPath(), AlgorismeDeFirmaFields.ALGORISMEDEFIRMAID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), AlgorismeDeFirmaFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), AlgorismeDeFirmaFields.DESCRIPCIO);
  }

  public BooleanField SUPORTAT() {
    return new BooleanField(getQueryPath(), AlgorismeDeFirmaFields.SUPORTAT);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (AlgorismeDeFirmaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PeticioDeFirmaQueryPath PETICIODEFIRMAS() {
    return new PeticioDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AlgorismeDeFirmaQueryPath.this.getQueryPath() + "peticioDeFirmas" + ".";
      }
    });
  }
*/

}
