package es.caib.portafib.back.controller.dele;

import es.caib.portafib.back.controller.AbstractEstatDeFirmaDestDeleColaController;
import es.caib.portafib.utils.Constants;
/**
 * @author anadal
 */
public abstract class EstatFirmaAbstractDeleController extends AbstractEstatDeFirmaDestDeleColaController {
    
    @Override
    public String getRole() {
      return Constants.ROLE_DELE;
    }

} // Final de Classe
