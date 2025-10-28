package es.caib.portafib.back.controller.adapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.aden.FluxDeFirmesAdenController;
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
@RequestMapping(value = FluxDeFirmesAdappController.CONTEXT_WEB)
@SessionAttributes(
        types = { SeleccioUsuariForm.class, PlantillaDeFluxDeFirmesForm.class, FluxDeFirmesForm.class,
                FluxDeFirmesFilterForm.class })
public class FluxDeFirmesAdappController extends FluxDeFirmesAdenController {

    public static final String CONTEXT_WEB = "/adapp/fluxdefirmes";

    @Override
    public String getTileForm() {
        return "PlantillaDeFluxDeFirmesFormAdapp";
    }

    @Override
    public String getTileList() {
        return "PlantillaDeFluxDeFirmesListAdapp";
    }

}
