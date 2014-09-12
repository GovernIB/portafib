
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TipusMetadadaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TipusMetadadaQueryPath() {
  }

  protected TipusMetadadaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public IntegerField TIPUSMETADADAID() {
    return new IntegerField(getQueryPath(), TipusMetadadaFields.TIPUSMETADADAID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), TipusMetadadaFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), TipusMetadadaFields.DESCRIPCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TipusMetadadaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public MetadadaQueryPath METADADAS() {
    return new MetadadaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TipusMetadadaQueryPath.this.getQueryPath() + "metadadas" + ".";
      }
    });
  }
*/

}
