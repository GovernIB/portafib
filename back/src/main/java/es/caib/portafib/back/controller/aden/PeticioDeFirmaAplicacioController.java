package es.caib.portafib.back.controller.aden;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.soli.PeticioDeFirmaSoliController;
import es.caib.portafib.back.form.SeleccioFluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.AnnexFilterForm;
import es.caib.portafib.back.form.webdb.AnnexForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.utils.ConstantsV2;

/**
 * Gestiona les peticions de firma dels Usuaris Aplicació de la meva entitat
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_ADEN_PETICIOFIRMA)
@SessionAttributes(types = {SeleccioFluxDeFirmesForm.class, PeticioDeFirmaForm.class,
    PeticioDeFirmaFilterForm.class, AnnexFilterForm.class, AnnexForm.class })
public class PeticioDeFirmaAplicacioController extends PeticioDeFirmaSoliController
    implements ConstantsV2 {

  @Override
  public boolean isSolicitantUsuariEntitat() {
    return false;
  }
  
  
  @Override
  public String getSessionAttributeFilterForm() {
    return super.getSessionAttributeFilterForm() + "_aden";
  }
  
  
  @Override
  public String getTileForm() {
    return "peticioDeFirmaAplicacioForm";
  }

  @Override
  public String getTileList() {
    return "peticioDeFirmaAplicacioList";
  }

  @Override
  public String getTileSeleccioFlux() {
    return "seleccionaFluxDeFirmaPerAplicacioForm";
  }
  
  

}
