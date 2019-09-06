package es.caib.portafib.back.controller.aden;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.form.SeleccioFluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.utils.ConstantsV2;

/**
 * Gestiona Totes les peticions de firma dels Usuaris Aplicació i Solicitants Web
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_ADEN_PETICIOFIRMA_TOTES_GESTIONAR)
@SessionAttributes(types = { SeleccioFluxDeFirmesForm.class, PeticioDeFirmaForm.class,
    PeticioDeFirmaFilterForm.class })
public class PeticioDeFirmaTotesGestionarAdenController extends
    AbstractPeticioDeFirmaAdenController {

  @Override
  public String getSessionAttributeFilterForm() {
    return super.getSessionAttributeFilterForm() + "_totes_peticions_gestionar";
  }

  @Override
  public String getEntityNameCode() {
    return "peticiodefirma.totes.gestionar";
  }

  @Override
  public TipusSolicitant getTipusSolicitant() {
    return TipusSolicitant.SOLICITANT_TOTS;
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
