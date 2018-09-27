package es.caib.portafib.back.controller.revi;

import es.caib.portafib.back.controller.AbstractEstatDeFirmaDestDeleColaController;
import es.caib.portafib.utils.ConstantsV2;

/**
 * @author anadal
 */
public abstract class EstatFirmaAbstractReviController extends
    AbstractEstatDeFirmaDestDeleColaController {

  @Override
  public String getBaseEntityNameCode() {
    return "revisor";
  }

  @Override
  public String getRole() {
    return ConstantsV2.ROLE_REVI;
  }

} // Final de Classe
