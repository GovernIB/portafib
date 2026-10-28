package es.caib.portafib.back.controller.all;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @autor anadal
 * 
 */
@Tile(name = "acessibilitat", extendsTile = Tab.MENU_INICI, type = TileType.ANOTHER, contentJsp = "/WEB-INF/jsp/all/acessibilitat.jsp")
@Controller
public class PublicController {

    protected final Logger log = Logger.getLogger(getClass());

    @RequestMapping(value = "/public/index.html")
    public ModelAndView principal(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Boolean initialized = (Boolean) session.getAttribute("inicialitzat");

        if (initialized == null) {
            HtmlUtils.saveMessageInfo(request, "MessageInfo : Benvingut a PortaFIB");
            session.setAttribute("inicialitzat", true);
        }

        return new ModelAndView("homepublic");

    }

    @RequestMapping(value = "/public/accessibilitat")
    public ModelAndView accessibilitat(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView mav = new ModelAndView("acessibilitat");
        mav.addObject("backurl", PropietatGlobalUtil.getAppUrl());
        return mav;
    }

}
