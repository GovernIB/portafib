package es.caib.portafib.back.controller.adapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.soli.PlantillaDeFluxDeFirmesController;
import es.caib.portafib.back.form.PlantillaDeFluxDeFirmesFilterForm;
import es.caib.portafib.back.form.PlantillaDeFluxDeFirmesForm;
import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.webdb.FluxDeFirmesFilterForm;
import es.caib.portafib.back.form.webdb.FluxDeFirmesForm;
import es.caib.portafib.back.utils.menuoptions.MenuOption;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/aden/plantilla")
@SessionAttributes(
        types = { PlantillaDeFluxDeFirmesFilterForm.class, SeleccioUsuariForm.class, PlantillaDeFluxDeFirmesForm.class,
                FluxDeFirmesForm.class, FluxDeFirmesFilterForm.class })
@MenuOption(
        group = ConstantsV2.ROLE_ADEN2,
        labelCode = "plantillaFluxDeFirmes.plantillaFluxDeFirmes.plural",
        addSeparatorBefore = true,
        order = 160)
public class PlantillaDeFluxDeFirmesAdappController extends PlantillaDeFluxDeFirmesController {

    @Override
    public String getTileForm() {
        return "PlantillaDeFluxDeFirmes2FormAden";
    }

    @Override
    public String getTileList() {
        return "PlantillaDeFluxDeFirmes2ListAden";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "PlantillaFluxDeFirmes_aden";
    }

    @Override
    public boolean isUsuariEntitat() {
        return false;
    }

    @Override
    public boolean isEditingPlantilla() {
        return true;
    }

}
