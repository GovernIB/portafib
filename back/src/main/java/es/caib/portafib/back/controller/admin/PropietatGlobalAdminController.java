package es.caib.portafib.back.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.AbstractPropietatsController;
import es.caib.portafib.back.form.webdb.PropietatGlobalFilterForm;
import es.caib.portafib.back.form.webdb.PropietatGlobalForm;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.logic.scheduler.AbstractScheduler;
import es.caib.portafib.logic.utils.PropietatsConstants;

/**
 *
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/propietatglobal")
@SessionAttributes(types = { PropietatGlobalForm.class, PropietatGlobalFilterForm.class })
@MenuOption(group = Tab.MENU_ADMIN, labelCode = "propietat.0.menu", order = 1010)
public class PropietatGlobalAdminController extends AbstractPropietatsController {

    @Override
    protected int getTipusPropietat() {
        return PropietatsConstants.TIPUS_PROPIETAT_BBDD_GLOBAL;
    }

    /** En la configuració del llistat afegir un boto per resetejar els schedulers registrats a AbstractScheduler */
    public PropietatGlobalFilterForm getPropietatGlobalFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        PropietatGlobalFilterForm propietatGlobalFilterForm;
        propietatGlobalFilterForm = super.getPropietatGlobalFilterForm(pagina, mav, request);
        if (propietatGlobalFilterForm.isNou()) {

            propietatGlobalFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_RELOAD, "scheduler.reset",
                    getContextWeb() + "/resetschedulers", AdditionalButtonStyle.SUCCESS));

        }

        return propietatGlobalFilterForm;
    }

    @GET
    @RequestMapping(value = "/resetschedulers")
    public String resetSchedulers(ModelAndView mav, HttpServletRequest request) throws I18NException {
        String info = AbstractScheduler.resetSchedulers();

        HtmlUtils.saveMessageInfo(request,
                "Schedulers reiniciats correctament. <br/>" + info.replaceAll("\n", "<br/>"));

        return "redirect:" + getContextWeb() + "/list";
    }

}
