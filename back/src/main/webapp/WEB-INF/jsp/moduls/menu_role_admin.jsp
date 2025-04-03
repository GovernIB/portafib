<%@page import="es.caib.portafib.commons.utils.Configuracio"
%><%@page import="org.fundaciobit.genapp.common.web.menuoptions.MenuOptionManager"
%><%@page import="java.util.List"
%><%@page import="es.caib.portafib.back.utils.Tab"
%><%@page import="java.util.ArrayList"
%><%@page import="org.fundaciobit.genapp.common.web.menuoptions.MenuItem"
%><%@page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <div>
        <h5>
            <fmt:message key="ROLE_ADMIN.menu" />
        </h5>
        <%! 
        public static MenuItem retallaDarrerPath(String label, String url, int order) {
            int i = url.lastIndexOf('/');
            return new MenuItem(label, url, url.substring(0, i), order);
        }
        
        %>
        <%
        List<List<MenuItem>> menus = new ArrayList<List<MenuItem>>();
        /*
        MenuItem menuGoogle = new MenuItem("=MENU Google", "", "http://www.google.com", 0);
        
        MenuItem menumeneame = new MenuItem("=MENU Meneame", "", "http://www.meneame.net", 1000);
*/      
        List<MenuItem> menu1 = new ArrayList<MenuItem>();

        if (!Configuracio.isCAIB()) {
            menu1.add(retallaDarrerPath("usuariaplicacio.gestio", "/admin/usuariAplicacio/list", 110));
            menu1.add(null);
        }

        List<MenuItem> discoveredMenus = MenuOptionManager.getMenuItems(Tab.MENU_ADMIN, menu1.toArray(new MenuItem [menu1.size()])); //(menuGoogle, menumeneame );
        menus.add(discoveredMenus);
        %>
        
        <%@ include file="/WEB-INF/jsp/moduls/menu_role_generator.jsp"%>

    </div>
</sec:authorize>
