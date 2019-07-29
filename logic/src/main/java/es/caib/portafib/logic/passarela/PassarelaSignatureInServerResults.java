package es.caib.portafib.logic.passarela;

import java.util.Map;

import es.caib.portafib.logic.passarela.api.PassarelaFullResults;
import es.caib.portafib.logic.utils.ValidacioCompletaResponse;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class PassarelaSignatureInServerResults {

  protected final PassarelaFullResults passarelaFullResults;

  protected final Map<String, ValidacioCompletaResponse> validacioResponseBySignID;

  public PassarelaSignatureInServerResults(PassarelaFullResults passarelaFullResults,
      Map<String, ValidacioCompletaResponse> validacioResponseBySignID) {
    super();
    this.passarelaFullResults = passarelaFullResults;
    this.validacioResponseBySignID = validacioResponseBySignID;
  }

  public PassarelaFullResults getPassarelaFullResults() {
    return passarelaFullResults;
  }

  public Map<String, ValidacioCompletaResponse> getValidacioResponseBySignID() {
    return validacioResponseBySignID;
  }

}
