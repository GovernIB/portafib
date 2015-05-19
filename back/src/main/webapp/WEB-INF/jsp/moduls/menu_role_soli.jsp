<%@page import="es.caib.portafib.utils.Constants"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<sec:authorize access="hasRole('ROLE_SOLI')">
<div>
  <h5><fmt:message key="ROLE_SOLI.menu" /></h5>
<%!

public static final String[] menu = {
    "peticiodefirma.crear", // Realitzar petició de firma via Web o WebServices
    "",
    //"peticioDeFirma.peticioDeFirma.plural", // Peticions de Firma
    //"",
    
    "peticiodefirma.activa.plural",
    "peticiodefirma.firmada.plural",
    "peticiodefirma.rebutjada.plural",
    /*
    "descarregardocumentfirmat", // Descarregar document firmat    
    "cancelarpeticiofirma", // Cancel·lar Petició de Firma 
    "eliminarpeticiofirma", // Eliminar Petició de Firma 
    */
    "",
    "custodiaInfo.custodiaInfo.plural",
    "",
    /*
    "llistartipusdocuments", // Llistar Tipus de Documents 
    "",
    */    
    "plantillaFluxDeFirmes.plantillaFluxDeFirmes.plural", // Modificar Plantilla de Flux de Firma    
    /*
    "crearplantillafluxfirmes", // Crear Plantilla de Flux de Firma    
    "modificarplantillafluxfirmes", // Modificar Plantilla de Flux de Firma    
    "eliminarplantillafluxfirmes", // Eliminar Plantilla de Flux de Firma
    */
}; 

public static final Map<String, String> mapping;


static {
  //Mapping to existent path
  mapping = new HashMap<String, String>();
  mapping.put("peticiodefirma.crear", Constants.CONTEXT_SOLI_PETICIOFIRMA + "/selectflux");
  
  //mapping.put("peticioDeFirma.peticioDeFirma.plural", Constants.CONTEXT_SOLI_PETICIOFIRMA + "/list");

  
  mapping.put("peticiodefirma.activa.plural", Constants.CONTEXT_SOLI_PETICIOFIRMA_ACTIVA + "/list");
  mapping.put("peticiodefirma.firmada.plural", Constants.CONTEXT_SOLI_PETICIOFIRMA_FIRMADA + "/list");
  mapping.put("peticiodefirma.rebutjada.plural", Constants.CONTEXT_SOLI_PETICIOFIRMA_REBUTJADA + "/list");
  
  /*
  mapping.put("cancelarpeticiofirma", Constants.CONTEXT_SOLI_PETICIOFIRMA + "/list");
  mapping.put("eliminarpeticiofirma", Constants.CONTEXT_SOLI_PETICIOFIRMA + "/list");
  mapping.put("descarregardocumentfirmat", Constants.CONTEXT_SOLI_PETICIOFIRMA + "/list");
  */
  
  mapping.put("plantillaFluxDeFirmes.plantillaFluxDeFirmes.plural", "/soli/plantilla/list");
  /*
  mapping.put("crearplantillafluxfirmes", "/soli/plantilla/new");
  mapping.put("modificarplantillafluxfirmes", "/soli/plantilla/list");
  mapping.put("eliminarplantillafluxfirmes", "/soli/plantilla/list");
  */

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
