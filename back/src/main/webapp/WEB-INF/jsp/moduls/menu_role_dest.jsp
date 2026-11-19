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
        <span style="font-size: 1.25rem">
            <fmt:message key="ROLE_DEST.menu" />
        </span>
        <%
        List<List<MenuItem>> menus = new ArrayList<List<MenuItem>>();
        {
            List<MenuItem> discoveredMenus = MenuOptionManager.getMenuItems(request, Tab.MENU_DEST);
            menus.add(discoveredMenus);
        }
        %>
        <%@ include file="/WEB-INF/jsp/moduls/menu_role_generator.jsp"%>
    </div>
</sec:authorize>
