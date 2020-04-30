<%@page import="es.caib.portafib.utils.Configuracio"
%><%@page import="es.caib.portafib.utils.ConstantsV2"
%><%@page import="java.util.HashMap"
%><%@page import="java.util.Map"
%><%@page contentType="text/html;charset=UTF-8" language="java"
%><%@include  file="/WEB-INF/jsp/moduls/includes.jsp"%>
<sec:authorize access="hasRole('ROLE_DEST')">
<%!

final String[] menu = {
    Configuracio.isDesenvolupament()?"solicituddefirma.llistat.totes.plural" : "    ",

    "solicituddefirma.llistat.pendent.plural",
    "solicituddefirma.llistat.acceptada.plural",
    "solicituddefirma.llistat.noacceptada.plural",
    "",
    "delegacio.gestio",
    "",
    "colaboracio.gestio"/*,
    "",
    "descarregar.apk"*/

};


public static final Map<String, String> mapping;


static {
  //Mapping to existent path
  mapping = new HashMap<String, String>();
  

  mapping.put("solicituddefirma.llistat.totes.plural", ConstantsV2.CONTEXT_DEST_ESTATFIRMA + "/list");
  
  mapping.put("solicituddefirma.llistat.pendent.plural", ConstantsV2.CONTEXT_DEST_ESTATFIRMA_PENDENT + "/list");
  mapping.put("solicituddefirma.llistat.acceptada.plural", ConstantsV2.CONTEXT_DEST_ESTATFIRMA_FIRMAT + "/list");
  mapping.put("solicituddefirma.llistat.noacceptada.plural", ConstantsV2.CONTEXT_DEST_ESTATFIRMA_REBUTJAT + "/list");

  mapping.put("colaboracio.gestio", "/dest/colaborador/list"); //Llistat de Col·laboradors

  mapping.put("delegacio.gestio", "/dest/delegat/list"); //Llista delegació
  
  //mapping.put("descarregar.apk", "/doc/portafibapp.apk"); //PortaFIBApp

}

%><%

session.setAttribute("menu", menu);

session.setAttribute("mapping", mapping);

%>
<div>
  <h5><fmt:message key="ROLE_DEST.menu" /></h5>

<ul class="tree" style="margin: 3px; padding: 0px;">


    <c:forEach var="item" items="${menu}" >
      <c:if test="${item != '    '}">
        <c:if test="${empty item }">
          <li><hr  style="margin-top: 6px;  margin-bottom: 6px;" /></li>
        </c:if>
        <c:if test="${not empty item }">
          <fmt:message var="traduccio" key="${item}" />
          
          <c:if test="${empty mapping[item]}">
            <c:set var="theurl" value="/dest/${item}"/>
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
