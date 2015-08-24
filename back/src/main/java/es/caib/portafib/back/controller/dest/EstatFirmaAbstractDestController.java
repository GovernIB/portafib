package es.caib.portafib.back.controller.dest;

import es.caib.portafib.back.controller.AbstractEstatDeFirmaDestDeleColaController;
import es.caib.portafib.utils.Constants;

/**
 * @author anadal
 */
public abstract class EstatFirmaAbstractDestController extends
    AbstractEstatDeFirmaDestDeleColaController {

  @Override
  public final String getBaseEntityNameCode() {
    return "solicituddefirma.llistat";
  }

  @Override
  public final String getRole() {
    return Constants.ROLE_DEST;
  }

} // Final de Classe
