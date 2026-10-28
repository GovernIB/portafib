<%@page import="es.caib.portafib.commons.utils.Constants"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.authority.SimpleGrantedAuthority"%>
<%@page import="java.util.Collection"%>
<%@page import="es.caib.portafib.back.utils.Tab"%>
<%@page import="org.fundaciobit.genapp.common.web.menuoptions.MenuOptionManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="es.caib.portafib.back.security.LoginInfo"%>
<%@page import="es.caib.portafib.logic.utils.PropietatGlobalUtil"%>
<%@page import="es.caib.portafib.commons.utils.Configuracio"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%
List<String> roles = new ArrayList<String>();
{
    Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
    .getContext().getAuthentication().getAuthorities();
    for (SimpleGrantedAuthority authority : authorities) {
        roles.add(authority.getAuthority());
        //System.out.println("ROLE MENU: " + authority.getAuthority());
    }
}
%>
<c:set var="url" value="${urlActual}" />

<div>
    <span style="font-size: 1.25rem"> <fmt:message key="menuinici" />
    </span>
    <%
    List<MenuItem> optionalMenus = new ArrayList<MenuItem>();

    optionalMenus.add(new MenuItem("pagina.principal", "/common/principal.html", "/common/principal.html", 10));

    Boolean autofirma = PropietatGlobalUtil.getAutofirmaAllowed(LoginInfo.getInstance().getEntitatID());
    if (autofirma == null) {
        autofirma = request.isUserInRole("ROLE_AUTOFIRMA");
    }
    if (LoginInfo.getInstance().getEntitatID() != null && autofirma) {
        optionalMenus.add(null); // Separator
        optionalMenus.add(new MenuItem("autofirma.gestio", "/common/autofirma/list", "/common/autofirma", 20));
    }
    %>


    <!-- RebreAvis -->


    <%
    if (roles.contains(Constants.ROLE_SOLI) || roles.contains(Constants.ROLE_DEST) || roles.contains(Constants.ROLE_COLA)
            || roles.contains(Constants.ROLE_DELE) || roles.contains(Constants.ROLE_REVI)) {
        optionalMenus.add(null); // Separator
        optionalMenus.add(new MenuItem("notificaciocorreu.plural", "/common/rebreAvis/list", "/common/rebreAvis", 30));
    }
    %>




    <%
    if (LoginInfo.getInstance().getUsuariPersona() != null) {
        optionalMenus.add(null); // Separator
        optionalMenus.add(new MenuItem(
        "inici.configuracio.usuaripersona", "/common/configuracio/usuaripersona/"
                + LoginInfo.getInstance().getUsuariPersona().getUsuariPersonaID() + "/edit",
        "/common/configuracio/usuaripersona/", 40));
    }

    if (LoginInfo.getInstance().getUsuariPersona() != null && LoginInfo.getInstance().getEntitatID() != null) {
        optionalMenus.add(new MenuItem(
        "inici.configuracio.usuarientitat", "/common/configuracio/usuarientitat/"
                + LoginInfo.getInstance().getUsuariEntitat().getUsuariEntitatID() + "/edit",
        "/common/configuracio/usuarientitat/", 50));
    }

    if (roles.contains(Constants.ROLE_USER) && (roles.contains(Constants.ROLE_SOLI) || roles.contains(Constants.ROLE_DEST)
            || roles.contains(Constants.ROLE_ADEN))) {
        optionalMenus.add(null); // Separator
        optionalMenus.add(
        new MenuItem("favorit.gestio", "/common/usuariEntitatFavorit/list/1", "/common/usuariEntitatFavorit/", 60));
    }

    if (roles.contains(Constants.ROLE_USER) && (roles.contains(Constants.ROLE_SOLI) || roles.contains(Constants.ROLE_DEST)
            || roles.contains(Constants.ROLE_COLA) || roles.contains(Constants.ROLE_DELE)
            || roles.contains(Constants.ROLE_ADEN))) {
        optionalMenus.add(null); // Separator
        optionalMenus.add(new MenuItem("carrec.llistat", "/common/carrecs/list/1", "/common/carrecs/", 70));
    }

    optionalMenus.add(null); // Separator

    optionalMenus.add(new MenuItem("manualusuari", "/doc/Manual_de_Usuari_de_PortaFIB.pdf",
            "/doc/Manual_de_Usuari_de_PortaFIB.pdf", 80));

    String androidApk = Configuracio.getAndroidApk();
    pageContext.setAttribute("androidApk", androidApk);
    if (androidApk != null && !androidApk.isEmpty()) {
        optionalMenus.add(null); // Separator
        optionalMenus.add(new MenuItem("appmobil", "/common/app.html", "/common/app.html", 90));
    }

    List<List<MenuItem>> menus = new ArrayList<List<MenuItem>>();

    menus.add(optionalMenus);
    List<MenuItem> discoveredMenus = MenuOptionManager.getMenuItems(Tab.MENU_INICI); //, menuGoogle, menumeneame);
    menus.add(discoveredMenus);
    %>

    <%@ include file="/WEB-INF/jsp/moduls/menu_role_generator.jsp"%>

</div>

