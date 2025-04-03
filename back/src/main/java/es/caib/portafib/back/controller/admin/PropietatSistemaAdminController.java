package es.caib.portafib.back.controller.admin;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.form.webdb.PropietatGlobalFilterForm;
import es.caib.portafib.back.form.webdb.PropietatGlobalForm;
import es.caib.portafib.logic.utils.PropietatsConstants;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = "/admin/propietatsistema")
@SessionAttributes(types = { PropietatGlobalForm.class, PropietatGlobalFilterForm.class })
@MenuOption(
        group = ConstantsV2.ROLE_ADMIN,
        labelCode = "propietatSistema.menu",
        order = 130)
public class PropietatSistemaAdminController extends PropietatGlobalAdminController {

    @Override
    public String getTileForm() {
        return "propietatSistemaFormAdmin";
    }

    @Override
    public String getTileList() {
        return "propietatSistemaListAdmin";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "PropietatSistemaAdmin_FilterForm";
    }

    @Override
    protected int getTipusPropietat() {
        return PropietatsConstants.TIPUS_PROPIETAT_SISTEMA;
    }

    @Override
    public String getEntityNameCode() {
        return "propietatSistema";
    }

    @Override
    public String getEntityNameCodePlural() {
        return "propietatSistema.plural";
    }

}
