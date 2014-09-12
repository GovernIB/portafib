
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TipusEstatDeFirmaFinalQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TipusEstatDeFirmaFinalQueryPath() {
  }

  protected TipusEstatDeFirmaFinalQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField TIPUSESTATDEFIRMAFINALID() {
    return new LongField(getQueryPath(), TipusEstatDeFirmaFinalFields.TIPUSESTATDEFIRMAFINALID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), TipusEstatDeFirmaFinalFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), TipusEstatDeFirmaFinalFields.DESCRIPCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TipusEstatDeFirmaFinalFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EstatDeFirmaQueryPath ESTATDEFIRMAS() {
    return new EstatDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TipusEstatDeFirmaFinalQueryPath.this.getQueryPath() + "estatDeFirmas" + ".";
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
          return TipusEstatDeFirmaFinalQueryPath.this.getQueryPath() + "firmas" + ".";
      }
    });
  }
*/

}
