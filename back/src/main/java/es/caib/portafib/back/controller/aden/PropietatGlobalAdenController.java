package es.caib.portafib.back.controller.aden;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.AbstractPropietatsController;
import es.caib.portafib.back.form.webdb.PropietatGlobalFilterForm;
import es.caib.portafib.back.form.webdb.PropietatGlobalForm;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.logic.utils.PropietatsConstants;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = "/aden/propietatglobal")
@SessionAttributes(types = { PropietatGlobalForm.class, PropietatGlobalFilterForm.class })
@MenuOption(group = Tab.MENU_ADEN, labelCode = "propietat.0.menu", order = 20)
public class PropietatGlobalAdenController extends AbstractPropietatsController {

    @Override
    public String getTileForm() {
        return "propietatGlobalFormAden";
    }

    @Override
    public String getTileList() {
        return "propietatGlobalListAden";
    }

    @Override
    protected int getTipusPropietat() {
        return PropietatsConstants.TIPUS_PROPIETAT_BBDD_PER_ENTITAT;
    }

}
