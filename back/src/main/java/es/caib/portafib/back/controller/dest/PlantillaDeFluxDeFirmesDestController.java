package es.caib.portafib.back.controller.dest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.soli.PlantillaDeFluxDeFirmesController;
import es.caib.portafib.back.form.PlantillaDeFluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.FluxDeFirmesFilterForm;
import es.caib.portafib.back.form.webdb.FluxDeFirmesForm;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/dest/plantilla")
@SessionAttributes(types = { PlantillaDeFluxDeFirmesForm.class, FluxDeFirmesForm.class,
    FluxDeFirmesFilterForm.class })
public class PlantillaDeFluxDeFirmesDestController extends PlantillaDeFluxDeFirmesController {

  
  @Override
  public String getSessionAttributeFilterForm() {
    return this.getClass().getName();
  }
  
}
