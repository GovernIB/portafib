<%@page import="es.caib.portafib.back.utils.MenuItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="es.caib.portafib.commons.utils.Configuracio"
%><%@page import="es.caib.portafib.utils.ConstantsV2"
%><%@page import="es.caib.portafib.back.security.LoginInfo"
%><%@page import="java.util.HashMap"
%><%@page import="java.util.Map"
%><%@page contentType="text/html;charset=UTF-8" language="java"
%><%@include  file="/WEB-INF/jsp/moduls/includes.jsp"%>
<sec:authorize access="hasRole('ROLE_DEST')">

<div>
  <h5><fmt:message key="ROLE_DEST.menu" /></h5>
  <%
  List<MenuItem> menu1 = new ArrayList<MenuItem>(10);

  if (Configuracio.isDesenvolupament()){
    menu1.add(MenuItem.retallaDarrerPath("solicituddefirma.llistat.totes.plural", ConstantsV2.CONTEXT_DEST_ESTATFIRMA  + "/list"));
    menu1.add(null);
  }
  menu1.add(MenuItem.retallaDarrerPath("solicituddefirma.llistat.pendent.plural", ConstantsV2.CONTEXT_DEST_ESTATFIRMA_PENDENT + "/list"));
  menu1.add(MenuItem.retallaDarrerPath("solicituddefirma.llistat.acceptada.plural", ConstantsV2.CONTEXT_DEST_ESTATFIRMA_FIRMAT + "/list"));
  menu1.add(MenuItem.retallaDarrerPath("solicituddefirma.llistat.noacceptada.plural", ConstantsV2.CONTEXT_DEST_ESTATFIRMA_REBUTJAT + "/list"));

  if (LoginInfo.getInstance().hasRole("ROLE_USER")) {
    menu1.add(null);
    menu1.add(MenuItem.retallaDarrerPath("colaboracio.gestio", "/dest/colaborador/list"));
    menu1.add(null);
    menu1.add(MenuItem.retallaDarrerPath("delegacio.gestio", "/dest/delegat/list"));
    menu1.add(null);
    menu1.add(MenuItem.retallaDarrerPath("revisor.gestio", "/dest/revisordedestinatari/list"));   
  }

  pageContext.setAttribute("menu", menu1);
%>
  <ul class="tree" style="margin: 3px; padding: 0px;">
    <c:forEach var="item" items="${menu}" >

    <c:if test="${empty item }">
    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    </c:if>
    <c:if test="${not empty item }">
      <fmt:message var="traduccio" key="${item.label}" />
      <c:set var="theurl" value="${item.url}"/>
      <c:set var="theurlbase" value="${item.urlbase}"/>
      <c:set var="match" value="${(fn:contains(urlActual, theurl))}"/>
      <li style="list-style-type: disc; list-style-position: inside;">
        <a href="<c:url value="${theurl}"/>">
          <span style="${match?"font-weight: bold;":""} ${(fn:endsWith(traduccio, '(*)'))? "color: red;" : ""}">${traduccio}</span>
        </a>
      </li>
    </c:if>
    </c:forEach>
  </ul>

</div>
</sec:authorize>
