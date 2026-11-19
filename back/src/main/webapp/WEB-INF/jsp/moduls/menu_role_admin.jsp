<%@page import="es.caib.portafib.back.utils.Utils"%>
<%@page import="es.caib.portafib.commons.utils.Configuracio"%><%@page
    import="org.fundaciobit.genapp.common.web.menuoptions.MenuOptionManager"%><%@page import="java.util.List"%><%@page
    import="es.caib.portafib.back.utils.Tab"%><%@page import="java.util.ArrayList"%><%@page
    import="org.fundaciobit.genapp.common.web.menuoptions.MenuItem"%><%@page contentType="text/html;charset=UTF-8" language="java"%><%@ include
    file="/WEB-INF/jsp/moduls/includes.jsp"%>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <div>
        <span style="font-size: 1.25rem"> <fmt:message key="ROLE_ADMIN.menu" />
        </span>

        <%
        List<List<MenuItem>> menus = new ArrayList<List<MenuItem>>();
        {
            List<MenuItem> discoveredMenus = MenuOptionManager.getMenuItems(request, Tab.MENU_ADMIN);
            menus.add(discoveredMenus);
        }
        %>

        <%@ include file="/WEB-INF/jsp/moduls/menu_role_generator.jsp"%>

    </div>
</sec:authorize>
