package es.caib.portafib.back.controller.soli;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.AbstractCustodiaInfoController;
import es.caib.portafib.back.form.webdb.CustodiaInfoFilterForm;
import es.caib.portafib.back.form.webdb.CustodiaInfoForm;
import es.caib.portafib.back.utils.Tab;


/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = CustodiaInfoSoliController.SOLI_CUSTODIA_CONTEXT)
@SessionAttributes(types = { CustodiaInfoForm.class, CustodiaInfoFilterForm.class })
@MenuOption(
        group = Tab.MENU_SOLI,
        labelCode = "custodiaInfo.custodiaInfo.plural",
        addSeparatorBefore = true,
        order = 60)
public class CustodiaInfoSoliController extends AbstractCustodiaInfoController {

    public static final String SOLI_CUSTODIA_CONTEXT = "/soli/peticio/custodiainfo";

    /**
     * 
     * @return
     */
    @Override
    public boolean isSolicitantWeb() {
        return true;
    }

}
