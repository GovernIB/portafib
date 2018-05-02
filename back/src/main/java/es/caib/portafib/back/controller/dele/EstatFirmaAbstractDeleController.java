package es.caib.portafib.back.controller.dele;

import es.caib.portafib.back.controller.AbstractEstatDeFirmaDestDeleColaController;
import es.caib.portafib.utils.ConstantsV2;

/**
 * @author anadal
 */
public abstract class EstatFirmaAbstractDeleController extends
    AbstractEstatDeFirmaDestDeleColaController {

  @Override
  public String getBaseEntityNameCode() {
    return "delegacio";
  }

  @Override
  public String getRole() {
    return ConstantsV2.ROLE_DELE;
  }

} // Final de Classe
