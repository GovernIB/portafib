package es.caib.portafib.back.controller.admin;

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
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/propietatglobal")
@SessionAttributes(types = { PropietatGlobalForm.class, PropietatGlobalFilterForm.class })
@MenuOption(
        group = Tab.MENU_ADMIN,
        labelCode = "propietat.0.menu",
        order = 1010)
public class PropietatGlobalAdminController extends AbstractPropietatsController {

    @Override
    protected int getTipusPropietat() {
        return PropietatsConstants.TIPUS_PROPIETAT_BBDD_GLOBAL;
    }
}
