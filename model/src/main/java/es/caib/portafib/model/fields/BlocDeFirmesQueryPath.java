
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class BlocDeFirmesQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public BlocDeFirmesQueryPath() {
  }

  protected BlocDeFirmesQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField BLOCDEFIRMESID() {
    return new LongField(getQueryPath(), BlocDeFirmesFields.BLOCDEFIRMESID);
  }

  public IntegerField ORDRE() {
    return new IntegerField(getQueryPath(), BlocDeFirmesFields.ORDRE);
  }

  public TimestampField DATAFINALITZACIO() {
    return new TimestampField(getQueryPath(), BlocDeFirmesFields.DATAFINALITZACIO);
  }

  public LongField FLUXDEFIRMESID() {
    return new LongField(getQueryPath(), BlocDeFirmesFields.FLUXDEFIRMESID);
  }

  public IntegerField MINIMDEFIRMES() {
    return new IntegerField(getQueryPath(), BlocDeFirmesFields.MINIMDEFIRMES);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (BlocDeFirmesFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public FirmaQueryPath FIRMAS() {
    return new FirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return BlocDeFirmesQueryPath.this.getQueryPath() + "firmas" + ".";
      }
    });
  }
*/

  public FluxDeFirmesQueryPath FLUXDEFIRMES() {
    return new FluxDeFirmesQueryPath(new QueryPath() {
      public String getQueryPath() {
          return BlocDeFirmesQueryPath.this.getQueryPath() + "fluxDeFirmes" + ".";
      }
    });
  }

}
