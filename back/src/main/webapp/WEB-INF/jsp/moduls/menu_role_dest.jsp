<%@page import="es.caib.portafib.back.utils.Tab"%>
<%@page import="es.caib.portafib.commons.utils.Constants"%>
<%@page import="es.caib.portafib.back.utils.Utils"%>
<%@page import="es.caib.portafib.back.security.LoginInfo"%>
<%@page import="es.caib.portafib.commons.utils.Configuracio"%>
<%@page import="org.fundaciobit.genapp.common.web.menuoptions.MenuOptionManager"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.fundaciobit.genapp.common.web.menuoptions.MenuItem"%>
<%@page import="es.caib.portafib.utils.ConstantsV2"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<sec:authorize access="hasRole('ROLE_DEST')">
    <div>
        <h5>
            <fmt:message key="ROLE_DEST.menu" />
        </h5>
        <%

        List<List<MenuItem>> menus = new ArrayList<List<MenuItem>>();

        List<MenuItem> menu1 = new ArrayList<MenuItem>();
        if (Configuracio.isDesenvolupament()) {
            menu1.add(Utils.retallaDarrerPath("solicituddefirma.llistat.totes.plural",
            ConstantsV2.CONTEXT_DEST_ESTATFIRMA + "/list", 10));
            menu1.add(null);
        }

        if (LoginInfo.getInstance().hasRole(Constants.ROLE_USER)) {
            menu1.add(null);
            menu1.add(Utils.retallaDarrerPath("colaboracio.gestio", "/dest/colaborador/list", 60));
            menu1.add(null);
            menu1.add(Utils.retallaDarrerPath("delegacio.gestio", "/dest/delegat/list",70));
            menu1.add(null);
            menu1.add(Utils.retallaDarrerPath("revisor.gestio", "/dest/revisordedestinatari/list",80));
        }

        List<MenuItem> discoveredMenus = MenuOptionManager.getMenuItems(Tab.MENU_DEST, menu1.toArray(new MenuItem [menu1.size()])); //(menuGoogle, menumeneame );


        menus.add(discoveredMenus);
        pageContext.setAttribute("menu", discoveredMenus);

        %>
        
        <%@ include file="/WEB-INF/jsp/moduls/menu_role_generator.jsp"%>

    </div>
</sec:authorize>
