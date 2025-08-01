package es.caib.portafib.back.controller.adapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.AbstractCustodiaInfoController;
import es.caib.portafib.back.form.webdb.CustodiaInfoFilterForm;
import es.caib.portafib.back.form.webdb.CustodiaInfoForm;
import es.caib.portafib.back.utils.Tab;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = CustodiaInfoAdappController.ADEN_CUSTODIA_CONTEXT)
@SessionAttributes(types = { CustodiaInfoForm.class, CustodiaInfoFilterForm.class })
@MenuOption(group = Tab.MENU_ADAPP, labelCode = "custodiaInfo.custodiaInfo.plural", order = 170)
public class CustodiaInfoAdappController extends AbstractCustodiaInfoController {

    public static final String ADEN_CUSTODIA_CONTEXT = "/adapp/peticio/custodiainfo";

    /**
     * 
     */
    @Override
    public boolean isSolicitantWeb() {
        return false;
    }
}
