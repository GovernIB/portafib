package es.caib.portafib.back.controller.aden;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.form.PlantillaDeFluxDeFirmesForm;
import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.webdb.FluxDeFirmesFilterForm;
import es.caib.portafib.back.form.webdb.FluxDeFirmesForm;


/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = FluxDeFirmes2AdenController.CONTEXT_WEB)
@SessionAttributes(types = {  SeleccioUsuariForm.class, PlantillaDeFluxDeFirmesForm.class,
    FluxDeFirmesForm.class,  FluxDeFirmesFilterForm.class })
public class FluxDeFirmes2AdenController extends FluxDeFirmesAdenController {
  
  public static final String CONTEXT_WEB = "/aden/fluxdefirmes2";

  @Override
  public String getTileForm() {
    return "PlantillaDeFluxDeFirmes2FormAden";
  }

  @Override
  public String getTileList() {
    return "PlantillaDeFluxDeFirmes2ListAden";
  }

}
