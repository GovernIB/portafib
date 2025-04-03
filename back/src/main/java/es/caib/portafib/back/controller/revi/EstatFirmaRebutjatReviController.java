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
@RequestMapping(value = ConstantsV2.CONTEXT_REVI_ESTATFIRMA_REBUTJAT)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
@MenuOption(
        group = ConstantsV2.ROLE_REVI,
        labelCode = "revisor.noacceptada.plural",
        order = 30)
public class EstatFirmaRebutjatReviController extends EstatFirmaAbstractReviController {
  
  @Override
  public final int getFilterType() {   
    return FILTRAR_PER_NOACCEPTAT; // == REBUTJAT
  }

}
