
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TipusFirmaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TipusFirmaQueryPath() {
  }

  protected TipusFirmaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public IntegerField TIPUSFIRMAID() {
    return new IntegerField(getQueryPath(), TipusFirmaFields.TIPUSFIRMAID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), TipusFirmaFields.NOM);
  }

  public BooleanField SUPORTADA() {
    return new BooleanField(getQueryPath(), TipusFirmaFields.SUPORTADA);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), TipusFirmaFields.DESCRIPCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TipusFirmaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PeticioDeFirmaQueryPath PETICIODEFIRMAS() {
    return new PeticioDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TipusFirmaQueryPath.this.getQueryPath() + "peticioDeFirmas" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public UsuariAplicacioConfiguracioQueryPath USUARIAPLICACIOCONFIGURACIOS() {
    return new UsuariAplicacioConfiguracioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TipusFirmaQueryPath.this.getQueryPath() + "usuariAplicacioConfiguracios" + ".";
      }
    });
  }
*/

}
