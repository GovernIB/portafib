
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PosicioPaginaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PosicioPaginaQueryPath() {
  }

  protected PosicioPaginaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField POSICIOPAGINAID() {
    return new LongField(getQueryPath(), PosicioPaginaFields.POSICIOPAGINAID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), PosicioPaginaFields.NOM);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PosicioPaginaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public CustodiaInfoQueryPath CUSTODIAINFO_CODIBARRESPOSICIOPAGINAIDS() {
    return new CustodiaInfoQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PosicioPaginaQueryPath.this.getQueryPath() + "custodiaInfo_codibarresposiciopaginaids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public CustodiaInfoQueryPath CUSTODIAINFO_MISSATGEPOSICIOPAGINAIDS() {
    return new CustodiaInfoQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PosicioPaginaQueryPath.this.getQueryPath() + "custodiaInfo_missatgeposiciopaginaids" + ".";
      }
    });
  }
*/

}
