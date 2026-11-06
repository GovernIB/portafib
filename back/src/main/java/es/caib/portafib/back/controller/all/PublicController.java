package es.caib.portafib.back.controller.all;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.admin.AdminController;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @autor anadal
 * 
 */
@Tile(
        name = "accessibilitat",
        extendsTile = Tab.MENU_INICI,
        type = TileType.ANOTHER,
        contentJsp = "/WEB-INF/jsp/all/accessibilitat.jsp")
@Tile(
        name = PublicController.KEYVALUE_PUBLIC_TILE,
        extendsTile = Tab.MENU_INICI,
        contentJsp = "/WEB-INF/jsp/common/keyvalue.jsp",
        attributes = { @TileAttribute(name = "titol", value = "public.public") },
        type = TileType.ANOTHER)
@Controller
public class PublicController {

    public static final String KEYVALUE_PUBLIC_TILE = "keyvaluePublic";

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
        ModelAndView mav = new ModelAndView("accessibilitat");
        mav.addObject("backurl", PropietatGlobalUtil.getAppUrl());
        return mav;
    }

    @RequestMapping(value = "/public/mapaweb")
    public ModelAndView mapWeb(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        /*
        String[] tabs = { Tab.MENU_INICI, Tab.MENU_SOLI, Tab.MENU_DEST, Tab.MENU_DELE, Tab.MENU_COLA, Tab.MENU_REVI,
                Tab.MENU_ADEN, Tab.MENU_ADAPP, Tab.MENU_ADMIN };
        */

        LoginInfo loginInfo = LoginInfo.getInstance();
        Set<GrantedAuthority> roles = loginInfo.getRoles();

        List<String> tabsList = new ArrayList<>();

        /*
         *     public static final String ROLE_ADMIN = "ROLE_ADMIN";
        public static final String ROLE_USER = "ROLE_USER";
        public static final String ROLE_USUARI_TIPUS_I = "ROLE_USUARI_TIPUS_I";
        
        // ROLE Any (tothom)
        public static final String ROLE_ANY = "ROLE_ANY";
        // Destinatari
        public static final String ROLE_DEST = "ROLE_DEST";
        // Delegat
        public static final String ROLE_DELE = "ROLE_DELE";
        // Col·laborador
        public static final String ROLE_COLA = "ROLE_COLA";
        // Administrador d''Entitat
        public static final String ROLE_ADEN = "ROLE_ADEN";
        // Administrador d'Aplicacions
        public static final String ROLE_ADAPP = "ROLE_ADAPP";
        // Sol·licitant
        public static final String ROLE_SOLI = "ROLE_SOLI";
        // Revisor
        public static final String ROLE_REVI = "ROLE_REVI";
         */

        tabsList.add(Tab.MENU_INICI);

        // Mapeig de roles a tabs
        for (GrantedAuthority role : roles) {
            String roleName = role.getAuthority();
            switch (roleName) {
                case Constants.ROLE_ADMIN:
                    tabsList.add(Tab.MENU_ADMIN);
                break;
                case Constants.ROLE_ANY:
                    // Any usuari autenticat ve l'inici
                    tabsList.add(Tab.MENU_INICI);
                break;
                case Constants.ROLE_USUARI_TIPUS_I:
                case Constants.ROLE_DEST:
                    tabsList.add(Tab.MENU_DEST);
                break;
                case Constants.ROLE_DELE:
                    tabsList.add(Tab.MENU_DELE);
                break;
                case Constants.ROLE_COLA:
                    tabsList.add(Tab.MENU_COLA);
                break;
                case Constants.ROLE_ADEN:
                    tabsList.add(Tab.MENU_ADEN);
                break;
                case Constants.ROLE_ADAPP:
                    tabsList.add(Tab.MENU_ADAPP);
                break;
                case Constants.ROLE_SOLI:
                    tabsList.add(Tab.MENU_SOLI);
                break;
                case Constants.ROLE_REVI:
                    tabsList.add(Tab.MENU_REVI);
                break;
                default:
                // Rol no reconegut, ignorar
                break;
            }
        }

        // Eliminar duplicats i convertir a array
        String[] tabs = tabsList.stream().distinct().toArray(String[]::new);

        String title = I18NUtils.tradueix("label.mapaweb");
        String subtitle = "";
        String tile = KEYVALUE_PUBLIC_TILE;

        return AdminController.webPages(session, request, response, tabs, title, subtitle, tile);

    }

}
