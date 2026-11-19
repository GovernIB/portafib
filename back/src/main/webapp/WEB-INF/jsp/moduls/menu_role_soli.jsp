<%@page import="es.caib.portafib.back.utils.Tab"%>
<%@page import="org.fundaciobit.genapp.common.web.menuoptions.MenuOptionManager"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.fundaciobit.genapp.common.web.menuoptions.MenuItem"%>
<%@page import="es.caib.portafib.utils.ConstantsV2"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<sec:authorize access="hasRole('ROLE_SOLI')">
    <div>
        <span style="font-size: 1.25rem">
            <fmt:message key="ROLE_SOLI.menu" />
        </span>
        <%
        List<List<MenuItem>> menus = new ArrayList<List<MenuItem>>(); 
        {
            List<MenuItem> discoveredMenus = MenuOptionManager.getMenuItems(request, Tab.MENU_SOLI); //, menuGoogle, menumeneame);
            menus.add(discoveredMenus);
        }
        %>
        <%@ include file="/WEB-INF/jsp/moduls/menu_role_generator.jsp"%>
    </div>
</sec:authorize>
