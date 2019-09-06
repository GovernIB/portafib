package es.caib.portafib.back.controller.aden;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.form.SeleccioFluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.utils.ConstantsV2;

/**
 * Consulta Totes les peticions de firma dels Usuaris Aplicació i Solicitants Web
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_ADEN_PETICIOFIRMA_TOTES_CONSULTAR)
@SessionAttributes(types = { SeleccioFluxDeFirmesForm.class, PeticioDeFirmaForm.class,
    PeticioDeFirmaFilterForm.class})
public class PeticioDeFirmaTotesConsultarAdenController extends AbstractPeticioDeFirmaAdenController {

  @Override
  public String getSessionAttributeFilterForm() {
    return super.getSessionAttributeFilterForm() + "_totes_peticions_consultar";
  }

  @Override
  public String getEntityNameCode() {
    return "peticiodefirma.totes.consultar";
  }

  @Override
  public TipusSolicitant getTipusSolicitant() {
    return TipusSolicitant.SOLICITANT_TOTS;
  }
  
  @Override
  public boolean isNomesConsulta() {
    return true;
  }

  @Override
  public boolean addCreateButton() {
    return false;
  }

}
