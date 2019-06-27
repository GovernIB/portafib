package es.caib.portafib.back.controller.aden;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.form.SeleccioFluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.AnnexFilterForm;
import es.caib.portafib.back.form.webdb.AnnexForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.utils.ConstantsV2;

/**
 * Gestiona les peticions de firma dels Usuaris Aplicació que ataquen emprant API FIRMA SIMPLE
 * ASYNC v2
 *  
 * @author anadal
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_ADEN_PETICIOFIRMA_ASYNC_SIMPLE)
@SessionAttributes(types = { SeleccioFluxDeFirmesForm.class, PeticioDeFirmaForm.class,
    PeticioDeFirmaFilterForm.class, AnnexFilterForm.class, AnnexForm.class })
public class PeticioDeFirmaAsyncSimpleController extends PeticioDeFirmaAplicacioController {

  @Override
  public int getOrigenPeticioDeFirma() {
    return ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_ASYNC_SIMPLE_V2;
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return super.getSessionAttributeFilterForm() + "_aden_async_simple";
  }

  @Override
  public String getEntityNameCode() {
    return "peticiodefirma.asyncsimple";
  }

}
