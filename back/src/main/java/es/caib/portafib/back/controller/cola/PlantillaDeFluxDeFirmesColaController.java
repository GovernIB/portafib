package es.caib.portafib.back.controller.cola;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.dest.PlantillaDeFluxDeFirmesDestController;
import es.caib.portafib.back.form.PlantillaDeFluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.FluxDeFirmesFilterForm;
import es.caib.portafib.back.form.webdb.FluxDeFirmesForm;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/cola/plantilla")
@SessionAttributes(types = { PlantillaDeFluxDeFirmesForm.class, FluxDeFirmesForm.class,
    FluxDeFirmesFilterForm.class })
public class PlantillaDeFluxDeFirmesColaController extends PlantillaDeFluxDeFirmesDestController {

}
