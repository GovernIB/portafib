
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class AnnexQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public AnnexQueryPath() {
  }

  protected AnnexQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ANNEXID() {
    return new LongField(getQueryPath(), AnnexFields.ANNEXID);
  }

  public LongField PETICIODEFIRMAID() {
    return new LongField(getQueryPath(), AnnexFields.PETICIODEFIRMAID);
  }

  public LongField FITXERID() {
    return new LongField(getQueryPath(), AnnexFields.FITXERID);
  }

  public BooleanField ADJUNTAR() {
    return new BooleanField(getQueryPath(), AnnexFields.ADJUNTAR);
  }

  public BooleanField FIRMAR() {
    return new BooleanField(getQueryPath(), AnnexFields.FIRMAR);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (AnnexFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public AnnexFirmatQueryPath ANNEXFIRMATS() {
    return new AnnexFirmatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AnnexQueryPath.this.getQueryPath() + "annexFirmats" + ".";
      }
    });
  }
*/

  public PeticioDeFirmaQueryPath PETICIODEFIRMA() {
    return new PeticioDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AnnexQueryPath.this.getQueryPath() + "peticioDeFirma" + ".";
      }
    });
  }

  public FitxerQueryPath FITXER() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AnnexQueryPath.this.getQueryPath() + "fitxer" + ".";
      }
    });
  }

}
