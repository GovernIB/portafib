package es.caib.portafib.back.controller.admin;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.CorreuAgrupatController;
import es.caib.portafib.back.form.webdb.CorreuAgrupatFilterForm;
import es.caib.portafib.back.form.webdb.CorreuAgrupatForm;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.logic.CorreuAgrupatLogicaLocal;

/**
 * 
 * @author anadal
 * 28 ago 2025 11:48:27
 */
@MenuOption(labelCode = "correuAgrupat.correuAgrupat.plural", order = 152, group = Tab.MENU_ADMIN)
@Controller
@RequestMapping(value = "/admin/correuagrupat")
@SessionAttributes(types = { CorreuAgrupatForm.class, CorreuAgrupatFilterForm.class })
/*
@Tile(
        name = "correuAgrupatFormAdmin",
        extendsTile = Tab.MENU_ADMIN,
        type = TileType.WEBDB_FORM)
@Tile(
        name = "correuAgrupatListAdmin",        
        extendsTile = Tab.MENU_ADMIN,
        type = TileType.WEBDB_LIST)
*/
public class CorreuAgrupatAdminController extends CorreuAgrupatController {

    @EJB(mappedName = CorreuAgrupatLogicaLocal.JNDI_NAME)
    protected CorreuAgrupatLogicaLocal correuAgrupatLogicaEjb;

    @Override
    public String getTileForm() {
        return "correuAgrupatFormAdmin";
    }

    @Override
    public String getTileList() {
        return "correuAgrupatListAdmin";
    }

    @Override
    public CorreuAgrupatFilterForm getCorreuAgrupatFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        CorreuAgrupatFilterForm correuAgrupatFilterForm = super.getCorreuAgrupatFilterForm(pagina, mav, request);

        if (correuAgrupatFilterForm.isNou()) {
            correuAgrupatFilterForm.addHiddenField(CORREUAGRUPATID);
            correuAgrupatFilterForm.addHiddenField(HTML);
            correuAgrupatFilterForm.addHiddenField(USUARIENTITATID);

            correuAgrupatFilterForm.setAddButtonVisible(false);

            correuAgrupatFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(IconUtils.ICON_ENVELOPE,
                    "agruparcorreus.enviar", getContextWeb() + "/enviar/{0}", AdditionalButtonStyle.WARNING));

        }

        return correuAgrupatFilterForm;
    }

    /**
     * Request mapping per enviar un correu agrupat a partir del seu ID
     */
    @RequestMapping(value = "/enviar/{correuAgrupatId}")
    public ModelAndView enviarCorreuAgrupat(HttpServletRequest request, @PathVariable("correuAgrupatId")
    Long correuAgrupatId) throws I18NException {
        ModelAndView mav = new ModelAndView("redirect:" + getContextWeb() + "/list");

        if (correuAgrupatId == null) {
            HtmlUtils.saveMessageError(request, "No s'ha indicat cap ID de correu agrupat");
        } else {
            String error = correuAgrupatLogicaEjb.enviarCorreuAgrupat(correuAgrupatId);
            if (error == null) {
                // XYZ ZZZ TRA 
                HtmlUtils.saveMessageSuccess(request, "Correu enviat correctament");
            } else {
                // XYZ ZZZ TRA
                HtmlUtils.saveMessageError(request, "Error enviant correu agrupat: " + error);
            }
        }

        return mav;
    }

}
