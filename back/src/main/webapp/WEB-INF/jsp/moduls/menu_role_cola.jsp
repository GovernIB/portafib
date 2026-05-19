<%@page import="es.caib.portafib.back.utils.Utils"%>
<%@page import="es.caib.portafib.back.utils.Tab"%>
<%@page import="es.caib.portafib.commons.utils.Configuracio"%>
<%@page import="org.fundaciobit.genapp.common.web.menuoptions.MenuOptionManager"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.fundaciobit.genapp.common.web.menuoptions.MenuItem"%>
<%@page import="es.caib.portafib.utils.ConstantsV2"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<sec:authorize access="hasRole('ROLE_COLA')">
    <div>
        <h5>
            <fmt:message key="ROLE_COLA.menu" />
        </h5>
        <%
        List<List<MenuItem>> menus = new ArrayList<List<MenuItem>>();
        /*
        MenuItem menuGoogle = new MenuItem("=MENU Google", "", "http://www.google.com", 0);
        
        MenuItem menumeneame = new MenuItem("=MENU Meneame", "", "http://www.meneame.net", 1000);
*/      
        List<MenuItem> menu1 = new ArrayList<MenuItem>();
        if (Configuracio.isDesenvolupament()) {
            menu1.add(Utils.retallaDarrerPath("colaboracio.totes.plural", ConstantsV2.CONTEXT_COLA_ESTATFIRMA  + "/list", 10));
            menu1.add(null);
        }


        List<MenuItem> discoveredMenus = MenuOptionManager.getMenuItems(Tab.MENU_COLA, menu1.toArray(new MenuItem [menu1.size()]));
        menus.add(discoveredMenus);
        %>
        
        <%@ include file="/WEB-INF/jsp/moduls/menu_role_generator.jsp"%>

    </div>
</sec:authorize>
