package es.caib.portafib.back.controller.dest;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.aden.GestioRoleRevisorController;
import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.webdb.RoleUsuariEntitatFilterForm;
import es.caib.portafib.back.form.webdb.RoleUsuariEntitatForm;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = "/dest/revisor")
@SessionAttributes(types = { RoleUsuariEntitatForm.class, RoleUsuariEntitatFilterForm.class, SeleccioUsuariForm.class })
public class GestioRoleRevisorDestController extends GestioRoleRevisorController {

    @Override
    public String getTileForm() {
        return "gestioRoleRevisorDestForm";
    }

    @Override
    public String getTileList() {
        // NO S'USARÀ, JA QUE NO HI HA LLISTAT
        return "gestioRoleRevisorDestList";
    }

    @Override
    protected String getTileSeleccioUsuari() {
        return "seleccioUsuariForm_DEST";
    }
    
    @Override
    protected String getRole() {
        return ConstantsV2.ROLE_DEST;
    }
    
    
    @Override
    public void initEditRoleForm(RoleUsuariEntitatForm roleUsuariEntitatForm, HttpServletRequest request,
            ModelAndView mav, UsuariPersona usuariPersona) throws I18NException {
        super.initEditRoleForm(roleUsuariEntitatForm, request, mav, usuariPersona);
        roleUsuariEntitatForm.setDeleteButtonVisible(false);
    }
}
