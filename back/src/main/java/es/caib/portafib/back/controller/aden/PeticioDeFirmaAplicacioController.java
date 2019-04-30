package es.caib.portafib.back.controller.aden;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

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
@SessionAttributes(types = { SeleccioFluxDeFirmesForm.class, PeticioDeFirmaForm.class,
    PeticioDeFirmaFilterForm.class, AnnexFilterForm.class, AnnexForm.class })
public class PeticioDeFirmaAplicacioController extends PeticioDeFirmaSoliController implements
    ConstantsV2 {

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

  @Override
  public PeticioDeFirmaFilterForm getPeticioDeFirmaFilterForm(Integer pagina,
      ModelAndView mav, HttpServletRequest request) throws I18NException {

    PeticioDeFirmaFilterForm peticioDeFirmaFilterForm;
    peticioDeFirmaFilterForm = super.getPeticioDeFirmaFilterForm(pagina, mav, request);

    
    boolean showUsuariEntitat = false;
    boolean showUsuariAplicacio = true;
    
    AbstractPeticioDeFirmaAdenController
        .configureFilterFieldsPeticioDeFirma(peticioDeFirmaFilterForm, showUsuariEntitat,showUsuariAplicacio);
    
    AbstractPeticioDeFirmaAdenController
         .configureGroupByFieldsPeticioDeFirma(peticioDeFirmaFilterForm, showUsuariEntitat,showUsuariAplicacio);

    AbstractPeticioDeFirmaAdenController.cleanFiltersAndGroups(peticioDeFirmaFilterForm);

    return peticioDeFirmaFilterForm;

  }

}
