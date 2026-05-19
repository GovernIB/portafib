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
    <h5>
        <fmt:message key="menuinici" />
    </h5>
    <%
    List<MenuItem> optionalMenus = new ArrayList<MenuItem>();

    /*
    <ul class="tree" style="margin: 3px; padding: 0px;">

        <li style="list-style-type: disc; list-style-position: inside;">
    <a href="<c:url value="/common/principal.html"/>"><span
        style="${(fn:contains(url, 'principal'))? "font-weight:bold;" : ""}"><fmt:message
        key="pagina.principal" /></span>
        </a>
        </li>
        */

    optionalMenus.add(new MenuItem("pagina.principal", "/common/principal.html", "/common/principal.html", 10));

    /*

    <c:if test="${not empty loginInfo.entitatID && autofirma}">
    <hr style="margin-top: 6px; margin-bottom: 6px;" />
    <li
    style="list-style-type: disc; list-style-position: inside;">
    <a href="<c:url value="/common/autofirma/list"/>"><span
        style="${(fn:contains(url, 'autofirma'))? "font-weight:bold;" : ""} }"><fmt:message
        key="autofirma.gestio" /></span>
    </a>
    </li>
    </c:if>
    */
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
    /*
    <sec:authorize access="hasAnyRole('ROLE_SOLI', 'ROLE_DEST', 'ROLE_COLA', 'ROLE_DELE', 'ROLE_REVI')">
    <hr style="margin-top: 6px; margin-bottom: 6px;" />
    <li
    style="list-style-type: disc; list-style-position: inside;"><a
    href="<c:url value="/common/rebreAvis/list/1"/>"><span
    style="${(fn:contains(url, 'rebreAvis/') && fn:contains(url, '/list'))? "font-weight:bold;" : ""}" ><fmt:message
            key="notificaciocorreu.plural" /></span></a></li>
            </sec:authorize>

    */
    if (roles.contains(Constants.ROLE_SOLI) || roles.contains(Constants.ROLE_DEST) || roles.contains(Constants.ROLE_COLA)
            || roles.contains(Constants.ROLE_DELE) || roles.contains(Constants.ROLE_REVI)) {
        optionalMenus.add(null); // Separator
        optionalMenus.add(new MenuItem("notificaciocorreu.plural", "/common/rebreAvis/list", "/common/rebreAvis", 30));
    }
    %>




    <%
    /*
    <c:if test="${not empty loginInfo.usuariPersona}">
    <hr style="margin-top: 6px; margin-bottom: 6px;" />




    <li
    style="list-style-type: disc; list-style-position: inside;"><a
    href="<c:url value="/common/configuracio/usuaripersona/${loginInfo.usuariPersona.usuariPersonaID}/edit"/>"><span
    style="${(fn:contains(url, 'configuracio/usuaripersona') && fn:contains(url, '/edit'))? "font-weight:bold;" : ""}" ><fmt:message
            key="inici.configuracio.usuaripersona" /></span></a></li>
            </c:if>
            */

    if (LoginInfo.getInstance().getUsuariPersona() != null) {
        optionalMenus.add(null); // Separator
        optionalMenus.add(new MenuItem(
        "inici.configuracio.usuaripersona", "/common/configuracio/usuaripersona/"
                + LoginInfo.getInstance().getUsuariPersona().getUsuariPersonaID() + "/edit",
        "/common/configuracio/usuaripersona/", 40));
    }

    /*
    <c:if test="${not empty loginInfo.usuariPersona && not empty loginInfo.entitatID  }">
    <li
    style="list-style-type: disc; list-style-position: inside;"><a
    href="<c:url value="/common/configuracio/usuarientitat/${loginInfo.usuariEntitatID}/edit"/>"><span
    style="${(fn:contains(url, 'configuracio/usuarientitat') && fn:contains(url, '/edit'))? "font-weight:bold;" : ""}" ><fmt:message
        key="inici.configuracio.usuarientitat" /></span></a></li>

    </c:if>
    */

    if (LoginInfo.getInstance().getUsuariPersona() != null && LoginInfo.getInstance().getEntitatID() != null) {
        optionalMenus.add(new MenuItem(
        "inici.configuracio.usuarientitat", "/common/configuracio/usuarientitat/"
                + LoginInfo.getInstance().getUsuariEntitat().getUsuariEntitatID() + "/edit",
        "/common/configuracio/usuarientitat/", 50));
    }

    /*
    <sec:authorize access="hasRole('ROLE_USER')">

    <sec:authorize
    access="hasAnyRole('ROLE_SOLI', 'ROLE_DEST', 'ROLE_ADEN')">
    <hr style="margin-top: 6px; margin-bottom: 6px;" />
    <li
    style="list-style-type: disc; list-style-position: inside;"><a
    href="<c:url value="/common/usuariEntitatFavorit/list/1"/>"><span
        style="${(fn:contains(url, '/common/usuariEntitatFavorit/'))? "font-weight:bold;" : ""}" ><fmt:message
        key="favorit.gestio" /></span></a></li>
    </sec:authorize>
    */

    if (roles.contains(Constants.ROLE_USER) && (roles.contains(Constants.ROLE_SOLI) || roles.contains(Constants.ROLE_DEST)
            || roles.contains(Constants.ROLE_ADEN))) {
        optionalMenus.add(null); // Separator
        optionalMenus.add(
        new MenuItem("favorit.gestio", "/common/usuariEntitatFavorit/list/1", "/common/usuariEntitatFavorit/", 60));
    }

    /*
    <!-- Carrecs -->
    <sec:authorize
    access="hasAnyRole('ROLE_SOLI', 'ROLE_DEST', 'ROLE_COLA', 'ROLE_DELE', 'ROLE_ADEN')">
    <hr style="margin-top: 6px; margin-bottom: 6px;" />
    <li
    style="list-style-type: disc; list-style-position: inside;"><a
    href="<c:url value="/common/carrecs/list/1"/>"><span
        style="${(fn:contains(url, 'carrecs/') && fn:contains(url, '/list'))? "font-weight:bold;" : ""}" ><fmt:message
        key="carrec.llistat" /></span></a></li>
    </sec:authorize>
    </sec:authorize>
    */
    if (roles.contains(Constants.ROLE_USER) && (roles.contains(Constants.ROLE_SOLI) || roles.contains(Constants.ROLE_DEST)
            || roles.contains(Constants.ROLE_COLA) || roles.contains(Constants.ROLE_DELE)
            || roles.contains(Constants.ROLE_ADEN))) {
        optionalMenus.add(null); // Separator
        optionalMenus.add(new MenuItem("carrec.llistat", "/common/carrecs/list/1", "/common/carrecs/", 70));
    }

    /* <hr style="margin-top: 6px; margin-bottom: 6px;" /> */
    optionalMenus.add(null); // Separator

    /*

    <li style="list-style-type: disc; list-style-position: inside;"><a
    target="_blank"
    href="<c:url value="/doc/Manual_de_Usuari_de_PortaFIB.pdf"/>"><fmt:message
    key="manualusuari" /></a></li>
    */
    optionalMenus.add(new MenuItem("manualusuari", "/doc/Manual_de_Usuari_de_PortaFIB.pdf",
            "/doc/Manual_de_Usuari_de_PortaFIB.pdf", 80));

    /*

    <c:if test="${not empty androidApk}">
    <hr style="margin-top: 6px; margin-bottom: 6px;" />
    <li
    style="list-style-type: disc; list-style-position: inside;">
    <a target="_blank"
    href="<c:url value="/common/app.html"/>"><fmt:message
        key="appmobil" /></a>
    </li>
    </c:if>
    */

    String androidApk = Configuracio.getAndroidApk();
    pageContext.setAttribute("androidApk", androidApk);
    if (androidApk != null && !androidApk.isEmpty()) {
        optionalMenus.add(null); // Separator
        optionalMenus.add(new MenuItem("appmobil", "/common/app.html", "/common/app.html", 90));
    }

    /*
    MenuItem menuGoogle = new MenuItem("=MENU Google", "", "http://www.google.com", 0);

    MenuItem menumeneame = new MenuItem("=MENU Meneame", "", "http://www.meneame.net", 1000);
    */

    List<List<MenuItem>> menus = new ArrayList<List<MenuItem>>();

    menus.add(optionalMenus);
    List<MenuItem> discoveredMenus = MenuOptionManager.getMenuItems(Tab.MENU_INICI); //, menuGoogle, menumeneame);
    menus.add(discoveredMenus);
    %>

    <%@ include file="/WEB-INF/jsp/moduls/menu_role_generator.jsp"%>
    </ul>
</div>

