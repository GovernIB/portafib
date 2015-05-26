package es.caib.portafib.back.controller.cola;

import es.caib.portafib.back.controller.AbstractEstatDeFirmaDestDeleColaController;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
public abstract class EstatFirmaAbstractColaController extends AbstractEstatDeFirmaDestDeleColaController {

  @Override
  public final String getRole() {
    return Constants.ROLE_COLA;
  }
  
}
