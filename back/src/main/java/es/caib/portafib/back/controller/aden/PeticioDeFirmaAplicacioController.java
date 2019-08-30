package es.caib.portafib.back.controller.aden;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.soli.PeticioDeFirmaSoliController;
import es.caib.portafib.back.controller.webdb.PeticioDeFirmaController;
import es.caib.portafib.back.form.SeleccioFluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.AnnexFilterForm;
import es.caib.portafib.back.form.webdb.AnnexForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.model.entity.PeticioDeFirma;
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
  
  
  
  
  
  


  @RequestMapping(value = "/rebutjar/{peticioDeFirmaID}")
  public String rebutjar(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long peticioDeFirmaID) throws I18NException {

    rebutjar(request, peticioDeFirmaID);

    return getRedirectWhenDelete(request, peticioDeFirmaID, null);
  }



  @RequestMapping(value = "/rebutjarSeleccionades", method = RequestMethod.POST)
  public String rebutjarSeleccionades(HttpServletRequest request,
      HttpServletResponse response, @ModelAttribute PeticioDeFirmaFilterForm filterForm) {

    String[] seleccionats = filterForm.getSelectedItems();

    if (seleccionats != null && seleccionats.length != 0) {
      for (int i = 0; i < seleccionats.length; i++) {
        rebutjar(request, Long.parseLong(seleccionats[i]));
      }
    }

    return getRedirectWhenDelete(request, null, null);
  }


  protected PeticioDeFirmaJPA rebutjar(HttpServletRequest request, Long peticioDeFirmaID) {
    try {
      // TODO ha d'anar a la part de lògica
      PeticioDeFirmaJPA peticioDeFirma = peticioDeFirmaLogicaEjb
          .findByPrimaryKeyFull(peticioDeFirmaID);
      if (peticioDeFirma == null) {
        // Error
        new PeticioDeFirmaController().createMessageError(request, "error.notfound", null);
      } else {
        
        String motiuDeRebuig2 = request.getParameter("motiuRebuig");

        String motiuDeRebuig = I18NUtils.tradueix("aturarpeticionsdefirma.motiurebuig",
            Utils.getNom(LoginInfo.getInstance().getUsuariPersona()), motiuDeRebuig2);


        peticioDeFirmaLogicaEjb.rebutjarADEN(peticioDeFirma, LoginInfo.getInstance()
            .getUsuariEntitatID(), motiuDeRebuig);

      }
      
      return peticioDeFirma;

    } catch (I18NException i18ne) {
      HtmlUtils.saveMessageError(request, I18NUtils.getMessage(i18ne));
      return null;
    }
    
   
  }
  
  
  
  @Override
  public void postList(HttpServletRequest request, ModelAndView mav,
      PeticioDeFirmaFilterForm filterForm, List<PeticioDeFirma> list) throws I18NException {

    super.postList(request, mav, filterForm, list);

    switch (getOrigenPeticioDeFirma()) {

      case ORIGEN_PETICIO_DE_FIRMA_SOLICITANT_WEB:
      break;

      case ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_ASYNC_SIMPLE_V2:
      case ORIGEN_PETICIO_DE_FIRMA_API_PORTAFIB_WS_V1:
      // REBUTJAR
      {

        boolean mostrarBotoGlobal = false;

        Long key;
        int estat;
        for (PeticioDeFirma peticio : list) {
          key = peticio.getPeticioDeFirmaID();
          estat = peticio.getTipusEstatPeticioDeFirmaID();
          if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES
              || estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT) {
            mostrarBotoGlobal = true;
            filterForm.addAdditionalButtonByPK(key, new AdditionalButton("icon-remove",
                "rebutjar", "javascript:rebutjar(" + key + ");", "btn-warning"));
          }
        }

        if (mostrarBotoGlobal) {
          filterForm.addAdditionalButton(new AdditionalButton("icon-remove",
              "rebutjarseleccionats", "javascript:rebutjarseleccionades();", "btn-warning"));

          filterForm.setAttachedAdditionalJspCode(true);
          filterForm.setVisibleMultipleSelection(true);

        }

      }
      break;

      default:
        // XYZ ZZZ TRA
        throw new I18NException("genapp.comodi", "No hi ha codi per gestionar "
            + "el PostList en PeticioDeFirmaAplicacioController amb Origen "
            + I18NUtils.tradueix("origenpeticiodefirma." + getOrigenPeticioDeFirma()));

    } // Final Switch
  }

}
