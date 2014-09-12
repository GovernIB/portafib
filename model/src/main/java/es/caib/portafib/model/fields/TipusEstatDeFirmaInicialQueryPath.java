
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TipusEstatDeFirmaInicialQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TipusEstatDeFirmaInicialQueryPath() {
  }

  protected TipusEstatDeFirmaInicialQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField TIPUSESTATDEFIRMAINICIALID() {
    return new LongField(getQueryPath(), TipusEstatDeFirmaInicialFields.TIPUSESTATDEFIRMAINICIALID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), TipusEstatDeFirmaInicialFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), TipusEstatDeFirmaInicialFields.DESCRIPCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TipusEstatDeFirmaInicialFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EstatDeFirmaQueryPath ESTATDEFIRMAS() {
    return new EstatDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TipusEstatDeFirmaInicialQueryPath.this.getQueryPath() + "estatDeFirmas" + ".";
      }
    });
  }
*/

}
