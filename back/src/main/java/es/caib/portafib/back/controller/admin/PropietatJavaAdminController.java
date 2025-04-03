package es.caib.portafib.back.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.AbstractPropietatsController;
import es.caib.portafib.back.form.webdb.PropietatGlobalFilterForm;
import es.caib.portafib.back.form.webdb.PropietatGlobalForm;
import es.caib.portafib.logic.utils.PropietatsConstants;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = "/admin/propietatsjava")
@SessionAttributes(types = { PropietatGlobalForm.class, PropietatGlobalFilterForm.class })
public class PropietatJavaAdminController extends AbstractPropietatsController {

    @Override
    protected int getTipusPropietat() {
        return PropietatsConstants.TIPUS_PROPIETAT_JAVA;
    }


}
