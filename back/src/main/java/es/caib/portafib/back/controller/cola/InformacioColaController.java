package es.caib.portafib.back.controller.cola;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.utils.Tab;

/**
 * 
 * @author anadal
 * 7 abr 2025 14:48:59
 */
@Controller
@MenuOption(
        group = Tab.MENU_COLA,
        baseLink = "/cola/informacio",
        relativeLink = "",
        labelCode = "colaboracio.quees",
        order = 60,
        addSeparatorBefore = true)
public class InformacioColaController {

    /**
     * 
     * @param request
     * @return
     */
    @RequestMapping("/cola/informacio")
    public ModelAndView getInformacioCola(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("role_cola");

        HtmlUtils.saveMessageInfo(request, I18NUtils.tradueix("colaboracio.ajuda"));

        return modelAndView;
    }

}
