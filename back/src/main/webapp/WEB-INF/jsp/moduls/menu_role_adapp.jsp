<%@page import="es.caib.portafib.back.utils.menuoptions.MenuOptionManager"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="es.caib.portafib.back.utils.menuoptions.MenuItem"%>
<%@page import="es.caib.portafib.utils.ConstantsV2"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<sec:authorize access="hasRole('ROLE_ADEN')">
    <div>
        <h5>
            <fmt:message key="ROLE_ADAPP.menu" />
        </h5>
        <%
        List<List<MenuItem>> menus = new ArrayList<List<MenuItem>>();
        /*
        MenuItem menuGoogle = new MenuItem("=MENU Google", "", "http://www.google.com", 0);
        
        MenuItem menumeneame = new MenuItem("=MENU Meneame", "", "http://www.meneame.net", 1000);
*/
        List<MenuItem> discoveredMenus = MenuOptionManager.getMenuItems(ConstantsV2.ROLE_ADAPP); //, menuGoogle, menumeneame);
        menus.add(discoveredMenus);
        %>
        
        <%@ include file="/WEB-INF/jsp/moduls/menu_role_generator.jsp"%>

    </div>
</sec:authorize>
