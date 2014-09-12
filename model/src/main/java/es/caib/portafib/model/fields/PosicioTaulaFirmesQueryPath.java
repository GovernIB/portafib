
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PosicioTaulaFirmesQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PosicioTaulaFirmesQueryPath() {
  }

  protected PosicioTaulaFirmesQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public IntegerField POSICIOTAULAFIRMESID() {
    return new IntegerField(getQueryPath(), PosicioTaulaFirmesFields.POSICIOTAULAFIRMESID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), PosicioTaulaFirmesFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), PosicioTaulaFirmesFields.DESCRIPCIO);
  }

  public BooleanField SUPORTADA() {
    return new BooleanField(getQueryPath(), PosicioTaulaFirmesFields.SUPORTADA);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PosicioTaulaFirmesFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PeticioDeFirmaQueryPath PETICIODEFIRMAS() {
    return new PeticioDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PosicioTaulaFirmesQueryPath.this.getQueryPath() + "peticioDeFirmas" + ".";
      }
    });
  }
*/

}
