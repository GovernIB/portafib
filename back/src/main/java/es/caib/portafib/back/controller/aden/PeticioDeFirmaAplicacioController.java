package es.caib.portafib.back.controller.aden;


import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
 * Gestiona les peticions de firma dels Usuaris Aplicació (API PortaFIB WS v1
 *  i API Indra) de la meva entitat
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
  public int getOrigenPeticioDeFirma() {
    return ORIGEN_PETICIO_DE_FIRMA_API_PORTAFIB_WS_V1;
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

    if (peticioDeFirmaFilterForm.isNou()) {
      peticioDeFirmaFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("icon-list-alt icon-white", "peticiodefirma.fitxerspeticio",
               FitxersDePeticioAdenController.CONTEXT_WEB + "/peticio/{0}", "btn-info"));

      String bitacolaLink = BitacolaPeticioAdenController.CONTEXT_WEB + "/peticio/{0}?returnPath=" + CONTEXT_ADEN_PETICIOFIRMA + "/list";
      peticioDeFirmaFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("icon-cog icon-white", "peticiodefirma.bitacola",
              bitacolaLink, "btn-info"));
    }
    
    boolean showUsuariEntitat = false;
    boolean showUsuariAplicacio = true;
    
    AbstractPeticioDeFirmaAdenController
        .configureFilterFieldsPeticioDeFirma(peticioDeFirmaFilterForm, showUsuariEntitat,showUsuariAplicacio);
    
    AbstractPeticioDeFirmaAdenController
         .configureGroupByFieldsPeticioDeFirma(peticioDeFirmaFilterForm, showUsuariEntitat,showUsuariAplicacio);

    AbstractPeticioDeFirmaAdenController.cleanFiltersAndGroups(peticioDeFirmaFilterForm);

    return peticioDeFirmaFilterForm;

  }

  @Override
  public String getEntityNameCode() {
    return "peticiodefirma.wsv1";
  }


  @Override
  public void postValidate(HttpServletRequest request, PeticioDeFirmaForm peticioDeFirmaForm,
      BindingResult result) throws I18NException {
    super.postValidate(request, peticioDeFirmaForm, result);

    switch (getOrigenPeticioDeFirma()) {

      case ORIGEN_PETICIO_DE_FIRMA_SOLICITANT_WEB:
      case ORIGEN_PETICIO_DE_FIRMA_API_PORTAFIB_WS_V1:
        // NO requereixen CONFIGURACIO DE FIRMA
      break;

      case ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_SIMPLE_WEB_V1:
      case ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_ASYNC_SIMPLE_V2:
        // Requereixen CONFIGURACIO DE FIRMA
        if (peticioDeFirmaForm.getPeticioDeFirma().getConfiguracioDeFirmaID() == null) {
          result.rejectValue(get(CONFIGURACIODEFIRMAID), "genapp.validation.required",
              new Object[] { I18NUtils.tradueix(CONFIGURACIODEFIRMAID.fullName) },
              "Camp Requerit");
        }
      break;

      default:
        // XYZ ZZZ TRA
        throw new I18NException("genapp.comodi", "getOrigenPeticioDeFirma = "
            + getOrigenPeticioDeFirma() + " desconegut");
    }
  }

}
