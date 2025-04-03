package es.caib.portafib.back.controller.revi;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_REVI_ESTATFIRMA_ACCEPTADA)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
@MenuOption(
        group = ConstantsV2.ROLE_REVI,
        labelCode = "revisor.acceptada.plural",
        order = 20)
public class EstatFirmaAcceptatReviController extends EstatFirmaAbstractReviController {

  
  @Override
  public int getFilterType() {   
    return FILTRAR_PER_ACCEPTAT; // == ACCEPTAT
  }


}
