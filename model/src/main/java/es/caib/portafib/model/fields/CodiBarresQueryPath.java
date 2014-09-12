
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class CodiBarresQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public CodiBarresQueryPath() {
  }

  protected CodiBarresQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public StringField CODIBARRESID() {
    return new StringField(getQueryPath(), CodiBarresFields.CODIBARRESID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), CodiBarresFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), CodiBarresFields.DESCRIPCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (CodiBarresFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public CustodiaInfoQueryPath CUSTODIAINFOS() {
    return new CustodiaInfoQueryPath(new QueryPath() {
      public String getQueryPath() {
          return CodiBarresQueryPath.this.getQueryPath() + "custodiaInfos" + ".";
      }
    });
  }
*/

}
