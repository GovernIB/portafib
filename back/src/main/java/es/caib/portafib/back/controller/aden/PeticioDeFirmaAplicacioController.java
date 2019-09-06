package es.caib.portafib.back.controller.aden;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.form.SeleccioFluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.utils.ConstantsV2;

/**
 * Gestiona les peticions de firma dels Usuaris Aplicació (API PortaFIB WS v1, API Indra i API
 * FIRMA SIMPLE ASYNC v2) de la meva entitat.
 *
 * @author anadal (u80067)
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_ADEN_PETICIOFIRMA_USRAPP)
@SessionAttributes(types = { SeleccioFluxDeFirmesForm.class, PeticioDeFirmaForm.class,
    PeticioDeFirmaFilterForm.class })
public final class PeticioDeFirmaAplicacioController extends AbstractPeticioDeFirmaAdenController {

  @Override
  public String getEntityNameCode() {
    return "peticiodefirma.usrapp";
  }

  @Override
  public TipusSolicitant getTipusSolicitant() {
    return TipusSolicitant.SOLICITANT_APLICACIO;
  }

  @Override
  public boolean isNomesConsulta() {
    return false;
  }

  @Override
  public boolean addCreateButton() {
    return true;
  }

}
