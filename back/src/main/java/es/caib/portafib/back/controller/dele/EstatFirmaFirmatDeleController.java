package es.caib.portafib.back.controller.dele;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_DELE_ESTATFIRMA_FIRMAT)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
@MenuOption(
        group = Tab.MENU_DELE,
        labelCode = "delegacio.acceptada.plural",
        order = 30)
public class EstatFirmaFirmatDeleController extends EstatFirmaAbstractDeleController {

  
  @Override
  public int getFilterType() {   
    return FILTRAR_PER_ACCEPTAT; // == FIRMAT
  }


}
