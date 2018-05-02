package es.caib.portafib.back.controller.cola;

import es.caib.portafib.back.controller.AbstractEstatDeFirmaDestDeleColaController;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
public abstract class EstatFirmaAbstractColaController extends AbstractEstatDeFirmaDestDeleColaController {

  @Override
  public final String getBaseEntityNameCode() {
    return "colaboracio";
  }
  
  @Override
  public final String getRole() {
    return ConstantsV2.ROLE_COLA;
  }
  
}
