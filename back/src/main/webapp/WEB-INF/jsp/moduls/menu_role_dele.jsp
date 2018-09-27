<%@page import="es.caib.portafib.utils.Configuracio"
%><%@page import="es.caib.portafib.utils.ConstantsV2"
%><%@page import="java.util.HashMap"
%><%@page import="java.util.Map"
%><%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<sec:authorize access="hasRole('ROLE_DELE')">
<%!

final String[] menu = {
    Configuracio.isDesenvolupament()? "delegacio.totes.plural" : "    ",
    "delegacio.pendent.plural",
    "delegacio.acceptada.plural",
    "delegacio.noacceptada.plural",
    "",
    "delegatde.menu"
};

public static final Map<String, String> mapping;


static {
  //Mapping to existent path
  mapping = new HashMap<String, String>();
  mapping.put("solicituddefirma.llistat", ConstantsV2.CONTEXT_DELE_ESTATFIRMA + "/list");
  mapping.put("delegacio.pendent.plural", ConstantsV2.CONTEXT_DELE_ESTATFIRMA_PENDENT + "/list");
  mapping.put("delegacio.acceptada.plural", ConstantsV2.CONTEXT_DELE_ESTATFIRMA_FIRMAT + "/list");
  mapping.put("delegacio.noacceptada.plural", ConstantsV2.CONTEXT_DELE_ESTATFIRMA_REBUTJAT + "/list");
  mapping.put("delegatde.menu", "/dele/delegatde/list");

}

%><%

session.setAttribute("menu", menu);

session.setAttribute("mapping", mapping);

%>
<div>
  <h5><fmt:message key="ROLE_DELE.menu" /></h5>

  <ul class="tree" style="margin: 3px; padding: 0px;">
    <c:forEach var="item" items="${menu}" >
      <c:if test="${item != '    ' }">
        <c:if test="${empty item }">
        <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
        </c:if>
        <c:if test="${not empty item }">
          <fmt:message var="traduccio" key="${item}" />
          
          <c:if test="${empty mapping[item]}">
            <c:set var="theurl" value="/dele/${item}"/>
          </c:if>
          <c:if test="${not (empty mapping[item])}">
            <c:set var="theurl" value="${mapping[item]}"/>
          </c:if>
          
          <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="${theurl}"/>">
              <span style="${(fn:contains(urlActual, theurl))? "font-weight: bold;" : ""} ${(fn:endsWith(traduccio, '(*)'))? "color: red;" : ""}">${traduccio}</span>
            </a>
          </li>
        </c:if>
      </c:if>
    </c:forEach>

  </ul>
</div>
</sec:authorize>
