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

public static final String[] menu = {
    
    "peticiodefirma.crear", // Realitzar petició de firma via Web o WebServices
    "peticioFirmaMassiva.titol",
    "",
    
    "peticiodefirma.activa.plural",
    "peticiodefirma.firmada.plural",
    "peticiodefirma.rebutjada.plural",

    "",
    "custodiaInfo.custodiaInfo.plural",
    "",
 
    "plantillaFluxDeFirmes.plantillaFluxDeFirmes.plural", // Modificar Plantilla de Flux de Firma    

}; 

public static final Map<String, String> mapping;


static {
  //Mapping to existent path
  mapping = new HashMap<String, String>();
  mapping.put("peticiodefirma.crear", ConstantsV2.CONTEXT_SOLI_PETICIOFIRMA_ACTIVA + "/selectflux");
  
  mapping.put("peticioFirmaMassiva.titol", PeticioFirmaMassivaController.CONTEXTWEB);

  
  mapping.put("peticiodefirma.activa.plural", ConstantsV2.CONTEXT_SOLI_PETICIOFIRMA_ACTIVA + "/list");
  mapping.put("peticiodefirma.firmada.plural", ConstantsV2.CONTEXT_SOLI_PETICIOFIRMA_FIRMADA + "/list");
  mapping.put("peticiodefirma.rebutjada.plural", ConstantsV2.CONTEXT_SOLI_PETICIOFIRMA_REBUTJADA + "/list");

  
  mapping.put("plantillaFluxDeFirmes.plantillaFluxDeFirmes.plural", "/soli/plantilla/list");


  mapping.put("custodiaInfo.custodiaInfo.plural", "/soli/peticio/custodiainfo/list");
  
}

%><%

session.setAttribute("menu", menu);

session.setAttribute("mapping", mapping);

%>
  
  <ul class="tree" style="margin: 3px; padding: 0px;">
   
    <c:forEach var="item" items="${menu}" >
      <c:if test="${empty item }">
    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    </c:if>
    <c:if test="${not empty item }">
      <fmt:message var="traduccio" key="${item}" />
      
      <c:if test="${empty mapping[item]}">
        <c:set var="theurl" value="/soli/${item}"/>
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
    </c:forEach>

  </ul>
</div>
</sec:authorize>
