package es.caib.portafib.back.controller.soli;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.form.SeleccioFluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.*;

import es.caib.portafib.utils.ConstantsV2;


/**
 * Controller per gestionar Peticions De Firma en progres.
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_SOLI_PETICIOFIRMA_FIRMADA)
@SessionAttributes(types = { SeleccioFluxDeFirmesForm.class, PeticioDeFirmaForm.class,
    PeticioDeFirmaFilterForm.class, AnnexFilterForm.class, AnnexForm.class })
@MenuOption(
        group = ConstantsV2.ROLE_SOLI,
        labelCode = "peticiodefirma.firmada.plural",
        addSeparatorBefore = true,
        order = 40)
public class PeticioDeFirmaFirmadaSoliController extends PeticioDeFirmaSoliController {

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    Where pare = super.getAdditionalCondition(request);
    // Seleccionam només les peticions firmades
    Where fill = TIPUSESTATPETICIODEFIRMAID.equal(TIPUSESTATPETICIODEFIRMA_FIRMAT);

    return Where.AND(pare, fill);
  }
  
  
  @Override
  public final String getEntityNameCode() {
    return "peticiodefirma.firmada";
  }

  @Override
  public PeticioDeFirmaFilterForm getPeticioDeFirmaFilterForm(Integer pagina,
      ModelAndView mav, HttpServletRequest request) throws I18NException {

    PeticioDeFirmaFilterForm peticioDeFirmaFilterForm;
    peticioDeFirmaFilterForm = super.getPeticioDeFirmaFilterForm(pagina, mav, request);

    if (peticioDeFirmaFilterForm.isNou()) {

      peticioDeFirmaFilterForm.addHiddenField(TIPUSESTATPETICIODEFIRMAID);
      
      if (peticioDeFirmaFilterForm.getGroupByFields() != null) {
        peticioDeFirmaFilterForm.getGroupByFields().remove(TIPUSESTATPETICIODEFIRMAID);
      }
      
      peticioDeFirmaFilterForm.setVisibleMultipleSelection(true);
      

    }
    return peticioDeFirmaFilterForm;
  }

}
