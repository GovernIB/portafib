<%@page import="org.fundaciobit.genapp.common.web.menuoptions.MenuItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="es.caib.portafib.back.controller.soli.PeticioFirmaMassivaController"
%><%@page import="es.caib.portafib.utils.ConstantsV2"
%><%@page import="java.util.HashMap"
%><%@page import="java.util.Map"
%><%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"
%>
<sec:authorize access="hasRole('ROLE_SOLI')">
<div>
  <h5><fmt:message key="ROLE_SOLI.menu" /></h5>
  
  <%!

private static final List<List<MenuItem>> menus  = new ArrayList<List<MenuItem>>();
    
static {
  
  List<MenuItem> menu1;
  
  boolean compactar= false;
  
  menu1 = new ArrayList<MenuItem>();

  menu1.add(MenuItem.retallaDarrerPath("peticiodefirma.crear", ConstantsV2.CONTEXT_SOLI_PETICIOFIRMA_ACTIVA + "/selectflux"));
  menu1.add(MenuItem.retallaDarrerPath("peticioFirmaMassiva.titol", PeticioFirmaMassivaController.CONTEXTWEB));
  menu1.add(null);

  menu1.add(MenuItem.retallaDarrerPath("peticiodefirma.activa.plural", ConstantsV2.CONTEXT_SOLI_PETICIOFIRMA_ACTIVA + "/list"));
  menu1.add(MenuItem.retallaDarrerPath("peticiodefirma.firmada.plural", ConstantsV2.CONTEXT_SOLI_PETICIOFIRMA_FIRMADA + "/list"));
  menu1.add(MenuItem.retallaDarrerPath("peticiodefirma.rebutjada.plural", ConstantsV2.CONTEXT_SOLI_PETICIOFIRMA_REBUTJADA + "/list"));
  menu1.add(null);  

  menu1.add(MenuItem.retallaDarrerPath("custodiaInfo.custodiaInfo.plural", "/soli/peticio/custodiainfo/list"));
  menu1.add(null);

  menu1.add(MenuItem.retallaDarrerPath("plantillaFluxDeFirmes.plantillaFluxDeFirmes.plural", "/soli/plantilla/list"));
  menus.add(menu1);
}


%><%
int count = 0;

for(List<MenuItem> menu : menus) {
  pageContext.setAttribute("menu", menu);
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
  
  <%  
 
  count++;
  
}  // final FOR 
  %>
  
  


</div>
</sec:authorize>
