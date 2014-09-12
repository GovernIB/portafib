
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TipusDocumentColaboracioDelegacioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TipusDocumentColaboracioDelegacioQueryPath() {
  }

  protected TipusDocumentColaboracioDelegacioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ID() {
    return new LongField(getQueryPath(), TipusDocumentColaboracioDelegacioFields.ID);
  }

  public LongField COLABORACIODELEGACIOID() {
    return new LongField(getQueryPath(), TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID);
  }

  public LongField TIPUSDOCUMENTID() {
    return new LongField(getQueryPath(), TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TipusDocumentColaboracioDelegacioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public ColaboracioDelegacioQueryPath COLABORACIODELEGACIO() {
    return new ColaboracioDelegacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TipusDocumentColaboracioDelegacioQueryPath.this.getQueryPath() + "colaboracioDelegacio" + ".";
      }
    });
  }

  public TipusDocumentQueryPath TIPUSDOCUMENT() {
    return new TipusDocumentQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TipusDocumentColaboracioDelegacioQueryPath.this.getQueryPath() + "tipusDocument" + ".";
      }
    });
  }

}
