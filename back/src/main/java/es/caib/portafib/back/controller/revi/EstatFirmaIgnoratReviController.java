package es.caib.portafib.back.controller.revi;

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
@RequestMapping(value = ConstantsV2.CONTEXT_REVI_ESTATFIRMA_DESCARTAT)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
@MenuOption(group = Tab.MENU_REVI, labelCode = "revisor.ignorada.plural", order = 40)
public class EstatFirmaIgnoratReviController extends EstatFirmaAbstractReviController {

    @Override
    public final int getFilterType() {
        return FILTRAR_PER_NODEFINIT; // == DESCARTAT
    }

}
