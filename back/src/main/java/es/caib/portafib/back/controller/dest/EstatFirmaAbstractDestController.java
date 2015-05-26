package es.caib.portafib.back.controller.dest;

import es.caib.portafib.back.controller.AbstractEstatDeFirmaDestDeleColaController;
import es.caib.portafib.utils.Constants;
/**
 * 
 */
public abstract class EstatFirmaAbstractDestController extends AbstractEstatDeFirmaDestDeleColaController {
    
    @Override
    public String getRole() {
      return Constants.ROLE_DEST;
    }

} // Final de Classe
