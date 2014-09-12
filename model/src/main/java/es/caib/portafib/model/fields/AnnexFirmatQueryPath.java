
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class AnnexFirmatQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public AnnexFirmatQueryPath() {
  }

  protected AnnexFirmatQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ANNEXFIRMATID() {
    return new LongField(getQueryPath(), AnnexFirmatFields.ANNEXFIRMATID);
  }

  public LongField FITXERID() {
    return new LongField(getQueryPath(), AnnexFirmatFields.FITXERID);
  }

  public LongField ANNEXID() {
    return new LongField(getQueryPath(), AnnexFirmatFields.ANNEXID);
  }

  public LongField FIRMAID() {
    return new LongField(getQueryPath(), AnnexFirmatFields.FIRMAID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (AnnexFirmatFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public FitxerQueryPath FITXER() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AnnexFirmatQueryPath.this.getQueryPath() + "fitxer" + ".";
      }
    });
  }

  public AnnexQueryPath ANNEX() {
    return new AnnexQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AnnexFirmatQueryPath.this.getQueryPath() + "annex" + ".";
      }
    });
  }

  public FirmaQueryPath FIRMA() {
    return new FirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AnnexFirmatQueryPath.this.getQueryPath() + "firma" + ".";
      }
    });
  }

}
