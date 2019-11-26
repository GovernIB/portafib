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
@RequestMapping(value = FluxDeFirmesAdenController.CONTEXT_WEB)
@SessionAttributes(types = {  SeleccioUsuariForm.class, PlantillaDeFluxDeFirmesForm.class,
    FluxDeFirmesForm.class,  FluxDeFirmesFilterForm.class })
public class FluxDeFirmesAdenController extends PlantillaDeFluxDeFirmesAdenController {

  public static final String CONTEXT_WEB = "/aden/fluxdefirmes";

  @Override
  public boolean isActiveList() {
    return false;
  }

  @Override
  public boolean isActiveFormNew() {
    return false;
  }
  
  @Override
  public boolean isActiveFormView() {
    return true;
  }

  @Override
  public boolean isActiveFormEdit() {
    return true;
  }

  @Override
  public boolean isActiveDelete() {
    return false;
  }
  
  @Override
  public String getEntityNameCode() {
    return "fluxDeFirmes.fluxDeFirmes";
  }
  
  
  @Override
  public boolean isEditingPlantilla() {
    return false;
  }
}
