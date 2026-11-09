package es.caib.portafib.back.controller.admin;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
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
import es.caib.portafib.persistence.PropietatGlobalJPA;

/**
 *
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = PropietatGlobalAdminController.CONTEXT_WEB)
@SessionAttributes(types = { PropietatGlobalForm.class, PropietatGlobalFilterForm.class })
@MenuOption(group = Tab.MENU_ADMIN, labelCode = "propietat.0.menu", order = 1010)
public class PropietatGlobalAdminController extends AbstractPropietatsController {

    public static final String CONTEXT_WEB = "/admin/propietatglobal";

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

            propietatGlobalFilterForm.setVisibleMultipleSelection(false);
            propietatGlobalFilterForm.setDeleteSelectedButtonVisible(false);

            propietatGlobalFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_RELOAD, "scheduler.reset",
                    getContextWeb() + "/resetschedulers", AdditionalButtonStyle.SUCCESS));

            propietatGlobalFilterForm
                    .addAdditionalButton(new AdditionalButton(IconUtils.ICON_LIST, "propietatsglobals.disponibles",
                            PropietatGlobalDisponibleAdminController.CONTEXT_WEB, AdditionalButtonStyle.INFO));

        }

        return propietatGlobalFilterForm;
    }

    /**
     * Afegir llistat de Propietats Globals disponibles de Administrador
#1160
     */
    @Override
    public PropietatGlobalForm getPropietatGlobalForm(PropietatGlobalJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        PropietatGlobalForm propietatGlobalForm = super.getPropietatGlobalForm(_jpa, __isView, request, mav);
        if (propietatGlobalForm.isNou()) {

            String clau = request.getParameter("clau");
            if (clau != null) {

                propietatGlobalForm.getPropietatGlobal().setClau(clau);
            }

            String descripcio = request.getParameter("descripcio");
            if (descripcio != null) {

                try {
                    descripcio = URLDecoder.decode(descripcio, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                propietatGlobalForm.getPropietatGlobal().setDescripcio(descripcio);
            }

        }

        return propietatGlobalForm;
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
