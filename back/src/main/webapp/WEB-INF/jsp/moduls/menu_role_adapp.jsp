<%@page import="es.caib.portafib.logic.utils.PropietatGlobalUtil"%><%@page import="java.util.ArrayList"%><%@page import="java.util.List"%><%@page
    import="es.caib.portafib.back.utils.MenuItem"%><%@page import="es.caib.portafib.utils.ConstantsV2"%><%@page
    import="es.caib.portafib.back.controller.aden.PerfilDeFirmaAdenController"%><%@page
    import="es.caib.portafib.model.fields.PerfilDeFirmaFields"%><%@page
    import="es.caib.portafib.back.controller.aden.ConfiguracioDeFirmaAdenController"%><%@page
    import="es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields"%><%@page import="es.caib.portafib.commons.utils.Configuracio"%><%@page
    import="es.caib.portafib.back.security.LoginInfo"%><%@page import="java.util.HashMap"%><%@page import="java.util.Map"%><%@ page
    contentType="text/html;charset=UTF-8" language="java"%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%><sec:authorize
    access="hasRole('ROLE_ADEN')">
    <div>
        <h5>
            <fmt:message key="ROLE_ADEN.menu2" />
        </h5>
        <%!private static final List<List<MenuItem>> menus = new ArrayList<List<MenuItem>>();

    static {

        List<MenuItem> menu2;

        boolean compactar = PropietatGlobalUtil.compactMenuOptionsOfAdEn();

        final String CONFIGURACIO_DE_FIRMA = UsuariAplicacioConfiguracioFields._TABLE_MODEL + "."
                + UsuariAplicacioConfiguracioFields._TABLE_MODEL + ".plural";
        final String PERFIL_DE_FIRMA = PerfilDeFirmaFields._TABLE_MODEL + "." + PerfilDeFirmaFields._TABLE_MODEL
                + ".plural";

        menu2 = new ArrayList<MenuItem>();
        menu2.add(MenuItem.retallaDarrerPath("usuariaplicacio.gestio", "/aden/usuariAplicacio/list"));
        menu2.add(MenuItem.retallaDarrerPath(PERFIL_DE_FIRMA, "/aden/perfildefirma/list"));
        menu2.add(MenuItem.retallaDarrerPath(CONFIGURACIO_DE_FIRMA, "/aden/configdefirma/list"));

        menu2.add(null);
        menu2.add(MenuItem.retallaDarrerPath("tipusdocument.adapp.plural", "/aden/gestiotipusdocapp/list"));
        menu2.add(null);
        if (compactar) {
            // No mostrar 
        } else {
            menu2.add(MenuItem.retallaDarrerPath("peticiodefirma.usrapp.llistar", "/aden/peticiofirmaaplicacio/list"));
        }

        menu2.add(MenuItem.retallaDarrerPath("peticiosincrona.menu", "/aden/peticiosincrona/list"));

        menu2.add(null);
        menu2.add(MenuItem.retallaDarrerPath("plantillaFluxDeFirmes.plantillaFluxDeFirmes.plural",
                "/aden/plantilla/list"));
        menu2.add(MenuItem.retallaDarrerPath("custodiaInfo.custodiaInfo.plural", "/aden/peticio/custodiainfo/list"));
        menu2.add(MenuItem.retallaDarrerPath("notificaciows.llistat", "/aden/notificaciows/list"));

        menus.add(menu2);
    }%>
        <%
        int count = 0;

        for (List<MenuItem> menu : menus) {
            pageContext.setAttribute("menu", menu);
        %>
        <ul class="tree" style="margin: 3px; padding: 0px;">
            <c:forEach var="item" items="${menu}">

                <c:if test="${empty item }">
                    <hr style="margin-top: 6px; margin-bottom: 6px;" />
                </c:if>
                <c:if test="${not empty item }">
                    <fmt:message var="traduccio" key="${item.label}" />
                    <c:set var="theurl" value="${item.url}" />
                    <c:set var="theurlbase" value="${item.urlbase}" />
                    <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="${theurl}"/>">
                            <span style="${(fn:contains(urlActual, theurlbase))? " font-weight:
                                bold;" : ""} ${(fn:endsWith(traduccio, '(*)'))? "color:red;" : ""}">${traduccio}</span>
                        </a></li>
                </c:if>
            </c:forEach>

        </ul>

        <%
        count++;

        } // final FOR
        %>

    </div>
</sec:authorize>
